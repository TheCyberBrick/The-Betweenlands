package thebetweenlands.common.inventory;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.inventory.container.SecureItemContainer;
import thebetweenlands.common.inventory.slot.FilteredSlot;
import thebetweenlands.common.registries.MenuRegistry;

public class LurkerSkinPouchMenu extends SecureInventoryItemMenu {

	private final int numRows;

	public LurkerSkinPouchMenu(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf buf) {
		this(containerId, playerInventory, new SecureItemContainer(ItemStack.STREAM_CODEC.decode(buf), buf.readInt(), TheBetweenlands.isRemote(playerInventory)));
	}

	public LurkerSkinPouchMenu(int containerId, Inventory playerInventory, SecureItemContainer pouch) {
		super(MenuRegistry.LURKER_SKIN_POUCH.get(), containerId, pouch);
		this.numRows = pouch.getContainerSize() / 9;
		int yOffset = (this.numRows - 4) * 18;

		for (int row = 0; row < this.numRows; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new FilteredSlot(pouch, column + row * 9, 8 + column * 18, 18 + row * 18, stack -> stack.getItem().canFitInsideContainerItems()));
			}
		}

		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 103 + row * 18 + yOffset));
			}
		}

		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 161 + yOffset));
		}

		pouch.startOpen(playerInventory.player);
	}

	public int getRows() {
		return this.numRows;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			if (!slotStack.getItem().canFitInsideContainerItems()) {
				return ItemStack.EMPTY;
			}

			if (index < this.numRows * 9) {
				if (!this.moveItemStackTo(slotStack, this.numRows * 9, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(slotStack, 0, this.numRows * 9, false)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.getCount() == 0) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return stack;
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.secureContainer == null) {
			return true; //Renaming pouch
		}
		return super.stillValid(player) && secureContainer.stillValid(player);

//		//Check if pouch is in equipment
//		IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
//		if (cap != null) {
//			Inventory inv = cap.getInventory(EnumEquipmentInventory.MISC);
//
//			for (int i = 0; i < inv.getContainerSize(); i++) {
//				if (inv.getItem(i) == this.pouch.getContainerStack()) {
//					return true;
//				}
//			}
//		}

		//Check if pouch is in main inventory
//		if (player.getInventory().contains(this.pouch.getContainerStack())) {
//			return pouch.stillValid(player);
//		}

//		//Check if pouch is in draeton
//		List<Draeton> draetons = player.level().getEntitiesOfClass(Draeton.class, player.getBoundingBox().inflate(6));
//		for (Draeton dreaton : draetons) {
//			if (player.distanceToSqr(dreaton) <= 64.0D) {
//				Inventory inv = dreaton.getUpgradesInventory();
//				for (int i = 0; i < inv.getContainerSize(); i++) {
//					if (inv.getItem(i) == this.pouch.getContainerStack()) {
//						return true;
//					}
//				}
//			}
//		}

//		return false;
	}
	
	@Override
	public void removed(Player player) {
		this.secureContainer.stopOpen(player);
		super.removed(player);
	}
}
