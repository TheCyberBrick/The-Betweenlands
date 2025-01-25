package thebetweenlands.common.datagen.builders;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.NonNullList;
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
import thebetweenlands.common.recipe.BasicTrimmingTableRecipe;

import java.util.Optional;

public class FishTrimmingRecipeBuilder implements RecipeBuilder {

	private final HolderGetter<Item> getter;
	private final Ingredient input;
	private final NonNullList<ItemStack> outputs = NonNullList.create();
	@Nullable
	private ItemStack remains = null;

	private FishTrimmingRecipeBuilder(HolderGetter<Item> getter, Ingredient input) {
		this.getter = getter;
		this.input = input;
	}

	public static FishTrimmingRecipeBuilder trimming(HolderGetter<Item> getter, Ingredient input) {
		return new FishTrimmingRecipeBuilder(getter, input);
	}

	public static FishTrimmingRecipeBuilder trimming(HolderGetter<Item> getter, TagKey<Item> input) {
		return new FishTrimmingRecipeBuilder(getter, Ingredient.of(getter.getOrThrow(input)));
	}

	public static FishTrimmingRecipeBuilder trimming(HolderGetter<Item> getter, ItemLike input) {
		return new FishTrimmingRecipeBuilder(getter, Ingredient.of(input));
	}

	public FishTrimmingRecipeBuilder outputs(ItemLike result) {
		return this.outputs(new ItemStack(result, 1));
	}

	public FishTrimmingRecipeBuilder outputs(ItemStack result) {
		this.outputs.add(result);
		return this;
	}

	public FishTrimmingRecipeBuilder setRemains(ItemStack remains) {
		this.remains = remains;
		return this;
	}

	public FishTrimmingRecipeBuilder setRemains(ItemLike remains) {
		this.remains = new ItemStack(remains);
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
		return this.input.getValues().get(0).value();
	}

	@Override
	public void save(RecipeOutput output) {
		this.save(output, ResourceKey.create(Registries.RECIPE, TheBetweenlands.prefix("trimming/" + RecipeBuilder.getDefaultRecipeId(this.getResult()).getPath())));
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> id) {
		recipeOutput.accept(id, new BasicTrimmingTableRecipe(this.input, this.outputs, Optional.ofNullable(this.remains)), null);
	}
}
