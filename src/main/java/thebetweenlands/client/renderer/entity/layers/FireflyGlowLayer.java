package thebetweenlands.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import thebetweenlands.client.model.entity.FireflyModel;
import thebetweenlands.client.state.FireflyRenderState;

public class FireflyGlowLayer extends GenericEyesLayer<FireflyRenderState, FireflyModel> {

	private float alpha;

	public FireflyGlowLayer(RenderLayerParent<FireflyRenderState, FireflyModel> parent, ResourceLocation eyeType) {
		super(parent, eyeType);
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource source, int light, FireflyRenderState state, float netHeadYaw, float headPitch) {
		VertexConsumer vertexconsumer = source.getBuffer(this.renderType());
		this.getParentModel().renderToBuffer(stack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, ARGB.colorFromFloat(this.alpha, this.alpha, this.alpha, this.alpha));
	}
}
