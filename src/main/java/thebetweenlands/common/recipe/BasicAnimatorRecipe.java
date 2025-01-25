package thebetweenlands.common.recipe;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import thebetweenlands.api.recipes.AnimatorRecipe;
import thebetweenlands.common.block.entity.AnimatorBlockEntity;
import thebetweenlands.common.recipe.display.AnimatorRecipeDisplay;
import thebetweenlands.common.recipe.display.AnimatorResultSlotDisplay;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.RecipeRegistry;
import thebetweenlands.util.EntityCache;

public class BasicAnimatorRecipe extends AnimatorRecipe {

	private final Optional<ItemStack> resultStack;
	private final Optional<EntityType<?>> resultEntity;
	private final int requiredFuel;
	private final int requiredLife;
	private final Optional<EntityType<?>> renderEntity;
	private final Optional<ResourceKey<LootTable>> lootTable;
	private final boolean closeOnFinish;

	public BasicAnimatorRecipe(Ingredient input, Optional<ItemStack> resultStack, Optional<EntityType<?>> resultEntity, int requiredFuel, int requiredLife, Optional<EntityType<?>> renderEntity, Optional<ResourceKey<LootTable>> lootTable, boolean closeOnFinish) {
		super(input);
		this.resultStack = resultStack;
		this.resultEntity = resultEntity;
		this.requiredFuel = requiredFuel;
		this.requiredLife = requiredLife;
		this.renderEntity = renderEntity;
		this.lootTable = lootTable;
		this.closeOnFinish = closeOnFinish;
	}

	@Nullable
	@Override
	public Entity getRenderEntity(SingleRecipeInput input, Level level) {
		if (this.renderEntity.isPresent()) {
			return EntityCache.fetchEntity(this.renderEntity.get(), level);
		}
		return null;
	}

	@Nullable
	@Override
	public EntityType<?> getSpawnEntity(SingleRecipeInput input) {
		return this.resultEntity.orElse(null);
	}

	@Nullable
	@Override
	public EntityType<?> getSpawnEntity() {
		return this.resultEntity.orElse(null);
	}

	@Override
	public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
		return this.resultStack.orElse(ItemStack.EMPTY).copy();
	}

	@Override
	public ItemStack onAnimated(ServerLevel level, BlockPos pos, SingleRecipeInput input) {
		if (this.lootTable.isPresent()) {
			LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(this.lootTable.get());
			List<ItemStack> loot = lootTable.getRandomItems(new LootParams.Builder(level).create(LootContextParamSets.EMPTY), level.getRandom());
			if (!loot.isEmpty()) {
				return loot.get(level.getRandom().nextInt(loot.size()));
			}
			return ItemStack.EMPTY;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public boolean onRetrieved(Player player, BlockPos pos, SingleRecipeInput input) {
		if (player.level().getBlockEntity(pos) instanceof AnimatorBlockEntity animator) {
			if (this.resultEntity.isPresent()) {
				Entity entity = this.resultEntity.get().create(player.level(), EntitySpawnReason.TRIGGERED);
				entity.moveTo(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, 0, 0);
				player.level().addFreshEntity(entity);
				animator.setItem(0, ItemStack.EMPTY);
				return false;
			}
			return true;
		}
		return true;
	}

	@Override
	public boolean getCloseOnFinish(SingleRecipeInput input) {
		return this.closeOnFinish;
	}

	@Override
	public int getRequiredFuel(SingleRecipeInput input) {
		return this.requiredFuel;
	}

	@Override
	public int getRequiredLife(SingleRecipeInput input) {
		return this.requiredLife;
	}

	@Override
	public RecipeSerializer<BasicAnimatorRecipe> getSerializer() {
		return RecipeRegistry.ANIMATOR_SERIALIZER.get();
	}

	@Override
	public List<RecipeDisplay> display() {
		return List.of(new AnimatorRecipeDisplay(
			this.input().display(),
			new SlotDisplay.Composite(List.of(new SlotDisplay.ItemSlotDisplay(ItemRegistry.LIFE_CRYSTAL), new SlotDisplay.ItemSlotDisplay(ItemRegistry.LIFE_CRYSTAL_FRAGMENT))),
			new SlotDisplay.ItemSlotDisplay(ItemRegistry.SULFUR),
			new AnimatorResultSlotDisplay(this.resultStack, this.resultEntity, this.lootTable),
			new SlotDisplay.ItemSlotDisplay(BlockRegistry.ANIMATOR.asItem())
		));
	}

	public static class Serializer implements RecipeSerializer<BasicAnimatorRecipe> {

		public static final MapCodec<BasicAnimatorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.fieldOf("input").forGetter(BasicAnimatorRecipe::input),
			ItemStack.STRICT_CODEC.optionalFieldOf("result_stack").forGetter(o -> o.resultStack),
			BuiltInRegistries.ENTITY_TYPE.byNameCodec().optionalFieldOf("result_entity").forGetter(o -> o.resultEntity),
			Codec.INT.fieldOf("required_fuel").forGetter(o -> o.requiredFuel),
			Codec.INT.fieldOf("required_life").forGetter(o -> o.requiredLife),
			BuiltInRegistries.ENTITY_TYPE.byNameCodec().optionalFieldOf("render_entity").forGetter(o -> o.renderEntity),
			ResourceKey.codec(Registries.LOOT_TABLE).optionalFieldOf("result_loot_table").forGetter(o -> o.lootTable),
			Codec.BOOL.optionalFieldOf("close_on_finish", false).forGetter(o -> o.closeOnFinish)
		).apply(instance, BasicAnimatorRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, BasicAnimatorRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, BasicAnimatorRecipe::input,
			ItemStack.STREAM_CODEC.apply(ByteBufCodecs::optional), o -> o.resultStack,
			ByteBufCodecs.registry(Registries.ENTITY_TYPE).apply(ByteBufCodecs::optional), o -> o.resultEntity,
			ByteBufCodecs.INT, o -> o.requiredFuel,
			ByteBufCodecs.INT, o -> o.requiredLife,
			ByteBufCodecs.registry(Registries.ENTITY_TYPE).apply(ByteBufCodecs::optional), o -> o.renderEntity,
			ResourceKey.streamCodec(Registries.LOOT_TABLE).apply(ByteBufCodecs::optional), o -> o.lootTable,
			ByteBufCodecs.BOOL, o -> o.closeOnFinish,
			BasicAnimatorRecipe::new
		);

		@Override
		public MapCodec<BasicAnimatorRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, BasicAnimatorRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
