package thebetweenlands.common.recipe.display;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record PurifierRecipeDisplay(SlotDisplay ingredient, SlotDisplay fuel, SlotDisplay result, SlotDisplay craftingStation, int duration, int requiredWater) implements RecipeDisplay {

	public static final MapCodec<PurifierRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			SlotDisplay.CODEC.fieldOf("ingredient").forGetter(PurifierRecipeDisplay::ingredient),
			SlotDisplay.CODEC.fieldOf("fuel").forGetter(PurifierRecipeDisplay::fuel),
			SlotDisplay.CODEC.fieldOf("result").forGetter(PurifierRecipeDisplay::result),
			SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(PurifierRecipeDisplay::craftingStation),
			Codec.INT.fieldOf("duration").forGetter(PurifierRecipeDisplay::duration),
			Codec.INT.fieldOf("required_water").forGetter(PurifierRecipeDisplay::requiredWater))
		.apply(instance, PurifierRecipeDisplay::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, PurifierRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
		SlotDisplay.STREAM_CODEC, PurifierRecipeDisplay::ingredient,
		SlotDisplay.STREAM_CODEC, PurifierRecipeDisplay::fuel,
		SlotDisplay.STREAM_CODEC, PurifierRecipeDisplay::result,
		SlotDisplay.STREAM_CODEC, PurifierRecipeDisplay::craftingStation,
		ByteBufCodecs.VAR_INT, PurifierRecipeDisplay::duration,
		ByteBufCodecs.VAR_INT, PurifierRecipeDisplay::requiredWater,
		PurifierRecipeDisplay::new);

	public static final Type<PurifierRecipeDisplay> TYPE = new Type<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public Type<PurifierRecipeDisplay> type() {
		return TYPE;
	}

	@Override
	public boolean isEnabled(FeatureFlagSet set) {
		return this.ingredient.isEnabled(set) && this.fuel.isEnabled(set) && RecipeDisplay.super.isEnabled(set);
	}
}
