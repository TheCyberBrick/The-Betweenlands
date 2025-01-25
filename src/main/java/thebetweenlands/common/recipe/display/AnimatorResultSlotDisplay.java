package thebetweenlands.common.recipe.display;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.storage.loot.LootTable;
import thebetweenlands.util.LootTableFetcher;

import java.util.Optional;
import java.util.stream.Stream;

public record AnimatorResultSlotDisplay(Optional<ItemStack> stack, Optional<EntityType<?>> entity, Optional<ResourceKey<LootTable>> lootTable) implements SlotDisplay {
	@Override
	public <T> Stream<T> resolve(ContextMap map, DisplayContentsFactory<T> factory) {
		if (this.entity.isPresent()) return Stream.empty();
		if (factory instanceof DisplayContentsFactory.ForStacks<T> stacks) {
			if (this.stack.isPresent()) return Stream.of(stacks.forStack(this.stack.get()));
			if (this.lootTable.isPresent()) return LootTableFetcher.getDropsForTable(this.lootTable.get()).stream().map(stacks::forStack);
		}
		return Stream.empty();
	}

	@Override
	public Type<? extends SlotDisplay> type() {
		return null;
	}
}
