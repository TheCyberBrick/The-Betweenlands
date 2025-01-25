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
import thebetweenlands.common.recipe.BubblerCrabPotFilterRecipe;
import thebetweenlands.common.recipe.SiltCrabPotFilterRecipe;

public class CrabPotFilterRecipeBuilder implements RecipeBuilder {
	private final boolean siltCrab;
	private final HolderGetter<Item> getter;
	private final Ingredient input;
	private final ItemStack output;
	private int time = 200;

	private CrabPotFilterRecipeBuilder(boolean siltCrab, HolderGetter<Item> getter, Ingredient input, ItemStack result) {
		this.getter = getter;
		this.siltCrab = siltCrab;
		this.input = input;
		this.output = result;
	}

	public static CrabPotFilterRecipeBuilder siltCrab(HolderGetter<Item> getter, Ingredient input, ItemLike result) {
		return new CrabPotFilterRecipeBuilder(true, getter, input, new ItemStack(result));
	}

	public static CrabPotFilterRecipeBuilder siltCrab(HolderGetter<Item> getter, TagKey<Item> input, ItemLike result) {
		return new CrabPotFilterRecipeBuilder(true, getter, Ingredient.of(getter.getOrThrow(input)), new ItemStack(result));
	}

	public static CrabPotFilterRecipeBuilder siltCrab(HolderGetter<Item> getter, ItemLike input, ItemLike result) {
		return new CrabPotFilterRecipeBuilder(true, getter, Ingredient.of(input), new ItemStack(result));
	}

	public static CrabPotFilterRecipeBuilder siltCrab(HolderGetter<Item> getter, Ingredient input, ItemStack result) {
		return new CrabPotFilterRecipeBuilder(true, getter, input, result);
	}

	public static CrabPotFilterRecipeBuilder siltCrab(HolderGetter<Item> getter, TagKey<Item> input, ItemStack result) {
		return new CrabPotFilterRecipeBuilder(true, getter, Ingredient.of(getter.getOrThrow(input)), result);
	}

	public static CrabPotFilterRecipeBuilder siltCrab(HolderGetter<Item> getter, ItemLike input, ItemStack result) {
		return new CrabPotFilterRecipeBuilder(true, getter, Ingredient.of(input), result);
	}

	public static CrabPotFilterRecipeBuilder bubblerCrab(HolderGetter<Item> getter, Ingredient input, ItemLike result) {
		return new CrabPotFilterRecipeBuilder(false, getter, input, new ItemStack(result));
	}

	public static CrabPotFilterRecipeBuilder bubblerCrab(HolderGetter<Item> getter, TagKey<Item> input, ItemLike result) {
		return new CrabPotFilterRecipeBuilder(false, getter, Ingredient.of(getter.getOrThrow(input)), new ItemStack(result));
	}

	public static CrabPotFilterRecipeBuilder bubblerCrab(HolderGetter<Item> getter, ItemLike input, ItemLike result) {
		return new CrabPotFilterRecipeBuilder(false, getter, Ingredient.of(input), new ItemStack(result));
	}

	public static CrabPotFilterRecipeBuilder bubblerCrab(HolderGetter<Item> getter, Ingredient input, ItemStack result) {
		return new CrabPotFilterRecipeBuilder(false, getter, input, result);
	}

	public static CrabPotFilterRecipeBuilder bubblerCrab(HolderGetter<Item> getter, TagKey<Item> input, ItemStack result) {
		return new CrabPotFilterRecipeBuilder(false, getter, Ingredient.of(getter.getOrThrow(input)), result);
	}

	public static CrabPotFilterRecipeBuilder bubblerCrab(HolderGetter<Item> getter, ItemLike input, ItemStack result) {
		return new CrabPotFilterRecipeBuilder(false, getter, Ingredient.of(input), result);
	}

	public CrabPotFilterRecipeBuilder setFilterTime(int time) {
		this.time = time;
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
		return this.output.getItem();
	}

	@Override
	public void save(RecipeOutput output) {
		this.save(output, ResourceKey.create(Registries.RECIPE, TheBetweenlands.prefix("filter/" + (this.siltCrab ? "silt/" : "bubbler/") + RecipeBuilder.getDefaultRecipeId(this.getResult()).getPath())));
	}

	@Override
	public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
		output.accept(id, this.siltCrab ? new SiltCrabPotFilterRecipe(this.input, this.output, this.time) : new BubblerCrabPotFilterRecipe(this.input, this.output, this.time), null);
	}
}
