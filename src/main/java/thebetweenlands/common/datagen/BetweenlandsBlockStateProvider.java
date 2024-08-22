package thebetweenlands.common.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.block.*;
import thebetweenlands.common.registries.BlockRegistry;

public class BetweenlandsBlockStateProvider extends BlockStateProvider {

	public BetweenlandsBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, TheBetweenlands.ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.simpleBlock(BlockRegistry.SWAMP_WATER.get(), this.models().getBuilder("swamp_water").texture("particle", this.modLoc("fluid/swamp_water_still")));
		this.simpleBlock(BlockRegistry.STAGNANT_WATER.get(), this.models().getBuilder("stagnant_water").texture("particle", this.modLoc("fluid/stagnant_water_still")));
		this.simpleBlock(BlockRegistry.RUBBER.get(), this.models().getBuilder("rubber").texture("particle", this.modLoc("fluid/rubber_still")));
		this.simpleBlock(BlockRegistry.TAR.get(), this.models().getBuilder("tar").texture("particle", this.modLoc("fluid/tar_still")));

		this.druidStone(BlockRegistry.DRUID_STONE_1);
		this.druidStone(BlockRegistry.DRUID_STONE_2);
		this.druidStone(BlockRegistry.DRUID_STONE_3);
		this.druidStone(BlockRegistry.DRUID_STONE_4);
		this.druidStone(BlockRegistry.DRUID_STONE_5);
		this.simpleBlockWithItem(BlockRegistry.DRUID_STONE_6.get(), this.models().getExistingFile(this.blockTexture(Blocks.STONE)));
		this.simpleBlockWithItem(BlockRegistry.BETWEENLANDS_BEDROCK);
		this.simpleBlockWithItem(BlockRegistry.BETWEENSTONE);
		this.simpleBlockWithItem(BlockRegistry.CORRUPT_BETWEENSTONE);
		this.simpleBlockWithItem(BlockRegistry.MUD);
		this.simpleBlockWithItem(BlockRegistry.PEAT);
		this.simpleBlockWithItem(BlockRegistry.SMOULDERING_PEAT);
		this.bottomSideTopBlockWithItem(BlockRegistry.SLUDGY_DIRT, this.modLoc("block/sludgy_dirt_side"), this.modLoc("block/sludgy_dirt_top"), this.modLoc("block/swamp_dirt"));
		this.simpleBlockWithItem(BlockRegistry.SLIMY_DIRT);
		this.bottomSideTopBlockWithItem(BlockRegistry.SLIMY_GRASS, this.modLoc("block/slimy_grass_side"), this.modLoc("block/slimy_grass_top"), this.modLoc("block/slimy_dirt"));
		this.simpleBlockWithItem(BlockRegistry.CRAGROCK);
		this.bottomSideTopBlockWithItem(BlockRegistry.MOSSY_CRAGROCK_TOP, this.modLoc("block/mossy_cragrock_side_1"), this.modLoc("block/mossy_cragrock_top"), this.modLoc("block/mossy_cragrock_side_2"));
		this.getVariantBuilder(BlockRegistry.MOSSY_CRAGROCK_BOTTOM.get()).forAllStates(state -> {
			if (state.getValue(MossyCragrockBottomBlock.IS_BOTTOM)) {
				return ConfiguredModel.builder().modelFile(this.models().cubeBottomTop("mossy_cragrock_bottom_bottom", this.modLoc("block/mossy_cragrock_side_2"), this.modLoc("block/cragrock"), this.modLoc("block/mossy_cragrock_side_2_2"))).build();
			} else {
				return ConfiguredModel.builder().modelFile(this.models().cubeAll("mossy_cragrock_bottom", this.modLoc("block/mossy_cragrock_side_2_2"))).build();
			}
		});
		this.simpleBlockItem(BlockRegistry.MOSSY_CRAGROCK_BOTTOM);
		this.simpleBlockWithItem(BlockRegistry.PITSTONE);
		this.simpleBlockWithItem(BlockRegistry.LIMESTONE);
		this.simpleBlockWithItem(BlockRegistry.SWAMP_DIRT);
		this.simpleBlockWithItem(BlockRegistry.COARSE_SWAMP_DIRT);
		this.bottomSideTopBlockWithItem(BlockRegistry.SWAMP_GRASS, this.modLoc("block/swamp_grass_side"), this.modLoc("block/swamp_grass_top"), this.modLoc("block/swamp_dirt"));
		this.simpleBlock(BlockRegistry.WISP.get(), this.models().getBuilder("wisp").texture("particle", this.modLoc("block/wisp")));
		this.basicItemTex(BlockRegistry.WISP, true);
		this.simpleBlockWithItem(BlockRegistry.OCTINE_ORE);
		this.simpleBlockWithItem(BlockRegistry.VALONITE_ORE);
		this.simpleBlockWithItem(BlockRegistry.SULFUR_ORE);
		this.simpleBlockWithItem(BlockRegistry.SLIMY_BONE_ORE);
		this.simpleBlockWithItem(BlockRegistry.SCABYST_ORE);
		this.simpleBlockWithItem(BlockRegistry.SYRMORITE_ORE);
		this.simpleBlockWithItem(BlockRegistry.AQUA_MIDDLE_GEM_ORE);
		this.simpleBlockWithItem(BlockRegistry.CRIMSON_MIDDLE_GEM_ORE);
		this.simpleBlockWithItem(BlockRegistry.GREEN_MIDDLE_GEM_ORE);
		this.basicItemTex(BlockRegistry.STALACTITE, false);
		this.basicItemTex(BlockRegistry.LIFE_CRYSTAL_STALACTITE, false);
		this.basicItemTex(BlockRegistry.LIFE_CRYSTAL_ORE_STALACTITE, false);
		this.simpleBlockWithItem(BlockRegistry.SILT);
		this.simpleBlockWithItem(BlockRegistry.FILTERED_SILT);
		this.bottomSideTopBlockWithItem(BlockRegistry.DEAD_GRASS, this.modLoc("block/dead_grass_side"), this.modLoc("block/dead_grass_top"), this.modLoc("block/swamp_dirt"));
		this.simpleBlockWithItem(BlockRegistry.SOLID_TAR);
		//puddle?
		this.simpleBlockWithItem(BlockRegistry.PEARL_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.ANCIENT_REMNANT_BLOCK);
		this.logBlockWithItem(BlockRegistry.WEEDWOOD_LOG); //TODO weedwood has 8 side variants
		this.barkBlockWithItem(BlockRegistry.WEEDWOOD_BARK, this.modLoc("block/weedwood_log_side"));
		this.barkBlockWithItem(BlockRegistry.ROTTEN_BARK, this.modLoc("block/rotten_log_side"));
		this.simpleBlockWithItem(BlockRegistry.SPREADING_ROTTEN_BARK.get(), this.models().getExistingFile(this.blockTexture(BlockRegistry.ROTTEN_BARK.get())));
		getMultipartBuilder(BlockRegistry.RUBBER_LOG.get())
			.part().modelFile(this.models().getExistingFile(this.modLoc("rubber_log"))).addModel().condition(RotatedPillarBlock.AXIS, Direction.Axis.Y).end()
			.part().modelFile(this.models().getExistingFile(this.modLoc("rubber_log"))).rotationX(90).addModel().condition(RotatedPillarBlock.AXIS, Direction.Axis.Z).end()
			.part().modelFile(this.models().getExistingFile(this.modLoc("rubber_log"))).rotationX(90).rotationY(90).addModel().condition(RotatedPillarBlock.AXIS, Direction.Axis.X).end()

