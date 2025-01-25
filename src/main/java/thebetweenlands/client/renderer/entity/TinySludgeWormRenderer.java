package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.TinySludgeWormModel;
import thebetweenlands.client.state.SludgeWormRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.TinySludgeWorm;

import java.util.ArrayList;
import java.util.List;

public class TinySludgeWormRenderer extends MobRenderer<TinySludgeWorm, SludgeWormRenderState, TinySludgeWormModel> {
	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/sludge_worm_tiny.png");

	public TinySludgeWormRenderer(EntityRendererProvider.Context context) {
		super(context, new TinySludgeWormModel(context.bakeLayer(BLModelLayers.TINY_SLUDGE_WORM)), 0F);
	}

	@Override
	public void render(SludgeWormRenderState state, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		boolean isVisible = this.isBodyVisible(state);
		boolean isTranslucentToPlayer = !isVisible && !state.isInvisibleToPlayer;
		int overlay = getOverlayCoords(state, this.getWhiteOverlayProgress(state));
		int color = isTranslucentToPlayer ? 654311423 : -1;

		stack.pushPose();
		float avgWibbleStrength = SludgeWormRenderer.getAvgWibbleStrength(state);

		var renderType = this.getRenderType(state, isVisible, isTranslucentToPlayer, state.appearsGlowing);
		if (renderType != null) {
			VertexConsumer consumer = buffer.getBuffer(renderType);
			this.renderHead(state, stack, consumer, packedLight, overlay, color, avgWibbleStrength);
			float zOffset = 0;

			for (int i = 1; i < state.parts.length - 1; i++) {
				this.renderBodyPart(stack, consumer, packedLight, overlay, color, state.parts[i], state.parts[i - 1], state.x, state.y, state.z, avgWibbleStrength, zOffset -= 0.001F);
			}
			this.renderTailPart(stack, consumer, packedLight, overlay, color, state.parts[state.parts.length - 1], state.parts[state.parts.length - 2], state.x, state.y, state.z, avgWibbleStrength);
		}
		stack.popPose();
	}

	public void renderHead(SludgeWormRenderState state, PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, float avgWibbleStrength) {
		double yawDiff = (state.yRot - state.parts[1].yRot()) % 360.0F;
		double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
		float wibbleStrength = Math.min(avgWibbleStrength, Math.clamp(1.0F - (float) Math.abs(yawInterpolant) / 60.0F, 0, 1));
		stack.pushPose();
		stack.translate(0.0F, 1.5F, 0.0F);
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.mulPose(Axis.YP.rotationDegrees(180.0F + state.yRot));
		this.model.renderHead(state.parts[0], stack, consumer, light, overlay, color, state.xRot, wibbleStrength);
		stack.popPose();
	}

	protected void renderBodyPart(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, SludgeWormRenderState.WormPartInfo part, SludgeWormRenderState.WormPartInfo prevPart, double rx, double ry, double rz, float avgWibbleStrength, float zOffset) {
		double x = part.pos().x() - rx;
		double y = part.pos().y() - ry;
		double z = part.pos().z() - rz;
		float yaw = part.yRot();
		double yawDiff = (prevPart.yRot() - part.yRot()) % 360.0F;
		double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
		float wibbleStrength = Math.min(avgWibbleStrength, Math.clamp(1.0F - (float) Math.abs(yawInterpolant) / 60.0F, 0, 1));

		stack.pushPose();
		stack.translate(x, y - 1.125F + zOffset, z);
		stack.mulPose(Axis.YN.rotationDegrees(yaw));
		this.model.renderBody(part, stack, consumer, light, overlay, color, wibbleStrength);
		stack.popPose();
	}

	protected void renderTailPart(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, SludgeWormRenderState.WormPartInfo part, SludgeWormRenderState.WormPartInfo prevPart, double rx, double ry, double rz, float avgWibbleStrength) {
		double x = part.pos().x() - rx;
		double y = part.pos().y() - ry;
		double z = part.pos().z() - rz;
		float yaw = part.yRot();
		double yawDiff = (prevPart.yRot() - part.yRot()) % 360.0F;
		double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
		float wibbleStrength = Math.min(avgWibbleStrength, Math.clamp(1.0F - (float) Math.abs(yawInterpolant) / 60.0F, 0, 1));

		stack.pushPose();
		stack.translate(x, y + 1.525F, z);
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.mulPose(Axis.YP.rotationDegrees(180.0F + yaw));
		this.model.renderTail(part, stack, consumer, light, overlay, color, wibbleStrength);
		stack.popPose();
	}

	@Override
	public SludgeWormRenderState createRenderState() {
		return new SludgeWormRenderState();
	}

	@Override
	public void extractRenderState(TinySludgeWorm entity, SludgeWormRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		List<SludgeWormRenderState.WormPartInfo> partList = new ArrayList<>();
		partList.addFirst(new SludgeWormRenderState.WormPartInfo(entity.position(), state.yRot, Mth.lerp(partialTick, entity.tickCount, entity.tickCount + 1)));
		for (int i = 0; i < entity.parts.length; i++) {
			var part = entity.parts[i];
			double x = Mth.lerp(partialTick, part.xOld, part.getX());
			double y = Mth.lerp(partialTick, part.yOld, part.getY());
			double z = Mth.lerp(partialTick, part.zOld, part.getZ());
			float smoothFrame = Mth.lerp(partialTick, entity.tickCount + i - 1, entity.tickCount + i);
			partList.add(new SludgeWormRenderState.WormPartInfo(new Vec3(x, y, z), part.getYRot(partialTick), smoothFrame));
		}
		state.parts = partList.toArray(SludgeWormRenderState.WormPartInfo[]::new);
	}

	@Override
	public ResourceLocation getTextureLocation(SludgeWormRenderState state) {
		return TEXTURE;
	}
}