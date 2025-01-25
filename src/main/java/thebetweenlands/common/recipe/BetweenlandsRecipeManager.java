package thebetweenlands.common.recipe;

import com.mojang.serialization.JsonOps;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.*;
import thebetweenlands.common.registries.RecipePropertySetRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import java.util.*;
import java.util.stream.Collectors;

public class BetweenlandsRecipeManager extends SimplePreparableReloadListener<RecipeMap> implements RecipeAccess {

	private static final Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> RECIPE_PROPERTY_SETS = Map.of(
		RecipePropertySetRegistry.MORTAR_INPUT, forSingleInput(RecipeRegistry.MORTAR_RECIPE.get()),
		RecipePropertySetRegistry.ANIMATOR_INPUT, forSingleInput(RecipeRegistry.ANIMATOR_RECIPE.get()),
		RecipePropertySetRegistry.SMOKING_RACK_INPUT, forSingleInput(RecipeRegistry.SMOKING_RECIPE.get())
	);
	private Map<ResourceKey<RecipePropertySet>, RecipePropertySet> propertySets = Map.of();


	private static RecipeManager.IngredientExtractor forSingleInput(RecipeType<? extends SingleItemRecipe> type) {
		return recipe -> recipe.getType() == type && recipe instanceof SingleItemRecipe singleitemrecipe ? Optional.of(singleitemrecipe.input()) : Optional.empty();
	}

	@Override
	protected RecipeMap prepare(ResourceManager manager, ProfilerFiller profiler) {
		List<RecipeManager.IngredientCollector> list1 = RECIPE_PROPERTY_SETS.entrySet()
			.stream()
			.map(p_380840_ -> new RecipeManager.IngredientCollector(p_380840_.getKey(), p_380840_.getValue()))
			.toList();
		this.recipes
			.values()
			.forEach(
				p_380845_ -> {
					Recipe<?> recipe = p_380845_.value();
					if (!recipe.isSpecial() && recipe.placementInfo().isImpossibleToPlace()) {
						LOGGER.warn("Recipe {} can't be placed due to empty ingredients and will be ignored", p_380845_.id().location());
					} else {
						list1.forEach(p_380839_ -> p_380839_.accept(recipe));
					}
				}
			);
		this.propertySets = list1.stream().collect(Collectors.toUnmodifiableMap(p_380846_ -> p_380846_.key, p_380852_ -> p_380852_.asPropertySet(p_379660_)));

	}

	@Override
	protected void apply(RecipeMap object, ResourceManager resourceManager, ProfilerFiller profiler) {

	}

	@Override
	public RecipePropertySet propertySet(ResourceKey<RecipePropertySet> p_379923_) {
		return null;
	}

	@Override
	public SelectableRecipe.SingleInputSet<StonecutterRecipe> stonecutterRecipes() {
		return null;
	}
}
