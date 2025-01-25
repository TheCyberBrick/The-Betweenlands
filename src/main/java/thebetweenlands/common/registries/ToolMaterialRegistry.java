package thebetweenlands.common.registries;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import thebetweenlands.common.datagen.tags.BLBlockTagProvider;
import thebetweenlands.common.datagen.tags.BLItemTagProvider;

public class ToolMaterialRegistry {

	public static final ToolMaterial WEEDWOOD = new ToolMaterial(BLBlockTagProvider.INCORRECT_FOR_WEEDWOOD_TOOL, 80, 2.0F, 0.0F, 0, BLItemTagProvider.REPAIRS_WEEDWOOD_TOOLS);
	public static final ToolMaterial BONE = new ToolMaterial(BLBlockTagProvider.INCORRECT_FOR_BONE_TOOL, 320, 4.0F, 1.0F, 0, BLItemTagProvider.REPAIRS_BONE_TOOLS);
	public static final ToolMaterial OCTINE = new ToolMaterial(BLBlockTagProvider.INCORRECT_FOR_OCTINE_TOOL, 900, 6.0F, 2.0F, 0, BLItemTagProvider.REPAIRS_OCTINE_TOOLS);
	public static final ToolMaterial VALONITE = new ToolMaterial(BLBlockTagProvider.INCORRECT_FOR_VALONITE_TOOL, 2500, 8.0F, 3.0F, 0, BLItemTagProvider.REPAIRS_VALONITE_TOOLS);

	public static final ToolMaterial GREEN_DENTROTHYST = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 600, 7.0F, 1.0F, 0, BLItemTagProvider.REPAIRS_GREEN_DENTROTHYST_SHIELD);
	public static final ToolMaterial ORANGE_DENTROTHYST = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 600, 7.0F, 1.0F, 0, BLItemTagProvider.REPAIRS_ORANGE_DENTROTHYST_SHIELD);
	public static final ToolMaterial SYRMORITE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 900, 6.0F, 2.0F, 0, BLItemTagProvider.REPAIRS_SYRMORITE_SHIELD);
	public static final ToolMaterial LURKER_SKIN = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 600, 5.0F, 1.0F, 0, BLItemTagProvider.REPAIRS_LURKER_SKIN_SHIELD);

}
