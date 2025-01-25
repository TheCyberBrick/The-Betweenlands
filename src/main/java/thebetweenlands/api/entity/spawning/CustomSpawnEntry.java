package thebetweenlands.api.entity.spawning;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

public interface CustomSpawnEntry extends WeightProvider {
	/**
	 * Returns the ID of this spawn entry
	 */
	ResourceLocation getID();

	/**
	 * Returns whether the data of this spawn entry should be saved
	 */
	boolean isSaved();

	/**
	 * Returns whether an entity can spawn based on the spawning position and the surface block below
	 */
	boolean canSpawn(Level level, ChunkAccess chunk, BlockPos pos, BlockState blockState, BlockState surfaceBlockState);

	/**
	 * Updates the spawning data based on the spawning position
	 */
	void tick(Level level, BlockPos pos);

	/**
	 * Returns the weight of this spawn entry
	 */
	@Override
	short getWeight();

	/**
	 * Sets the weight of this spawn entry
	 */
	CustomSpawnEntry setWeight(short weight);

	/**
	 * Sets the spawning interval
	 */
	CustomSpawnEntry setSpawningInterval(int interval);

	/**
	 * Returns the spawning interval
	 */
	int getSpawningInterval();

	/**
	 * Returns the initial base weight
	 */
	short getBaseWeight();

	/**
	 * Sets the desired minimum and maximum group size. The minimum desired group size
	 * may not always be achieved depending on the area around the initial spawn point.
	 */
	CustomSpawnEntry setGroupSize(int min, int max);

	/**
	 * Returns the maximum group size
	 */
	int getMaxGroupSize();

	/**
	 * Returns the minimum group size
	 */
	int getMinGroupSize();

	/**
	 * Returns the entity limit per chunk
	 */
	int getChunkLimit();

	/**
	 * Sets the entity limit per chunk
	 */
	CustomSpawnEntry setChunkLimit(int limit);

	/**
	 * Returns the entity limit per world
	 */
	int getWorldLimit();

	/**
	 * Sets the entity limit per world
	 */
	CustomSpawnEntry setWorldLimit(int limit);

	/**
	 * Returns the entity limit per cubic chunk
	 */
	int getSubChunkLimit();

	/**
	 * Sets the entity limit per cubic chunk
	 */
	CustomSpawnEntry setSubChunkLimit(int limit);

	/**
	 * Sets whether the entity is hostile
	 */
	CustomSpawnEntry setHostile(boolean hostile);

	/**
	 * Returns whether the entity is hostile
	 */
	boolean isHostile();

	/**
	 * Sets the group size spawn check radius
	 */
	CustomSpawnEntry setSpawnCheckRadius(double radius);

	/**
	 * Returns the group size spawn check radius
	 */
	double getSpawnCheckRadius();

	/**
	 * Sets the group size spawn check range in Y direction
	 */
	CustomSpawnEntry setSpawnCheckRangeY(double y);

	/**
	 * Returns the group size spawn check range in Y direction
	 */
	double getSpawnCheckRangeY();

	/**
	 * Sets the group spawn radius
	 */
	CustomSpawnEntry setGroupSpawnRadius(double radius);

	/**
	 * Returns the group spawn radius
	 */
	double getGroupSpawnRadius();

	/**
	 * Returns whether already existing entity should be taken into account when spawning groups
	 */
	boolean shouldCheckExistingGroups();

	/**
	 * Creates a new entity
	 */
	Mob createEntity(Level world);

	/**
	 * Returns the entity type
	 */
	Class<? extends Mob> getEntityType();

	/**
	 * Called when the entity is spawned
	 */
	void onSpawned(Mob entity);
}