			.part().modelFile(this.models().getExistingFile(this.modLoc("rubber_log_top"))).rotationX(90).addModel().condition(PipeBlock.UP, true).end()
			.part().modelFile(this.models().getExistingFile(this.modLoc("rubber_log_bottom"))).rotationX(90).addModel().condition(PipeBlock.DOWN, true).end()
			.part().modelFile(this.models().getExistingFile(this.modLoc("rubber_log_top"))).rotationY(270).addModel().condition(PipeBlock.EAST, true).end()
			.part().modelFile(this.models().getExistingFile(this.modLoc("rubber_log_bottom"))).rotationY(270).addModel().condition(PipeBlock.WEST, true).end()
			.part().modelFile(this.models().getExistingFile(this.modLoc("rubber_log_top"))).addModel().condition(PipeBlock.SOUTH, true).end()
			.part().modelFile(this.models().getExistingFile(this.modLoc("rubber_log_bottom"))).addModel().condition(PipeBlock.NORTH, true).end();
		this.simpleBlockItem(BlockRegistry.RUBBER_LOG);
		this.logBlockWithItem(BlockRegistry.HEARTHGROVE_LOG);
		this.axisBlock((RotatedPillarBlock) BlockRegistry.TARRED_HEARTHGROVE_LOG.get(), this.blockTexture(BlockRegistry.TARRED_HEARTHGROVE_LOG.get()).withSuffix("_side"), this.blockTexture(BlockRegistry.HEARTHGROVE_LOG.get()).withSuffix("_end"));
		this.simpleBlockItem(BlockRegistry.TARRED_HEARTHGROVE_LOG);
		this.barkBlockWithItem(BlockRegistry.HEARTHGROVE_BARK, this.modLoc("block/hearthgrove_log_side"));
		this.barkBlockWithItem(BlockRegistry.TARRED_HEARTHGROVE_BARK, this.modLoc("block/tarred_hearthgrove_log_side"));
		this.nibbletwigLogs();
		this.axisBlock((RotatedPillarBlock) BlockRegistry.SPIRIT_TREE_LOG.get(), this.blockTexture(BlockRegistry.SPIRIT_TREE_LOG.get()).withSuffix("_side"), this.blockTexture(BlockRegistry.SPIRIT_TREE_LOG.get()).withSuffix("_side"));
		this.simpleBlockItem(BlockRegistry.SPIRIT_TREE_LOG);
		this.barkBlockWithItem(BlockRegistry.SPIRIT_TREE_BARK, this.modLoc("block/spirit_tree_log_side"));
		this.simpleBlockWithItem(BlockRegistry.WEEDWOOD);
		this.logBlockWithItem(BlockRegistry.SAP_LOG);
		this.barkBlockWithItem(BlockRegistry.SAP_BARK, this.modLoc("block/sap_log_side"));
		this.simpleBlockWithItem(BlockRegistry.WEEDWOOD_LEAVES);
		this.simpleBlockWithItem(BlockRegistry.SAP_LEAVES);
		this.simpleBlockWithItem(BlockRegistry.RUBBER_TREE_LEAVES);
		this.simpleBlockWithItem(BlockRegistry.HEARTHGROVE_LEAVES);
		this.simpleBlockWithItem(BlockRegistry.NIBBLETWIG_LEAVES);
		this.simpleBlock(BlockRegistry.TOP_SPIRIT_TREE_LEAVES.get(), this.models().withExistingParent(BlockRegistry.TOP_SPIRIT_TREE_LEAVES.getId().getPath(), this.modLoc("block/spirit_tree_leaves")).texture("side", this.blockTexture(BlockRegistry.TOP_SPIRIT_TREE_LEAVES.get())).texture("top", this.modLoc("block/top_spirit_tree_leaves_top")));
		this.basicItemTex(BlockRegistry.TOP_SPIRIT_TREE_LEAVES, true);
		this.simpleBlock(BlockRegistry.MIDDLE_SPIRIT_TREE_LEAVES.get(), this.models().withExistingParent(BlockRegistry.MIDDLE_SPIRIT_TREE_LEAVES.getId().getPath(), this.modLoc("block/spirit_tree_leaves")).texture("side", this.blockTexture(BlockRegistry.MIDDLE_SPIRIT_TREE_LEAVES.get())).texture("top", this.modLoc("block/blank")));
		this.basicItemTex(BlockRegistry.MIDDLE_SPIRIT_TREE_LEAVES, true);
		this.simpleBlock(BlockRegistry.BOTTOM_SPIRIT_TREE_LEAVES.get(), this.models().withExistingParent(BlockRegistry.BOTTOM_SPIRIT_TREE_LEAVES.getId().getPath(), this.modLoc("block/spirit_tree_leaves")).texture("side", this.blockTexture(BlockRegistry.BOTTOM_SPIRIT_TREE_LEAVES.get())).texture("top", this.modLoc("block/blank")));
		this.basicItemTex(BlockRegistry.BOTTOM_SPIRIT_TREE_LEAVES, true);
		this.simpleBlockWithItem(BlockRegistry.WEEDWOOD_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.RUBBER_TREE_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.GIANT_ROOT_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.HEARTHGROVE_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.NIBBLETWIG_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.ANGRY_BETWEENSTONE);
		this.simpleBlockWithItem(BlockRegistry.BETWEENSTONE_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.MIRAGE_BETWEENSTONE_BRICKS.get(), this.models().getExistingFile(this.blockTexture(BlockRegistry.BETWEENSTONE_BRICKS.get())));
		this.simpleBlockWithItem(BlockRegistry.BETWEENSTONE_TILES);
		this.simpleBlockWithItem(BlockRegistry.CHISELED_BETWEENSTONE);
		this.simpleBlockWithItem(BlockRegistry.CHISELED_CRAGROCK);
		this.simpleBlockWithItem(BlockRegistry.CRACKED_CHISELED_CRAGROCK);
		this.simpleBlockWithItem(BlockRegistry.MOSSY_CHISELED_CRAGROCK);
		this.simpleBlockWithItem(BlockRegistry.CHISELED_LIMESTONE);
		this.simpleBlockWithItem(BlockRegistry.CHISELED_PITSTONE);
		this.simpleBlockWithItem(BlockRegistry.CHISELED_SCABYST_1);
		this.simpleBlockWithItem(BlockRegistry.CHISELED_SCABYST_2);
		this.simpleBlockWithItem(BlockRegistry.CHISELED_SCABYST_3);
		this.sidedBlockWithItem(BlockRegistry.DOTTED_SCABYST_PITSTONE, this.modLoc("block/scabyst_pitstone_dotted"), this.modLoc("block/pitstone_bricks"));
		this.sidedBlockWithItem(BlockRegistry.HORIZONTAL_SCABYST_PITSTONE, this.modLoc("block/scabyst_pitstone_horizontal"), this.modLoc("block/pitstone_bricks"));
		this.simpleBlockWithItem(BlockRegistry.SCABYST_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.CRACKED_BETWEENSTONE_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.CRACKED_BETWEENSTONE_TILES);
		this.simpleBlockWithItem(BlockRegistry.CRACKED_LIMESTONE_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.CRAGROCK_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.CRACKED_CRAGROCK_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.MOSSY_CRAGROCK_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.CRAGROCK_TILES);
		this.simpleBlockWithItem(BlockRegistry.CRACKED_CRAGROCK_TILES);
		this.simpleBlockWithItem(BlockRegistry.MOSSY_CRAGROCK_TILES);
		this.simpleBlockWithItem(BlockRegistry.GLOWING_BETWEENSTONE_TILE);
		this.simpleBlockWithItem(BlockRegistry.INACTIVE_GLOWING_SMOOTH_CRAGROCK);
		this.simpleBlockWithItem(BlockRegistry.GLOWING_SMOOTH_CRAGROCK);
		this.simpleBlockWithItem(BlockRegistry.LIMESTONE_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.LIMESTONE_TILES);
		this.simpleBlockWithItem(BlockRegistry.MOSSY_BETWEENSTONE_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.MOSSY_BETWEENSTONE_TILES);
		this.simpleBlockWithItem(BlockRegistry.MOSSY_LIMESTONE_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.MOSSY_SMOOTH_BETWEENSTONE);
		this.simpleBlockWithItem(BlockRegistry.MUD_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.RUBBER_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.PITSTONE_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.PITSTONE_TILES);
		this.simpleBlockWithItem(BlockRegistry.POLISHED_LIMESTONE);
		this.simpleBlockWithItem(BlockRegistry.SMOOTH_BETWEENSTONE);
		this.simpleBlockWithItem(BlockRegistry.SMOOTH_CRAGROCK);
		this.simpleBlockWithItem(BlockRegistry.OCTINE_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.SYRMORITE_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.VALONITE_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.SCABYST_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.WEAK_BETWEENSTONE_TILES);
		this.simpleBlockWithItem(BlockRegistry.WEAK_POLISHED_LIMESTONE);
		this.simpleBlockWithItem(BlockRegistry.WEAK_MOSSY_BETWEENSTONE_TILES);
		this.simpleBlockWithItem(BlockRegistry.WEAK_SMOOTH_CRAGROCK);
		this.simpleBlockRenderTypeAndItem(BlockRegistry.GREEN_DENTROTHYST, "translucent");
		this.simpleBlockRenderTypeAndItem(BlockRegistry.ORANGE_DENTROTHYST, "translucent");
		this.builtinEntityAndItem(BlockRegistry.LOOT_POT_1, this.modLoc("block/particle/loot_pot_1_particle"), 0.625F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.LOOT_POT_2, this.modLoc("block/particle/loot_pot_2_particle"), 0.625F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.LOOT_POT_3, this.modLoc("block/particle/loot_pot_3_particle"), 0.625F, 0.0F);
		//monster spawner
		this.pillarWithItem(BlockRegistry.TEMPLE_PILLAR);
		this.pillarWithItem(BlockRegistry.BETWEENSTONE_PILLAR);
		this.pillarWithItem(BlockRegistry.PITSTONE_PILLAR);
		this.pillarWithItem(BlockRegistry.LIMESTONE_PILLAR);
		this.pillarWithItem(BlockRegistry.CRAGROCK_PILLAR);
		this.builtinEntityAndItem(BlockRegistry.TAR_LOOT_POT_1, this.modLoc("block/solid_tar"), 0.625F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.TAR_LOOT_POT_2, this.modLoc("block/solid_tar"), 0.625F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.TAR_LOOT_POT_3, this.modLoc("block/solid_tar"), 0.625F, 0.0F);
		this.stairBlockWithItem(BlockRegistry.CRAGROCK_STAIRS, BlockRegistry.CRAGROCK);
		this.stairBlockWithItem(BlockRegistry.PITSTONE_STAIRS, BlockRegistry.PITSTONE);
		this.stairBlockWithItem(BlockRegistry.BETWEENSTONE_STAIRS, BlockRegistry.BETWEENSTONE);
		this.stairBlockWithItem(BlockRegistry.BETWEENSTONE_BRICK_STAIRS, BlockRegistry.BETWEENSTONE_BRICKS);
		this.stairBlockWithItem(BlockRegistry.MUD_BRICK_STAIRS, BlockRegistry.MUD_BRICKS);
		this.stairBlockWithItem(BlockRegistry.CRAGROCK_BRICK_STAIRS, BlockRegistry.CRAGROCK_BRICKS);
		this.stairBlockWithItem(BlockRegistry.LIMESTONE_BRICK_STAIRS, BlockRegistry.LIMESTONE_BRICKS);
		this.stairBlockWithItem(BlockRegistry.PITSTONE_BRICK_STAIRS, BlockRegistry.PITSTONE_BRICKS);
		this.stairBlockWithItem(BlockRegistry.LIMESTONE_STAIRS, BlockRegistry.LIMESTONE);
		this.stairBlockWithItem(BlockRegistry.SMOOTH_BETWEENSTONE_STAIRS, BlockRegistry.SMOOTH_BETWEENSTONE);
		this.stairBlockWithItem(BlockRegistry.SMOOTH_CRAGROCK_STAIRS, BlockRegistry.SMOOTH_CRAGROCK);
		this.stairBlockWithItem(BlockRegistry.POLISHED_LIMESTONE_STAIRS, BlockRegistry.POLISHED_LIMESTONE);
		this.stairBlockWithItem(BlockRegistry.MOSSY_BETWEENSTONE_BRICK_STAIRS, BlockRegistry.MOSSY_BETWEENSTONE_BRICKS);
		this.stairBlockWithItem(BlockRegistry.MOSSY_SMOOTH_BETWEENSTONE_STAIRS, BlockRegistry.MOSSY_SMOOTH_BETWEENSTONE);
		this.stairBlockWithItem(BlockRegistry.CRACKED_BETWEENSTONE_BRICK_STAIRS, BlockRegistry.CRACKED_BETWEENSTONE_BRICKS);
		this.stairBlockWithItem(BlockRegistry.SCABYST_BRICK_STAIRS, BlockRegistry.SCABYST_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.SULFUR_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.TEMPLE_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.SMOOTH_PITSTONE);
		this.simpleBlockWithItem(BlockRegistry.MIRE_CORAL_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.DEEP_WATER_CORAL_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.SLIMY_BONE_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.AQUA_MIDDLE_GEM_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.CRIMSON_MIDDLE_GEM_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.GREEN_MIDDLE_GEM_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.COMPOST_BLOCK);
		this.simpleBlockRenderTypeAndItem(BlockRegistry.POLISHED_GREEN_DENTROTHYST, "translucent");
		this.simpleBlockRenderTypeAndItem(BlockRegistry.POLISHED_ORANGE_DENTROTHYST, "translucent");
		this.simpleBlockRenderTypeAndItem(BlockRegistry.SILT_GLASS, "translucent");
		this.paneBlockWithItem(BlockRegistry.SILT_GLASS_PANE, true, "translucent");
		this.paneBlockWithItem(BlockRegistry.LATTICE, false);
		this.paneBlockWithItem(BlockRegistry.FINE_LATTICE, false);
		//filtered glass and pane
		this.paneBlockWithItem(BlockRegistry.POLISHED_ORANGE_DENTROTHYST_PANE, true, "translucent");
		this.paneBlockWithItem(BlockRegistry.POLISHED_GREEN_DENTROTHYST_PANE, true, "translucent");
		//connect amate pane
		this.thickPaneBlockWithItem(BlockRegistry.ROUNDED_AMATE_PAPER_PANE);
		this.thickPaneBlockWithItem(BlockRegistry.SQUARED_AMATE_PAPER_PANE);
		this.stairBlockWithItem(BlockRegistry.SMOOTH_PITSTONE_STAIRS, BlockRegistry.SMOOTH_PITSTONE);
		this.stairBlockWithItem(BlockRegistry.SOLID_TAR_STAIRS, BlockRegistry.SOLID_TAR);
		this.stairBlockWithItem(BlockRegistry.TEMPLE_BRICK_STAIRS, BlockRegistry.TEMPLE_BRICKS);
		this.bottomSideTopBlockWithItem(BlockRegistry.SPIKE_TRAP, this.modLoc("block/polished_limestone"), this.modLoc("block/spike_block_inactive_top"), this.modLoc("block/polished_limestone"));
		this.stairBlockWithItem(BlockRegistry.WEEDWOOD_STAIRS, BlockRegistry.WEEDWOOD_PLANKS);
		this.stairBlockWithItem(BlockRegistry.RUBBER_TREE_STAIRS, BlockRegistry.RUBBER_TREE_PLANKS);
		this.stairBlockWithItem(BlockRegistry.GIANT_ROOT_STAIRS, BlockRegistry.GIANT_ROOT_PLANKS);
		this.stairBlockWithItem(BlockRegistry.HEARTHGROVE_STAIRS, BlockRegistry.HEARTHGROVE_PLANKS);
		this.stairBlockWithItem(BlockRegistry.NIBBLETWIG_STAIRS, BlockRegistry.NIBBLETWIG_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.POSSESSED_BLOCK.get(), this.models().getExistingFile(this.blockTexture(BlockRegistry.BETWEENSTONE_BRICKS.get())));
		//item cage
		//item shelf
		this.simpleBlockWithItem(BlockRegistry.THATCH);
		this.slabBlockWithItem(BlockRegistry.CRAGROCK_SLAB, BlockRegistry.CRAGROCK);
		this.slabBlockWithItem(BlockRegistry.PITSTONE_SLAB, BlockRegistry.PITSTONE);
		this.slabBlockWithItem(BlockRegistry.BETWEENSTONE_SLAB, BlockRegistry.BETWEENSTONE);
		this.slabBlockWithItem(BlockRegistry.SMOOTH_PITSTONE_SLAB, BlockRegistry.SMOOTH_PITSTONE);
		this.slabBlockWithItem(BlockRegistry.SOLID_TAR_SLAB, BlockRegistry.SOLID_TAR);
		this.slabBlockWithItem(BlockRegistry.TEMPLE_BRICK_SLAB, BlockRegistry.TEMPLE_BRICKS);
		this.slabBlockWithItem(BlockRegistry.BETWEENSTONE_BRICK_SLAB, BlockRegistry.BETWEENSTONE_BRICKS);
		this.slabBlockWithItem(BlockRegistry.MUD_BRICK_SLAB, BlockRegistry.MUD_BRICKS);
		this.slabBlockWithItem(BlockRegistry.CRAGROCK_BRICK_SLAB, BlockRegistry.CRAGROCK_BRICKS);
		this.slabBlockWithItem(BlockRegistry.LIMESTONE_BRICK_SLAB, BlockRegistry.LIMESTONE_BRICKS);
		this.slabBlockWithItem(BlockRegistry.PITSTONE_BRICK_SLAB, BlockRegistry.PITSTONE_BRICKS);
		this.slabBlockWithItem(BlockRegistry.LIMESTONE_SLAB, BlockRegistry.LIMESTONE);
		this.slabBlockWithItem(BlockRegistry.SMOOTH_BETWEENSTONE_SLAB, BlockRegistry.SMOOTH_BETWEENSTONE);
		this.slabBlockWithItem(BlockRegistry.SMOOTH_CRAGROCK_SLAB, BlockRegistry.SMOOTH_CRAGROCK);
		this.slabBlockWithItem(BlockRegistry.POLISHED_LIMESTONE_SLAB, BlockRegistry.POLISHED_LIMESTONE);
		this.slabBlockWithItem(BlockRegistry.MOSSY_BETWEENSTONE_BRICK_SLAB, BlockRegistry.MOSSY_BETWEENSTONE_BRICKS);
		this.slabBlockWithItem(BlockRegistry.MOSSY_SMOOTH_BETWEENSTONE_SLAB, BlockRegistry.MOSSY_SMOOTH_BETWEENSTONE);
		this.slabBlockWithItem(BlockRegistry.CRACKED_BETWEENSTONE_BRICK_SLAB, BlockRegistry.CRACKED_BETWEENSTONE_BRICKS);
		this.slabBlockWithItem(BlockRegistry.SCABYST_BRICK_SLAB, BlockRegistry.SCABYST_BRICKS);
		this.slabBlockWithItem(BlockRegistry.WEEDWOOD_SLAB, BlockRegistry.WEEDWOOD_PLANKS);
		this.slabBlockWithItem(BlockRegistry.RUBBER_TREE_SLAB, BlockRegistry.RUBBER_TREE_PLANKS);
		this.slabBlockWithItem(BlockRegistry.GIANT_ROOT_SLAB, BlockRegistry.GIANT_ROOT_PLANKS);
		this.slabBlockWithItem(BlockRegistry.HEARTHGROVE_SLAB, BlockRegistry.HEARTHGROVE_PLANKS);
		this.slabBlockWithItem(BlockRegistry.NIBBLETWIG_SLAB, BlockRegistry.NIBBLETWIG_PLANKS);
		this.slabBlockWithItem(BlockRegistry.MUD_BRICK_SHINGLE_SLAB, BlockRegistry.MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.MUD_BRICK_SHINGLES);
		this.wallBlockWithItem(BlockRegistry.MUD_BRICK_SHINGLE_WALL, BlockRegistry.MUD_BRICK_SHINGLES);
		//TODO CTM for filtered silt
//		this.simpleBlockWithItem(BlockRegistry.DULL_LAVENDER_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.MAROON_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.SHADOW_GREEN_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.CAMELOT_MAGENTA_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.SAFFRON_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.CARIBBEAN_GREEN_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.VIVID_TANGERINE_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.CHAMPAGNE_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.RAISIN_BLACK_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.SUSHI_GREEN_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.ELM_CYAN_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.CADMIUM_GREEN_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.LAVENDER_BLUE_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.BROWN_RUST_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.MIDNIGHT_PURPLE_FILTERED_SILT_GLASS_BLOCK);
//		this.simpleBlockWithItem(BlockRegistry.PEWTER_GREY_FILTERED_SILT_GLASS_BLOCK);
		this.simpleBlockWithItem(BlockRegistry.DULL_LAVENDER_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.MAROON_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.SHADOW_GREEN_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.CAMELOT_MAGENTA_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.SAFFRON_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.CARIBBEAN_GREEN_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.VIVID_TANGERINE_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.CHAMPAGNE_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.RAISIN_BLACK_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.SUSHI_GREEN_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.ELM_CYAN_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.CADMIUM_GREEN_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.LAVENDER_BLUE_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.BROWN_RUST_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.MIDNIGHT_PURPLE_MUD_BRICK_SHINGLES);
		this.simpleBlockWithItem(BlockRegistry.PEWTER_GREY_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.DULL_LAVENDER_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.DULL_LAVENDER_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.MAROON_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.MAROON_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.SHADOW_GREEN_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.SHADOW_GREEN_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.CAMELOT_MAGENTA_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.CAMELOT_MAGENTA_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.SAFFRON_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.SAFFRON_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.CARIBBEAN_GREEN_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.CARIBBEAN_GREEN_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.VIVID_TANGERINE_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.VIVID_TANGERINE_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.CHAMPAGNE_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.CHAMPAGNE_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.RAISIN_BLACK_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.RAISIN_BLACK_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.SUSHI_GREEN_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.SUSHI_GREEN_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.ELM_CYAN_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.ELM_CYAN_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.CADMIUM_GREEN_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.CADMIUM_GREEN_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.LAVENDER_BLUE_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.LAVENDER_BLUE_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.BROWN_RUST_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.BROWN_RUST_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.MIDNIGHT_PURPLE_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.MIDNIGHT_PURPLE_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.PEWTER_GREY_MUD_BRICK_SHINGLE_SLAB, BlockRegistry.PEWTER_GREY_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.DULL_LAVENDER_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.DULL_LAVENDER_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.MAROON_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.MAROON_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.SHADOW_GREEN_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.SHADOW_GREEN_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.CAMELOT_MAGENTA_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.CAMELOT_MAGENTA_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.SAFFRON_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.SAFFRON_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.CARIBBEAN_GREEN_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.CARIBBEAN_GREEN_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.VIVID_TANGERINE_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.VIVID_TANGERINE_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.CHAMPAGNE_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.CHAMPAGNE_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.RAISIN_BLACK_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.RAISIN_BLACK_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.SUSHI_GREEN_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.SUSHI_GREEN_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.ELM_CYAN_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.ELM_CYAN_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.CADMIUM_GREEN_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.CADMIUM_GREEN_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.LAVENDER_BLUE_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.LAVENDER_BLUE_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.BROWN_RUST_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.BROWN_RUST_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.MIDNIGHT_PURPLE_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.MIDNIGHT_PURPLE_MUD_BRICK_SHINGLES);
		this.stairBlockWithItem(BlockRegistry.PEWTER_GREY_MUD_BRICK_SHINGLE_STAIRS, BlockRegistry.PEWTER_GREY_MUD_BRICK_SHINGLES);
		this.slabBlockWithItem(BlockRegistry.THATCH_SLAB, BlockRegistry.THATCH);
		this.slabBlockWithItem(BlockRegistry.SCABYST_BRICK_SLAB, BlockRegistry.SCABYST_BRICKS);
		this.wallBlockWithItem(BlockRegistry.CRAGROCK_WALL, BlockRegistry.CRAGROCK);
		this.wallBlockWithItem(BlockRegistry.PITSTONE_WALL, BlockRegistry.PITSTONE);
		this.wallBlockWithItem(BlockRegistry.BETWEENSTONE_WALL, BlockRegistry.BETWEENSTONE);
		this.wallBlockWithItem(BlockRegistry.SMOOTH_PITSTONE_WALL, BlockRegistry.SMOOTH_PITSTONE);
		this.wallBlockWithItem(BlockRegistry.SOLID_TAR_WALL, BlockRegistry.SOLID_TAR);
		this.wallBlockWithItem(BlockRegistry.TEMPLE_BRICK_WALL, BlockRegistry.TEMPLE_BRICKS);
		this.wallBlockWithItem(BlockRegistry.BETWEENSTONE_BRICK_WALL, BlockRegistry.BETWEENSTONE_BRICKS);
		this.wallBlockWithItem(BlockRegistry.MUD_BRICK_WALL, BlockRegistry.MUD_BRICKS);
		this.wallBlockWithItem(BlockRegistry.CRAGROCK_BRICK_WALL, BlockRegistry.CRAGROCK_BRICKS);
		this.wallBlockWithItem(BlockRegistry.LIMESTONE_BRICK_WALL, BlockRegistry.LIMESTONE_BRICKS);
		this.wallBlockWithItem(BlockRegistry.PITSTONE_BRICK_WALL, BlockRegistry.PITSTONE_BRICKS);
		this.wallBlockWithItem(BlockRegistry.LIMESTONE_WALL, BlockRegistry.LIMESTONE);
		this.wallBlockWithItem(BlockRegistry.SMOOTH_BETWEENSTONE_WALL, BlockRegistry.SMOOTH_BETWEENSTONE);
		this.wallBlockWithItem(BlockRegistry.SMOOTH_CRAGROCK_WALL, BlockRegistry.SMOOTH_CRAGROCK);
		this.wallBlockWithItem(BlockRegistry.POLISHED_LIMESTONE_WALL, BlockRegistry.POLISHED_LIMESTONE);
		this.wallBlockWithItem(BlockRegistry.MOSSY_BETWEENSTONE_BRICK_WALL, BlockRegistry.MOSSY_BETWEENSTONE_BRICKS);
		this.wallBlockWithItem(BlockRegistry.MOSSY_SMOOTH_BETWEENSTONE_WALL, BlockRegistry.MOSSY_SMOOTH_BETWEENSTONE);
		this.wallBlockWithItem(BlockRegistry.CRACKED_BETWEENSTONE_BRICK_WALL, BlockRegistry.CRACKED_BETWEENSTONE_BRICKS);
		this.wallBlockWithItem(BlockRegistry.SCABYST_BRICK_WALL, BlockRegistry.SCABYST_BRICKS);
		this.fenceBlockWithItem(BlockRegistry.WEEDWOOD_FENCE, BlockRegistry.WEEDWOOD_PLANKS);
		this.fenceBlock((FenceBlock) BlockRegistry.WEEDWOOD_LOG_FENCE.get(), this.modLoc("block/weedwood_log_side"));
		this.itemModels().fenceInventory(BlockRegistry.WEEDWOOD_LOG_FENCE.getId().getPath(), this.modLoc("block/weedwood_log_side"));
		this.fenceBlockWithItem(BlockRegistry.RUBBER_TREE_FENCE, BlockRegistry.RUBBER_TREE_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.GIANT_ROOT_FENCE, BlockRegistry.GIANT_ROOT_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.HEARTHGROVE_FENCE, BlockRegistry.HEARTHGROVE_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.NIBBLETWIG_FENCE, BlockRegistry.NIBBLETWIG_PLANKS);
		this.gateBlockWithItem(BlockRegistry.WEEDWOOD_FENCE_GATE, BlockRegistry.WEEDWOOD_PLANKS);
		this.fenceGateBlock((FenceGateBlock) BlockRegistry.WEEDWOOD_LOG_FENCE_GATE.get(), this.modLoc("block/weedwood_log_side"));
		this.itemModels().fenceInventory(BlockRegistry.WEEDWOOD_LOG_FENCE_GATE.getId().getPath(), this.modLoc("block/weedwood_log_side"));
		this.gateBlockWithItem(BlockRegistry.RUBBER_TREE_FENCE_GATE, BlockRegistry.RUBBER_TREE_PLANKS);
		this.gateBlockWithItem(BlockRegistry.GIANT_ROOT_FENCE_GATE, BlockRegistry.GIANT_ROOT_PLANKS);
		this.gateBlockWithItem(BlockRegistry.HEARTHGROVE_FENCE_GATE, BlockRegistry.HEARTHGROVE_PLANKS);
		this.gateBlockWithItem(BlockRegistry.NIBBLETWIG_FENCE_GATE, BlockRegistry.NIBBLETWIG_PLANKS);
		this.plateBlockWithItem(BlockRegistry.WEEDWOOD_PRESSURE_PLATE, BlockRegistry.WEEDWOOD_PLANKS);
		this.plateBlockWithItem(BlockRegistry.BETWEENSTONE_PRESSURE_PLATE, BlockRegistry.BETWEENSTONE);
		this.plateBlockWithItem(BlockRegistry.SYRMORITE_PRESSURE_PLATE, BlockRegistry.SYRMORITE_BLOCK);
		this.buttonBlockWithItem(BlockRegistry.WEEDWOOD_BUTTON, BlockRegistry.WEEDWOOD_PLANKS);
		this.buttonBlockWithItem(BlockRegistry.BETWEENSTONE_BUTTON, BlockRegistry.BETWEENSTONE);
		this.horizontalBlock(BlockRegistry.WEEDWOOD_LADDER.get(), this.models().withExistingParent("weedwood_ladder", this.mcLoc("block/ladder")).texture("texture", this.modLoc("block/weedwood_ladder")).renderType("cutout"));
		this.basicItemTex(BlockRegistry.WEEDWOOD_LADDER, true);
		this.getVariantBuilder(BlockRegistry.WEEDWOOD_LEVER.get()).forAllStates(state -> {
			ModelFile lever = this.models().withExistingParent("weedwood_lever", this.mcLoc("block/lever_on")).ao(false).texture("lever", this.modLoc("block/weedwood_lever")).texture("base", "block/weedwood_log_side").texture("particle", "block/weedwood_log_side");
			ModelFile lever_on = this.models().withExistingParent("weedwood_lever_powered", this.mcLoc("block/lever")).ao(false).texture("lever", this.modLoc("block/weedwood_lever")).texture("base", "block/weedwood_log_side").texture("particle", "block/weedwood_log_side");
			Direction facing = state.getValue(LeverBlock.FACING);
			AttachFace face = state.getValue(LeverBlock.FACE);
			boolean powered = state.getValue(LeverBlock.POWERED);

			return ConfiguredModel.builder()
				.modelFile(powered ? lever_on : lever)
				.rotationX(face == AttachFace.FLOOR ? 0 : (face == AttachFace.WALL ? 90 : 180))
				.rotationY((int) (face == AttachFace.CEILING ? facing : facing.getOpposite()).toYRot())
				.build();
		});
		this.basicItemTex(BlockRegistry.WEEDWOOD_LEVER, true);
		this.builtinEntityAndItem(BlockRegistry.MUD_LOOT_POT_1, this.modLoc("block/compacted_mud"), 0.625F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.MUD_LOOT_POT_2, this.modLoc("block/compacted_mud"), 0.625F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.MUD_LOOT_POT_3, this.modLoc("block/compacted_mud"), 0.625F, 0.0F);
		this.simpleBlockWithItem(BlockRegistry.WORM_PILLAR);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_1);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_2);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_3);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_4);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_5);
		this.simpleBlockWithItem(BlockRegistry.WORM_PILLAR_TOP);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_TOP_1);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_TOP_2);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_TOP_3);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_TOP_4);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_WORM_PILLAR_TOP_5);
		this.simpleBlockWithItem(BlockRegistry.COMPACTED_MUD);
		this.simpleBlockWithItem(BlockRegistry.MUD_TILES);
