package thebetweenlands.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import thebetweenlands.api.recipes.SteepingPotRecipe;
import thebetweenlands.common.recipe.display.SteepingPotDisplay;
import thebetweenlands.common.recipe.input.FluidRecipeInput;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSteepingPotRecipe implements SteepingPotRecipe {

	private final FluidIngredient inputFluid;
	private final List<Ingredient> items;
	private final ItemStack result;
	@Nullable
	private PlacementInfo placementInfo;

	public ItemSteepingPotRecipe(FluidIngredient inputFluid, List<Ingredient> items, ItemStack result) {
		this.inputFluid = inputFluid;
		this.items = items;
		this.result = result;
	}

	@Override
	public boolean matches(FluidRecipeInput input, Level level) {
		if (!this.inputFluid.test(input.getFluid())) return false;
		if (input.ingredientCount() != this.items.size()) {
			return false;
		}
		return input.size() == 1 && this.items.size() == 1
			? this.items.getFirst().test(input.getItem(0))
			: input.stackedContents().canCraft(this, null);
	}

	@Override
	public ItemStack assemble(FluidRecipeInput input, HolderLookup.Provider registries) {
		return this.result.copy();
	}

	@Override
	public FluidIngredient getInputFluid() {
		return this.inputFluid;
	}

	@Override
	public FluidStack getResultFluid(HolderLookup.Provider registries) {
		return FluidStack.EMPTY;
	}

	@Override
	public PlacementInfo placementInfo() {
		if (this.placementInfo == null) {
			this.placementInfo = PlacementInfo.create(this.items);
		}

		return this.placementInfo;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new SteepingPotDisplay(
			this.inputFluid.display(),
			this.items.stream().map(Ingredient::display).toList(),
			new SlotDisplay.ItemStackSlotDisplay(this.result),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.STEEPING_POT.asItem())
		));
	}

	@Override
	public RecipeSerializer<ItemSteepingPotRecipe> getSerializer() {
		return RecipeRegistry.ITEM_STEEPING_POT_SERIALIZER.get();
	}

	public static class Serializer implements RecipeSerializer<ItemSteepingPotRecipe> {

		public static final MapCodec<ItemSteepingPotRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			FluidIngredient.CODEC.fieldOf("input_fluid").forGetter(o -> o.inputFluid),
			Codec.lazyInitialized(() -> Ingredient.CODEC.listOf(1, 4)).fieldOf("ingredients").forGetter(o -> o.items),
			ItemStack.STRICT_CODEC.fieldOf("result").forGetter(o -> o.result)
		).apply(instance, ItemSteepingPotRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, ItemSteepingPotRecipe> STREAM_CODEC = StreamCodec.composite(
			FluidIngredient.STREAM_CODEC, o -> o.inputFluid,
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), o -> o.items,
			ItemStack.STREAM_CODEC, o -> o.result,
			ItemSteepingPotRecipe::new
		);

		@Override
		public MapCodec<ItemSteepingPotRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, ItemSteepingPotRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
