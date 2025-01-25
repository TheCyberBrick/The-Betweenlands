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

public record SingleDurationDisplay(SlotDisplay ingredient, SlotDisplay fuel, SlotDisplay result, SlotDisplay craftingStation, int duration) implements RecipeDisplay {

	public static final MapCodec<SingleDurationDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			SlotDisplay.CODEC.fieldOf("ingredient").forGetter(SingleDurationDisplay::ingredient),
			SlotDisplay.CODEC.fieldOf("fuel").forGetter(SingleDurationDisplay::fuel),
			SlotDisplay.CODEC.fieldOf("result").forGetter(SingleDurationDisplay::result),
			SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(SingleDurationDisplay::craftingStation),
			Codec.INT.fieldOf("duration").forGetter(SingleDurationDisplay::duration))
		.apply(instance, SingleDurationDisplay::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, SingleDurationDisplay> STREAM_CODEC = StreamCodec.composite(
		SlotDisplay.STREAM_CODEC, SingleDurationDisplay::ingredient,
		SlotDisplay.STREAM_CODEC, SingleDurationDisplay::fuel,
		SlotDisplay.STREAM_CODEC, SingleDurationDisplay::result,
		SlotDisplay.STREAM_CODEC, SingleDurationDisplay::craftingStation,
		ByteBufCodecs.VAR_INT, SingleDurationDisplay::duration,
		SingleDurationDisplay::new);

	public static final Type<SingleDurationDisplay> TYPE = new Type<>(MAP_CODEC, STREAM_CODEC);

	@Override
	public Type<SingleDurationDisplay> type() {
		return TYPE;
	}

	@Override
	public boolean isEnabled(FeatureFlagSet set) {
		return this.ingredient.isEnabled(set) && this.fuel.isEnabled(set) && RecipeDisplay.super.isEnabled(set);
	}
}
