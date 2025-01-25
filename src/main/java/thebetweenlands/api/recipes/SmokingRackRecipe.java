package thebetweenlands.api.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import thebetweenlands.common.recipe.display.SingleDurationDisplay;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.RecipeCategoryRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import java.util.List;

public abstract class SmokingRackRecipe extends SingleItemRecipe {

	public final int smokingTime;

	public SmokingRackRecipe(Ingredient input, ItemStack result, int smokingTime) {
		super("", input, result);
		this.smokingTime = smokingTime;
	}

	public int smokingTime() {
		return this.smokingTime;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new SingleDurationDisplay(
			this.input().display(),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.FALLEN_LEAVES.asItem()),
			new SlotDisplay.ItemStackSlotDisplay(this.result()),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.SMOKING_RACK.asItem()),
			this.smokingTime()
		));
	}

	@Override
	public RecipeType<SmokingRackRecipe> getType() {
		return RecipeRegistry.SMOKING_RECIPE.get();
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeCategoryRegistry.SMOKING_RACK.get();
	}
}
