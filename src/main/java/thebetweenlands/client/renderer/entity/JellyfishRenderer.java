package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.JellyfishModel;
import thebetweenlands.client.shader.LightSource;
import thebetweenlands.client.shader.ShaderHelper;
import thebetweenlands.client.state.JellyfishRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.Jellyfish;

public class JellyfishRenderer extends MobRenderer<Jellyfish, JellyfishRenderState, JellyfishModel> {

	private static final ResourceLocation[] TEXTURE = new ResourceLocation[]{
		TheBetweenlands.prefix("textures/entity/jellyfish_1.png"),
		TheBetweenlands.prefix("textures/entity/jellyfish_2.png"),
		TheBetweenlands.prefix("textures/entity/jellyfish_3.png"),
		TheBetweenlands.prefix("textures/entity/jellyfish_4.png"),
		TheBetweenlands.prefix("textures/entity/jellyfish_5.png")
	};

	private static final int[] GLOW_COLORS = {15641970, 11807274, 14701420, 5941905, 14135491};

	public JellyfishRenderer(EntityRendererProvider.Context context) {
		super(context, new JellyfishModel(context.bakeLayer(BLModelLayers.JELLYFISH)), 0.5F);
	}

	@Override
	public void render(JellyfishRenderState state, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		super.render(state, poseStack, buffer, packedLight);
		this.addLighting(state);
	}

	protected void addLighting(JellyfishRenderState state) {
		if (ShaderHelper.INSTANCE.isWorldShaderActive()) {
			float str = 5.0f * state.jellyScale;

			int color = GLOW_COLORS[state.color];

			ShaderHelper.INSTANCE.require();
			ShaderHelper.INSTANCE.getWorldShader().addLight(new LightSource(state.x, state.y, state.z, 3.0f, str * ARGB.red(color), str * ARGB.green(color), str * ARGB.blue(color)));
		}
	}

	@Override
	protected void scale(JellyfishRenderState state, PoseStack stack) {
		stack.scale(state.jellyScale, state.jellyScale * state.jellyLength, state.jellyScale);

		stack.scale(
			1.0F + Mth.sin(state.swimAnimationPos * 0.75F) * Math.min(state.walkAnimationSpeed, 0.2F) * 2.0F,
			1.0F - Mth.sin(state.swimAnimationPos * 0.75F) * Math.min(state.walkAnimationSpeed, 0.2F),
			1.0F + Mth.sin(state.swimAnimationPos * 0.75F) * Math.min(state.walkAnimationSpeed, 0.2F) * 2.0F
		);
	}

	@Override
	protected void setupRotations(JellyfishRenderState state, PoseStack stack, float yBodyRot, float scale) {
		Vec3 weightPos = state.orientation;

		double dx = state.x - weightPos.x;
		double dy = state.y - weightPos.y;
		double dz = state.z - weightPos.z;

		float yaw = -(float) Math.toDegrees(Mth.atan2(dz, dx));
		float pitch = (float) Math.toDegrees(Mth.atan2(Math.sqrt(dx * dx + dz * dz), -dy)) - 180;

		stack.translate(0.0D, 0.5D, 0.0D);
		stack.mulPose(Axis.YP.rotationDegrees(yaw));
		stack.mulPose(Axis.ZP.rotationDegrees(pitch));
		stack.mulPose(Axis.YP.rotationDegrees(-yaw));
		stack.translate(0.0D, -0.5D, 0.0D);
	}

	@Override
	public JellyfishRenderState createRenderState() {
		return new JellyfishRenderState();
	}

	@Override
	public void extractRenderState(Jellyfish entity, JellyfishRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.color = entity.getJellyfishColor();
		state.jellyLength = entity.getJellyfishLength();
		state.jellyScale = entity.getJellyfishSize();
		state.swimAnimationPos = entity.walkAnimation.position(1.0F - partialTick);
		state.orientation = entity.getOrientationPos(partialTick);
	}

	@Override
	public ResourceLocation getTextureLocation(JellyfishRenderState state) {
		return TEXTURE[state.color];
	}
}
