package thebetweenlands.common.datagen.builders;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import javax.annotation.Nullable;
import thebetweenlands.api.recipes.DruidAltarRecipe;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.recipe.DruidAltarAssemblyRecipe;
import thebetweenlands.common.recipe.DruidAltarReversionRecipe;

public class DruidAltarRecipeBuilder implements RecipeBuilder {

	private final HolderGetter<Item> getter;
	@Nullable
	private final ItemStack resultStack;
	private final NonNullList<Ingredient> ingredients = NonNullList.create();

	private DruidAltarRecipeBuilder(HolderGetter<Item> getter, @Nullable ItemStack result) {
		this.getter = getter;
		this.resultStack = result;
	}

	public static DruidAltarRecipeBuilder assembly(HolderGetter<Item> getter, ItemLike result) {
		return new DruidAltarRecipeBuilder(getter, new ItemStack(result, 1));
	}

	public static DruidAltarRecipeBuilder assembly(HolderGetter<Item> getter, ItemStack result) {
		return new DruidAltarRecipeBuilder(getter, result);
	}

	public static DruidAltarRecipeBuilder reversion(HolderGetter<Item> getter) {
		return new DruidAltarRecipeBuilder(getter, null);
	}

	public DruidAltarRecipeBuilder requires(TagKey<Item> tag) {
		return this.requires(Ingredient.of(this.getter.getOrThrow(tag)));
	}

	public DruidAltarRecipeBuilder requires(ItemLike item) {
		return this.requires(item, 1);
	}

	public DruidAltarRecipeBuilder requires(ItemLike item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.requires(Ingredient.of(item));
		}

		return this;
	}

	public DruidAltarRecipeBuilder requires(Ingredient ingredient) {
		return this.requires(ingredient, 1);
	}

	public DruidAltarRecipeBuilder requires(Ingredient ingredient, int quantity) {
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
		return this.resultStack != null ? this.resultStack.getItem() : Items.AIR;
	}

	@Override
	public void save(RecipeOutput output) {
		this.save(output, ResourceKey.create(Registries.RECIPE, TheBetweenlands.prefix("druid_altar/" + RecipeBuilder.getDefaultRecipeId(this.getResult()).getPath())));
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> id) {
		DruidAltarRecipe recipe;
		if (this.resultStack != null) {
			recipe = new DruidAltarAssemblyRecipe(this.ingredients, this.resultStack);
		} else {
			recipe = new DruidAltarReversionRecipe(this.ingredients);
		}
		recipeOutput.accept(id, recipe, null);
	}
}
