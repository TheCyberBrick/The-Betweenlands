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
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import org.joml.Matrix4f;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.BLFishHookModel;
import thebetweenlands.client.state.HookRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.fishing.BLFishHook;

public class BLFishHookRenderer extends EntityRenderer<BLFishHook, HookRenderState> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/fish_hook.png");
	private final BLFishHookModel hook;

	public BLFishHookRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.hook = new BLFishHookModel(context.bakeLayer(BLModelLayers.FISH_HOOK));
	}

	@Override
	public void render(HookRenderState state, PoseStack stack, MultiBufferSource source, int light) {
		stack.pushPose();
		stack.pushPose();
		stack.translate(0.0F, 1.1F, 0.0F);
		stack.scale(-0.8F, -0.8F, 0.8F);
		this.hook.renderToBuffer(stack, source.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), light, OverlayTexture.NO_OVERLAY);
		stack.popPose();
		float startX = (float) state.lineOriginOffset.x();
		float startY = (float) state.lineOriginOffset.y();
		float startZ = (float) state.lineOriginOffset.z();

		VertexConsumer consumer = source.getBuffer(RenderType.leash());
		Matrix4f pose = stack.last().pose();

		for (int count = 0; count <= 48; count++) {
			renderString(consumer, pose, startX, startY, startZ, 0.2375F, 0.2625F, state, count, false);
		}

		for (int count = 48; count >= 0; count--) {
			renderString(consumer, pose, startX, startY, startZ, 0.2625F, 0.2375F, state, count, true);
		}

		stack.popPose();
		super.render(state, stack, source, light);
	}

	private static void renderString(VertexConsumer consumer, Matrix4f pose, float startX, float startY, float startZ, float minOffsetY, float maxOffsetY, HookRenderState state, int index, boolean reverse) {
		float f = index / 48.0F;
		int i = (int) Mth.lerp(f, state.startBlockLight, state.endBlockLight);
		int j = (int) Mth.lerp(f, state.startSkyLight, state.endSkyLight);
		int k = LightTexture.pack(i, j);

		float f1 = index % 2 == (reverse ? 1 : 0) ? 0.8F : 1.0F;
		float f2 = 0.9764F * f1;
		float f3 = 0.9215F * f1;
		float f4 = 0.8F * f1;
		consumer.addVertex(pose, startX * f - 0.0125F, startY * (f * f + f) * 0.5F + maxOffsetY, startZ * f).setColor(f2, f3, f4, 1.0F).setLight(k);
		consumer.addVertex(pose, startX * f + 0.0125F, startY * (f * f + f) * 0.5F + minOffsetY, startZ * f).setColor(f2, f3, f4, 1.0F).setLight(k);
	}

	private Vec3 getPlayerHandPos(Player player, float attackTime, float partialTick) {
		int i = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
		ItemStack itemstack = player.getMainHandItem();
		if (!itemstack.canPerformAction(ItemAbilities.FISHING_ROD_CAST)) {
			i = -i;
		}

		if (this.entityRenderDispatcher.options.getCameraType().isFirstPerson() && player == Minecraft.getInstance().player) {
			double d4 = 960.0 / (double) this.entityRenderDispatcher.options.fov().get();
			Vec3 vec3 = this.entityRenderDispatcher.camera.getNearPlane().getPointOnPlane((float) i * 0.525F, -0.1F).scale(d4).yRot(attackTime * 0.5F).xRot(-attackTime * 0.7F);
			return player.getEyePosition(partialTick).add(vec3);
		} else {
			float f = Mth.lerp(partialTick, player.yBodyRotO, player.yBodyRot) * (float) (Math.PI / 180.0);
			double d0 = Mth.sin(f);
			double d1 = Mth.cos(f);
			float f1 = player.getScale();
			double d2 = (double) i * 0.35 * (double) f1;
			double d3 = 0.8 * (double) f1;
			float f2 = player.isCrouching() ? -0.1875F : 0.0F;
			return player.getEyePosition(partialTick).add(-d1 * d2 - d0 * d3, (double) f2 - 0.45 * (double) f1, -d0 * d2 + d1 * d3);
		}
	}

	public HookRenderState createRenderState() {
		return new HookRenderState();
	}

	public void extractRenderState(BLFishHook entity, HookRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		Player player = entity.getPlayerOwner();
		if (player == null) {
			state.lineOriginOffset = Vec3.ZERO;
		} else {
			float f = player.getAttackAnim(partialTick);
			float f1 = Mth.sin(Mth.sqrt(f) * Mth.PI);
			Vec3 vec3 = this.getPlayerHandPos(player, f1, partialTick);
			Vec3 vec31 = entity.getPosition(partialTick).add(0.0, 0.25, 0.0);

			BlockPos hookPos = BlockPos.containing(entity.getEyePosition(partialTick));
			BlockPos playerPos = BlockPos.containing(player.getEyePosition(partialTick));
			state.lineOriginOffset = vec3.subtract(vec31);
			state.startBlockLight = this.getBlockLightLevel(entity, hookPos);
			state.endBlockLight = this.entityRenderDispatcher.getRenderer(player).getBlockLightLevel(player, playerPos);
			state.startSkyLight = entity.level().getBrightness(LightLayer.SKY, hookPos);
			state.endSkyLight = entity.level().getBrightness(LightLayer.SKY, playerPos);
		}
	}
}
