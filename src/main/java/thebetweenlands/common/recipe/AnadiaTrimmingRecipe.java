package thebetweenlands.common.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import thebetweenlands.api.recipes.TrimmingTableRecipe;
import thebetweenlands.common.entity.fishing.anadia.Anadia;
import thebetweenlands.common.item.misc.AnadiaMobItem;
import thebetweenlands.common.recipe.display.TrimmingTableDisplay;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import java.util.ArrayList;
import java.util.List;

public class AnadiaTrimmingRecipe extends TrimmingTableRecipe {

	public AnadiaTrimmingRecipe() {
		super(Ingredient.of(ItemRegistry.ANADIA));
	}

	@Override
	public List<ItemStack> assembleRecipe(SingleRecipeInput input, Level level) {
		List<ItemStack> results = new ArrayList<>();
		ItemStack stack = input.item();
		if (stack.getItem() instanceof AnadiaMobItem mob && !mob.getEntityData(stack).isEmpty()) {
			results.add(mob.getItemFromEntity(Anadia.HEAD_KEY, stack, level));
			results.add(mob.getItemFromEntity(Anadia.BODY_KEY, stack, level));
			results.add(mob.getItemFromEntity(Anadia.TAIL_KEY, stack, level));
		}
		return results;
	}

	@Override
	public ItemStack getRemains() {
		return new ItemStack(ItemRegistry.ANADIA_REMAINS.get());
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new TrimmingTableDisplay(
			Ingredient.displayForSingleItem(ItemRegistry.ANADIA),
			List.of(new SlotDisplay.ItemSlotDisplay(ItemRegistry.ANADIA_EYE), new SlotDisplay.ItemSlotDisplay(ItemRegistry.RAW_ANADIA_MEAT), new SlotDisplay.ItemSlotDisplay(ItemRegistry.ANADIA_SWIM_BLADDER)),
			new SlotDisplay.ItemSlotDisplay(ItemRegistry.ANADIA_REMAINS),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.FISH_TRIMMING_TABLE.asItem())
		));
	}

	@Override
	public RecipeSerializer<AnadiaTrimmingRecipe> getSerializer() {
		return RecipeRegistry.ANADIA_TRIMMING_SERIALIZER.get();
	}

	public static class Serializer implements RecipeSerializer<AnadiaTrimmingRecipe> {

		private static final AnadiaTrimmingRecipe INSTANCE = new AnadiaTrimmingRecipe();
		private static final MapCodec<AnadiaTrimmingRecipe> CODEC = MapCodec.unit(INSTANCE);
		private static final StreamCodec<RegistryFriendlyByteBuf, AnadiaTrimmingRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);

		@Override
		public MapCodec<AnadiaTrimmingRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, AnadiaTrimmingRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
