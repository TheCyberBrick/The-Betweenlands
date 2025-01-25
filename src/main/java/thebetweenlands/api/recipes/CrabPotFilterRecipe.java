package thebetweenlands.api.recipes;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import thebetweenlands.common.recipe.input.ItemAndEntityInput;
import thebetweenlands.common.registries.RecipeRegistry;

public interface CrabPotFilterRecipe extends Recipe<ItemAndEntityInput> {

	int filterTime();

	@Override
	default RecipeType<CrabPotFilterRecipe> getType() {
		return RecipeRegistry.CRAB_POT_FILTER_RECIPE.get();
	}
}
