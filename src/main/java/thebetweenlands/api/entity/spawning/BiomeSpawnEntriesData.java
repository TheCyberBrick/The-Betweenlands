package thebetweenlands.api.entity.spawning;

/**
 * Stores persistent per biome information about entity spawn entries
 */
public interface BiomeSpawnEntriesData {
	/**
	 * Returns when the specified spawn entry last spawned an entity.
	 * Returns -1 if there is no information stored about the spawn entry.
	 */
	long getLastSpawn(CustomSpawnEntry spawnEntry);

	/**
	 * Removes the last spawned data for the specified spawn entry
	 */
	long removeLastSpawn(CustomSpawnEntry spawnEntry);

	/**
	 * Sets when the specified spawn entry last spawned an entity
	 */
	void setLastSpawn(CustomSpawnEntry spawnEntry, long lastSpawn);
}
