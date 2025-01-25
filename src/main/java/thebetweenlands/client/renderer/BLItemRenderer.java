package thebetweenlands.client.renderer;

import com.google.common.base.Suppliers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.item.*;
import thebetweenlands.client.renderer.entity.DraetonRenderer;
import thebetweenlands.client.renderer.entity.WeedwoodRowboatRenderer;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.component.item.ShieldSpitData;
import thebetweenlands.common.item.shield.BaseShieldItem;
import thebetweenlands.common.item.shield.LivingWeedwoodShieldItem;
import thebetweenlands.common.registries.DataComponentRegistry;
import thebetweenlands.common.registries.ItemRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Supplier;

public class BLItemRenderer extends BlockEntityWithoutLevelRenderer {

	private static final Map<ResourceLocation, BlockEntity> cachedBEInstances = new WeakHashMap<>();
	public static final Supplier<BLItemRenderer> INSTANCE = Suppliers.memoize(BLItemRenderer::new);
	public static final IClientItemExtensions CLIENT_ITEM_EXTENSION = Util.make(() -> new IClientItemExtensions() {
		@Override
		public BlockEntityWithoutLevelRenderer getCustomRenderer() {
			return INSTANCE.get();
		}
	});
	private static final Map<Item, Model> SHIELD_MODELS = new HashMap<>();
	private final EntityModelSet models;
	private ModelPart face;
	private ModelPart draetonCarriage;
	private ModelPart draetonFurnace;
	private ModelPart draetonCrafting;
	private ModelPart draetonAnchor;
	private ModelPart weedwoodRowboat;
	private ModelPart lantern;

