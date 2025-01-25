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
import thebetweenlands.common.component.entity.FallDamageReductionData;
import thebetweenlands.common.registries.AttachmentRegistry;
import thebetweenlands.common.registries.ConsumeEffectRegistry;

public record FallDamageImmunityConsumeEffect(int ticks) implements ConsumeEffect {

	public static final MapCodec<FallDamageImmunityConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codec.INT.fieldOf("ticks").forGetter(FallDamageImmunityConsumeEffect::ticks))
		.apply(instance, FallDamageImmunityConsumeEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, FallDamageImmunityConsumeEffect> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, FallDamageImmunityConsumeEffect::ticks, FallDamageImmunityConsumeEffect::new);

	@Override
	public Type<FallDamageImmunityConsumeEffect> getType() {
		return ConsumeEffectRegistry.FALL_DAMAGE_IMMUNITY.get();
	}

	@Override
	public boolean apply(Level level, ItemStack stack, LivingEntity entity) {
		if (!level.isClientSide() && entity instanceof Player player) {
			FallDamageReductionData fall = player.getData(AttachmentRegistry.FALL_DAMAGE_REDUCTION);
			if (!fall.isActive(player)) {
				fall.setActive(player, Math.max(fall.getRemainingImmunityTicks(player), this.ticks()));
				return true;
			}
		}
		return false;
	}
}
