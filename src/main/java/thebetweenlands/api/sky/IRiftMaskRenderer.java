package thebetweenlands.api.sky;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;

public interface IRiftMaskRenderer {
	/**
	 * Renders the sky mask
	 */
	void renderMask(ClientLevel level, float partialTicks, PoseStack stack, float skyBrightness);

	/**
	 * Renders the rift overlay
	 */
	void renderOverlay(ClientLevel level, float partialTicks, PoseStack stack, float skyBrightness);

	/**
	 * Renders the rift projection mesh
	 */
	void renderRiftProjection(ClientLevel level, float partialTicks, Camera camera, float skyBrightness);
}
