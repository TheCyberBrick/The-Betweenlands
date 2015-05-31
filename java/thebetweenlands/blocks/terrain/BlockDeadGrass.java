package thebetweenlands.blocks.terrain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import thebetweenlands.blocks.BLBlockRegistry;
import thebetweenlands.creativetabs.ModCreativeTabs;

import java.util.Random;

public class BlockDeadGrass
extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;

	public BlockDeadGrass() {
		super(Material.grass);
		setHardness(0.5F);
		setStepSound(soundTypeGrass);
		setHarvestLevel("shovel", 0);
		setCreativeTab(ModCreativeTabs.blocks);
		setBlockName("thebetweenlands.deadGrass");
		setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if( side == 1 ) {
			return this.topIcon;
		}
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon("thebetweenlands:swampDirt");
		this.topIcon = reg.registerIcon("thebetweenlands:deadGrassTop");
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return BLBlockRegistry.swampDirt.getItemDropped(0, rand, fortune);
	}
}
