package thebetweenlands.api.storage;

import net.minecraft.nbt.CompoundTag;

public interface IDeferredStorageOperation {
	/**
	 * Called when the chunk is loaded and this operation is to be run
	 */
	void apply(IChunkStorage chunkStorage);

	/**
	 * Reads the deferred storage operation data from NBT.
	 */
	void readFromNBT(CompoundTag tag);

	/**
	 * Writes the deferred storage operation data to NBT.
	 */
	CompoundTag writeToNBT(CompoundTag tag);
}
