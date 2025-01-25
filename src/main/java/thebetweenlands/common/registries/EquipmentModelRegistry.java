package thebetweenlands.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentModel;
import thebetweenlands.common.TheBetweenlands;

import java.util.function.BiConsumer;

public class EquipmentModelRegistry {

	public static final ResourceLocation RUBBER = TheBetweenlands.prefix("rubber");
	public static final ResourceLocation LURKER_SKIN = TheBetweenlands.prefix("lurker_skin");
	public static final ResourceLocation BONE = TheBetweenlands.prefix("bone");
	public static final ResourceLocation SYRMORITE = TheBetweenlands.prefix("syrmorite");
	public static final ResourceLocation VALONITE = TheBetweenlands.prefix("valonite");
	public static final ResourceLocation ANCIENT = TheBetweenlands.prefix("ancient");
	public static final ResourceLocation AMPHIBIOUS = TheBetweenlands.prefix("amphibious");

	public static final ResourceLocation SKULL_MASK = TheBetweenlands.prefix("skull_mask");

	public static void bootstrap(BiConsumer<ResourceLocation, EquipmentModel> consumer) {
		consumer.accept(RUBBER, EquipmentModel.builder().addMainHumanoidLayer(TheBetweenlands.prefix("rubber"), false).build());
		consumer.accept(LURKER_SKIN, EquipmentModel.builder().addHumanoidLayers(TheBetweenlands.prefix("lurker_skin")).build());
		consumer.accept(BONE, EquipmentModel.builder().addHumanoidLayers(TheBetweenlands.prefix("bone")).build());
		consumer.accept(SYRMORITE, EquipmentModel.builder().addHumanoidLayers(TheBetweenlands.prefix("syrmorite")).build());
		consumer.accept(VALONITE, EquipmentModel.builder().addHumanoidLayers(TheBetweenlands.prefix("valonite")).build());
		consumer.accept(ANCIENT, EquipmentModel.builder().addHumanoidLayers(TheBetweenlands.prefix("ancient")).build());
		consumer.accept(AMPHIBIOUS, EquipmentModel.builder().addHumanoidLayers(TheBetweenlands.prefix("amphibious")).build());

		consumer.accept(SKULL_MASK, EquipmentModel.builder().addMainHumanoidLayer(TheBetweenlands.prefix("skull_mask"), false).build());

	}
}
