package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.TarminionModel;
import thebetweenlands.client.renderer.entity.layers.AnimatedLayer;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.Tarminion;

public class TarminionRenderer extends MobRenderer<Tarminion, LivingEntityRenderState, TarminionModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/tarminion.png");

	public TarminionRenderer(EntityRendererProvider.Context context) {
		super(context, new TarminionModel(context.bakeLayer(BLModelLayers.TARMINION)), 0.2F);
		this.addLayer(new AnimatedLayer<>(this, TheBetweenlands.prefix("textures/entity/tarminion_overlay.png")));
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
