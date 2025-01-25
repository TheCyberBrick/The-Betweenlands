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

public record SteepingPotDisplay(SlotDisplay input, List<SlotDisplay> ingredients, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {

	public static final MapCodec<SteepingPotDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			SlotDisplay.CODEC.fieldOf("input").forGetter(SteepingPotDisplay::input),
			SlotDisplay.CODEC.listOf().fieldOf("ingredients").forGetter(SteepingPotDisplay::ingredients),
			SlotDisplay.CODEC.fieldOf("result").forGetter(SteepingPotDisplay::result),
			SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(SteepingPotDisplay::craftingStation))
		.apply(instance, SteepingPotDisplay::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, SteepingPotDisplay> STREAM_CODEC = StreamCodec.composite(
		SlotDisplay.STREAM_CODEC, SteepingPotDisplay::input,
		SlotDisplay.STREAM_CODEC.apply(ByteBufCodecs.list()), SteepingPotDisplay::ingredients,
		SlotDisplay.STREAM_CODEC, SteepingPotDisplay::result,
		SlotDisplay.STREAM_CODEC, SteepingPotDisplay::craftingStation,
		SteepingPotDisplay::new);

	public static final RecipeDisplay.Type<SteepingPotDisplay> TYPE = new RecipeDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public RecipeDisplay.Type<SteepingPotDisplay> type() {
		return TYPE;
	}

	@Override
	public boolean isEnabled(FeatureFlagSet set) {
		return this.input().isEnabled(set) && this.ingredients().stream().allMatch(display -> display.isEnabled(set)) && RecipeDisplay.super.isEnabled(set);
	}
}
