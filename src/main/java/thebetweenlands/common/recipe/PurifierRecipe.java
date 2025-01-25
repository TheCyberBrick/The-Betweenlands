package thebetweenlands.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import thebetweenlands.common.recipe.display.PurifierRecipeDisplay;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.RecipeCategoryRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import java.util.List;

public class PurifierRecipe extends SingleItemRecipe {
	public final int purifyingTime;
	public final int requiredWater;

	public PurifierRecipe(Ingredient input, ItemStack result, int purifyingTime, int requiredWater) {
		super("", input, result);
		this.purifyingTime = purifyingTime;
		this.requiredWater = requiredWater;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new PurifierRecipeDisplay(
			this.input().display(),
			new SlotDisplay.ItemSlotDisplay(ItemRegistry.SULFUR),
			new SlotDisplay.ItemStackSlotDisplay(this.result()),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.PURIFIER.asItem()),
			this.purifyingTime, this.requiredWater
		));
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeCategoryRegistry.PURIFIER.get();
	}

	@Override
	public RecipeSerializer<PurifierRecipe> getSerializer() {
		return RecipeRegistry.PURIFIER_SERIALIZER.get();
	}

	@Override
	public RecipeType<PurifierRecipe> getType() {
		return RecipeRegistry.PURIFIER_RECIPE.get();
	}

	public static class Serializer implements RecipeSerializer<PurifierRecipe> {

		public static final MapCodec<PurifierRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.fieldOf("input").forGetter(PurifierRecipe::input),
			ItemStack.STRICT_CODEC.fieldOf("result").forGetter(PurifierRecipe::result),
			Codec.INT.fieldOf("purifying_time").forGetter(o -> o.purifyingTime),
			Codec.INT.fieldOf("water_needed").forGetter(o -> o.requiredWater)
		).apply(instance, PurifierRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, PurifierRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, PurifierRecipe::input,
			ItemStack.STREAM_CODEC, PurifierRecipe::result,
			ByteBufCodecs.INT, o -> o.purifyingTime,
			ByteBufCodecs.INT, o -> o.requiredWater,
			PurifierRecipe::new);

		@Override
		public MapCodec<PurifierRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, PurifierRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
