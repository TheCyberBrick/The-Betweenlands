package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.OlmModel;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.Olm;

public class OlmRenderer extends MobRenderer<Olm, LivingEntityRenderState, OlmModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/olm.png");

	public OlmRenderer(EntityRendererProvider.Context context) {
		super(context, new OlmModel(context.bakeLayer(BLModelLayers.OLM)), 0.2F);
	}

	@Override
	protected void scale(LivingEntityRenderState state, PoseStack stack) {
		stack.scale(0.75F, 0.75F, 0.75F);
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
