package thebetweenlands.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.EquipmentModelSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thebetweenlands.client.CircleGemTextureManager;
import thebetweenlands.common.component.entity.circlegem.CircleGemType;
import thebetweenlands.common.component.item.CircleGemData;
import thebetweenlands.common.registries.DataComponentRegistry;

import java.util.List;

@Mixin(EquipmentLayerRenderer.class)
public abstract class EquipmentLayerRendererMixin {

	@Shadow(remap = false)
	@Final
	private EquipmentModelSet equipmentModels;

	@Inject(method = "renderLayers(Lnet/minecraft/world/item/equipment/EquipmentModel$LayerType;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/model/Model;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/resources/ResourceLocation;)V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/ItemStack;get(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;", shift = At.Shift.BEFORE), remap = false)
	public void renderGems(EquipmentModel.LayerType type, ResourceLocation modelLocation, Model model, ItemStack itemstack, PoseStack stack, MultiBufferSource buffer, int light, ResourceLocation layerTexture, CallbackInfo ci) {
		var gem = itemstack.getOrDefault(DataComponentRegistry.CIRCLE_GEM, CircleGemData.EMPTY);
		if (gem.type() != CircleGemType.NONE) {
			List<EquipmentModel.Layer> list = this.equipmentModels.get(modelLocation).getLayers(type);

			for (var layer : list) {
				VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.armorCutoutNoCull(CircleGemTextureManager.getMiddleGemTexture(layer, type, gem.type(), gem.overrideTexture().orElse(null))));
				model.renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY);
			}
		}
	}
}
