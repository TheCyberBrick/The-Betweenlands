package thebetweenlands.common.item.consumable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import thebetweenlands.common.registries.ConsumeEffectRegistry;

public record GiveXPConsumeEffect(int xp) implements ConsumeEffect {

	public static final MapCodec<GiveXPConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codec.INT.fieldOf("xp").forGetter(GiveXPConsumeEffect::xp))
		.apply(instance, GiveXPConsumeEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, GiveXPConsumeEffect> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, GiveXPConsumeEffect::xp, GiveXPConsumeEffect::new);

	@Override
	public ConsumeEffect.Type<GiveXPConsumeEffect> getType() {
		return ConsumeEffectRegistry.GIVE_XP.get();
	}

	@Override
	public boolean apply(Level level, ItemStack stack, LivingEntity entity) {
		if (entity instanceof Player player) {
			if (level.isClientSide()) {
				for (int i = 0; i < 20; i++) {
//					BLParticles.XP_PIECES.spawn(level, player.getX() + level.getRandom().nextFloat() * 0.6F - 0.3F, player.getY() + player.getEyeHeight() - 0.1F + level.getRandom().nextFloat() * 0.6F - 0.3F, player.getZ() + level.getRandom().nextFloat() * 0.6F - 0.3F);
				}
			} else {
				level.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.5F, 0.8F + level.getRandom().nextFloat() * 0.4F);
				player.giveExperiencePoints(this.xp());
			}
		}
		return true;
	}
}
