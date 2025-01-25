package thebetweenlands.common.datagen.builders;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import javax.annotation.Nullable;

import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.recipe.MortarGrindRecipe;

public class MortarRecipeBuilder implements RecipeBuilder {

	private final HolderGetter<Item> getter;
	private final Ingredient input;
	private final ItemStack result;

	private MortarRecipeBuilder(HolderGetter<Item> getter, Ingredient input, ItemStack result) {
		this.getter = getter;
		this.input = input;
		this.result = result;
	}

	public static MortarRecipeBuilder grinding(HolderGetter<Item> getter, ItemLike input, ItemLike output) {
		return new MortarRecipeBuilder(getter, Ingredient.of(input), new ItemStack(output));
	}

	public static MortarRecipeBuilder grinding(HolderGetter<Item> getter, TagKey<Item> input, ItemLike output) {
		return new MortarRecipeBuilder(getter, Ingredient.of(getter.getOrThrow(input)), new ItemStack(output));
	}

	public static MortarRecipeBuilder grinding(HolderGetter<Item> getter, Ingredient input, ItemLike output) {
		return new MortarRecipeBuilder(getter, input, new ItemStack(output));
	}

	public static MortarRecipeBuilder grinding(HolderGetter<Item> getter, ItemLike input, ItemStack output) {
		return new MortarRecipeBuilder(getter, Ingredient.of(input), output);
	}

	public static MortarRecipeBuilder grinding(HolderGetter<Item> getter, TagKey<Item> input, ItemStack output) {
		return new MortarRecipeBuilder(getter, Ingredient.of(getter.getOrThrow(input)), output);
	}

	public static MortarRecipeBuilder grinding(HolderGetter<Item> getter, Ingredient input, ItemStack output) {
		return new MortarRecipeBuilder(getter, input, output);
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
		return this.result.getItem();
	}

	@Override
	public void save(RecipeOutput output) {
		this.save(output, ResourceKey.create(Registries.RECIPE, TheBetweenlands.prefix("mortar/" + RecipeBuilder.getDefaultRecipeId(this.getResult()).getPath())));
	}

	@Override
	public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
		output.accept(id, new MortarGrindRecipe(this.input, this.result), null);
	}
}
