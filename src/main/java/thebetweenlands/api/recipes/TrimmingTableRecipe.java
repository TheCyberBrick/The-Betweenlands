package thebetweenlands.api.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import thebetweenlands.common.registries.RecipeCategoryRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import java.util.List;

public abstract class TrimmingTableRecipe extends SingleItemRecipe {

	public TrimmingTableRecipe(Ingredient input) {
		super("", input, ItemStack.EMPTY);
	}

	public abstract List<ItemStack> assembleRecipe(SingleRecipeInput input, Level level);

	public abstract ItemStack getRemains();

	@Override
	public RecipeType<TrimmingTableRecipe> getType() {
		return RecipeRegistry.TRIMMING_TABLE_RECIPE.get();
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeCategoryRegistry.TRIMMING_TABLE.get();
	}
}
