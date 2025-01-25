package thebetweenlands.api.recipes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import thebetweenlands.common.registries.RecipeCategoryRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import javax.annotation.Nullable;

public abstract class AnimatorRecipe extends SingleItemRecipe {

	public AnimatorRecipe(Ingredient input) {
		super("", input, ItemStack.EMPTY);
	}

	@Override
	public abstract ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries);

	/**
	 * Returns the amount of required fuel
	 */
	public abstract int getRequiredFuel(SingleRecipeInput input);

	/**
	 * Returns the amount of required life crystal
	 */
	public abstract int getRequiredLife(SingleRecipeInput input);

	/**
	 * Returns the entity to be rendered when animating the item
	 */
	@Nullable
	public abstract Entity getRenderEntity(SingleRecipeInput input, Level level);

	/**
	 * Returns the entity that is spawned when this recipe is finished
	 */
	@Nullable
	public abstract EntityType<?> getSpawnEntity(SingleRecipeInput input);

	@Nullable
	public abstract EntityType<?> getSpawnEntity();

	/**
	 * Called when the item is animated. Can return the resulting ItemStack (overrides {@link AnimatorRecipe#assemble(SingleRecipeInput, HolderLookup.Provider)}).
	 * Also used to spawn entities from animator once animated
	 */
	public abstract ItemStack onAnimated(ServerLevel level, BlockPos pos, SingleRecipeInput input);

	/**
	 * Called when the animator has finished animating and is right-clicked.
	 * Return true if GUI should be opened on first click
	 */
	public abstract boolean onRetrieved(Player player, BlockPos pos, SingleRecipeInput input);

	/**
	 * Returns whether the GUI should close when the animator has finished
	 */
	public boolean getCloseOnFinish(SingleRecipeInput input) {
		return false;
	}

	@Override
	public RecipeType<AnimatorRecipe> getType() {
		return RecipeRegistry.ANIMATOR_RECIPE.get();
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeCategoryRegistry.ANIMATOR.get();
	}
}
