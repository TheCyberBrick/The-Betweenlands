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

public record TrimmingTableDisplay(SlotDisplay ingredient, List<SlotDisplay> results, SlotDisplay remains, SlotDisplay craftingStation) implements RecipeDisplay {

	public static final MapCodec<TrimmingTableDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			SlotDisplay.CODEC.fieldOf("ingredient").forGetter(TrimmingTableDisplay::ingredient),
			SlotDisplay.CODEC.listOf().fieldOf("result").forGetter(TrimmingTableDisplay::results),
			SlotDisplay.CODEC.fieldOf("remains").forGetter(TrimmingTableDisplay::remains),
			SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(TrimmingTableDisplay::craftingStation))
		.apply(instance, TrimmingTableDisplay::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, TrimmingTableDisplay> STREAM_CODEC = StreamCodec.composite(
		SlotDisplay.STREAM_CODEC, TrimmingTableDisplay::ingredient,
		SlotDisplay.STREAM_CODEC.apply(ByteBufCodecs.list()), TrimmingTableDisplay::results,
		SlotDisplay.STREAM_CODEC, TrimmingTableDisplay::remains,
		SlotDisplay.STREAM_CODEC, TrimmingTableDisplay::craftingStation,
		TrimmingTableDisplay::new);

	public static final Type<TrimmingTableDisplay> TYPE = new Type<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public Type<TrimmingTableDisplay> type() {
		return TYPE;
	}

	@Override
	public SlotDisplay result() {
		return this.results().getFirst();
	}

	@Override
	public boolean isEnabled(FeatureFlagSet set) {
		return this.ingredient().isEnabled(set) && this.results().stream().allMatch(display -> display.isEnabled(set)) && this.remains().isEnabled(set) && this.craftingStation().isEnabled(set);
	}
}
