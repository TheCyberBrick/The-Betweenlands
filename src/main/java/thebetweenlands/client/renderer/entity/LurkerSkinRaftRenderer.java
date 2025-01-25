package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.state.LurkerSkinRaftRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.LurkerSkinRaft;

public class LurkerSkinRaftRenderer extends EntityRenderer<LurkerSkinRaft, LurkerSkinRaftRenderState> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/shield/lurker_skin_shield.png");
	private final ModelPart model;

	public LurkerSkinRaftRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = context.bakeLayer(BLModelLayers.LURKER_SKIN_SHIELD).getChild("handle").getChild("main");
	}

	@Override
	public void render(LurkerSkinRaftRenderState state, PoseStack stack, MultiBufferSource source, int light) {
		stack.pushPose();
		stack.translate(0.0F, 0.4F, 0.0F);
		stack.mulPose(Axis.YP.rotationDegrees(180.0F - state.yRot));
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.mulPose(Axis.XP.rotationDegrees(90.0F));
		VertexConsumer vertexconsumer = source.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
		this.model.render(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);

		stack.popPose();
		super.render(state, stack, source, light);
	}

	@Override
	public LurkerSkinRaftRenderState createRenderState() {
		return new LurkerSkinRaftRenderState();
	}

	@Override
	public void extractRenderState(LurkerSkinRaft entity, LurkerSkinRaftRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.yRot = entity.getYRot(partialTick);
	}
}
