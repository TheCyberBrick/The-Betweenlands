package thebetweenlands.common.block.waterlog;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class WeedwoodWallSignBlock extends WeedwoodSignBlock {

	public static final MapCodec<WallSignBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
		WoodType.CODEC.fieldOf("wood_type").forGetter(SignBlock::type),
		propertiesCodec()
	).apply(instance, WallSignBlock::new));
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
		Direction.NORTH, Block.box(0.0, 4.5, 14.0, 16.0, 12.5, 16.0),
		Direction.SOUTH, Block.box(0.0, 4.5, 0.0, 16.0, 12.5, 2.0),
		Direction.EAST, Block.box(0.0, 4.5, 0.0, 2.0, 12.5, 16.0),
		Direction.WEST, Block.box(14.0, 4.5, 0.0, 16.0, 12.5, 16.0)));

	@Override
	public MapCodec<WallSignBlock> codec() {
		return CODEC;
	}

	public WeedwoodWallSignBlock(WoodType type, BlockBehaviour.Properties properties) {
		super(type, properties.sound(type.soundType()));
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATER_TYPE, WaterType.NONE));
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return AABBS.get(state.getValue(FACING));
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).isSolid();
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockstate = this.defaultBlockState();
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		LevelReader levelreader = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		Direction[] adirection = context.getNearestLookingDirections();

		for (Direction direction : adirection) {
			if (direction.getAxis().isHorizontal()) {
				Direction direction1 = direction.getOpposite();
				blockstate = blockstate.setValue(FACING, direction1);
				if (blockstate.canSurvive(levelreader, blockpos)) {
					return blockstate.setValue(WATER_TYPE, WaterType.getFromFluid(fluidstate.getType()));
				}
			}
		}

		return null;
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader reader, ScheduledTickAccess access, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		return direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(reader, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, reader, access, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	public float getYRotationDegrees(BlockState state) {
		return state.getValue(FACING).toYRot();
	}

	@Override
	public Vec3 getSignHitboxCenterPosition(BlockState state) {
		VoxelShape voxelshape = AABBS.get(state.getValue(FACING));
		return voxelshape.bounds().getCenter();
	}

	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATER_TYPE);
	}
}