	private BLItemRenderer() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
		this.models = Minecraft.getInstance().getEntityModels();
	}

	@Override
	public void onResourceManagerReload(ResourceManager manager) {
		cachedBEInstances.clear();
		SHIELD_MODELS.clear();
		this.face = this.models.bakeLayer(BLModelLayers.SMALL_SPIRIT_TREE_FACE_2);
		this.draetonCarriage = this.models.bakeLayer(BLModelLayers.DRAETON_CARRIAGE);
		this.draetonFurnace = this.models.bakeLayer(BLModelLayers.DRAETON_FURNACE);
		this.draetonCrafting = this.models.bakeLayer(BLModelLayers.DRAETON_CRAFTING);
		this.draetonAnchor = this.models.bakeLayer(BLModelLayers.DRAETON_ANCHOR);
		this.weedwoodRowboat = this.models.bakeLayer(BLModelLayers.WEEDWOOD_ROWBOAT);
		this.lantern = this.models.bakeLayer(BLModelLayers.WEEDWOOD_ROWBOAT_LANTERN);
		SHIELD_MODELS.put(ItemRegistry.BONE_SHIELD.get(), new BoneShieldModel(this.models.bakeLayer(BLModelLayers.BONE_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.GREEN_DENTROTHYST_SHIELD.get(), new DentrothystShieldModel(this.models.bakeLayer(BLModelLayers.DENTROTHYST_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.ORANGE_DENTROTHYST_SHIELD.get(), new DentrothystShieldModel(this.models.bakeLayer(BLModelLayers.DENTROTHYST_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.POLISHED_GREEN_DENTROTHYST_SHIELD.get(), new DentrothystShieldModel(this.models.bakeLayer(BLModelLayers.DENTROTHYST_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.POLISHED_ORANGE_DENTROTHYST_SHIELD.get(), new DentrothystShieldModel(this.models.bakeLayer(BLModelLayers.DENTROTHYST_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.LURKER_SKIN_SHIELD.get(), new LurkerSkinShieldModel(this.models.bakeLayer(BLModelLayers.LURKER_SKIN_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.OCTINE_SHIELD.get(), new OctineShieldModel(this.models.bakeLayer(BLModelLayers.OCTINE_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.SYRMORITE_SHIELD.get(), new SyrmoriteShieldModel(this.models.bakeLayer(BLModelLayers.SYRMORITE_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.VALONITE_SHIELD.get(), new ValoniteShieldModel(this.models.bakeLayer(BLModelLayers.VALONITE_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.WEEDWOOD_SHIELD.get(), new WeedwoodShieldModel(this.models.bakeLayer(BLModelLayers.WEEDWOOD_SHIELD)));
		SHIELD_MODELS.put(ItemRegistry.LIVING_WEEDWOOD_SHIELD.get(), new WeedwoodShieldModel(this.models.bakeLayer(BLModelLayers.WEEDWOOD_SHIELD)));
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack pose, MultiBufferSource source, int light, int overlay) {
		Item item = stack.getItem();
		if (item instanceof BlockItem blockItem) {
			Block block = blockItem.getBlock();
			Minecraft minecraft = Minecraft.getInstance();
			if (block instanceof BaseEntityBlock entity) {
				cachedBEInstances.putIfAbsent(BuiltInRegistries.BLOCK.getKey(entity), entity.newBlockEntity(BlockPos.ZERO, entity.defaultBlockState()));
				minecraft.getBlockEntityRenderDispatcher().renderItem(cachedBEInstances.get(BuiltInRegistries.BLOCK.getKey(entity)), pose, source, light, overlay);
			}
		} else if (item instanceof BaseShieldItem shield) {
			Model shieldModel = SHIELD_MODELS.get(shield);
			if (shieldModel == null) return;
			pose.pushPose();
			pose.scale(-1.0F, -1.0F, -1.0F);
			Material material = new Material(Sheets.SHIELD_SHEET, TheBetweenlands.prefix("entity/shield/" + BuiltInRegistries.ITEM.getKey(item).getPath().replace("living_", "")));
			VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBuffer(source, shieldModel.renderType(material.atlasLocation()), true, stack.hasFoil()));
			shieldModel.renderToBuffer(pose, vertexconsumer, light, overlay);
			if (shield instanceof LivingWeedwoodShieldItem) {
				pose.pushPose();
				pose.translate(0.0D, -0.05D, -0.55D);
				pose.scale(0.75F, 0.75F, 0.75F);
				pose.mulPose(Axis.XP.rotationDegrees(-5.0F));
				this.face.render(pose, source.getBuffer(RenderType.entityCutoutNoCull(TheBetweenlands.prefix("textures/entity/small_spirit_tree_face.png"))), light, OverlayTexture.NO_OVERLAY);
				if (stack.getOrDefault(DataComponentRegistry.SHIELD_SPIT, ShieldSpitData.EMPTY).ticks() > 0) {
					int ticks = stack.getOrDefault(DataComponentRegistry.SHIELD_SPIT, ShieldSpitData.EMPTY).ticks();
					float alpha = (ticks - Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(true)) / 15.0F;
					this.face.render(pose, source.getBuffer(RenderType.entityTranslucent(TheBetweenlands.prefix("textures/entity/small_spirit_tree_face_glow.png"))), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, ARGB.white(alpha));
				}
				pose.popPose();
			}
			pose.popPose();
		} else if (stack.is(ItemRegistry.DRAETON)) {
			pose.pushPose();
			pose.scale(1.0F, -1.0F, -1.0F);
			pose.translate(0.5D, -1.25D, 0.0D);
			this.draetonCarriage.render(pose, source.getBuffer(RenderType.entityCutoutNoCull(DraetonRenderer.TEXTURE)), light, overlay);
			pose.popPose();
		} else if (stack.is(ItemRegistry.DRAETON_ANCHOR_UPGRADE)) {
			pose.pushPose();
			pose.scale(1.0F, -1.0F, -1.0F);
			pose.translate(1.0D, -1.25D, 0.0D);
			this.draetonAnchor.render(pose, source.getBuffer(RenderType.entityCutoutNoCull(DraetonRenderer.TEXTURE_ANCHOR)), light, overlay);
			pose.popPose();
		} else if (stack.is(ItemRegistry.DRAETON_CRAFTING_UPGRADE)) {
			pose.pushPose();
			pose.scale(1.0F, -1.0F, -1.0F);
			pose.translate(1.0D, -1.25D, 0.0D);
			this.draetonCrafting.render(pose, source.getBuffer(RenderType.entityCutoutNoCull(DraetonRenderer.TEXTURE_CRAFTING)), light, overlay);
			pose.popPose();
		} else if (stack.is(ItemRegistry.DRAETON_FURNACE_UPGRADE)) {
			pose.pushPose();
			pose.scale(1.0F, -1.0F, -1.0F);
			pose.translate(1.0D, -1.25D, 0.0D);
			this.draetonFurnace.render(pose, source.getBuffer(RenderType.entityCutoutNoCull(DraetonRenderer.TEXTURE_FURNACE)), light, overlay);
			pose.popPose();
		} else if (stack.is(ItemRegistry.WEEDWOOD_ROWBOAT)) {
			pose.pushPose();
			pose.scale(1.0F, -1.0F, -1.0F);
			pose.translate(1.0D, 0D, 0.0D);
			this.weedwoodRowboat.render(pose, source.getBuffer(RenderType.entityCutoutNoCull(WeedwoodRowboatRenderer.TEXTURE)), light, overlay);
			pose.popPose();
		} else if (stack.is(ItemRegistry.WEEDWOOD_ROWBOAT_LANTERN_UPGRADE)) {
			pose.pushPose();
			pose.scale(1.0F, -1.0F, -1.0F);
			pose.translate(1.0D, -1.25D, 0.0D);
			this.lantern.render(pose, source.getBuffer(RenderType.entityCutoutNoCull(WeedwoodRowboatRenderer.TEXTURE)), light, overlay);
			pose.popPose();
		}
	}
}
