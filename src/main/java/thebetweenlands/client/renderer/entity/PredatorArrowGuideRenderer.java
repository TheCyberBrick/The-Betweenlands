package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import thebetweenlands.client.renderer.BeamRenderer;
import thebetweenlands.client.state.PredatorArrowGuideRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.PredatorArrowGuide;

public class PredatorArrowGuideRenderer extends EntityRenderer<PredatorArrowGuide, PredatorArrowGuideRenderState> {

	private static final ResourceLocation BEAM_TEXTURE = TheBetweenlands.prefix("textures/particle/beam.png");

	public PredatorArrowGuideRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(PredatorArrowGuideRenderState state, PoseStack stack, MultiBufferSource buffer, int light) {
		super.render(state, stack, buffer, light);

		if(state.targetEye != Vec3.ZERO) {
			double diffX = state.targetEye.x - state.x;
			double diffY = state.targetEye.y - state.y;
			double diffZ = state.targetEye.z - state.z;

			stack.pushPose();
			stack.translate(0.0D, -state.ridingOffset, 0.0D);
			VertexConsumer consumer = buffer.getBuffer(RenderType.energySwirl(BEAM_TEXTURE, 0.0F, 0.0F));

			var rot = Minecraft.getInstance().gameRenderer.getMainCamera().rotation();
			BeamRenderer.buildBeam(diffX, diffY, diffZ, new Vec3(-diffX, -diffY, -diffZ), 0.05F, 0, 2F,
				rot.x(), rot.z(), rot.y() * rot.z(), rot.x() * rot.y(), rot.y(),
				(vx, vy, vz, u, v) -> consumer.addVertex(stack.last(), vx, vy, vz).setUv(u, v).setColor(35, 80, 110, 255).setLight(LightTexture.FULL_BRIGHT).setOverlay(OverlayTexture.NO_OVERLAY).setNormal(stack.last(), 0.0F, 1.0F, 0.0F));

			stack.popPose();
		}
	}

	@Override
	public PredatorArrowGuideRenderState createRenderState() {
		return new PredatorArrowGuideRenderState();
	}

	@Override
	public void extractRenderState(PredatorArrowGuide entity, PredatorArrowGuideRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.targetEye = entity.getTarget() != null ? entity.getTarget().getEyePosition() : Vec3.ZERO;
		state.ridingOffset = entity.getVehicle() != null ? entity.getY() - entity.getVehicle().getY() : 0.0D;
	}
}
