package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.FreshwaterUrchinModel;
import thebetweenlands.client.state.FreshwaterUrchinRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.FreshwaterUrchin;

public class FreshwaterUrchinRenderer extends MobRenderer<FreshwaterUrchin, FreshwaterUrchinRenderState, FreshwaterUrchinModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/freshwater_urchin.png");

	public FreshwaterUrchinRenderer(EntityRendererProvider.Context context) {
		super(context, new FreshwaterUrchinModel(context.bakeLayer(BLModelLayers.FRESHWATER_URCHIN)), 0.2F);
	}

	@Override
	public FreshwaterUrchinRenderState createRenderState() {
		return new FreshwaterUrchinRenderState();
	}

	@Override
	public void extractRenderState(FreshwaterUrchin entity, FreshwaterUrchinRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.spikeGrowTimer = entity.getSpikeGrowTimer();
	}

	@Override
	public ResourceLocation getTextureLocation(FreshwaterUrchinRenderState state) {
		return TEXTURE;
	}
}
