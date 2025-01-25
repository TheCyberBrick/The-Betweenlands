package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.EmberlingModel;
import thebetweenlands.client.renderer.entity.layers.GenericEyesLayer;
import thebetweenlands.client.state.EmberlingRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.Emberling;

public class EmberlingRenderer extends MobRenderer<Emberling, EmberlingRenderState, EmberlingModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/emberling.png");
	public static final ResourceLocation TEXTURE_SLEEPING = TheBetweenlands.prefix("textures/entity/emberling_sleeping.png");

	public EmberlingRenderer(EntityRendererProvider.Context context) {
		super(context, new EmberlingModel(context.bakeLayer(BLModelLayers.EMBERLING)), 0.5F);
		this.addLayer(new GenericEyesLayer<>(this, TheBetweenlands.prefix("textures/entity/emberling_glow.png")) {
			@Override
			public void render(PoseStack stack, MultiBufferSource buffer, int light, EmberlingRenderState state, float netHeadYaw, float headPitch) {
				if (!state.sleeping) {
					super.render(stack, buffer, light, state, netHeadYaw, headPitch);
				}
			}
		});
	}

	@Override
	public EmberlingRenderState createRenderState() {
		return new EmberlingRenderState();
	}

	@Override
	public void extractRenderState(Emberling entity, EmberlingRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.sleeping = entity.isInSittingPose();
		state.shootingFlames = entity.isShootingFlames();
		state.animationTicks = Mth.lerp(partialTick, entity.prevAnimationTicks, entity.animationTicks);
	}

	@Override
	public ResourceLocation getTextureLocation(EmberlingRenderState state) {
		return state.sleeping ? TEXTURE_SLEEPING : TEXTURE;
	}
}
