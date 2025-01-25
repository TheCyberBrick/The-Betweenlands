package thebetweenlands.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import thebetweenlands.common.datagen.recipes.BLBlockRecipeProvider;
import thebetweenlands.common.datagen.recipes.BLCookingRecipeProvider;
import thebetweenlands.common.datagen.recipes.BLCustomRecipeProvider;
import thebetweenlands.common.datagen.recipes.BLItemRecipeProvider;

import java.util.concurrent.CompletableFuture;

public class BLRecipeProvider extends RecipeProvider.Runner {

	public BLRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
		return new Builder(provider, output);
	}

	public static class Builder extends RecipeProvider {

		protected Builder(HolderLookup.Provider provider, RecipeOutput output) {
			super(provider, output);
		}

		@Override
		protected void buildRecipes() {
			BLBlockRecipeProvider.buildRecipes(this.output, this.registries);
			BLCookingRecipeProvider.buildRecipes(this.output, this.registries);
			BLCustomRecipeProvider.buildRecipes(this.output, this.registries);
			BLItemRecipeProvider.buildRecipes(this.output, this.registries);
		}
	}

	@Override
	public String getName() {
		return "The Betweenlands: Recipe Edition";
	}
}
