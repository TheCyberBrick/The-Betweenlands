package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.SludgeWormArrowModel;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.projectile.arrow.SludgeWormArrow;

public class SludgeWormArrowRenderer extends ArrowRenderer<SludgeWormArrow, ArrowRenderState> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/sludge_worm_tiny.png");
	private final SludgeWormArrowModel model;

	public SludgeWormArrowRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SludgeWormArrowModel(context.bakeLayer(BLModelLayers.SLUDGE_WORM_ARROW));
	}

	@Override
	public void render(ArrowRenderState state, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
		stack.mulPose(Axis.ZP.rotationDegrees(state.xRot + 90.0F));
		if (state.shake > 0.0F) {
			float f10 = -Mth.sin(state.shake * 3.0F) * state.shake;
			stack.mulPose(Axis.ZP.rotationDegrees(f10));
		}
		stack.mulPose(Axis.XN.rotationDegrees(90.0F));
		stack.mulPose(Axis.ZN.rotationDegrees(90.0F));
		stack.scale(-1.0F, -1.0F, 1.0F);
		this.model.renderToBuffer(stack, buffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(state))), packedLight, OverlayTexture.NO_OVERLAY);
		stack.popPose();
	}

	@Override
	protected ResourceLocation getTextureLocation(ArrowRenderState state) {
		return TEXTURE;
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}
}
