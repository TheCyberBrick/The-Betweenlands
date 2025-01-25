package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.ShamblerModel;
import thebetweenlands.client.state.ShamblerRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.Shambler;
import thebetweenlands.common.entity.monster.ShamblerTongueMultipart;

import java.util.ArrayList;
import java.util.List;

public class ShamblerRenderer extends MobRenderer<Shambler, ShamblerRenderState, ShamblerModel> {
	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/shambler.png");

	public ShamblerRenderer(EntityRendererProvider.Context context) {
		super(context, new ShamblerModel(context.bakeLayer(BLModelLayers.SHAMBLER)), 0.5F);
	}

	@Override
    protected void scale(ShamblerRenderState state, PoseStack stack) {
		float flap = (float) (Math.sin(state.ageInTicks * 0.3F) * 0.8F);
		stack.translate(0.0F, -flap * 0.0625F, 0.0F);
    }

	@Override
	public void render(ShamblerRenderState state, PoseStack stack, MultiBufferSource buffer, int light) {
		super.render(state, stack, buffer, light);
		boolean isVisible = this.isBodyVisible(state);
		boolean isTranslucentToPlayer = !isVisible && !state.isInvisibleToPlayer;
		int overlay = getOverlayCoords(state, this.getWhiteOverlayProgress(state));
		int colour = isTranslucentToPlayer ? 654311423 : -1;

		if (state.tongueLength > 0) {
			RenderType renderType = this.getRenderType(state, isVisible, isTranslucentToPlayer, state.appearsGlowing);
			if (renderType != null) {
				for (int i = 0; i < state.tongueParts.length; i++) {
					renderTonguePart(state, state.tongueParts[i], stack, buffer.getBuffer(renderType), light, overlay, colour);
				}
			}
		}
	}

	private void renderTonguePart(ShamblerRenderState state, ShamblerRenderState.ShamblerTongueInfo info, PoseStack stack, VertexConsumer consumer, int packedLight, int overlay, int colour) {
		double x = info.pos().x() - state.x;
		double y = info.pos().y() - state.y;
		double z = info.pos().z() - state.z;
		stack.pushPose();
		stack.translate(x, y - 0.9375F, z);
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.mulPose(Axis.YP.rotationDegrees(180.0F + state.yRot));
		stack.mulPose(Axis.XP.rotationDegrees(180.0F + state.xRot));
		this.model.renderTonguePart(stack, consumer, packedLight, overlay, colour, info.end());
		stack.popPose();
	}

	@Override
	public ShamblerRenderState createRenderState() {
		return new ShamblerRenderState();
	}

	@Override
	public void extractRenderState(Shambler entity, ShamblerRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.jawsOpen = entity.areJawsOpen();
		state.tongueLength = entity.getTongueLength();
		state.jawAngle = Mth.lerp(partialTick, entity.getJawAnglePrev(), entity.getJawAngle());
		List<ShamblerRenderState.ShamblerTongueInfo> partList = new ArrayList<>();
		for (ShamblerTongueMultipart part : entity.tongue_array) {
			double x = Mth.lerp(partialTick, part.xOld, part.getX());
			double y = Mth.lerp(partialTick, part.yOld, part.getY());
			double z = Mth.lerp(partialTick, part.zOld, part.getZ());
			partList.add(new ShamblerRenderState.ShamblerTongueInfo(new Vec3(x, y, z), part == entity.tongue_end));
		}
		state.tongueParts = partList.toArray(ShamblerRenderState.ShamblerTongueInfo[]::new);
	}

	@Override
	public ResourceLocation getTextureLocation(ShamblerRenderState state) {
		return TEXTURE;
	}
}
