package thebetweenlands.api.sky;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import org.joml.Matrix4f;

public interface IRiftSkyRenderer {
	/**
	 * Sets the sky's clear color
	 */
	void setClearColor(Camera camera, ClientLevel level, float partialTicks);

	/**
	 * Renders the sky inside the rift
	 */
	void render(ClientLevel level, float partialTicks, Matrix4f projectionMatrix, Camera camera, Matrix4f frustrumMatrix, boolean isFoggy, Runnable skyFogSetup);

	/**
	 * Returns the sky's relative brightness between 0 and 1
	 */
	float getSkyBrightness(ClientLevel level, float partialTicks);
}
