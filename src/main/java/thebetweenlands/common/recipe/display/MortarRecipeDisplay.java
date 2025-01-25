package thebetweenlands.common.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record MortarRecipeDisplay(SlotDisplay input, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {

	public static final MapCodec<MortarRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			SlotDisplay.CODEC.fieldOf("input").forGetter(MortarRecipeDisplay::input),
			SlotDisplay.CODEC.fieldOf("result").forGetter(MortarRecipeDisplay::result),
			SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(MortarRecipeDisplay::craftingStation))
		.apply(instance, MortarRecipeDisplay::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, MortarRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
		SlotDisplay.STREAM_CODEC, MortarRecipeDisplay::input,
		SlotDisplay.STREAM_CODEC, MortarRecipeDisplay::result,
		SlotDisplay.STREAM_CODEC, MortarRecipeDisplay::craftingStation,
		MortarRecipeDisplay::new
	);

	public static final RecipeDisplay.Type<MortarRecipeDisplay> TYPE = new RecipeDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public RecipeDisplay.Type<MortarRecipeDisplay> type() {
		return TYPE;
	}
}
