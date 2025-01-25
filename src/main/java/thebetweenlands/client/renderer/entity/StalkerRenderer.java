package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.StalkerModel;
import thebetweenlands.client.state.StalkerRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.Stalker;

public class StalkerRenderer extends ClimberRenderer<Stalker, StalkerRenderState, StalkerModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/stalker.png");

	public StalkerRenderer(EntityRendererProvider.Context context) {
		super(context, new StalkerModel(context.bakeLayer(BLModelLayers.STALKER)), 0.5F);
	}

	@Override
	public StalkerRenderState createRenderState() {
		return new StalkerRenderState();
	}

	@Override
	public void extractRenderState(Stalker entity, StalkerRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.eyeRotation = entity.prevEyeRotation.add(entity.eyeRotation.subtract(entity.prevEyeRotation).scale(partialTick));
		state.screechingTicks = Mth.lerp(partialTick, entity.prevScreechingTicks, entity.screechingTicks);
	}

	@Override
	public ResourceLocation getTextureLocation(StalkerRenderState state) {
		return TEXTURE;
	}
}
