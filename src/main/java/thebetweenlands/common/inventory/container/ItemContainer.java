package thebetweenlands.common.inventory.container;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

/**
 * Allows you to read & write the contents of a container item
 * <br/>
 * For secure access (anything the player can directly interact with) use {@linkplain SecureItemContainer}
 */
public class ItemContainer implements Container {

	protected final @Nonnull ItemStack stack;
	protected final NonNullList<ItemStack> contents;
	protected boolean changed = false;
    private @Nullable List<ContainerListener> listeners;

	public ItemContainer(@Nonnull ItemStack stack, int slots) {
		this.stack = stack;
		this.contents = NonNullList.withSize(slots, ItemStack.EMPTY);
		ItemContainerContents container = stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
		for (int i = 0; i < slots; i++) {
			if (container.getSlots() > i) {
				this.contents.set(i, container.getStackInSlot(i));
			} else {
				this.contents.set(i, ItemStack.EMPTY);
			}
		}
	}
	
    /**
     * Add a listener that will be notified when any item in this inventory is modified.
     */
    public void addListener(ContainerListener listener) {
        if (this.listeners == null) {
            this.listeners = Lists.newArrayList();
        }

        this.listeners.add(listener);
    }

    /**
     * Removes the specified {@link net.minecraft.world.ContainerListener} from receiving further change notices.
     */
    public void removeListener(ContainerListener listener) {
        if (this.listeners != null) {
            this.listeners.remove(listener);
        }
    }


	@Deprecated
	@Nonnull
	public ItemStack getContainerStack() {
		return this.stack;
	}

	@Nonnull
	public ItemStack getContainerStackFromPlayer(Player player) {
		return this.getContainerStack();
	}

	@Override
	public int getContainerSize() {
		return this.contents.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.contents) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return this.contents.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack itemstack = ContainerHelper.removeItem(this.contents, slot, amount);
		if (!itemstack.isEmpty()) {
			this.setChanged();
		}

		return itemstack;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.contents, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		this.contents.set(slot, stack);
		stack.limitSize(this.getMaxStackSize(stack));
		this.setChanged();
	}

	@Override
	public void setChanged() {
		this.changed = true;

        if (this.listeners != null) {
            for (ContainerListener containerlistener : this.listeners) {
                containerlistener.containerChanged(this);
            }
        }
	}

	@Override
	public void clearContent() {
		this.contents.clear();
		this.setChanged();
	}

	@Override
	public boolean stillValid(Player player) {
		// See SecureItemContainer
		return true;
	}
	
	public void saveTo(ItemStack stack) {
		if (this.changed) {
			stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.contents));
		}
	}
	
	public void releaseStack(ItemStack stack, boolean force) {
		saveTo(stack);
	}
	
	@Override
	public void stopOpen(Player player) {
		Container.super.stopOpen(player);
		releaseStack(getContainerStackFromPlayer(player), false);
	}
}
