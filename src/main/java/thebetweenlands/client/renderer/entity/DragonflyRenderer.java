package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.DragonflyModel;
import thebetweenlands.client.state.DragonflyRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.Dragonfly;

public class DragonflyRenderer extends MobRenderer<Dragonfly, DragonflyRenderState, DragonflyModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/dragonfly.png");

	public DragonflyRenderer(EntityRendererProvider.Context context) {
		super(context, new DragonflyModel(context.bakeLayer(BLModelLayers.DRAGONFLY)), 0.5F);
	}

	@Override
	protected void scale(DragonflyRenderState state, PoseStack stack) {
		if (state.flying) stack.mulPose(Axis.XP.rotationDegrees(state.xRot));
		stack.scale(0.6F, 0.6F, 0.6F);
	}

	@Override
	public DragonflyRenderState createRenderState() {
		return new DragonflyRenderState();
	}

	@Override
	public void extractRenderState(Dragonfly entity, DragonflyRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.flying = entity.isFlying();
	}

	@Override
	public ResourceLocation getTextureLocation(DragonflyRenderState state) {
		return TEXTURE;
	}
}
