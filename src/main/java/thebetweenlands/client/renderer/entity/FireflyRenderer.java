package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.joml.Quaternionf;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.FireflyModel;
import thebetweenlands.client.renderer.entity.layers.FireflyGlowLayer;
import thebetweenlands.client.shader.LightSource;
import thebetweenlands.client.shader.ShaderHelper;
import thebetweenlands.client.state.FireflyRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.Firefly;

public class FireflyRenderer extends MobRenderer<Firefly, FireflyRenderState, FireflyModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/firefly.png");
	public static final ResourceLocation GLOW = TheBetweenlands.prefix("textures/entity/firefly_glow.png");
	private final FireflyGlowLayer layer;
	protected static final RandomSource RANDOM = RandomSource.create();

	public FireflyRenderer(EntityRendererProvider.Context context) {
		super(context, new FireflyModel(context.bakeLayer(BLModelLayers.FIREFLY)), 0.0F);
		this.addLayer(this.layer = new FireflyGlowLayer(this, TheBetweenlands.prefix("textures/entity/firefly_glow_overlay.png")));
	}

	@Override
	public void render(FireflyRenderState state, PoseStack stack, MultiBufferSource buffer, int light) {
		float glowStrength = state.glowTicks;
		this.layer.setAlpha(state.glowTicks * (float) Math.min(glowStrength, 1.0D));

		stack.pushPose();
		stack.translate(0, Math.sin(state.ageInTicks / 10.0F) * 0.15F, 0);
		super.render(state, stack, buffer, light);
		renderFireflyGlow(stack, buffer.getBuffer(RenderType.eyes(GLOW)), glowStrength);
		stack.popPose();

		if (ShaderHelper.INSTANCE.isWorldShaderActive()) {
			float radius = glowStrength * 7.0F;

			if (radius > 0.1F) {
				addFireflyLight(state.x, state.y - 0.5D, state.z, radius);
			}
		}
	}

	@Override
	public FireflyRenderState createRenderState() {
		return new FireflyRenderState();
	}

	@Override
	public void extractRenderState(Firefly entity, FireflyRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.glowTicks = Mth.lerp(partialTick, entity.prevGlowTicks, entity.glowTicks) / 20.0F;
	}

	public static void addFireflyLight(double x, double y, double z, float radius) {
		ShaderHelper.INSTANCE.require();
		ShaderHelper.INSTANCE.getWorldShader().addLight(new LightSource(x, y, z,
			RANDOM.nextFloat() * 0.1f + radius,
			16.0f / 255.0f * 60.0F + RANDOM.nextFloat() * 0.4F,
			12.0f / 255.0f * 60.0F + RANDOM.nextFloat() * 0.1F,
			8.0f / 255.0f * 60.0F));
	}

	public static void renderFireflyGlow(PoseStack stack, VertexConsumer consumer, float scale) {
		stack.pushPose();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
		//GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);

		Quaternionf angle = Minecraft.getInstance().gameRenderer.getMainCamera().rotation();

		float red = 0.4F;
		float green = 0.2F;
		float blue = 0.0F;

		if (RANDOM.nextInt(10) <= 2) {
			red = 0.4F + RANDOM.nextFloat() * 0.3F;
		}

		float currentScale = scale;

		for (int i = 0; i < 10; i++) {
			currentScale -= scale * 0.15F;
			renderQuad(stack.last(), consumer, angle, currentScale, red, green, blue);
		}

		red = 0.6F;
		green = 0.6F;
		blue = 0.6F;

		currentScale = scale / 4.0F;
		for (int i = 0; i < 10; i++) {
			currentScale -= scale * 0.15F / 4.0F;
			renderQuad(stack.last(), consumer, angle, currentScale, red, green, blue);
		}

		RenderSystem.depthMask(false);
		stack.popPose();

		RenderSystem.defaultBlendFunc();
	}

	private static void renderQuad(PoseStack.Pose pose, VertexConsumer consumer, Quaternionf angle, float scale, float red, float green, float blue) {
		float rx = angle.x();
		float rxz = angle.x() * angle.z();
		float rz = angle.z();
		float ryz = angle.y() * angle.z();
		float rxy = angle.x() * angle.y();
		consumer.addVertex(pose, rx * scale - ryz * scale, rxz * scale, rz * scale - rxy * scale).setUv(0.0F, 1.0F).setColor(red, green, blue, 0.3F).setLight(LightTexture.FULL_BRIGHT).setOverlay(OverlayTexture.NO_OVERLAY).setNormal(pose, 0.0F, 1.0F, 0.0F);
		consumer.addVertex(pose, rx * scale + ryz * scale, rxz * scale, rz * scale + rxy * scale).setUv(1.0F, 1.0F).setColor(red, green, blue, 0.3F).setLight(LightTexture.FULL_BRIGHT).setOverlay(OverlayTexture.NO_OVERLAY).setNormal(pose, 0.0F, 1.0F, 0.0F);
		consumer.addVertex(pose, rx * scale + ryz * scale, rxz * scale, rz * scale + rxy * scale).setUv(1.0F, 0.0F).setColor(red, green, blue, 0.3F).setLight(LightTexture.FULL_BRIGHT).setOverlay(OverlayTexture.NO_OVERLAY).setNormal(pose, 0.0F, 1.0F, 0.0F);
		consumer.addVertex(pose, rx * scale - ryz * scale, rxz * scale, rz * scale - rxy * scale).setUv(0.0F, 0.0F).setColor(red, green, blue, 0.3F).setLight(LightTexture.FULL_BRIGHT).setOverlay(OverlayTexture.NO_OVERLAY).setNormal(pose, 0.0F, 1.0F, 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(FireflyRenderState state) {
		return TEXTURE;
	}
}
