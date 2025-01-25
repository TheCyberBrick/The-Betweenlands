package thebetweenlands.common.datagen.builders;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import javax.annotation.Nullable;

import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.recipe.FluidSteepingPotRecipe;

public class SteepingPotRecipeBuilder implements RecipeBuilder {

	private final HolderGetter<Item> itemGetter;
	private final HolderGetter<Fluid> fluidGetter;
	private final FluidIngredient input;
	private final NonNullList<Ingredient> ingredients = NonNullList.create();
	private final FluidStack result;

	private SteepingPotRecipeBuilder(HolderLookup.Provider registries, FluidIngredient input, FluidStack result) {
		this.itemGetter = registries.lookupOrThrow(Registries.ITEM);
		this.fluidGetter = registries.lookupOrThrow(Registries.FLUID);
		this.input = input;
		this.result = result;
	}

	public static SteepingPotRecipeBuilder steeping(HolderLookup.Provider registries, Fluid input, Fluid result, int amount) {
		return new SteepingPotRecipeBuilder(registries, FluidIngredient.of(input), new FluidStack(result, amount));
	}

	public static SteepingPotRecipeBuilder steeping(HolderLookup.Provider registries, TagKey<Fluid> input, Fluid result, int amount) {
		return new SteepingPotRecipeBuilder(registries, FluidIngredient.of(registries.lookupOrThrow(Registries.FLUID).getOrThrow(input)), new FluidStack(result, amount));
	}

	public static SteepingPotRecipeBuilder steeping(HolderLookup.Provider registries, FluidStack input, Fluid result, int amount) {
		return new SteepingPotRecipeBuilder(registries, FluidIngredient.of(input), new FluidStack(result, amount));
	}

	public static SteepingPotRecipeBuilder steeping(HolderLookup.Provider registries, FluidIngredient input, Fluid result, int amount) {
		return new SteepingPotRecipeBuilder(registries, input, new FluidStack(result, amount));
	}

	public static SteepingPotRecipeBuilder steeping(HolderLookup.Provider registries, Fluid input, FluidStack result) {
		return new SteepingPotRecipeBuilder(registries, FluidIngredient.of(input), result);
	}

	public static SteepingPotRecipeBuilder steeping(HolderLookup.Provider registries, TagKey<Fluid> input, FluidStack result) {
		return new SteepingPotRecipeBuilder(registries, FluidIngredient.of(registries.lookupOrThrow(Registries.FLUID).getOrThrow(input)), result);
	}

	public static SteepingPotRecipeBuilder steeping(HolderLookup.Provider registries, FluidStack input, FluidStack result) {
		return new SteepingPotRecipeBuilder(registries, FluidIngredient.of(input), result);
	}

	public static SteepingPotRecipeBuilder steeping(HolderLookup.Provider registries, FluidIngredient input, FluidStack result) {
		return new SteepingPotRecipeBuilder(registries, input, result);
	}

	public SteepingPotRecipeBuilder requires(TagKey<Item> tag) {
		return this.requires(Ingredient.of(this.itemGetter.getOrThrow(tag)));
	}

	public SteepingPotRecipeBuilder requires(ItemLike item) {
		return this.requires(item, 1);
	}

	public SteepingPotRecipeBuilder requires(ItemLike item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.requires(Ingredient.of(item));
		}

		return this;
	}

	public SteepingPotRecipeBuilder requires(Ingredient ingredient) {
		return this.requires(ingredient, 1);
	}

	public SteepingPotRecipeBuilder requires(Ingredient ingredient, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.ingredients.add(ingredient);
		}

		return this;
	}

	@Override
	public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		return this;
	}

	@Override
	public RecipeBuilder group(@Nullable String groupName) {
		return this;
	}

	@Override
	public Item getResult() {
		return Items.AIR;
	}

	@Override
	public void save(RecipeOutput output) {
		this.save(output, ResourceKey.create(Registries.RECIPE, TheBetweenlands.prefix("steeping/" + getDefaultRecipeId(this.result).getPath())));
	}

	@Override
	public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
		output.accept(id, new FluidSteepingPotRecipe(this.input, this.ingredients, this.result), null);
	}

	static ResourceLocation getDefaultRecipeId(FluidStack result) {
		return BuiltInRegistries.FLUID.getKey(result.getFluid());
	}
}
