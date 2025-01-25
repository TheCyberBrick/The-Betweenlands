package thebetweenlands.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import thebetweenlands.api.recipes.TrimmingTableRecipe;
import thebetweenlands.common.recipe.display.TrimmingTableDisplay;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import java.util.List;
import java.util.Optional;

public class BasicTrimmingTableRecipe extends TrimmingTableRecipe {

	private final List<ItemStack> outputs;
	private final Optional<ItemStack> remains;

	public BasicTrimmingTableRecipe(Ingredient input, List<ItemStack> outputs, Optional<ItemStack> remains) {
		super(input);
		this.outputs = outputs;
		this.remains = remains;
	}

	@Override
	public List<ItemStack> assembleRecipe(SingleRecipeInput input, Level level) {
		return List.copyOf(this.outputs);
	}

	@Override
	public ItemStack getRemains() {
		return this.remains.orElse(ItemStack.EMPTY);
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new TrimmingTableDisplay(
			this.input().display(),
			this.outputs.stream().map(SlotDisplay.ItemStackSlotDisplay::new).map(SlotDisplay.class::cast).toList(),
			new SlotDisplay.ItemStackSlotDisplay(this.remains.orElse(ItemStack.EMPTY)),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.FISH_TRIMMING_TABLE.asItem())
		));
	}

	@Override
	public RecipeSerializer<BasicTrimmingTableRecipe> getSerializer() {
		return RecipeRegistry.TRIMMING_SERIALIZER.get();
	}

	public static class Serializer implements RecipeSerializer<BasicTrimmingTableRecipe> {

		public static final MapCodec<BasicTrimmingTableRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.fieldOf("input").forGetter(BasicTrimmingTableRecipe::input),
			Codec.lazyInitialized(() -> ItemStack.CODEC.listOf(1, 3)).fieldOf("outputs").forGetter(o -> o.outputs),
			ItemStack.STRICT_SINGLE_ITEM_CODEC.optionalFieldOf("remains").forGetter(o -> o.remains)
		).apply(instance, BasicTrimmingTableRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, BasicTrimmingTableRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, BasicTrimmingTableRecipe::input,
			ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), o -> o.outputs,
			ItemStack.STREAM_CODEC.apply(ByteBufCodecs::optional), o -> o.remains,
			BasicTrimmingTableRecipe::new
		);

		@Override
		public MapCodec<BasicTrimmingTableRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, BasicTrimmingTableRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
