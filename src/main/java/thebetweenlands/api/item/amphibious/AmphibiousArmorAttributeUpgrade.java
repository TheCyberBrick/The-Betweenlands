package thebetweenlands.api.item.amphibious;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;

public interface AmphibiousArmorAttributeUpgrade {
	void applyAttributeModifiers(EquipmentSlot slot, ItemStack stack, int count, List<ItemAttributeModifiers.Entry> modifiers);
}
