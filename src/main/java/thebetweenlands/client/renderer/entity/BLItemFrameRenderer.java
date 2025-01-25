package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.RenderItemInFrameEvent;
import net.neoforged.neoforge.common.NeoForge;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.BLItemFrame;
import thebetweenlands.util.BLDyeColor;

public class BLItemFrameRenderer extends ItemFrameRenderer<BLItemFrame> {

	public static final ModelResourceLocation FRAME_MODEL = ModelResourceLocation.standalone(TheBetweenlands.prefix("block/item_frame"));
	public static final ModelResourceLocation FRAME_MAP_MODEL = ModelResourceLocation.standalone(TheBetweenlands.prefix("block/item_frame_map"));
	public static final ModelResourceLocation FRAME_BG_MODEL = ModelResourceLocation.standalone(TheBetweenlands.prefix("block/item_frame_background"));
	private final ItemRenderer itemRenderer;
	private final BlockRenderDispatcher blockRenderer;
	private int color = BLDyeColor.DULL_LAVENDER.getColorValue();

	public BLItemFrameRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.itemRenderer = context.getItemRenderer();
		this.blockRenderer = context.getBlockRenderDispatcher();
	}

	@Override
	protected int getBlockLightLevel(BLItemFrame entity, BlockPos pos) {
		return entity.isFrameGlowing() ? Math.max(5, super.getBlockLightLevel(entity, pos)) : super.getBlockLightLevel(entity, pos);
	}

	@Override
	public void render(ItemFrameRenderState state, PoseStack stack, MultiBufferSource buffer, int light) {
		if (state.nameTag != null) {
			var event = new net.neoforged.neoforge.client.event.RenderNameTagEvent.DoRender(state, state.nameTag, this, stack, buffer, light, state.partialTick);
			if (!net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(event).isCanceled())
				this.renderNameTag(state, state.nameTag, stack, buffer, light);
		}

		stack.pushPose();
		Direction direction = state.direction;
		Vec3 vec3 = this.getRenderOffset(state);
		stack.translate(-vec3.x(), -vec3.y(), -vec3.z());
		stack.translate((double)direction.getStepX() * 0.46875, (double)direction.getStepY() * 0.46875, (double)direction.getStepZ() * 0.46875);
		float f;
		float f1;
		if (direction.getAxis().isHorizontal()) {
			f = 0.0F;
			f1 = 180.0F - direction.toYRot();
		} else {
			f = (float)(-90 * direction.getAxisDirection().getStep());
			f1 = 180.0F;
		}

		stack.mulPose(Axis.XP.rotationDegrees(f));
		stack.mulPose(Axis.YP.rotationDegrees(f1));
		ItemStack itemstack = state.itemStack;
		if (!state.isInvisible) {
			ModelManager modelmanager = this.blockRenderer.getBlockModelShaper().getModelManager();
			stack.pushPose();
			stack.translate(-0.5F, -0.5F, -0.5F);
			int frameLight = state.isGlowFrame ? LightTexture.FULL_BRIGHT : light;
			if (itemstack.getItem() instanceof MapItem) {
				this.blockRenderer.getModelRenderer().renderModel(stack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null, modelmanager.getModel(FRAME_MAP_MODEL), 1.0F, 1.0F, 1.0F, frameLight, OverlayTexture.NO_OVERLAY);
			} else {
				this.blockRenderer.getModelRenderer().renderModel(stack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null, modelmanager.getModel(FRAME_MODEL), 1.0F, 1.0F, 1.0F, light, OverlayTexture.NO_OVERLAY);
				int color = this.color;
				float r = ((color >> 16) & 0xFF) / 255.0f;
				float g = ((color >> 8) & 0xFF) / 255.0f;
				float b = ((color) & 0xFF) / 255.0f;
				this.blockRenderer.getModelRenderer().renderModel(stack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null, modelmanager.getModel(FRAME_BG_MODEL), r, g, b, frameLight, OverlayTexture.NO_OVERLAY);
			}
			stack.popPose();
		}

		if (!itemstack.isEmpty()) {
			MapId mapid = state.mapId;
			if (state.isInvisible) {
				stack.translate(0.0F, 0.0F, 0.5F);
			} else {
				stack.translate(0.0F, 0.0F, 0.4375F);
			}

			int j = mapid != null ? state.rotation % 4 * 2 : state.rotation;
			stack.mulPose(Axis.ZP.rotationDegrees((float)j * 360.0F / 8.0F));
			if (!NeoForge.EVENT_BUS.post(new RenderItemInFrameEvent(state, this, stack, buffer, light)).isCanceled()) {
				if (mapid != null) {
					stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
					stack.scale(0.0078125F, 0.0078125F, 0.0078125F);
					stack.translate(-64.0F, -64.0F, 0.0F);
					stack.translate(0.0F, 0.0F, -1.0F);
					int i = state.isGlowFrame ? LightTexture.FULL_BRIGHT : light;
					Minecraft.getInstance().getMapRenderer().render(state.mapRenderState, stack, buffer, true, i);
				} else if (state.itemModel != null) {
					int k = state.isGlowFrame ? LightTexture.FULL_BRIGHT : light;
					stack.scale(0.5F, 0.5F, 0.5F);
					this.itemRenderer.render(itemstack, ItemDisplayContext.FIXED, false, stack, buffer, k, OverlayTexture.NO_OVERLAY, state.itemModel);
				}
			}
		}

		stack.popPose();
	}

	@Override
	public void extractRenderState(BLItemFrame entity, ItemFrameRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		this.color = entity.getColor();
	}
}
