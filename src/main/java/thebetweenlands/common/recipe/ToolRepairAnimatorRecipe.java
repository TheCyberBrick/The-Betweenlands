package thebetweenlands.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import thebetweenlands.api.recipes.AnimatorRecipe;
import thebetweenlands.common.recipe.display.AnimatorRecipeDisplay;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import java.util.List;

public class ToolRepairAnimatorRecipe extends AnimatorRecipe {

	private final int minRepairLifeCost;
	private final int fullRepairLifeCost;
	private final int minRepairFuelCost;
	private final int fullRepairFuelCost;

	public ToolRepairAnimatorRecipe(Ingredient input, int minRepairLifeCost, int fullRepairLifeCost, int minRepairFuelCost, int fullRepairFuelCost) {
		super(input);
		this.minRepairFuelCost = minRepairFuelCost;
		this.fullRepairFuelCost = fullRepairFuelCost;
		this.minRepairLifeCost = minRepairLifeCost;
		this.fullRepairLifeCost = fullRepairLifeCost;
	}

	@Override
	public boolean matches(SingleRecipeInput input, Level level) {
		return !input.item().isEmpty() && input.item().getDamageValue() > 0 && this.input().test(input.item());
	}

	@Override
	public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
		ItemStack result = input.item().copy();
		result.setDamageValue(0);
		return result;
	}

	@Override
	public int getRequiredFuel(SingleRecipeInput input) {
		return this.minRepairFuelCost + Mth.ceil((this.fullRepairFuelCost - this.minRepairFuelCost) / (float)input.item().getMaxDamage() * (float)input.item().getDamageValue());
	}

	@Override
	public int getRequiredLife(SingleRecipeInput input) {
		return this.minRepairLifeCost + Mth.ceil((this.fullRepairLifeCost - this.minRepairLifeCost) / (float)input.item().getMaxDamage() * (float)input.item().getDamageValue());
	}

	@Nullable
	@Override
	public Entity getRenderEntity(SingleRecipeInput input, Level level) {
		return null;
	}

	@Nullable
	@Override
	public EntityType<?> getSpawnEntity(SingleRecipeInput input) {
		return null;
	}

	@Nullable
	@Override
	public EntityType<?> getSpawnEntity() {
		return null;
	}

	@Override
	public ItemStack onAnimated(ServerLevel level, BlockPos pos, SingleRecipeInput input) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean onRetrieved(Player player, BlockPos pos, SingleRecipeInput input) {
		return true;
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new AnimatorRecipeDisplay(
			this.input().display(),
			new SlotDisplay.Composite(List.of(new SlotDisplay.ItemSlotDisplay(ItemRegistry.LIFE_CRYSTAL), new SlotDisplay.ItemSlotDisplay(ItemRegistry.LIFE_CRYSTAL_FRAGMENT))),
			new SlotDisplay.ItemSlotDisplay(ItemRegistry.SULFUR),
			this.input().display(),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.ANIMATOR.asItem())
		));
	}

	@Override
	public RecipeSerializer<ToolRepairAnimatorRecipe> getSerializer() {
		return RecipeRegistry.ANIMATOR_TOOL_SERIALIZER.get();
	}

	public static class Serializer implements RecipeSerializer<ToolRepairAnimatorRecipe> {

		public static final MapCodec<ToolRepairAnimatorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.fieldOf("input").forGetter(ToolRepairAnimatorRecipe::input),
			Codec.INT.fieldOf("min_required_fuel").forGetter(o -> o.minRepairFuelCost),
			Codec.INT.fieldOf("full_required_fuel").forGetter(o -> o.fullRepairFuelCost),
			Codec.INT.fieldOf("min_required_life").forGetter(o -> o.minRepairLifeCost),
			Codec.INT.fieldOf("full_required_life").forGetter(o -> o.fullRepairLifeCost)
		).apply(instance, ToolRepairAnimatorRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, ToolRepairAnimatorRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, ToolRepairAnimatorRecipe::input,
			ByteBufCodecs.INT, o -> o.minRepairFuelCost,
			ByteBufCodecs.INT, o -> o.fullRepairFuelCost,
			ByteBufCodecs.INT, o -> o.minRepairLifeCost,
			ByteBufCodecs.INT, o -> o.fullRepairLifeCost,
			ToolRepairAnimatorRecipe::new);

		@Override
		public MapCodec<ToolRepairAnimatorRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, ToolRepairAnimatorRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
