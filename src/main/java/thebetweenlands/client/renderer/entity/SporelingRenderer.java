package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.SporelingModel;
import thebetweenlands.client.renderer.entity.layers.GenericEyesLayer;
import thebetweenlands.client.state.SporelingRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.creature.Sporeling;

public class SporelingRenderer extends MobRenderer<Sporeling, SporelingRenderState, SporelingModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/sporeling.png");

	public SporelingRenderer(EntityRendererProvider.Context context) {
		super(context, new SporelingModel(context.bakeLayer(BLModelLayers.SPORELING)), 0.2F);
		this.addLayer(new GenericEyesLayer<>(this, TheBetweenlands.prefix("textures/entity/sporeling_glow.png")));
	}

	@Override
	protected void setupRotations(SporelingRenderState state, PoseStack stack, float yBodyRot, float scale) {
		if (state.falling) {
			stack.mulPose(Axis.YP.rotationDegrees(state.rotationTicks));
		}
		super.setupRotations(state, stack, yBodyRot, scale);
	}

	@Override
	public SporelingRenderState createRenderState() {
		return new SporelingRenderState();
	}

	@Override
	public void extractRenderState(Sporeling entity, SporelingRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.falling = entity.getIsFalling();
		state.passenger = entity.isPassenger();
		state.rotationTicks = Mth.lerp(partialTick, entity.prevFloatingRotationTicks, entity.floatingRotationTicks);
	}

	@Override
	public ResourceLocation getTextureLocation(SporelingRenderState state) {
		return TEXTURE;
	}
}
