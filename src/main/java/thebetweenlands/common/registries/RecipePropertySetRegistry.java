package thebetweenlands.common.registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipePropertySet;
import thebetweenlands.common.TheBetweenlands;

public class RecipePropertySetRegistry {

	public static final ResourceKey<RecipePropertySet> MORTAR_INPUT = register("mortar_input");
	public static final ResourceKey<RecipePropertySet> ANIMATOR_INPUT = register("animator_input");
	public static final ResourceKey<RecipePropertySet> SMOKING_RACK_INPUT = register("smoking_rack_input");

	private static ResourceKey<RecipePropertySet> register(String name) {
		return ResourceKey.create(RecipePropertySet.TYPE_KEY, TheBetweenlands.prefix(name));
	}
}
