package thebetweenlands.api.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import thebetweenlands.common.recipe.input.FluidRecipeInput;
import thebetweenlands.common.registries.RecipeCategoryRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

public interface SteepingPotRecipe extends Recipe<FluidRecipeInput> {

	FluidIngredient getInputFluid();

	FluidStack getResultFluid(HolderLookup.Provider registries);

	@Override
	default RecipeType<SteepingPotRecipe> getType() {
		return RecipeRegistry.STEEPING_POT_RECIPE.get();
	}

	@Override
	default RecipeBookCategory recipeBookCategory() {
		return RecipeCategoryRegistry.STEEPING_POT.get();
	}
}
