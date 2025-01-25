package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.SplodeshroomModel;
import thebetweenlands.client.state.SplodeshroomRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.Splodeshroom;

public class SplodeshroomRenderer extends MobRenderer<Splodeshroom, SplodeshroomRenderState, SplodeshroomModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/splodeshroom.png");

	public SplodeshroomRenderer(EntityRendererProvider.Context context) {
		super(context, new SplodeshroomModel(context.bakeLayer(BLModelLayers.SPLODESHROOM)), 0.0F);
	}

	@Override
	public SplodeshroomRenderState createRenderState() {
		return new SplodeshroomRenderState();
	}

	@Override
	public void extractRenderState(Splodeshroom entity, SplodeshroomRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.exploded = entity.getHasExploded();
		state.swellCount = entity.getSwellCount();
	}

	@Override
	public ResourceLocation getTextureLocation(SplodeshroomRenderState state) {
		return TEXTURE;
	}
}
