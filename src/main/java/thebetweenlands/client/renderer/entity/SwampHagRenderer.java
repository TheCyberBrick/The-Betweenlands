package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.renderer.entity.layers.GenericEyesLayer;
import thebetweenlands.client.model.entity.SwampHagModel;
import thebetweenlands.client.state.SwampHagRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.SwampHag;

public class SwampHagRenderer extends MobRenderer<SwampHag, SwampHagRenderState, SwampHagModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/swamp_hag.png");

	public SwampHagRenderer(EntityRendererProvider.Context context) {
		super(context, new SwampHagModel(context.bakeLayer(BLModelLayers.SWAMP_HAG)), 0.8F);
		this.addLayer(new GenericEyesLayer<>(this, TheBetweenlands.prefix("textures/entity/swamp_hag_eyes.png")));
	}

	@Override
	public void scale(SwampHagRenderState state, PoseStack stack) {
		stack.scale(0.74F, 0.74F, 0.74F);
	}

	@Override
	public SwampHagRenderState createRenderState() {
		return new SwampHagRenderState();
	}

	@Override
	public void extractRenderState(SwampHag entity, SwampHagRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.jawAngle = entity.jawFloat;
		state.isRidingMummy = entity.isRidingMummy();
		state.isMountEmerged = entity.getMummyMount() != null && entity.getMummyMount().isSpawningFinished();
		state.throwTimer = entity.getThrowTimer();
	}

	@Override
	public ResourceLocation getTextureLocation(SwampHagRenderState state) {
		return TEXTURE;
	}
}
