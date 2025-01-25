package thebetweenlands.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import thebetweenlands.api.recipes.DruidAltarRecipe;
import thebetweenlands.common.block.entity.spawner.BetweenlandsBaseSpawner;
import thebetweenlands.common.block.entity.spawner.MobSpawnerBlockEntity;
import thebetweenlands.common.recipe.display.DruidAltarRecipeDisplay;
import thebetweenlands.common.recipe.input.MultiStackInput;
import thebetweenlands.common.registries.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DruidAltarReversionRecipe implements DruidAltarRecipe {

	private final List<Ingredient> ingredients;
	@Nullable
	private PlacementInfo placementInfo;

	public DruidAltarReversionRecipe(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
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
		return ItemStack.EMPTY;
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
		return List.of(new DruidAltarRecipeDisplay(
			this.ingredients.stream().map(Ingredient::display).toList(),
			SlotDisplay.Empty.INSTANCE,
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.DRUID_ALTAR.asItem())));
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
	public RecipeSerializer<DruidAltarReversionRecipe> getSerializer() {
		return RecipeRegistry.DRUID_ALTAR_REVERSION_SERIALIZER.get();
	}

	@Override
	public void onCrafted(Level level, BlockPos pos, MultiStackInput input, ItemStack output) {
		BlockPos spawnerPos = pos.below();
		if (level.getBlockState(spawnerPos).getDestroySpeed(level, spawnerPos) >= 0.0F) {
			level.setBlockAndUpdate(spawnerPos, BlockRegistry.MOB_SPAWNER.get().defaultBlockState());
			if (level.getBlockEntity(pos) instanceof MobSpawnerBlockEntity spawner) {
				BetweenlandsBaseSpawner logic = spawner.getSpawner();
				logic.setNextEntityName(EntityRegistry.DARK_DRUID.get(), level, level.getRandom(), pos).setCheckRange(32.0D).setSpawnRange(6).setSpawnInAir(false).setMaxEntities(1 + level.getRandom().nextInt(3));
			}

			level.playSound(null, spawnerPos, SoundRegistry.DRUID_TELEPORT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

			// Block break effect, see LevelRenderer#levelEvent
			level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, spawnerPos.above(4), Block.getId(Blocks.OAK_SAPLING.defaultBlockState()));
			level.levelEvent(LevelEvent.PARTICLES_EYE_OF_ENDER_DEATH, spawnerPos.above(4), 0);
		}
	}

	public static class Serializer implements RecipeSerializer<DruidAltarReversionRecipe> {

		public static final MapCodec<DruidAltarReversionRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Codec.lazyInitialized(() -> Ingredient.CODEC.listOf(1, 4)).fieldOf("ingredients").forGetter(o -> o.ingredients)
		).apply(instance, DruidAltarReversionRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, DruidAltarReversionRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), o -> o.ingredients,
			DruidAltarReversionRecipe::new
		);

		@Override
		public MapCodec<DruidAltarReversionRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, DruidAltarReversionRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
