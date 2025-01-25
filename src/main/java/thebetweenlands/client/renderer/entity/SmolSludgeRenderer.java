package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.SmolSludgeModel;
import thebetweenlands.client.state.SludgeRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.SmolSludge;
import thebetweenlands.common.world.event.SpoopyEvent;

public class SmolSludgeRenderer extends MobRenderer<SmolSludge, SludgeRenderState, SmolSludgeModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/smol_sludge.png");
	private final BlockState pumpkin = Blocks.JACK_O_LANTERN.defaultBlockState();

	public SmolSludgeRenderer(EntityRendererProvider.Context context) {
		super(context, new SmolSludgeModel(context.bakeLayer(BLModelLayers.SMOL_SLUDGE)), 0.0F);
		this.addLayer(new SmolSludgeOuterLayer(this));
	}

	@Override
	protected void scale(SludgeRenderState state, PoseStack stack) {
		float squishFactor = state.squishFactor / 1.5F;
		float scale = 1.0F / (squishFactor + 1.0F);

		float squish = state.scale.getAnimationProgressSinSqrt(state.partialTick);
		stack.scale(squish, squish, squish);
		stack.translate(0, (1.0F - state.scale.getAnimationProgressSin(state.partialTick)) * 2.5F, 0);
		stack.scale(scale, 1.0F / scale, scale);

		if (SpoopyEvent.isSpoooopy(Minecraft.getInstance().level)) {
			stack.pushPose();
			stack.mulPose(Axis.YP.rotationDegrees(90F));
			stack.translate(0.37F, -0.13F, 0.37F);
			stack.scale(-scale * 0.65F, -1.0F / scale * 0.65F, scale * 0.65F);
			Minecraft.getInstance().getBlockRenderer().renderSingleBlock(this.pumpkin, stack, Minecraft.getInstance().renderBuffers().bufferSource(), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
			stack.popPose();
		}
	}

	@Override
	public SludgeRenderState createRenderState() {
		return new SludgeRenderState();
	}

	@Override
	public void extractRenderState(SmolSludge entity, SludgeRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.squishFactor = Mth.lerp(partialTick, entity.prevSquishFactor, entity.squishFactor);
		state.scale.copyFrom(entity.scale);
	}

	@Override
	public ResourceLocation getTextureLocation(SludgeRenderState state) {
		return TEXTURE;
	}

	public static class SmolSludgeOuterLayer extends RenderLayer<SludgeRenderState, SmolSludgeModel> {

		public SmolSludgeOuterLayer(RenderLayerParent<SludgeRenderState, SmolSludgeModel> renderer) {
			super(renderer);
		}

		public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, SludgeRenderState state, float netHeadYaw, float headPitch) {
			boolean flag = state.appearsGlowing && state.isInvisible;
			if (!state.isInvisible || flag) {
				VertexConsumer vertexconsumer;
				if (flag) {
					vertexconsumer = buffer.getBuffer(RenderType.outline(TEXTURE));
				} else {
					vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
				}

				this.getParentModel().setupAnim(state);
				this.getParentModel().renderSlime(stack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(state, 0.0F));
			}
		}
	}
}
