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
import thebetweenlands.common.component.entity.MudWalkerData;
import thebetweenlands.common.registries.AttachmentRegistry;
import thebetweenlands.common.registries.ConsumeEffectRegistry;

public record MudWalkerConsumeEffect(int ticks) implements ConsumeEffect {

	public static final MapCodec<MudWalkerConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codec.INT.fieldOf("ticks").forGetter(MudWalkerConsumeEffect::ticks))
		.apply(instance, MudWalkerConsumeEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, MudWalkerConsumeEffect> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, MudWalkerConsumeEffect::ticks, MudWalkerConsumeEffect::new);

	@Override
	public Type<MudWalkerConsumeEffect> getType() {
		return ConsumeEffectRegistry.MUD_WALKER.get();
	}

	@Override
	public boolean apply(Level level, ItemStack stack, LivingEntity entity) {
		if (!level.isClientSide() && entity instanceof Player player) {
			MudWalkerData mud = player.getData(AttachmentRegistry.MUD_WALKER);
			if (!mud.isActive(player)) {
				mud.setActive(player, Math.max(mud.getRemainingActiveTicks(player), this.ticks()));
				return true;
			}
		}
		return false;
	}
}
