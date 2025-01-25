package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.FrogModel;
import thebetweenlands.client.state.FrogRenderState;
import thebetweenlands.common.entity.creature.frog.Frog;

public class FrogRenderer extends MobRenderer<Frog, FrogRenderState, FrogModel> {
	public FrogRenderer(EntityRendererProvider.Context context) {
		super(context, new FrogModel(context.bakeLayer(BLModelLayers.FROG)), 0.2F);
	}

	@Override
	public FrogRenderState createRenderState() {
		return new FrogRenderState();
	}

	@Override
	public void extractRenderState(Frog entity, FrogRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.texture = entity.getVariant().value().texture();
		state.jumpTicks = entity.jumpAnimationTicks > 0 ? Mth.lerp(partialTick, entity.prevJumpAnimationTicks, entity.jumpAnimationTicks) : 0;
		state.onGround = entity.onGround();
	}

	@Override
	public ResourceLocation getTextureLocation(FrogRenderState state) {
		return state.texture;
	}
}
