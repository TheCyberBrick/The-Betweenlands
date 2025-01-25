package thebetweenlands.common.item.consumable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import thebetweenlands.common.component.entity.InfestationIgnoreData;
import thebetweenlands.common.registries.AttachmentRegistry;
import thebetweenlands.common.registries.ConsumeEffectRegistry;

public record MaskInfestationConsumeEffect(int ticks) implements ConsumeEffect {

	public static final MapCodec<MaskInfestationConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codec.INT.fieldOf("ticks").forGetter(MaskInfestationConsumeEffect::ticks))
		.apply(instance, MaskInfestationConsumeEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, MaskInfestationConsumeEffect> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, MaskInfestationConsumeEffect::ticks, MaskInfestationConsumeEffect::new);

	@Override
	public ConsumeEffect.Type<MaskInfestationConsumeEffect> getType() {
		return ConsumeEffectRegistry.MASK_INFESTATION.get();
	}

	@Override
	public boolean apply(Level level, ItemStack stack, LivingEntity entity) {
		if (!level.isClientSide() && entity instanceof Player player) {
			InfestationIgnoreData ignore = player.getData(AttachmentRegistry.INFESTATION_IGNORE);
			if (!ignore.isImmune(player)) {
				ignore.setImmune(player, Math.max(ignore.getRemainingImmunityTicks(player), this.ticks()));
				return true;
			}
		}
		return false;
	}
}
