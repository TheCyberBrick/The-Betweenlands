package thebetweenlands.common.block.waterlog;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import javax.annotation.Nullable;

import java.util.Optional;

public class SwampBarsBlock extends IronBarsBlock implements SwampWaterLoggable {
	public SwampBarsBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(WATER_TYPE, WaterType.NONE));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockGetter blockgetter = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		BlockPos blockpos1 = blockpos.north();
		BlockPos blockpos2 = blockpos.south();
		BlockPos blockpos3 = blockpos.west();
		BlockPos blockpos4 = blockpos.east();
		BlockState blockstate = blockgetter.getBlockState(blockpos1);
		BlockState blockstate1 = blockgetter.getBlockState(blockpos2);
		BlockState blockstate2 = blockgetter.getBlockState(blockpos3);
		BlockState blockstate3 = blockgetter.getBlockState(blockpos4);
		return this.defaultBlockState()
			.setValue(NORTH, this.attachsTo(blockstate, blockstate.isFaceSturdy(blockgetter, blockpos1, Direction.SOUTH)))
			.setValue(SOUTH, this.attachsTo(blockstate1, blockstate1.isFaceSturdy(blockgetter, blockpos2, Direction.NORTH)))
			.setValue(WEST, this.attachsTo(blockstate2, blockstate2.isFaceSturdy(blockgetter, blockpos3, Direction.EAST)))
			.setValue(EAST, this.attachsTo(blockstate3, blockstate3.isFaceSturdy(blockgetter, blockpos4, Direction.WEST)))
			.setValue(WATER_TYPE, WaterType.getFromFluid(fluidstate.getType()));
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader reader, ScheduledTickAccess access, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (state.getValue(WATER_TYPE) != WaterType.NONE) {
			access.scheduleTick(pos, state.getValue(WATER_TYPE).getFluid(), state.getValue(WATER_TYPE).getFluid().getTickDelay(reader));
		}

		return direction.getAxis().isHorizontal() ? state.setValue(PROPERTY_BY_DIRECTION.get(direction), this.attachsTo(neighborState, neighborState.isFaceSturdy(reader, neighborPos, direction.getOpposite()))) : state;
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATER_TYPE).getFluid().defaultFluidState();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, WEST, SOUTH, WATER_TYPE);
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState state) {
		return state.getValue(WATER_TYPE) == WaterType.NONE;
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
		return SwampWaterLoggable.super.canPlaceLiquid(player, level, pos, state, fluid);
	}

	@Override
	public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
		return SwampWaterLoggable.super.placeLiquid(level, pos, state, fluidState);
	}

	@Override
	public ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state) {
		return SwampWaterLoggable.super.pickupBlock(player, level, pos, state);
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return SwampWaterLoggable.super.getPickupSound();
	}
}
