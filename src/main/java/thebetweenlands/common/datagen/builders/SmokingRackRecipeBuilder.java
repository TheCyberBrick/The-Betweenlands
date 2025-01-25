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
import thebetweenlands.common.recipe.BasicSmokingRackRecipe;

public class SmokingRackRecipeBuilder implements RecipeBuilder {

	private final HolderGetter<Item> getter;
	private final Ingredient input;
	private final ItemStack result;
	private final int time;

	private SmokingRackRecipeBuilder(HolderGetter<Item> getter, Ingredient input, ItemStack result, int time) {
		this.getter = getter;
		this.input = input;
		this.result = result;
		this.time = time;
	}

	public static SmokingRackRecipeBuilder smoking(HolderGetter<Item> getter, ItemLike input, ItemLike output, int time) {
		return new SmokingRackRecipeBuilder(getter, Ingredient.of(input), new ItemStack(output), time);
	}

	public static SmokingRackRecipeBuilder smoking(HolderGetter<Item> getter, TagKey<Item> input, ItemLike output, int time) {
		return new SmokingRackRecipeBuilder(getter, Ingredient.of(getter.getOrThrow(input)), new ItemStack(output), time);
	}

	public static SmokingRackRecipeBuilder smoking(HolderGetter<Item> getter, Ingredient input, ItemLike output, int time) {
		return new SmokingRackRecipeBuilder(getter, input, new ItemStack(output), time);
	}

	public static SmokingRackRecipeBuilder smoking(HolderGetter<Item> getter, ItemLike input, ItemStack output, int time) {
		return new SmokingRackRecipeBuilder(getter, Ingredient.of(input), output, time);
	}

	public static SmokingRackRecipeBuilder smoking(HolderGetter<Item> getter, TagKey<Item> input, ItemStack output, int time) {
		return new SmokingRackRecipeBuilder(getter, Ingredient.of(getter.getOrThrow(input)), output, time);
	}

	public static SmokingRackRecipeBuilder smoking(HolderGetter<Item> getter, Ingredient input, ItemStack output, int time) {
		return new SmokingRackRecipeBuilder(getter, input, output, time);
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
		this.save(output, ResourceKey.create(Registries.RECIPE, TheBetweenlands.prefix("smoking_rack/" + RecipeBuilder.getDefaultRecipeId(this.getResult()).getPath())));
	}

	@Override
	public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
		output.accept(id, new BasicSmokingRackRecipe(this.input, this.result, this.time), null);
	}
}
