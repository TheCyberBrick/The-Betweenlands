package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.GreeblingModel;
import thebetweenlands.client.state.GreeblingRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.Greebling;

public class GreeblingRenderer extends MobRenderer<Greebling, GreeblingRenderState, GreeblingModel> {

	private static final ResourceLocation TEXTURE_1 = TheBetweenlands.prefix("textures/entity/greebling_0.png");
	private static final ResourceLocation TEXTURE_2 = TheBetweenlands.prefix("textures/entity/greebling_1.png");
	private final GreeblingModel variant1 = this.getModel();
	private final GreeblingModel variant2;

	public GreeblingRenderer(EntityRendererProvider.Context context) {
		super(context, new GreeblingModel(context.bakeLayer(BLModelLayers.GREEBLING_1)), 0.0F);
		this.variant2 = new GreeblingModel(context.bakeLayer(BLModelLayers.GREEBLING_2));
	}

	@Override
	public void render(GreeblingRenderState state, PoseStack stack, MultiBufferSource buffer, int light) {
		this.model = state.type == 0 ? this.variant1 : this.variant2;
		float disappearFrame = state.vanishTimer > 0 ? (float) Math.pow(state.vanishTimer / 8f, 4) : 0;
		float scaleXZ = 1 - disappearFrame;
		float scaleY = 1 + 0.1F * disappearFrame;
		stack.scale(scaleXZ, scaleY, scaleXZ);
		super.render(state, stack, buffer, light);
		stack.scale(1 / scaleXZ, 1 / scaleY, 1 / scaleXZ);
		if (this.model.getCup() != null) {
			stack.pushPose();
			stack.scale(-1.0F, -1.0F, 1.0F);
			stack.translate(0.0F, -1.501F, 0.0F);
			this.model.getCup().render(stack, buffer.getBuffer(this.model.renderType(TEXTURE_2)), light, OverlayTexture.NO_OVERLAY, -1);
			stack.popPose();
		}
	}

	@Override
	public GreeblingRenderState createRenderState() {
		return new GreeblingRenderState();
	}

	@Override
	public void extractRenderState(Greebling entity, GreeblingRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.type = entity.getGreeblingType();
		state.vanishTimer = entity.disappearTimer > 0 ? entity.disappearTimer + partialTick : 0;
	}

	@Override
	public ResourceLocation getTextureLocation(GreeblingRenderState state) {
		return state.type == 0 ? TEXTURE_1 : TEXTURE_2;
	}
}
