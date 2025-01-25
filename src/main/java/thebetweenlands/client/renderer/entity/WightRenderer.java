package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.WightModel;
import thebetweenlands.client.shader.LightSource;
import thebetweenlands.client.shader.ShaderHelper;
import thebetweenlands.client.state.WightRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.Wight;
import thebetweenlands.common.world.event.WinterEvent;

public class WightRenderer extends MobRenderer<Wight, WightRenderState, WightModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/wight.png");
	public static final ResourceLocation TEXTURE_FROSTY =TheBetweenlands.prefix("textures/entity/wight_frosty.png");

	public WightRenderer(EntityRendererProvider.Context context) {
		super(context, new WightModel(context.bakeLayer(BLModelLayers.WIGHT)), 0.5F);
	}

	@Override
	public void render(WightRenderState state, PoseStack stack, MultiBufferSource source, int light) {
		if(!state.isVolatile) {
			stack.pushPose();
			RenderSystem.disableBlend();
			RenderSystem.colorMask(false, false, false, false);
			RenderSystem.setShaderColor(1, 1, 1, 1);

			super.render(state, stack, source, light);

			RenderSystem.colorMask(true, true, true, true);
			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			RenderSystem.setShaderColor(1F, 1F, 1F, 1F - state.hidingProgress * 0.5F);

			super.render(state, stack, source, light);

			stack.popPose();
		} else {
			stack.pushPose();
			RenderSystem.disableBlend();
			RenderSystem.colorMask(false, false, false, false);
			RenderSystem.setShaderColor(1, 1, 1, 1);

			if (state.isPossessing) {
				stack.scale(-1.0F, -1.0F, 1.0F);
				stack.mulPose(Axis.YP.rotationDegrees(state.ageInTicks / 30.0F * 360.0F));
				stack.mulPose(Axis.YP.rotationDegrees(180));
				stack.translate(0, -state.eyelineHover + 1.65D, 0.8D);
				stack.scale(0.5F, 0.5F, 0.5F);

				VertexConsumer consumer = source.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(state)));
				this.model.renderToBuffer(stack, consumer, light, OverlayTexture.NO_OVERLAY);

				RenderSystem.colorMask(true, true, true, true);
				RenderSystem.enableBlend();
				RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
				RenderSystem.setShaderColor(1F, 1F, 1F, 0.4F);

				this.model.renderToBuffer(stack, consumer, light, OverlayTexture.NO_OVERLAY);
			} else {
				super.render(state, stack, source, light);

				RenderSystem.colorMask(true, true, true, true);
				RenderSystem.enableBlend();
				RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
				RenderSystem.setShaderColor(1F, 1F, 1F, 0.4F);

				super.render(state, stack, source, light);
			}

			stack.popPose();
		}
	}

	@Override
	public void scale(WightRenderState state, PoseStack stack) {
		if (ShaderHelper.INSTANCE.isWorldShaderActive()) {
			ShaderHelper.INSTANCE.require();
			ShaderHelper.INSTANCE.getWorldShader().addLight(new LightSource(state.x, state.y, state.z, 10.0f, -1, -1, -1));
		}

		float scale = 0.9F / 40F * state.growthProgress;
		stack.scale(0.9F, scale, 0.9F);

		if (state.isVolatile) {
			stack.scale(0.5f, 0.5f, 0.5f);
			stack.translate(0, 1.0D, 0);
		}
	}

	@Override
	public WightRenderState createRenderState() {
		return new WightRenderState();
	}

	@Override
	public void extractRenderState(Wight entity, WightRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.isPossessing = entity.getVehicle() != null;
		state.isVolatile = entity.isVolatile();
		state.hidingProgress = Mth.lerp(partialTick, entity.lastHidingAnimationTicks, entity.hidingAnimationTicks);
		state.growthProgress = Mth.lerp(partialTick, entity.prevGrowCount, entity.growCount);
	}

	@Override
	public ResourceLocation getTextureLocation(WightRenderState state) {
		if (WinterEvent.isFroooosty(Minecraft.getInstance().level)) {
			return TEXTURE_FROSTY;
		}
		return TEXTURE;
	}
}
