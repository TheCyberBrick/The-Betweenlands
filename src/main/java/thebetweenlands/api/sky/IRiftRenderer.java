package thebetweenlands.api.sky;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import org.joml.Matrix4f;

public interface IRiftRenderer {
	/**
	 * Renders the sky rift
	 */
	void render(ClientLevel level, float partialTicks, Matrix4f projectionMatrix, Camera camera, Matrix4f frustrumMatrix, boolean isFoggy, Runnable skyFogSetup);

	/**
	 * Sets the rift mask renderer that renders the rift mask and the overlay
	 */
	void setRiftMaskRenderer(IRiftMaskRenderer maskRenderer);

	/**
	 * Returns the rift mask renderer
	 */
	IRiftMaskRenderer getRiftMaskRenderer();

	/**
	 * Sets the rift sky renderer
	 */
	void setRiftSkyRenderer(IRiftSkyRenderer skyRenderer);

	/**
	 * Returns the rift sky renderer
	 */
	IRiftSkyRenderer getRiftSkyRenderer();
}
