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
import thebetweenlands.common.component.entity.FoodSicknessData;
import thebetweenlands.common.entity.monster.TinySludgeWormHelper;
import thebetweenlands.common.registries.AttachmentRegistry;
import thebetweenlands.common.registries.ConsumeEffectRegistry;
import thebetweenlands.common.registries.EntityRegistry;
import thebetweenlands.util.FoodSickness;

//TODO split into worm spawn and food sickness effects
public record WitchTeaConsumeEffect(int worms) implements ConsumeEffect {

	public static final MapCodec<WitchTeaConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codec.INT.fieldOf("worms").forGetter(WitchTeaConsumeEffect::worms))
		.apply(instance, WitchTeaConsumeEffect::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, WitchTeaConsumeEffect> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, WitchTeaConsumeEffect::worms, WitchTeaConsumeEffect::new);

	@Override
	public Type<WitchTeaConsumeEffect> getType() {
		return ConsumeEffectRegistry.WITCH_TEA.get();
	}

	@Override
	public boolean apply(Level level, ItemStack stack, LivingEntity entity) {
		if (!level.isClientSide() && entity instanceof Player player) {
			FoodSicknessData data = player.getData(AttachmentRegistry.FOOD_SICKNESS);
			if (FoodSickness.getSicknessForHatred(data.getFoodHatred(stack.getItem())) != FoodSickness.SICK) {
				data.increaseFoodHatred(player, stack.getItem(), 0, FoodSickness.SICK.maxHatred);
			}
			for (int count = 0; count < this.worms(); count++) {
				TinySludgeWormHelper worm = new TinySludgeWormHelper(EntityRegistry.TINY_SLUDGE_WORM_HELPER.get(), level);
				worm.moveTo(player.getX(), player.getY() + 1D, player.getZ(), player.getYRot(), player.getXRot());
				worm.setOwnerUUID(player.getUUID());
				level.addFreshEntity(worm);
			}
			return true;
		}
		return false;
	}
}
