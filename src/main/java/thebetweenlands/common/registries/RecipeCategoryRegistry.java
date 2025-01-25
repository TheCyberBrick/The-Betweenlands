package thebetweenlands.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import thebetweenlands.common.TheBetweenlands;

public class RecipeCategoryRegistry {

	public static final DeferredRegister<RecipeBookCategory> CATEGORIES = DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, TheBetweenlands.ID);

	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> ANIMATOR = CATEGORIES.register("animator", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> BUBBLER_CRAB = CATEGORIES.register("bubbler_crab", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> DRUID_ALTAR = CATEGORIES.register("druid_altar", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> MORTAR = CATEGORIES.register("mortar", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> PURIFIER = CATEGORIES.register("purifier", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> SILT_CRAB = CATEGORIES.register("silt_crab", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> SMOKING_RACK = CATEGORIES.register("smoking_rack", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> STEEPING_POT = CATEGORIES.register("steeping_pot", RecipeBookCategory::new);
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> TRIMMING_TABLE = CATEGORIES.register("trimming_table", RecipeBookCategory::new);

}
