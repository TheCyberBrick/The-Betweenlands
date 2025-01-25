package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.DreadfulPeatMummyModel;
import thebetweenlands.client.state.DreadfulPeatMummyRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.boss.DreadfulPeatMummy;

public class DreadfulPeatMummyRenderer extends MobRenderer<DreadfulPeatMummy, DreadfulPeatMummyRenderState, DreadfulPeatMummyModel> {
	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/dreadful_mummy.png");

	public DreadfulPeatMummyRenderer(EntityRendererProvider.Context context) {
		super(context, new DreadfulPeatMummyModel(context.bakeLayer(BLModelLayers.DREADFUL_PEAT_MUMMY)), 0.7F);
	}

	@Override
	public void render(DreadfulPeatMummyRenderState state, PoseStack stack, MultiBufferSource buffer, int light) {
		if (state.spawningProgress > 0) {
			this.shadowRadius = Math.max(0.0F, state.spawningProgress - 0.3F);
			super.render(state, stack, buffer, light);
		}
	}

	@Override
	protected void scale(DreadfulPeatMummyRenderState state, PoseStack stack) {
		stack.translate(0.0D, -state.yOffset, 0.0D);
	}

	@Override
	public DreadfulPeatMummyRenderState createRenderState() {
		return new DreadfulPeatMummyRenderState();
	}

	@Override
	public void extractRenderState(DreadfulPeatMummy entity, DreadfulPeatMummyRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.spawningProgress = entity.getSpawningLength() == 0 ? 1.0F : 1.0F / entity.getSpawningLength() * Mth.lerp(partialTick, entity.prevSpawningState, entity.getSpawningState());
		state.yOffset = Mth.lerp(partialTick, entity.prevYOffset, entity.getYOffset());
		state.hasPrey = entity.currentEatPrey != null;
	}

	@Override
	public ResourceLocation getTextureLocation(DreadfulPeatMummyRenderState state) {
		return TEXTURE;
	}
}
