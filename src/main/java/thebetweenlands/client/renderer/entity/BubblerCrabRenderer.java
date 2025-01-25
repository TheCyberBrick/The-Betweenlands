package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.BubblerCrabModel;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.fishing.BubblerCrab;

public class BubblerCrabRenderer extends MobRenderer<BubblerCrab, LivingEntityRenderState, BubblerCrabModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/bubbler_crab.png");

	public BubblerCrabRenderer(EntityRendererProvider.Context context) {
		super(context, new BubblerCrabModel(context.bakeLayer(BLModelLayers.BUBBLER_CRAB)), 0.5F);
	}

	@Override
	protected void setupRotations(LivingEntityRenderState state, PoseStack stack, float yRot, float scale) {
		stack.mulPose(Axis.YN.rotationDegrees(90.0F));
		super.setupRotations(state, stack, yRot, scale);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return TEXTURE;
	}
}
