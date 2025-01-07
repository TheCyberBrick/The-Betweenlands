package thebetweenlands.common.inventory;

import java.util.Objects;
import java.util.UUID;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import thebetweenlands.common.inventory.container.SecureItemContainer;

public abstract class SecureInventoryItemMenu extends AbstractContainerMenu {

	protected final SecureItemContainer secureContainer;
	
	protected final ContainerData stackUuidDataSlot;
	
	protected SecureInventoryItemMenu(MenuType<?> menuType, int containerId, SecureItemContainer container) {
		super(menuType, containerId);
		Objects.nonNull(container);
		this.secureContainer = container;
		
		this.stackUuidDataSlot = new SimpleContainerData(4);
		addDataSlots(stackUuidDataSlot);
		
		setDataUUID(stackUuidDataSlot, secureContainer.getContainerStackUUID());
	}

	public class UUIDChangeListener implements ContainerListener {
		@Override
		public void slotChanged(AbstractContainerMenu containerToSend, int dataSlotIndex, ItemStack stack) { }

		@Override
		public void dataChanged(AbstractContainerMenu containerMenu, int dataSlotIndex, int value) {
			secureContainer.setContainerStackUUID(getDataUUID(stackUuidDataSlot));
		}
	}
	
	public void addUUIDChangeListener() {
		this.addSlotListener(new UUIDChangeListener());
		secureContainer.setContainerStackUUID(getDataUUID(stackUuidDataSlot));
	}

	public SecureItemContainer getSecureContainer() {
		return secureContainer;
	}

	public ContainerData getUuidDataSlot() {
		return this.stackUuidDataSlot;
	}
	
	@Override
	public boolean stillValid(Player player) {
		return secureContainer.stillValid(player);
	}
	
	public static void setDataUUID(final ContainerData data, final UUID uuid) {
		final long mostSignificantBits = uuid.getMostSignificantBits();
		final long leastSignificantBits = uuid.getLeastSignificantBits();
		
		data.set(0, (int)(mostSignificantBits >> 32L));
		data.set(1, (int)(mostSignificantBits));
		data.set(2, (int)(leastSignificantBits >> 32L));
		data.set(3, (int)(leastSignificantBits));
	}

	public static UUID getDataUUID(ContainerData data) {
		final long mostSigBits = (((long)data.get(0)) << 32L) | ((long)data.get(1) & 0xFFFFFFFFL);
		final long leastSigBits = (((long)data.get(2)) << 32L) | ((long)data.get(3) & 0xFFFFFFFFL);
		
		return new UUID(mostSigBits, leastSigBits);
	}
}
