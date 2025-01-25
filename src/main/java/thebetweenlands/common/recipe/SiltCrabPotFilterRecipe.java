package thebetweenlands.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import thebetweenlands.api.recipes.CrabPotFilterRecipe;
import thebetweenlands.common.recipe.display.SingleDurationDisplay;
import thebetweenlands.common.recipe.input.ItemAndEntityInput;
import thebetweenlands.common.registries.*;

import javax.annotation.Nullable;
import java.util.List;

public class SiltCrabPotFilterRecipe implements CrabPotFilterRecipe {

	public final Ingredient input;
	public final ItemStack result;
	public final int filterTime;
	@Nullable
	private PlacementInfo placementInfo;

	public SiltCrabPotFilterRecipe(Ingredient input, ItemStack result, int filterTime) {
		this.input = input;
		this.result = result;
		this.filterTime = filterTime;
	}

	@Override
	public boolean matches(ItemAndEntityInput input, Level level) {
		return this.input.test(input.stack()) && input.type() == EntityRegistry.SILT_CRAB.get();
	}

	@Override
	public ItemStack assemble(ItemAndEntityInput input, HolderLookup.Provider registries) {
		return this.result.copy();
	}

	@Override
	public int filterTime() {
		return this.filterTime;
	}

	@Override
	public PlacementInfo placementInfo() {
		if (this.placementInfo == null) {
			this.placementInfo = PlacementInfo.create(this.input);
		}

		return this.placementInfo;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new SingleDurationDisplay(
			this.input.display(),
			new SlotDisplay.ItemSlotDisplay(ItemRegistry.ANADIA_REMAINS.get()),
			new SlotDisplay.ItemStackSlotDisplay(this.result),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.CRAB_POT_FILTER.asItem()),
			this.filterTime
		));
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeCategoryRegistry.SILT_CRAB.get();
	}

	@Override
	public RecipeSerializer<SiltCrabPotFilterRecipe> getSerializer() {
		return RecipeRegistry.SILT_CRAB_POT_FILTER_SERIALIZER.get();
	}

	public static class Serializer implements RecipeSerializer<SiltCrabPotFilterRecipe> {

		public static final MapCodec<SiltCrabPotFilterRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.fieldOf("input").forGetter(o -> o.input),
			ItemStack.STRICT_CODEC.fieldOf("result").forGetter(o -> o.result),
			Codec.INT.fieldOf("filter_time").forGetter(SiltCrabPotFilterRecipe::filterTime)
		).apply(instance, SiltCrabPotFilterRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, SiltCrabPotFilterRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, o -> o.input,
			ItemStack.STREAM_CODEC, o -> o.result,
			ByteBufCodecs.INT, SiltCrabPotFilterRecipe::filterTime,
			SiltCrabPotFilterRecipe::new);

		@Override
		public MapCodec<SiltCrabPotFilterRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, SiltCrabPotFilterRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
