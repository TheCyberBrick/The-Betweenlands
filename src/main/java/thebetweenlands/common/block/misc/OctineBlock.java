package thebetweenlands.common.block.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import thebetweenlands.common.block.plant.MossBlock;
import thebetweenlands.common.block.plant.ThornsBlock;
import thebetweenlands.common.item.misc.OctineIngotItem;

public class OctineBlock extends Block {
	public OctineBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader reader, ScheduledTickAccess access, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (OctineIngotItem.isTinder(ItemStack.EMPTY, neighborState)) {
			boolean isTouching = true;

			if (neighborState.getBlock() instanceof MossBlock) {
				if (direction.equals(neighborState.getValue(DirectionalBlock.FACING)))
					isTouching = false;
			} else if (neighborState.getBlock() instanceof ThornsBlock) {
				BooleanProperty side = PipeBlock.PROPERTY_BY_DIRECTION.get(direction.getOpposite());
				if (side == null || neighborState.getValue(side))
					isTouching = false;
			}
			if (isTouching) {
				//level.setBlock(neighborPos, Blocks.FIRE.defaultBlockState(), 3); //TODO
			}
		}
		return state;
	}
}
