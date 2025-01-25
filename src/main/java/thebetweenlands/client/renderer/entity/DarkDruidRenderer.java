package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.DarkDruidModel;
import thebetweenlands.client.renderer.entity.layers.GenericEyesLayer;
import thebetweenlands.client.state.DarkDruidRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.DarkDruid;

public class DarkDruidRenderer extends MobRenderer<DarkDruid, DarkDruidRenderState, DarkDruidModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/dark_druid.png");

	public DarkDruidRenderer(EntityRendererProvider.Context context) {
		super(context, new DarkDruidModel(context.bakeLayer(BLModelLayers.DARK_DRUID)), 0.7F);
		this.addLayer(new GenericEyesLayer<>(this, TheBetweenlands.prefix("textures/entity/dark_druid_glow.png")));
	}

	@Override
	public DarkDruidRenderState createRenderState() {
		return new DarkDruidRenderState();
	}

	@Override
	public void extractRenderState(DarkDruid entity, DarkDruidRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.attackAnimationTime = Mth.lerp(partialTick, entity.prevAttackAnimationTime, entity.attackAnimationTime) / DarkDruid.MAX_ATTACK_ANIMATION_TIME;
	}

	@Override
	public ResourceLocation getTextureLocation(DarkDruidRenderState state) {
		return TEXTURE;
	}
}
