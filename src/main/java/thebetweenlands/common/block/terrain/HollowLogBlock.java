package thebetweenlands.common.block.terrain;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import thebetweenlands.common.block.waterlog.SwampWaterLoggable;

public class HollowLogBlock extends HorizontalDirectionalBlock implements SwampWaterLoggable {

	public static final MapCodec<HollowLogBlock> CODEC = simpleCodec(HollowLogBlock::new);

	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
	// originally CLOCKWISE_FACE and ANTICLOCKWISE_FACE, but this is easier to work with on the collisions side of thing
	public static final BooleanProperty POSITIVE_FACE = BooleanProperty.create("positive_face");
	public static final BooleanProperty NEGATIVE_FACE = BooleanProperty.create("negative_face");

	private static final VoxelShape COLLISION_SHAPE_X = Shapes.join(Shapes.block(), Block.box(0, 1, 1, 16, 15, 15), BooleanOp.ONLY_FIRST);
	private static final VoxelShape COLLISION_SHAPE_Z = Shapes.join(Shapes.block(), Block.box(1, 1, 0, 15, 15, 16), BooleanOp.ONLY_FIRST);

	// No horizontal faces
	private static final VoxelShape COLLISION_SHAPE_NONE = Shapes.join(COLLISION_SHAPE_X, Block.box(1, 1, 0, 15, 15, 16), BooleanOp.ONLY_FIRST);

	private static final VoxelShape[] COLLISION_MAP_X = new VoxelShape[] {
			COLLISION_SHAPE_X,
			Shapes.join(COLLISION_SHAPE_X, Block.box(1, 1, 8, 15, 15, 16), BooleanOp.ONLY_FIRST), // no +z face
			Shapes.join(COLLISION_SHAPE_X, Block.box(1, 1, 0, 15, 15, 7), BooleanOp.ONLY_FIRST), // no -z face,
			COLLISION_SHAPE_NONE // no horizontal faces at all
	};

	private static final VoxelShape[] COLLISION_MAP_Z = new VoxelShape[] {
			COLLISION_SHAPE_Z,
			Shapes.join(COLLISION_SHAPE_Z, Block.box(8, 1, 1, 16, 15, 15), BooleanOp.ONLY_FIRST), // no +x face
			Shapes.join(COLLISION_SHAPE_Z, Block.box(0, 1, 1, 7, 15, 15), BooleanOp.ONLY_FIRST), // no -x face,
			COLLISION_SHAPE_NONE // no horizontal faces at all
	};

	public HollowLogBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(POSITIVE_FACE, true).setValue(NEGATIVE_FACE, true).setValue(WATER_TYPE, WaterType.NONE));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

