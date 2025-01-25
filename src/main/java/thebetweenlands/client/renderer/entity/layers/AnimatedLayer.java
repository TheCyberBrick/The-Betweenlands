package thebetweenlands.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.renderer.BLRenderTypes;

public class AnimatedLayer<S extends EntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

	private final ResourceLocation texture;

	public AnimatedLayer(RenderLayerParent<S, M> renderer, ResourceLocation texture) {
		super(renderer);
		this.texture = texture;
	}

	@Override
	public void render(PoseStack stack, MultiBufferSource buffer, int light, S state, float netHeadYaw, float headPitch) {
		VertexConsumer vertexconsumer = buffer.getBuffer(BLRenderTypes.animatedLayer(this.texture, 0, -state.ageInTicks * 0.002F % 1.0F));
		this.getParentModel().renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
	}
}
