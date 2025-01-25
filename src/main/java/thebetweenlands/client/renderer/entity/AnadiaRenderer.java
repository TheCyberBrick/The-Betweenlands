package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.common.NeoForge;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.AnadiaModel;
import thebetweenlands.client.state.AnadiaRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.fishing.anadia.Anadia;
import thebetweenlands.common.entity.fishing.anadia.AnadiaParts;

import javax.annotation.Nullable;

public class AnadiaRenderer extends MobRenderer<Anadia, AnadiaRenderState, AnadiaModel> {

	public AnadiaRenderer(EntityRendererProvider.Context context) {
		super(context, new AnadiaModel(context.bakeLayer(BLModelLayers.ANADIA)), 0.5F);
	}

	@Override
	protected void setupRotations(AnadiaRenderState state, PoseStack stack, float yRot, float scale) {
		stack.mulPose(Axis.YP.rotationDegrees(180 - state.yRot));

		float fishSize = state.fishSize;
		stack.translate(0, 0.4f * fishSize, 0);
		//stack.mulPose(Axis.XP.rotationDegrees(-smoothedPitch));
		stack.translate(0, -0.4f * fishSize, 0);

		super.setupRotations(state, stack, yRot, scale);
	}

	@Override
	protected void scale(AnadiaRenderState state, PoseStack stack) {
		stack.scale(state.fishSize, state.fishSize, state.fishSize);
	}

	//[VanillaCopy] of LivingEntityRenderer.render, replaced Model.renderToBuffer with a custom rendering method
	@Override
	public void render(AnadiaRenderState state, PoseStack stack, MultiBufferSource buffer, int light) {
		if (NeoForge.EVENT_BUS.post(new RenderLivingEvent.Pre<>(state, this, state.partialTick, stack, buffer, light)).isCanceled()) return;
		stack.pushPose();
		float f1 = state.scale;
		stack.scale(f1, f1, f1);
		this.setupRotations(state, stack, state.bodyRot, f1);
		stack.scale(-1.0F, -1.0F, 1.0F);
		this.scale(state, stack);
		stack.translate(0.0F, -1.501F, 0.0F);
		this.model.setupAnim(state);
		boolean visible = this.isBodyVisible(state);
		boolean translucent = !visible && !state.isInvisibleToPlayer;
		this.renderFish(state, stack, buffer, light, visible, translucent, state.appearsGlowing);

		if (this.shouldRenderLayers(state)) {
			for (RenderLayer<AnadiaRenderState, AnadiaModel> renderlayer : this.layers) {
				renderlayer.render(stack, buffer, light, state, state.yRot, state.xRot);
			}
		}

		stack.popPose();
		if (state.nameTag != null) {
			var event = new RenderNameTagEvent.DoRender(state, state.nameTag, this, stack, buffer, light, state.partialTick);
			if (!NeoForge.EVENT_BUS.post(event).isCanceled())
				this.renderNameTag(state, state.nameTag, stack, buffer, light);
		}
		NeoForge.EVENT_BUS.post(new RenderLivingEvent.Post<>(state, this, state.partialTick, stack, buffer, light));
	}

	@Override
	public AnadiaRenderState createRenderState() {
		return new AnadiaRenderState();
	}

	@Override
	public void extractRenderState(Anadia entity, AnadiaRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.fishSize = entity.getFishSize();
		state.color = entity.getFishColor();
		state.headType = entity.getHeadType();
		state.bodyType = entity.getBodyType();
		state.tailType = entity.getTailType();
	}

	@Override //not used here, still required
	public ResourceLocation getTextureLocation(AnadiaRenderState state) {
		return TheBetweenlands.prefix("textures/entity/anadia_1_base.png");
	}

	private void renderFish(AnadiaRenderState state, PoseStack stack, MultiBufferSource buffer, int light, boolean visible, boolean translucent, boolean glowing) {
		int overlay = getOverlayCoords(state, this.getWhiteOverlayProgress(state));
		int j = translucent ? 654311423 : -1;
		int color = ARGB.multiply(j, this.getModelTint(state));

		RenderType rendertype = this.getRenderType(state.headType, state, visible, translucent, glowing);
		if (rendertype != null) {
			VertexConsumer vertexconsumer = buffer.getBuffer(rendertype);
			this.model.renderPart(state.headType, "head", stack, vertexconsumer, light, overlay, color);
		}

		rendertype = this.getRenderType(state.bodyType, state, visible, translucent, glowing);
		if (rendertype != null) {
			VertexConsumer vertexconsumer = buffer.getBuffer(rendertype);
			this.model.renderPart(state.bodyType, "body", stack, vertexconsumer, light, overlay, color);
		}

		rendertype = this.getRenderType(state.tailType, state, visible, translucent, glowing);
		if (rendertype != null) {
			VertexConsumer vertexconsumer = buffer.getBuffer(rendertype);
			this.model.renderPart(state.tailType, "tail", stack, vertexconsumer, light, overlay, color);
		}
	}

	@Nullable
	protected RenderType getRenderType(Enum<?> type, AnadiaRenderState state, boolean bodyVisible, boolean translucent, boolean glowing) {
		ResourceLocation texture = this.assembleTexturePath(type, state.color);
		if (translucent) {
			return RenderType.itemEntityTranslucentCull(texture);
		} else if (bodyVisible) {
			return this.model.renderType(texture);
		} else {
			return glowing ? RenderType.outline(texture) : null;
		}
	}

	private ResourceLocation assembleTexturePath(Enum<?> type, AnadiaParts.AnadiaColor color) {
		return TheBetweenlands.prefix("textures/entity/anadia/anadia_" + (type.ordinal() + 1) + "_" + color.getSerializedName() + ".png");
	}
}
