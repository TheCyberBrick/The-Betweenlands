package thebetweenlands.api.storage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;

import javax.annotation.Nullable;

public interface IWorldStorage {

	/**
	 * Called when a chunk storage needs to be read from the specified NBT and loaded
	 */
	void readAndLoadChunk(ChunkAccess chunk, CompoundTag tag);

	/**
	 * Called when a new chunk is loaded without any NBT data
	 */
	void loadChunk(ChunkAccess chunk);

	/**
	 * Saves the chunk storage data to NBT. May return
	 * null if no data needs to be saved
	 */
	@Nullable
	CompoundTag saveChunk(ChunkAccess chunk);

	/**
	 * Called when a chunk is unloaded
	 */
	void unloadChunk(ChunkAccess chunk);

	/**
	 * Called when a player starts watching the specified chunk
	 */
	void watchChunk(ChunkPos pos, ServerPlayer player);

	/**
	 * Called when a player stops watching the specified chunk
	 */
	void unwatchChunk(ChunkPos pos, ServerPlayer player);

	/**
	 * Returns the chunk storage of the specified chunk
	 */
	IChunkStorage getChunkStorage(ChunkAccess chunk);

	/**
	 * Returns the local storage handler responsible for loading and
	 * saving local storage from/to files and keeping track
	 * the local storage instances
	 */
	ILocalStorageHandler getLocalStorageHandler();

	/**
	 * Ticks the world storage
	 */
	void tick(Level level);
}
