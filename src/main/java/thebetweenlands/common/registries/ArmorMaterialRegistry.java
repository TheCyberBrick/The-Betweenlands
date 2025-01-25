package thebetweenlands.common.registries;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import thebetweenlands.common.datagen.tags.BLItemTagProvider;

import java.util.EnumMap;

public class ArmorMaterialRegistry {

	public static final ArmorMaterial SKULL_MASK = new ArmorMaterial(10, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 0);
		map.put(ArmorType.LEGGINGS, 0);
		map.put(ArmorType.CHESTPLATE, 0);
		map.put(ArmorType.HELMET, 0);
		map.put(ArmorType.BODY, 0);
	}), 0, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, BLItemTagProvider.REPAIRS_SPECIAL_ARMORS, EquipmentModelRegistry.SKULL_MASK);

	public static final ArmorMaterial LURKER_SKIN = new ArmorMaterial(12, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 3);
		map.put(ArmorType.HELMET, 1);
		map.put(ArmorType.BODY, 3);
	}), 0, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, BLItemTagProvider.REPAIRS_LURKER_ARMOR, EquipmentModelRegistry.LURKER_SKIN);

	public static final ArmorMaterial BONE = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
		map.put(ArmorType.LEGGINGS, 3);
		map.put(ArmorType.CHESTPLATE, 5);
		map.put(ArmorType.HELMET, 2);
		map.put(ArmorType.BODY, 5);
	}), 0, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, BLItemTagProvider.REPAIRS_BONE_ARMOR, EquipmentModelRegistry.BONE);

	public static final ArmorMaterial SYRMORITE = new ArmorMaterial(16, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 5);
		map.put(ArmorType.CHESTPLATE, 6);
		map.put(ArmorType.HELMET, 2);
		map.put(ArmorType.BODY, 6);
	}), 0, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, BLItemTagProvider.REPAIRS_SYRMORITE_ARMOR, EquipmentModelRegistry.SYRMORITE);

	public static final ArmorMaterial VALONITE = new ArmorMaterial(35, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 8);
		map.put(ArmorType.HELMET, 3);
		map.put(ArmorType.BODY, 8);
	}), 0, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, BLItemTagProvider.REPAIRS_VALONITE_ARMOR, EquipmentModelRegistry.VALONITE);

	public static final ArmorMaterial RUBBER = new ArmorMaterial(10, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
		map.put(ArmorType.LEGGINGS, 0);
		map.put(ArmorType.CHESTPLATE, 0);
		map.put(ArmorType.HELMET, 0);
		map.put(ArmorType.BODY, 0);
	}), 10, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, BLItemTagProvider.REPAIRS_RUBBER_BOOTS, EquipmentModelRegistry.RUBBER);

	public static final ArmorMaterial ANCIENT = new ArmorMaterial(35, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 8);
		map.put(ArmorType.HELMET, 3);
		map.put(ArmorType.BODY, 8);
	}), 35, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0F, 0.0F, BLItemTagProvider.REPAIRS_ANCIENT_ARMOR, EquipmentModelRegistry.ANCIENT);

	public static final ArmorMaterial AMPHIBIOUS = new ArmorMaterial(12, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 3);
		map.put(ArmorType.HELMET, 1);
		map.put(ArmorType.BODY, 3);
	}), 12, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, BLItemTagProvider.REPAIRS_AMPHIBIOUS_ARMOR, EquipmentModelRegistry.AMPHIBIOUS);
}
