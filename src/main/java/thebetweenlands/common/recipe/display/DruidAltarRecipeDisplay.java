package thebetweenlands.common.recipe.display;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.util.List;

public record DruidAltarRecipeDisplay(List<SlotDisplay> ingredients, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {

	public static final MapCodec<DruidAltarRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			SlotDisplay.CODEC.listOf().fieldOf("ingredients").forGetter(DruidAltarRecipeDisplay::ingredients),
			SlotDisplay.CODEC.fieldOf("result").forGetter(DruidAltarRecipeDisplay::result),
			SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(DruidAltarRecipeDisplay::craftingStation))
		.apply(instance, DruidAltarRecipeDisplay::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, DruidAltarRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
		SlotDisplay.STREAM_CODEC.apply(ByteBufCodecs.list()), DruidAltarRecipeDisplay::ingredients,
		SlotDisplay.STREAM_CODEC, DruidAltarRecipeDisplay::result,
		SlotDisplay.STREAM_CODEC, DruidAltarRecipeDisplay::craftingStation,
		DruidAltarRecipeDisplay::new);

	public static final RecipeDisplay.Type<DruidAltarRecipeDisplay> TYPE = new RecipeDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public RecipeDisplay.Type<DruidAltarRecipeDisplay> type() {
		return TYPE;
	}

	@Override
	public boolean isEnabled(FeatureFlagSet set) {
		return this.ingredients.stream().allMatch(display -> display.isEnabled(set)) && RecipeDisplay.super.isEnabled(set);
	}
}
