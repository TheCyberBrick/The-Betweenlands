package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.EmberlingShamanModel;
import thebetweenlands.client.renderer.entity.layers.GenericEyesLayer;
import thebetweenlands.client.state.EmberlingShamanRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.EmberlingShaman;

public class EmberlingShamanRenderer extends MobRenderer<EmberlingShaman, EmberlingShamanRenderState, EmberlingShamanModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/emberling.png");

	public EmberlingShamanRenderer(EntityRendererProvider.Context context) {
		super(context, new EmberlingShamanModel(context.bakeLayer(BLModelLayers.EMBERLING_SHAMAN)), 0.5F);
		this.addLayer(new GenericEyesLayer<>(this, TheBetweenlands.prefix("textures/entity/emberling_glow.png")));
	}

	@Override
	public EmberlingShamanRenderState createRenderState() {
		return new EmberlingShamanRenderState();
	}

	@Override
	public void extractRenderState(EmberlingShaman entity, EmberlingShamanRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.animationTicks = Mth.lerp(partialTick, entity.prevAnimationTicks, entity.animationTicks);
	}

	@Override
	public ResourceLocation getTextureLocation(EmberlingShamanRenderState state) {
		return TEXTURE;
	}
}
