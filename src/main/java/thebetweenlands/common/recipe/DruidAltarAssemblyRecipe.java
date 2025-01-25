package thebetweenlands.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import thebetweenlands.api.recipes.DruidAltarRecipe;
import thebetweenlands.common.recipe.display.DruidAltarRecipeDisplay;
import thebetweenlands.common.recipe.input.MultiStackInput;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.RecipeCategoryRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DruidAltarAssemblyRecipe implements DruidAltarRecipe {

	private final List<Ingredient> ingredients;
	private final ItemStack result;
	@Nullable
	private PlacementInfo placementInfo;

	public DruidAltarAssemblyRecipe(List<Ingredient> ingredients, ItemStack result) {
		this.ingredients = ingredients;
		this.result = result;
	}

	@Override
	public boolean matches(MultiStackInput input, Level level) {
		if (input.ingredientCount() != this.ingredients.size()) {
			return false;
		}
		List<ItemStack> nonEmptyItems = new ArrayList<>(input.ingredientCount());
		for (var item : input.items()) {
			if (!item.isEmpty()) {
				nonEmptyItems.add(item);
			}
		}
		return RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
	}

	@Override
	public ItemStack assemble(MultiStackInput input, HolderLookup.Provider registries) {
		return this.result.copy();
	}

	@Override
	public PlacementInfo placementInfo() {
		if (this.placementInfo == null) {
			this.placementInfo = PlacementInfo.create(this.ingredients);
		}

		return this.placementInfo;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new DruidAltarRecipeDisplay(this.ingredients.stream().map(Ingredient::display).toList(), new SlotDisplay.ItemStackSlotDisplay(this.result), new SlotDisplay.ItemSlotDisplay(BlockRegistry.DRUID_ALTAR.asItem())));
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return RecipeCategoryRegistry.DRUID_ALTAR.get();
	}

	@Override
	public RecipeSerializer<DruidAltarAssemblyRecipe> getSerializer() {
		return RecipeRegistry.DRUID_ALTAR_ASSEMBLY_SERIALIZER.get();
	}

	public static class Serializer implements RecipeSerializer<DruidAltarAssemblyRecipe> {

		public static final MapCodec<DruidAltarAssemblyRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codec.lazyInitialized(() -> Ingredient.CODEC.listOf(1, 4)).fieldOf("ingredients").forGetter(o -> o.ingredients),
			ItemStack.STRICT_CODEC.fieldOf("result").forGetter(o -> o.result)
		).apply(instance, DruidAltarAssemblyRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, DruidAltarAssemblyRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), o -> o.ingredients,
			ItemStack.STREAM_CODEC, o -> o.result,
			DruidAltarAssemblyRecipe::new
		);

		@Override
		public MapCodec<DruidAltarAssemblyRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, DruidAltarAssemblyRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
