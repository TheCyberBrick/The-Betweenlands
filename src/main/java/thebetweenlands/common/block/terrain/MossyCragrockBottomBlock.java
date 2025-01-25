package thebetweenlands.common.block.terrain;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import javax.annotation.Nullable;

public class MossyCragrockBottomBlock extends MossyCragrockBlock {

	public static final BooleanProperty IS_BOTTOM = BooleanProperty.create("is_bottom");

	public MossyCragrockBottomBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(IS_BOTTOM, false));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(IS_BOTTOM, !context.getLevel().getBlockState(context.getClickedPos().below()).is(this));
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader reader, ScheduledTickAccess access, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (direction == Direction.DOWN) {
			return state.setValue(IS_BOTTOM, !neighborState.is(this));
		}
		return super.updateShape(state, reader, access, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(IS_BOTTOM);
	}
}
