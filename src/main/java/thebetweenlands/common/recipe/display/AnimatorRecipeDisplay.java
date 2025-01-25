package thebetweenlands.common.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record AnimatorRecipeDisplay(SlotDisplay input, SlotDisplay crystal, SlotDisplay fuel, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {

	public static final MapCodec<AnimatorRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			SlotDisplay.CODEC.fieldOf("input").forGetter(AnimatorRecipeDisplay::input),
			SlotDisplay.CODEC.fieldOf("crystal").forGetter(AnimatorRecipeDisplay::result),
			SlotDisplay.CODEC.fieldOf("fuel").forGetter(AnimatorRecipeDisplay::result),
			SlotDisplay.CODEC.fieldOf("result").forGetter(AnimatorRecipeDisplay::result),
			SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(AnimatorRecipeDisplay::craftingStation))
		.apply(instance, AnimatorRecipeDisplay::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, AnimatorRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
		SlotDisplay.STREAM_CODEC, AnimatorRecipeDisplay::input,
		SlotDisplay.STREAM_CODEC, AnimatorRecipeDisplay::crystal,
		SlotDisplay.STREAM_CODEC, AnimatorRecipeDisplay::fuel,
		SlotDisplay.STREAM_CODEC, AnimatorRecipeDisplay::result,
		SlotDisplay.STREAM_CODEC, AnimatorRecipeDisplay::craftingStation,
		AnimatorRecipeDisplay::new);

	public static final Type<AnimatorRecipeDisplay> TYPE = new Type<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public Type<AnimatorRecipeDisplay> type() {
		return TYPE;
	}

	@Override
	public boolean isEnabled(FeatureFlagSet set) {
		return this.input().isEnabled(set) && this.crystal().isEnabled(set) && this.fuel().isEnabled(set) && RecipeDisplay.super.isEnabled(set);
	}
}
