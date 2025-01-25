package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.GreeblingCoracleModel;
import thebetweenlands.client.state.GreeblingCoracleRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.GreeblingCoracle;

public class GreeblingCoracleRenderer extends MobRenderer<GreeblingCoracle, GreeblingCoracleRenderState, GreeblingCoracleModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/greebling_coracle.png");

	public GreeblingCoracleRenderer(EntityRendererProvider.Context context) {
		super(context, new GreeblingCoracleModel(context.bakeLayer(BLModelLayers.GREEBLING_CORACLE)), 0.0F);
	}

	@Override
	public GreeblingCoracleRenderState createRenderState() {
		return new GreeblingCoracleRenderState();
	}

	@Override
	public void extractRenderState(GreeblingCoracle entity, GreeblingCoracleRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.aboveWater = entity.isGreeblingAboveWater();
		state.sinkTicks = entity.getSinkingTicks();
	}

	@Override
	public ResourceLocation getTextureLocation(GreeblingCoracleRenderState state) {
		return TEXTURE;
	}
}
