package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.CaveFishModel;
import thebetweenlands.client.renderer.entity.layers.GenericEyesLayer;
import thebetweenlands.client.state.CaveFishRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.CaveFish;

public class CaveFishRenderer extends MobRenderer<CaveFish, CaveFishRenderState, CaveFishModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/cave_fish.png");

	public CaveFishRenderer(EntityRendererProvider.Context context) {
		super(context, new CaveFishModel(context.bakeLayer(BLModelLayers.CAVE_FISH)), 0.25F);
		this.addLayer(new GenericEyesLayer<>(this, TheBetweenlands.prefix("textures/entity/cave_fish_leader_glow.png")) {
			@Override
			public void render(PoseStack stack, MultiBufferSource buffer, int light, CaveFishRenderState state, float netHeadYaw, float headPitch) {
				if (state.isLeader) {
					super.render(stack, buffer, light, state, netHeadYaw, headPitch);
				}
			}
		});
	}

	@Override
	public CaveFishRenderState createRenderState() {
		return new CaveFishRenderState();
	}

	@Override
	public void extractRenderState(CaveFish entity, CaveFishRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.isLeader = entity.isLeader();
	}

	@Override
	protected void scale(CaveFishRenderState state, PoseStack stack) {
		if (!state.isLeader) {
			stack.scale(0.5F, 0.5F, 0.5F);
		}
		stack.translate(0, -0.25F, 0);
		stack.mulPose(Axis.XP.rotationDegrees(state.xRot));
		stack.translate(0, 0.75F, 0);
	}

	@Override
	public ResourceLocation getTextureLocation(CaveFishRenderState state) {
		return TEXTURE;
	}
}