//		this.simpleBlockWithItem(BlockRegistry.DECAYED_MUD_TILES);
		this.simpleBlockWithItem(BlockRegistry.CRACKED_MUD_TILES);
//		this.simpleBlockWithItem(BlockRegistry.CRACKED_DECAYED_MUD_TILES);
		//puffshroom
		this.simpleBlockWithItem(BlockRegistry.CARVED_MUD_BRICKS);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_CARVED_MUD_BRICKS_1);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_CARVED_MUD_BRICKS_2);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_CARVED_MUD_BRICKS_3);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_CARVED_MUD_BRICKS_4);
		this.simpleBlockWithItem(BlockRegistry.CARVED_MUD_BRICK_EDGE);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_CARVED_MUD_BRICK_EDGE_1);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_CARVED_MUD_BRICK_EDGE_2);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_CARVED_MUD_BRICK_EDGE_3);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_CARVED_MUD_BRICK_EDGE_4);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICKS_1);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICKS_2);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICKS_3);
		this.simpleBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICKS_4);
		this.stairBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICK_STAIRS_1, BlockRegistry.SLUDGY_MUD_BRICKS_1);
		this.stairBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICK_STAIRS_2, BlockRegistry.SLUDGY_MUD_BRICKS_2);
		this.stairBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICK_STAIRS_3, BlockRegistry.SLUDGY_MUD_BRICKS_3);
		this.stairBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICK_STAIRS_4, BlockRegistry.SLUDGY_MUD_BRICKS_4);
		this.slabBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICK_SLAB_1, BlockRegistry.SLUDGY_MUD_BRICKS_1);
		this.slabBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICK_SLAB_2, BlockRegistry.SLUDGY_MUD_BRICKS_2);
		this.slabBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICK_SLAB_3, BlockRegistry.SLUDGY_MUD_BRICKS_3);
		this.slabBlockWithItem(BlockRegistry.SLUDGY_MUD_BRICK_SLAB_4, BlockRegistry.SLUDGY_MUD_BRICKS_4);
		this.builtinEntityAndItem(BlockRegistry.LOOT_URN_1, this.modLoc("block/particle/loot_urn_particle"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.LOOT_URN_2, this.modLoc("block/particle/loot_urn_particle"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.LOOT_URN_3, this.modLoc("block/particle/loot_urn_particle"), 0.75F, 0.0F);
		//rotten beams
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_1, this.modLoc("block/rotten_bark_carved_1"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_2, this.modLoc("block/rotten_bark_carved_2"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_3, this.modLoc("block/rotten_bark_carved_3"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_4, this.modLoc("block/rotten_bark_carved_4"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_5, this.modLoc("block/rotten_bark_carved_5"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_6, this.modLoc("block/rotten_bark_carved_6"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_7, this.modLoc("block/rotten_bark_carved_7"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_8, this.modLoc("block/rotten_bark_carved_8"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_9, this.modLoc("block/rotten_bark_carved_9"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_10, this.modLoc("block/rotten_bark_carved_10"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_11, this.modLoc("block/rotten_bark_carved_11"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_12, this.modLoc("block/rotten_bark_carved_12"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_13, this.modLoc("block/rotten_bark_carved_13"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_14, this.modLoc("block/rotten_bark_carved_14"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_15, this.modLoc("block/rotten_bark_carved_15"), this.modLoc("block/rotten_bark_rotated"));
		this.sidedBlockWithItem(BlockRegistry.CARVED_ROTTEN_BARK_16, this.modLoc("block/rotten_bark_carved_16"), this.modLoc("block/rotten_bark_rotated"));
		this.simpleBlockRenderTypeAndItem(BlockRegistry.MUD_ENERGY_BARRIER, "translucent");
		this.bottomSideTopBlockWithItem(BlockRegistry.MUD_BRICK_SPIKE_TRAP, this.modLoc("block/mud_bricks"), this.modLoc("block/mud_brick_spike_block_inactive_top"), this.modLoc("block/mud_bricks"));
		this.bottomSideTopBlockWithItem(BlockRegistry.MUD_TILES_SPIKE_TRAP, this.modLoc("block/mud_tiles"), this.modLoc("block/mud_tiles_spike_block_inactive_top"), this.modLoc("block/mud_tiles"));
		//compacted mud slope
		this.slabBlockWithItem(BlockRegistry.COMPACTED_MUD_SLAB, BlockRegistry.COMPACTED_MUD);
		this.simpleBlockWithItem(BlockRegistry.COMPACTED_MUD_MIRAGE.get(), this.models().getExistingFile(this.blockTexture(BlockRegistry.COMPACTED_MUD.get())));
		this.simpleBlockWithItem(BlockRegistry.ROTTEN_PLANKS);
		this.slabBlockWithItem(BlockRegistry.ROTTEN_SLAB, BlockRegistry.ROTTEN_PLANKS);
		this.stairBlockWithItem(BlockRegistry.ROTTEN_STAIRS, BlockRegistry.ROTTEN_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.ROTTEN_FENCE, BlockRegistry.ROTTEN_PLANKS);
		this.gateBlockWithItem(BlockRegistry.ROTTEN_FENCE_GATE, BlockRegistry.ROTTEN_PLANKS);
		this.simpleBlockRenderTypeAndItem(BlockRegistry.BULB_CAPPED_MUSHROOM_CAP, "translucent");
		this.logBlockWithItem(BlockRegistry.BULB_CAPPED_MUSHROOM_STALK);
		this.getVariantBuilder(BlockRegistry.SHELF_FUNGUS.get()).forAllStates(state -> {
			if (state.getValue(ShelfFungusBlock.TOP)) {
				return ConfiguredModel.builder().modelFile(this.models().cubeBottomTop("shelf_fungus", this.modLoc("block/shelf_fungus_side"), this.modLoc("block/shelf_fungus_bottom"), this.modLoc("block/shelf_fungus_top"))).build();
			} else {
				return ConfiguredModel.builder().modelFile(this.models().cubeColumn("shelf_fungus_no_top", this.modLoc("block/shelf_fungus_side_2"), this.modLoc("block/shelf_fungus_bottom"))).build();
			}
		});
		this.simpleBlockItem(BlockRegistry.SHELF_FUNGUS);
		this.basicItemTex(BlockRegistry.ROOT, false);
		this.simpleBlock(BlockRegistry.GIANT_ROOT.get(), this.models().cubeAll("giant_root", this.modLoc("block/root_bottom")));
		this.simpleBlockItem(BlockRegistry.GIANT_ROOT);
		this.simpleBlockWithItem(BlockRegistry.PURIFIED_SWAMP_DIRT);
		//dug dirt and grass
		this.simpleBlockRenderTypeAndItem(BlockRegistry.BLACK_ICE, "translucent");
		this.axisBlock((RotatedPillarBlock) BlockRegistry.PORTAL_LOG.get(), this.modLoc("block/portal_log_side"), this.modLoc("block/weedwood_log_end"));
		this.simpleBlockItem(BlockRegistry.PORTAL_LOG);
		this.portalFrame(BlockRegistry.PORTAL_FRAME_BOTTOM);
		this.portalFrame(BlockRegistry.PORTAL_FRAME_BOTTOM_LEFT);
		this.portalFrame(BlockRegistry.PORTAL_FRAME_BOTTOM_RIGHT);
		this.portalFrame(BlockRegistry.PORTAL_FRAME_LEFT);
		this.portalFrame(BlockRegistry.PORTAL_FRAME_RIGHT);
		this.portalFrame(BlockRegistry.PORTAL_FRAME_TOP);
		this.portalFrame(BlockRegistry.PORTAL_FRAME_TOP_LEFT);
		this.portalFrame(BlockRegistry.PORTAL_FRAME_TOP_RIGHT);
		this.builtinEntityAndItem(BlockRegistry.DRUID_ALTAR, this.modLoc("block/particle/druid_altar_particle"), 0.325F, -1.25F);
		this.builtinEntityAndItem(BlockRegistry.PURIFIER, this.modLoc("block/particle/purifier_particle"), 0.625F, 0.0F);
		this.simpleBlock(BlockRegistry.WEEDWOOD_CRAFTING_TABLE.get(), this.models().cube("weedwood_crafting_table", this.modLoc("block/weedwood_planks"), this.modLoc("block/weedwood_workbench_top"), this.modLoc("block/weedwood_workbench_front"), this.modLoc("block/weedwood_workbench"), this.modLoc("block/weedwood_workbench"), this.modLoc("block/weedwood_workbench_front")));
		this.simpleBlockItem(BlockRegistry.WEEDWOOD_CRAFTING_TABLE);
		this.simpleBlock(BlockRegistry.WEEDWOOD_JUKEBOX.get(), this.models().cubeTop("weedwood_jukebox", this.modLoc("block/weedwood_jukebox_side"), this.modLoc("block/weedwood_jukebox_top")));
		this.simpleBlockItem(BlockRegistry.WEEDWOOD_JUKEBOX);
		this.simpleBlockRenderTypeAndItem(BlockRegistry.ENERGY_BARRIER, "translucent");
		this.torchBlockWithItem(BlockRegistry.SULFUR_TORCH, BlockRegistry.SULFUR_WALL_TORCH);
		this.torchBlockWithItem(BlockRegistry.EXTINGUISHED_SULFUR_TORCH, BlockRegistry.EXTINGUISHED_SULFUR_WALL_TORCH);
		this.trapdoorBlockWithItem(BlockRegistry.WEEDWOOD_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.RUBBER_TREE_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.SYRMORITE_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.GIANT_ROOT_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.HEARTHGROVE_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.NIBBLETWIG_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.ROTTEN_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.TREATED_WEEDWOOD_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.TREATED_RUBBER_TREE_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.TREATED_GIANT_ROOT_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.TREATED_HEARTHGROVE_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.TREATED_NIBBLETWIG_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.TREATED_ROTTEN_TRAPDOOR);
		this.trapdoorBlockWithItem(BlockRegistry.SCABYST_TRAPDOOR);
		this.basicItemTex(BlockRegistry.MUD_FLOWER_POT, false);
		this.basicItemTex(BlockRegistry.MUD_FLOWER_POT_CANDLE, false);
		this.builtinEntityAndItem(BlockRegistry.CENSER, this.modLoc("block/particle/censer_particle"), 0.625F, 0.0F);
		this.torchBlockWithItem(BlockRegistry.DAMP_TORCH, BlockRegistry.DAMP_WALL_TORCH);
		this.builtinEntityAndItem(BlockRegistry.DEEPMAN_SIMULACRUM_1, this.modLoc("block/smooth_betweenstone"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.DEEPMAN_SIMULACRUM_2, this.modLoc("block/smooth_betweenstone"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.DEEPMAN_SIMULACRUM_3, this.modLoc("block/smooth_betweenstone"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.LAKE_CAVERN_SIMULACRUM_1, this.modLoc("block/pitstone"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.LAKE_CAVERN_SIMULACRUM_2, this.modLoc("block/pitstone"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.LAKE_CAVERN_SIMULACRUM_3, this.modLoc("block/pitstone"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.ROOTMAN_SIMULACRUM_1, this.modLoc("block/root_bottom"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.ROOTMAN_SIMULACRUM_2, this.modLoc("block/root_bottom"), 0.75F, 0.0F);
		this.builtinEntityAndItem(BlockRegistry.ROOTMAN_SIMULACRUM_3, this.modLoc("block/root_bottom"), 0.75F, 0.0F);
		this.carpetBlockWithItem(BlockRegistry.REED_MAT);
		this.simpleBlockWithItem(BlockRegistry.LYESTONE.get(), this.models().getExistingFile(this.blockTexture(BlockRegistry.LIMESTONE.get())));
		this.simpleBlockRenderTypeAndItem(BlockRegistry.MIST_BRIDGE, "translucent");
		this.simpleBlockRenderTypeAndItem(BlockRegistry.SHADOW_WALKER, "translucent");
		this.simpleBlockWithItem(BlockRegistry.DULL_LAVENDER_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.MAROON_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.SHADOW_GREEN_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.CAMELOT_MAGENTA_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.SAFFRON_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.CARIBBEAN_GREEN_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.VIVID_TANGERINE_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.CHAMPAGNE_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.RAISIN_BLACK_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.SUSHI_GREEN_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.ELM_CYAN_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.CADMIUM_GREEN_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.LAVENDER_BLUE_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.BROWN_RUST_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.MIDNIGHT_PURPLE_SAMITE);
		this.simpleBlockWithItem(BlockRegistry.PEWTER_GREY_SAMITE);
		this.samitePanel(BlockRegistry.DULL_LAVENDER_SAMITE_CANVAS_PANEL, BlockRegistry.DULL_LAVENDER_SAMITE);
		this.samitePanel(BlockRegistry.MAROON_SAMITE_CANVAS_PANEL, BlockRegistry.MAROON_SAMITE);
		this.samitePanel(BlockRegistry.SHADOW_GREEN_SAMITE_CANVAS_PANEL, BlockRegistry.SHADOW_GREEN_SAMITE);
		this.samitePanel(BlockRegistry.CAMELOT_MAGENTA_SAMITE_CANVAS_PANEL, BlockRegistry.CAMELOT_MAGENTA_SAMITE);
		this.samitePanel(BlockRegistry.SAFFRON_SAMITE_CANVAS_PANEL, BlockRegistry.SAFFRON_SAMITE);
		this.samitePanel(BlockRegistry.CARIBBEAN_GREEN_SAMITE_CANVAS_PANEL, BlockRegistry.CARIBBEAN_GREEN_SAMITE);
		this.samitePanel(BlockRegistry.VIVID_TANGERINE_SAMITE_CANVAS_PANEL, BlockRegistry.VIVID_TANGERINE_SAMITE);
		this.samitePanel(BlockRegistry.CHAMPAGNE_SAMITE_CANVAS_PANEL, BlockRegistry.CHAMPAGNE_SAMITE);
		this.samitePanel(BlockRegistry.RAISIN_BLACK_SAMITE_CANVAS_PANEL, BlockRegistry.RAISIN_BLACK_SAMITE);
		this.samitePanel(BlockRegistry.SUSHI_GREEN_SAMITE_CANVAS_PANEL, BlockRegistry.SUSHI_GREEN_SAMITE);
		this.samitePanel(BlockRegistry.ELM_CYAN_SAMITE_CANVAS_PANEL, BlockRegistry.ELM_CYAN_SAMITE);
		this.samitePanel(BlockRegistry.CADMIUM_GREEN_SAMITE_CANVAS_PANEL, BlockRegistry.CADMIUM_GREEN_SAMITE);
		this.samitePanel(BlockRegistry.LAVENDER_BLUE_SAMITE_CANVAS_PANEL, BlockRegistry.LAVENDER_BLUE_SAMITE);
		this.samitePanel(BlockRegistry.BROWN_RUST_SAMITE_CANVAS_PANEL, BlockRegistry.BROWN_RUST_SAMITE);
		this.samitePanel(BlockRegistry.MIDNIGHT_PURPLE_SAMITE_CANVAS_PANEL, BlockRegistry.MIDNIGHT_PURPLE_SAMITE);
		this.samitePanel(BlockRegistry.PEWTER_GREY_SAMITE_CANVAS_PANEL, BlockRegistry.PEWTER_GREY_SAMITE);
		this.carpetBlockWithItem(BlockRegistry.DULL_LAVENDER_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.MAROON_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.SHADOW_GREEN_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.CAMELOT_MAGENTA_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.SAFFRON_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.CARIBBEAN_GREEN_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.VIVID_TANGERINE_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.CHAMPAGNE_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.RAISIN_BLACK_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.SUSHI_GREEN_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.ELM_CYAN_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.CADMIUM_GREEN_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.LAVENDER_BLUE_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.BROWN_RUST_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.MIDNIGHT_PURPLE_REED_MAT);
		this.carpetBlockWithItem(BlockRegistry.PEWTER_GREY_REED_MAT);
		this.simpleBlockWithItem(BlockRegistry.TREATED_WEEDWOOD_PLANKS);
		this.slabBlockWithItem(BlockRegistry.TREATED_WEEDWOOD_SLAB, BlockRegistry.TREATED_WEEDWOOD_PLANKS);
		this.stairBlockWithItem(BlockRegistry.TREATED_WEEDWOOD_STAIRS, BlockRegistry.TREATED_WEEDWOOD_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.TREATED_WEEDWOOD_FENCE, BlockRegistry.TREATED_WEEDWOOD_PLANKS);
		this.gateBlockWithItem(BlockRegistry.TREATED_WEEDWOOD_FENCE_GATE, BlockRegistry.TREATED_WEEDWOOD_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.TREATED_RUBBER_TREE_PLANKS);
		this.slabBlockWithItem(BlockRegistry.TREATED_RUBBER_TREE_SLAB, BlockRegistry.TREATED_RUBBER_TREE_PLANKS);
		this.stairBlockWithItem(BlockRegistry.TREATED_RUBBER_TREE_STAIRS, BlockRegistry.TREATED_RUBBER_TREE_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.TREATED_RUBBER_TREE_FENCE, BlockRegistry.TREATED_RUBBER_TREE_PLANKS);
		this.gateBlockWithItem(BlockRegistry.TREATED_RUBBER_TREE_FENCE_GATE, BlockRegistry.TREATED_RUBBER_TREE_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.TREATED_GIANT_ROOT_PLANKS);
		this.slabBlockWithItem(BlockRegistry.TREATED_GIANT_ROOT_SLAB, BlockRegistry.TREATED_GIANT_ROOT_PLANKS);
		this.stairBlockWithItem(BlockRegistry.TREATED_GIANT_ROOT_STAIRS, BlockRegistry.TREATED_GIANT_ROOT_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.TREATED_GIANT_ROOT_FENCE, BlockRegistry.TREATED_GIANT_ROOT_PLANKS);
		this.gateBlockWithItem(BlockRegistry.TREATED_GIANT_ROOT_FENCE_GATE, BlockRegistry.TREATED_GIANT_ROOT_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.TREATED_HEARTHGROVE_PLANKS);
		this.slabBlockWithItem(BlockRegistry.TREATED_HEARTHGROVE_SLAB, BlockRegistry.TREATED_HEARTHGROVE_PLANKS);
		this.stairBlockWithItem(BlockRegistry.TREATED_HEARTHGROVE_STAIRS, BlockRegistry.TREATED_HEARTHGROVE_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.TREATED_HEARTHGROVE_FENCE, BlockRegistry.TREATED_HEARTHGROVE_PLANKS);
		this.gateBlockWithItem(BlockRegistry.TREATED_HEARTHGROVE_FENCE_GATE, BlockRegistry.TREATED_HEARTHGROVE_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.TREATED_NIBBLETWIG_PLANKS);
		this.slabBlockWithItem(BlockRegistry.TREATED_NIBBLETWIG_SLAB, BlockRegistry.TREATED_NIBBLETWIG_PLANKS);
		this.stairBlockWithItem(BlockRegistry.TREATED_NIBBLETWIG_STAIRS, BlockRegistry.TREATED_NIBBLETWIG_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.TREATED_NIBBLETWIG_FENCE, BlockRegistry.TREATED_NIBBLETWIG_PLANKS);
		this.gateBlockWithItem(BlockRegistry.TREATED_NIBBLETWIG_FENCE_GATE, BlockRegistry.TREATED_NIBBLETWIG_PLANKS);
		this.simpleBlockWithItem(BlockRegistry.TREATED_ROTTEN_PLANKS);
		this.slabBlockWithItem(BlockRegistry.TREATED_ROTTEN_SLAB, BlockRegistry.TREATED_ROTTEN_PLANKS);
		this.stairBlockWithItem(BlockRegistry.TREATED_ROTTEN_STAIRS, BlockRegistry.TREATED_ROTTEN_PLANKS);
		this.fenceBlockWithItem(BlockRegistry.TREATED_ROTTEN_FENCE, BlockRegistry.TREATED_ROTTEN_PLANKS);
		this.gateBlockWithItem(BlockRegistry.TREATED_ROTTEN_FENCE_GATE, BlockRegistry.TREATED_ROTTEN_PLANKS);
		this.doorBlockWithItem(BlockRegistry.WEEDWOOD_DOOR);
		this.doorBlockWithItem(BlockRegistry.RUBBER_TREE_DOOR);
		this.doorBlockWithItem(BlockRegistry.GIANT_ROOT_DOOR);
		this.doorBlockWithItem(BlockRegistry.HEARTHGROVE_DOOR);
		this.doorBlockWithItem(BlockRegistry.NIBBLETWIG_DOOR);
		this.doorBlockWithItem(BlockRegistry.ROTTEN_DOOR);
		this.doorBlockWithItem(BlockRegistry.SYRMORITE_DOOR);
		this.doorBlockWithItem(BlockRegistry.SCABYST_DOOR);
		this.doorBlockWithItem(BlockRegistry.TREATED_WEEDWOOD_DOOR);
		this.doorBlockWithItem(BlockRegistry.TREATED_RUBBER_TREE_DOOR);
		this.doorBlockWithItem(BlockRegistry.TREATED_GIANT_ROOT_DOOR);
		this.doorBlockWithItem(BlockRegistry.TREATED_HEARTHGROVE_DOOR);
		this.doorBlockWithItem(BlockRegistry.TREATED_NIBBLETWIG_DOOR);
		this.doorBlockWithItem(BlockRegistry.TREATED_ROTTEN_DOOR);
		this.builtinEntity(BlockRegistry.WEEDWOOD_SIGN, this.modLoc("block/weedwood_planks"));
		this.builtinEntity(BlockRegistry.WEEDWOOD_WALL_SIGN, this.modLoc("block/weedwood_planks"));
		this.basicItemTex(BlockRegistry.WEEDWOOD_SIGN, false);
		this.builtinEntity(BlockRegistry.MOSS_BED, this.modLoc("block/moss"));
		this.basicItemTex(BlockRegistry.MOSS_BED, false);
	}

	private void druidStone(DeferredBlock<Block> stone) {
		ModelFile inactive = this.models().orientable(stone.getId().toString(), this.blockTexture(Blocks.STONE), this.blockTexture(stone.get()), this.blockTexture(Blocks.STONE));
		ModelFile active = this.models().orientable(stone.getId() + "_active", this.blockTexture(Blocks.STONE), this.blockTexture(stone.get()), this.blockTexture(Blocks.STONE));

		this.getVariantBuilder(stone.get()).forAllStates(state -> ConfiguredModel.builder().rotationY((int) state.getValue(DruidStoneBlock.FACING).toYRot()).modelFile(state.getValue(DruidStoneBlock.ACTIVE) ? active : inactive).build());
		this.simpleBlockItem(stone);
	}

	private void portalFrame(DeferredBlock<Block> frame) {
		this.getVariantBuilder(frame.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(this.models().cube(frame.getId().getPath(), this.modLoc("block/portal_log_side"), this.modLoc("block/portal_log_side"), this.blockTexture(frame.get()), this.blockTexture(frame.get()), this.modLoc("block/portal_log_side"), this.modLoc("block/portal_log_side"))).rotationY(state.getValue(PortalFrameBlock.AXIS) == Direction.Axis.Z ? 0 : 90).build());
		this.simpleBlockItem(frame);
	}

	private void sidedBlockWithItem(DeferredBlock<Block> block, ResourceLocation side, ResourceLocation end) {
		this.simpleBlock(block.get(), this.models().cubeColumn(block.getId().getPath(), side, end));
		this.simpleBlockItem(block);
	}

	private void bottomSideTopBlockWithItem(DeferredBlock<Block> block, ResourceLocation side, ResourceLocation top, ResourceLocation bottom) {
		this.simpleBlock(block.get(), this.models().cubeBottomTop(block.getId().getPath(), side, bottom, top));
		this.simpleBlockItem(block);
	}

	public void logBlockWithItem(DeferredBlock<Block> block) {
		this.axisBlock((RotatedPillarBlock) block.get());
		this.simpleBlockItem(block);
	}

	public void barkBlockWithItem(DeferredBlock<Block> block, ResourceLocation texture) {
		this.axisBlock((RotatedPillarBlock) block.get(), this.models().cubeColumn(block.getId().toString(), texture, texture), this.models().cubeColumnHorizontal(block.getId() + "_horizontal", texture, texture));
		this.simpleBlockItem(block);
	}

	public void slabBlockWithItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
		this.slabBlock((SlabBlock) block.get(), this.blockTexture(baseBlock.get()), this.blockTexture(baseBlock.get()));
		this.simpleBlockItem(block);
	}

	public void stairBlockWithItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
		this.stairsBlock((StairBlock) block.get(), this.blockTexture(baseBlock.get()));
		this.simpleBlockItem(block);
	}

	public void wallBlockWithItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
		this.wallBlock((WallBlock) block.get(), this.blockTexture(baseBlock.get()));
		this.itemModels().wallInventory(block.getId().toString(), this.blockTexture(baseBlock.get()));
	}

	public void fenceBlockWithItem(DeferredBlock<Block> fence, DeferredBlock<Block> planks) {
		this.fenceBlock((FenceBlock) fence.get(), this.blockTexture(planks.get()));
		this.itemModels().fenceInventory(fence.getId().getPath(), this.blockTexture(planks.get()));
	}

	public void gateBlockWithItem(DeferredBlock<Block> gate, DeferredBlock<Block> planks) {
		this.fenceGateBlock((FenceGateBlock) gate.get(), this.blockTexture(planks.get()));
		this.simpleBlockItem(gate);
	}

	public void plateBlockWithItem(DeferredBlock<Block> plate, DeferredBlock<Block> planks) {
		this.pressurePlateBlock((PressurePlateBlock) plate.get(), this.blockTexture(planks.get()));
		this.simpleBlockItem(plate);
	}

	public void buttonBlockWithItem(DeferredBlock<Block> button, DeferredBlock<Block> planks) {
		this.buttonBlock((ButtonBlock) button.get(), this.blockTexture(planks.get()));
		this.itemModels().buttonInventory(button.getId().getPath(), this.blockTexture(planks.get()));
	}

	public void trapdoorBlockWithItem(DeferredBlock<Block> trapdoor) {
		this.trapdoorBlock((TrapDoorBlock) trapdoor.get(), this.blockTexture(trapdoor.get()), true);
		this.itemModels().trapdoorBottom(trapdoor.getId().getPath(), this.blockTexture(trapdoor.get()));
	}

	public void doorBlockWithItem(DeferredBlock<Block> door) {
		this.doorBlock((DoorBlock) door.get(), this.blockTexture(door.get()).withSuffix("_bottom"), this.blockTexture(door.get()).withSuffix("_top"));
		this.basicItemTex(door, false);
	}

	public void torchBlockWithItem(DeferredBlock<Block> block, DeferredBlock<Block> wall) {
		ModelFile torch = models().torch(block.getId().getPath(), this.blockTexture(block.get())).renderType("cutout");
		ModelFile torchwall = models().torchWall(wall.getId().getPath(), this.blockTexture(block.get())).renderType("cutout");
		this.simpleBlock(block.get(), torch);
		this.getVariantBuilder(wall.get()).forAllStates(state ->
			ConfiguredModel.builder()
				.modelFile(torchwall)
				.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
				.build());
		this.basicItemTex(block, true);
	}

	public void carpetBlockWithItem(DeferredBlock<Block> trapdoor) {
		this.simpleBlock(trapdoor.get(), this.models().carpet(trapdoor.getId().getPath(), this.blockTexture(trapdoor.get())));
		this.simpleBlockItem(trapdoor);
	}

	public void paneBlockWithItem(DeferredBlock<Block> pane, boolean uniqueSideTex, String renderType) {
		this.paneBlockWithRenderType((IronBarsBlock) pane.get(), this.modLoc("block/" + pane.getId().getPath().replace("_pane", "")), this.blockTexture(pane.get()).withSuffix(uniqueSideTex ? "_top" : ""), renderType);
		this.itemModels().withExistingParent(pane.getId().toString(), new ModelFile.UncheckedModelFile("item/generated").getLocation()).texture("layer0", this.modLoc("block/" + pane.getId().getPath().replace("_pane", "")));
	}

	public void paneBlockWithItem(DeferredBlock<Block> pane, boolean uniqueSideTex) {
		this.paneBlock((IronBarsBlock) pane.get(), this.modLoc("block/" + pane.getId().getPath().replace("_pane", "")), this.blockTexture(pane.get()).withSuffix(uniqueSideTex ? "_top" : ""));
		this.itemModels().withExistingParent(pane.getId().toString(), new ModelFile.UncheckedModelFile("item/generated").getLocation()).texture("layer0", this.modLoc("block/" + pane.getId().getPath().replace("_pane", "")));
	}

	public void thickPaneBlockWithItem(DeferredBlock<Block> pane) {
		ModelFile post = this.models().withExistingParent(pane.getId().getPath() + "_post", this.modLoc("block/thick_pane_post")).texture("pane", this.blockTexture(pane.get()));
		ModelFile side = this.models().withExistingParent(pane.getId().getPath() + "_side", this.modLoc("block/thick_pane_side")).texture("pane", this.blockTexture(pane.get()));
		ModelFile sideAlt = this.models().withExistingParent(pane.getId().getPath() + "_side_alt", this.modLoc("block/thick_pane_side_alt")).texture("pane", this.blockTexture(pane.get()));
		ModelFile noSide = this.models().withExistingParent(pane.getId().getPath() + "_noside", this.modLoc("block/thick_pane_noside")).texture("pane", this.blockTexture(pane.get()));
		ModelFile noSideAlt = this.models().withExistingParent(pane.getId().getPath() + "_noside_alt", this.modLoc("block/thick_pane_noside_alt")).texture("pane", this.blockTexture(pane.get()));
		MultiPartBlockStateBuilder builder = this.getMultipartBuilder(pane.get()).part().modelFile(post).addModel().end();
		PipeBlock.PROPERTY_BY_DIRECTION.forEach((dir, value) -> {
			if (dir.getAxis().isHorizontal()) {
				boolean alt = dir == Direction.SOUTH;
				builder.part().modelFile(alt || dir == Direction.WEST ? sideAlt : side).rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
					.condition(value, true).end()
					.part().modelFile(alt || dir == Direction.EAST ? noSideAlt : noSide).rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
					.condition(value, false);
			}
		});
		this.basicItemTex(pane, true);
	}

	public void samitePanel(DeferredBlock<Block> block, DeferredBlock<Block> samite) {
		this.getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(this.models().withExistingParent(block.getId().getPath(), this.modLoc("block/samite_canvas_panel")).texture("samite", this.blockTexture(samite.get()))).rotationY(state.getValue(SamiteCanvasPanelBlock.AXIS) == Direction.Axis.Z ? 90 : 0).build());
		this.simpleBlockItem(block);
	}

	public void pillarWithItem(DeferredBlock<Block> block) {
		this.axisBlock((RotatedPillarBlock) block.get(),
			this.models().withExistingParent(block.getId().getPath(), this.modLoc("block/template_pillar")).texture("side", this.modLoc("block/" + block.getId().getPath())).texture("end", this.modLoc("block/" + block.getId().getPath() + "_top")),
			this.models().withExistingParent(block.getId().getPath() + "_side", this.modLoc("block/template_pillar_side")).texture("side", this.modLoc("block/" + block.getId().getPath())).texture("end", this.modLoc("block/" + block.getId().getPath() + "_top")));
		this.simpleBlockItem(block);
	}

	public void simpleBlockWithItem(DeferredBlock<Block> block) {
		this.simpleBlock(block.get());
		this.simpleBlockItem(block);
	}

	public void simpleBlockRenderTypeAndItem(DeferredBlock<Block> block, String renderType) {
		this.simpleBlock(block.get(), this.models().cubeAll(block.getId().getPath(), this.blockTexture(block.get())).renderType(renderType));
		this.simpleBlockItem(block);
	}

	public void basicItemTex(DeferredBlock<Block> block, boolean blockFolder) {
		this.itemModels().withExistingParent(block.getId().toString(), new ModelFile.UncheckedModelFile("item/generated").getLocation())
			.texture("layer0", ResourceLocation.fromNamespaceAndPath(block.getId().getNamespace(), (blockFolder ? "block/" : "item/") + block.getId().getPath()));
	}

	public void simpleBlockItem(DeferredBlock<Block> block) {
		this.itemModels().withExistingParent(block.getId().toString(), this.modLoc("block/" + block.getId().getPath()));
	}

	public void builtinEntity(DeferredBlock<Block> block, ResourceLocation particle) {
		this.simpleBlock(block.get(), this.models().getBuilder(block.getId().getPath())
			.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
			.texture("particle", particle));
	}

	public void builtinEntityAndItem(DeferredBlock<Block> block, ResourceLocation particle, float itemScale, float yOffs) {
		this.simpleBlock(block.get(), this.models().getBuilder(block.getId().getPath())
			.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
			.texture("particle", particle));
		this.itemModels().getBuilder(block.getId().getPath()).parent(new ModelFile.UncheckedModelFile("builtin/entity")).texture("particle", particle).transforms()
			.transform(ItemDisplayContext.GUI).scale(itemScale).translation(0, yOffs, 0).rotation(30, 225, 0).end()
			.transform(ItemDisplayContext.GROUND).scale(0.3F).translation(0, 3, 0).end()
			.transform(ItemDisplayContext.FIXED).scale(itemScale).end()
			.transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).scale(itemScale / 2.0F).rotation(75, 315, 0).translation(0, 2.5F, 0).end()
			.transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).scale(itemScale / 1.5F).rotation(0, 315, 0).end();
	}

	public void nibbletwigLogs() {
		this.getVariantBuilder(BlockRegistry.NIBBLETWIG_LOG.get()).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState()
			.modelFile(this.models().cubeColumn(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_1", this.modLoc("block/nibbletwig_log_side_1"), this.modLoc("block/nibbletwig_log_end"))).nextModel()
			.modelFile(this.models().cubeColumn(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_2", this.modLoc("block/nibbletwig_log_side_2"), this.modLoc("block/nibbletwig_log_end"))).nextModel()
			.modelFile(this.models().cubeColumn(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_3", this.modLoc("block/nibbletwig_log_side_3"), this.modLoc("block/nibbletwig_log_end"))).nextModel()
			.modelFile(this.models().cubeColumn(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_4", this.modLoc("block/nibbletwig_log_side_4"), this.modLoc("block/nibbletwig_log_end"))).addModel()
			.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_horizontal_1", this.modLoc("block/nibbletwig_log_side_1"), this.modLoc("block/nibbletwig_log_end"))).rotationX(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_horizontal_2", this.modLoc("block/nibbletwig_log_side_2"), this.modLoc("block/nibbletwig_log_end"))).rotationX(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_horizontal_3", this.modLoc("block/nibbletwig_log_side_3"), this.modLoc("block/nibbletwig_log_end"))).rotationX(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_horizontal_4", this.modLoc("block/nibbletwig_log_side_4"), this.modLoc("block/nibbletwig_log_end"))).rotationX(90).addModel()
			.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_horizontal_1", this.modLoc("block/nibbletwig_log_side_1"), this.modLoc("block/nibbletwig_log_end"))).rotationX(90).rotationY(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_horizontal_2", this.modLoc("block/nibbletwig_log_side_2"), this.modLoc("block/nibbletwig_log_end"))).rotationX(90).rotationY(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_horizontal_3", this.modLoc("block/nibbletwig_log_side_3"), this.modLoc("block/nibbletwig_log_end"))).rotationX(90).rotationY(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_horizontal_4", this.modLoc("block/nibbletwig_log_side_4"), this.modLoc("block/nibbletwig_log_end"))).rotationX(90).rotationY(90).addModel();
		this.simpleBlockItem(BlockRegistry.NIBBLETWIG_LOG.get(), this.models().withExistingParent(BlockRegistry.NIBBLETWIG_LOG.getId().getPath(), this.modLoc("block/" + BlockRegistry.NIBBLETWIG_LOG.getId().getPath() + "_1")));

		this.getVariantBuilder(BlockRegistry.NIBBLETWIG_BARK.get()).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState()
			.modelFile(this.models().cubeColumn(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_1", this.modLoc("block/nibbletwig_log_side_1"), this.modLoc("block/nibbletwig_log_side_1"))).nextModel()
			.modelFile(this.models().cubeColumn(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_2", this.modLoc("block/nibbletwig_log_side_2"), this.modLoc("block/nibbletwig_log_side_2"))).nextModel()
			.modelFile(this.models().cubeColumn(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_2", this.modLoc("block/nibbletwig_log_side_3"), this.modLoc("block/nibbletwig_log_side_3"))).nextModel()
			.modelFile(this.models().cubeColumn(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_4", this.modLoc("block/nibbletwig_log_side_4"), this.modLoc("block/nibbletwig_log_side_4"))).addModel()
			.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_horizontal", this.modLoc("block/nibbletwig_log_side_1"), this.modLoc("block/nibbletwig_log_side_1"))).rotationX(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_horizontal", this.modLoc("block/nibbletwig_log_side_2"), this.modLoc("block/nibbletwig_log_side_2"))).rotationX(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_horizontal", this.modLoc("block/nibbletwig_log_side_3"), this.modLoc("block/nibbletwig_log_side_3"))).rotationX(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_horizontal", this.modLoc("block/nibbletwig_log_side_4"), this.modLoc("block/nibbletwig_log_side_4"))).rotationX(90).addModel()
			.partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_horizontal", this.modLoc("block/nibbletwig_log_side_1"), this.modLoc("block/nibbletwig_log_side_1"))).rotationX(90).rotationY(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_horizontal", this.modLoc("block/nibbletwig_log_side_2"), this.modLoc("block/nibbletwig_log_side_2"))).rotationX(90).rotationY(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_horizontal", this.modLoc("block/nibbletwig_log_side_3"), this.modLoc("block/nibbletwig_log_side_3"))).rotationX(90).rotationY(90).nextModel()
			.modelFile(this.models().cubeColumnHorizontal(BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_horizontal", this.modLoc("block/nibbletwig_log_side_4"), this.modLoc("block/nibbletwig_log_side_4"))).rotationX(90).rotationY(90).addModel();
		this.simpleBlockItem(BlockRegistry.NIBBLETWIG_BARK.get(), this.models().withExistingParent(BlockRegistry.NIBBLETWIG_BARK.getId().getPath(), this.modLoc("block/" + BlockRegistry.NIBBLETWIG_BARK.getId().getPath() + "_1")));
	}
}
