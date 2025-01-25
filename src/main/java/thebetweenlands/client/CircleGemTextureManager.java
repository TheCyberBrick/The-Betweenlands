package thebetweenlands.client;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentModel;
import thebetweenlands.common.component.entity.circlegem.CircleGemType;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

public class CircleGemTextureManager {

	private static final BiFunction<LayerTextureKey, CircleGemType, ResourceLocation> GEM_LOOKUP = Util.memoize((layer, type) -> layer.layer().textureId().withPath(s -> "textures/circle_gems/" + type.getSerializedName() + "/" + layer.layerType().getSerializedName() + "/" + s + ".png"));
	private static final BiFunction<LayerTextureKey, CircleGemType, ResourceLocation> DEFAULT_LOOKUP = Util.memoize((layer, type) -> layer.layer().textureId().withPath(s -> "textures/circle_gems/" + type.getSerializedName() + "/" + layer.layerType().getSerializedName() + "/default.png"));

	public static ResourceLocation getMiddleGemTexture(EquipmentModel.Layer layer, EquipmentModel.LayerType layerType, CircleGemType type, @Nullable ResourceLocation textureOverride) {
		if (textureOverride != null) return textureOverride;

		var texture = GEM_LOOKUP.apply(new LayerTextureKey(layerType, layer), type);
		if (Minecraft.getInstance().getTextureManager().getTexture(texture) == MissingTextureAtlasSprite.getTexture()) {
			return DEFAULT_LOOKUP.apply(new LayerTextureKey(layerType, layer), type);
		}
		return texture;
	}

	record LayerTextureKey(EquipmentModel.LayerType layerType, EquipmentModel.Layer layer) {
	}
}
