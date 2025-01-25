package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.RootSpriteModel;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.RootSprite;

public class RootSpriteRenderer extends MobRenderer<RootSprite, LivingEntityRenderState, RootSpriteModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/root_sprite.png");

	public RootSpriteRenderer(EntityRendererProvider.Context context) {
		super(context, new RootSpriteModel(context.bakeLayer(BLModelLayers.ROOT_SPRITE)), 0.2F);
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
