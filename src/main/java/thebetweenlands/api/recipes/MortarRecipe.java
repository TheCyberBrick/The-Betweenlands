package thebetweenlands.api.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import thebetweenlands.common.recipe.display.MortarRecipeDisplay;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.RecipeCategoryRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import java.util.List;

public abstract class MortarRecipe extends SingleItemRecipe {

	public MortarRecipe(Ingredient input, ItemStack result) {
		super("", input, result);
	}

	/**
	 * Returns whether this recipe matches the given input slot stack and output slot stack, can be used to create recipes
	 * that need a certain item in the output slot
	 */
	public boolean matchesInput(SingleRecipeInput input, ItemStack output, Level level, boolean useInputOnly) {
		return this.matches(input, level);
	}

	/**
	 * Returns the output slot for the given input slot stack and output slot stack, can be used to create recipes
	 * that need a certain item in the output slot
	 */
	public ItemStack getOutput(SingleRecipeInput input, ItemStack outputStack, HolderLookup.Provider registries) {
		return this.assemble(input, registries);
	}

	/**
	 * Returns whether this recipe requires or uses the given stack in the output slot.
	 * If {@link #matchesInput(SingleRecipeInput, ItemStack, Level, boolean)} depends on an output slot stack then this method should
	 * reflect that.
	 */
	public boolean isOutputUsed(ItemStack output) {
		return false;
	}

	public boolean replacesOutput() {
		return false;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new MortarRecipeDisplay(this.input().display(), new SlotDisplay.ItemStackSlotDisplay(this.result()), new SlotDisplay.ItemSlotDisplay(BlockRegistry.MORTAR.asItem())));
	}

	@Override
	public RecipeType<MortarRecipe> getType() {
		return RecipeRegistry.MORTAR_RECIPE.get();
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeCategoryRegistry.MORTAR.get();
	}
}
