package thebetweenlands.common.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import thebetweenlands.api.recipes.MortarRecipe;
import thebetweenlands.common.component.item.AspectContents;
import thebetweenlands.common.herblore.Amounts;
import thebetweenlands.common.item.herblore.AspectVialItem;
import thebetweenlands.common.item.herblore.DentrothystVialItem;
import thebetweenlands.common.recipe.display.MortarRecipeDisplay;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.DataComponentRegistry;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import java.util.List;

public class MortarAspectrusRecipe extends MortarRecipe {

	public MortarAspectrusRecipe() {
		super(Ingredient.of(ItemRegistry.ASPECTRUS_FRUIT), ItemStack.EMPTY);
	}

	@Override
	public boolean matchesInput(SingleRecipeInput input, ItemStack output, Level level, boolean useInputOnly) {
		if (input.item().is(ItemRegistry.ASPECTRUS_FRUIT) && input.item().has(DataComponentRegistry.ASPECT_CONTENTS)) {
			AspectContents contents = input.item().get(DataComponentRegistry.ASPECT_CONTENTS);

			if (contents.amount() <= Amounts.VIAL && contents.aspect().isPresent()) {
				if (useInputOnly) {
					return true;
				} else if (output.getCount() == 1 && output.getItem() instanceof DentrothystVialItem) {
					return true;
				} else if (!output.isEmpty() && output.getItem() instanceof AspectVialItem) {
					AspectContents outputContents = output.getOrDefault(DataComponentRegistry.ASPECT_CONTENTS, AspectContents.EMPTY);

					if (outputContents.aspect().isEmpty()) {
						return true;
					} else {
						int storedAmount = outputContents.amount();
						return outputContents.aspect().get().is(contents.aspect().get()) && storedAmount + contents.amount() <= Amounts.VIAL;
					}
				}
			}
		}

		return false;
	}

	@Override
	public ItemStack getOutput(SingleRecipeInput input, ItemStack outputStack, HolderLookup.Provider registries) {
		if (input.item().has(DataComponentRegistry.ASPECT_CONTENTS)) {
			AspectContents contents = input.item().get(DataComponentRegistry.ASPECT_CONTENTS);
			if (contents.aspect().isPresent()) {
				if (outputStack.getItem() instanceof DentrothystVialItem vial) {
					return AspectContents.createItemStack(vial.getFullAspectBottle().value(), contents.aspect().get(), contents.amount());
				} else if (outputStack.getItem() instanceof AspectVialItem) {
					AspectContents outputContents = outputStack.getOrDefault(DataComponentRegistry.ASPECT_CONTENTS, AspectContents.EMPTY);
					outputStack.set(DataComponentRegistry.ASPECT_CONTENTS, new AspectContents(contents.aspect().get(), contents.amount() + outputContents.amount()));
					return outputStack;
				}
			}
		}

		return outputStack;
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public boolean showNotification() {
		return false;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new MortarRecipeDisplay(
			Ingredient.displayForSingleItem(ItemRegistry.ASPECTRUS_FRUIT),
			new SlotDisplay.Composite(List.of(new SlotDisplay.ItemSlotDisplay(ItemRegistry.GREEN_ASPECT_VIAL), new SlotDisplay.ItemSlotDisplay(ItemRegistry.ORANGE_ASPECT_VIAL))),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.MORTAR.asItem())
		));
	}

	@Override
	public RecipeSerializer<MortarAspectrusRecipe> getSerializer() {
		return RecipeRegistry.MORTAR_ASPECTRUS_SERIALIZER.get();
	}

	@Override
	public boolean isOutputUsed(ItemStack output) {
		return output.getItem() instanceof DentrothystVialItem || output.getItem() instanceof AspectVialItem;
	}

	@Override
	public boolean replacesOutput() {
		return true;
	}

	public static class Serializer implements RecipeSerializer<MortarAspectrusRecipe> {

		private static final MortarAspectrusRecipe INSTANCE = new MortarAspectrusRecipe();
		private static final MapCodec<MortarAspectrusRecipe> CODEC = MapCodec.unit(INSTANCE);
		private static final StreamCodec<RegistryFriendlyByteBuf, MortarAspectrusRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);

		@Override
		public MapCodec<MortarAspectrusRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, MortarAspectrusRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