//	@Override
//	public @Nullable PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
//		if(mob != null && state.is(this)) {
//			Axis axis;
//			Vec3 axisVec = mob.position().subtract(pos.getCenter());
//			if(axisVec.x > axisVec.z) {
//				axis = Axis.X;
//			} else {
//				axis = Axis.Z;
//			}
//			if(state.getValue(FACING).getAxis() == axis) {
//				return PathType.OPEN;
//			} else {
//				return PathType.BLOCKED;
//			}
//		}
//		return super.getBlockPathType(state, level, pos, mob);
//	}
//
//	@Override
//	public @Nullable PathType getAdjacentBlockPathType(BlockState currentState, BlockGetter level, BlockPos pos, @Nullable Mob mob, PathType originalType) {
//		BlockState adjacentState = level.getBlockState(pos);
//		if(mob != null && adjacentState.is(this)) {
//			Axis axis;
//			Vec3 axisVec = mob.position().subtract(pos.getCenter());
//			if(axisVec.x > axisVec.z) {
//				axis = Axis.X;
//			} else {
//				axis = Axis.Z;
//			}
//			//don't walk through the wall of a log
//			// TODO: open sides on connections
//			if(adjacentState.getValue(FACING).getAxis() == axis) {
//				AABB block = new AABB(pos);
//				AABB mobAABB = mob.getBoundingBox();
//				// If it can even fit
//				if(block.contains(mobAABB.getBottomCenter()) && block.contains(Mth.lerp(0.5, mobAABB.minX, mobAABB.maxX), mobAABB.maxY, Mth.lerp(0.5, mobAABB.minZ, mobAABB.maxZ))) {
//					return PathType.OPEN;
//				}
//			}
//			return PathType.BLOCKED;
//		}
//		return super.getAdjacentBlockPathType(currentState, level, pos, mob, originalType);
//	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		final Axis axis = state.getValue(FACING).getAxis();
		if(!axis.isHorizontal()) return Shapes.empty();
		int mapIndex = 0;
		mapIndex |= !state.getValue(POSITIVE_FACE) ? 1 : 0;
		mapIndex |= !state.getValue(NEGATIVE_FACE) ? 2 : 0;

		return switch (axis) {
			case X -> COLLISION_MAP_X[mapIndex];
			case Z -> COLLISION_MAP_Z[mapIndex];
			default -> Shapes.empty(); // Should never be reached
		};
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		final Direction facing = context.getHorizontalDirection().getOpposite();
		BlockState state = this.defaultBlockState().setValue(FACING, facing);

		final Axis oppositeAxis = facing.getAxis() == Axis.X ? Axis.Z : Axis.X;
		final Level level = context.getLevel();
		final BlockPos pos = context.getClickedPos();

		final BlockState positiveState = level.getBlockState(pos.relative(Direction.get(AxisDirection.POSITIVE, oppositeAxis)));
		final boolean positiveFace = !(positiveState.is(this) && positiveState.getValue(FACING).getAxis() == oppositeAxis);

		final BlockState negativeState = level.getBlockState(pos.relative(Direction.get(AxisDirection.NEGATIVE, oppositeAxis)));
		final boolean negativeFace = !(negativeState.is(this) && negativeState.getValue(FACING).getAxis() == oppositeAxis);

		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return state
				.setValue(POSITIVE_FACE, positiveFace)
				.setValue(NEGATIVE_FACE, negativeFace)
				.setValue(WATER_TYPE, WaterType.getFromFluid(fluidstate.getType()));
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader reader, ScheduledTickAccess access, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		BlockState newState = super.updateShape(state, reader, access, pos, direction, neighborPos, neighborState, random);

		if(state.is(this)) {
			final Axis oppositeAxis = state.getValue(FACING).getAxis() == Axis.X ? Axis.Z : Axis.X;

			final BlockState positiveState = reader.getBlockState(pos.relative(Direction.get(AxisDirection.POSITIVE, oppositeAxis)));
			newState = newState.setValue(POSITIVE_FACE, !positiveState.is(this) || positiveState.getValue(FACING).getAxis() != oppositeAxis);

			final BlockState negativeState = reader.getBlockState(pos.relative(Direction.get(AxisDirection.NEGATIVE, oppositeAxis)));
			newState = newState.setValue(NEGATIVE_FACE, !negativeState.is(this) || negativeState.getValue(FACING).getAxis() != oppositeAxis);

			//waterlogging
			if (state.getValue(WATER_TYPE) != WaterType.NONE) {
				access.scheduleTick(pos, state.getValue(WATER_TYPE).getFluid(), state.getValue(WATER_TYPE).getFluid().getTickDelay(reader));
			}
		}

		return newState;
	}

	@Override
	protected VoxelShape getOcclusionShape(BlockState state) {
		return Shapes.empty();
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
		return 1.0F;
	}

	@Override
	protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
		return super.skipRendering(state, adjacentState, direction) || adjacentState.is(this) && direction.getAxis() == adjacentState.getValue(FACING).getAxis();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, POSITIVE_FACE, NEGATIVE_FACE, WATER_TYPE);
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATER_TYPE).getFluid().defaultFluidState();
	}
}
