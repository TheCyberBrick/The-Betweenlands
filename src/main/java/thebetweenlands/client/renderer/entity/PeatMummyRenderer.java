package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.PeatMummyModel;
import thebetweenlands.client.state.PeatMummyRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.PeatMummy;

public class PeatMummyRenderer extends MobRenderer<PeatMummy, PeatMummyRenderState, PeatMummyModel> {
	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/peat_mummy.png");

	public PeatMummyRenderer(EntityRendererProvider.Context context) {
		super(context, new PeatMummyModel(context.bakeLayer(BLModelLayers.PEAT_MUMMY)), 0.7F);
	}

	@Override
	public void render(PeatMummyRenderState state, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		if(state.spawningProgress >= 0.1F) {
			super.render(state, stack, buffer, packedLight);
		}
	}

	@Override
	protected void scale(PeatMummyRenderState state, PoseStack stack) {
		stack.translate(0.0D, -state.yOffset, 0.0D);
	}

	@Override
	protected float getShadowRadius(PeatMummyRenderState state) {
		return state.spawningProgress >= 0.1F ? super.getShadowRadius(state) : 0.0F;
	}

	@Override
	public PeatMummyRenderState createRenderState() {
		return new PeatMummyRenderState();
	}

	@Override
	public void extractRenderState(PeatMummy entity, PeatMummyRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.spawningProgress = entity.getSpawningProgress();
		state.yOffset = Mth.lerp(partialTick, entity.prevSpawningOffset, entity.getSpawningOffset());
		state.screamingProgress = entity.getScreamingProgress();
	}

	@Override
	public ResourceLocation getTextureLocation(PeatMummyRenderState state) {
		return TEXTURE;
	}
}
