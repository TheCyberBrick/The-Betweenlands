package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.GeckoModel;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.Gecko;

public class GeckoRenderer extends MobRenderer<Gecko, LivingEntityRenderState, GeckoModel> {

	private static final ResourceLocation GECKO_TEXTURE = TheBetweenlands.prefix("textures/entity/gecko/gecko.png");

	public GeckoRenderer(EntityRendererProvider.Context context) {
		super(context, new GeckoModel(context.bakeLayer(BLModelLayers.GECKO)), 0.25F);
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack stack) {
		stack.scale(0.74F, 0.74F, 0.74F);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return GECKO_TEXTURE;
	}
}
