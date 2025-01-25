package thebetweenlands.client.renderer.entity.layers;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class GenericEyesLayer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends EyesLayer<S, M> {

	private final ResourceLocation eyes;

	public GenericEyesLayer(RenderLayerParent<S, M> parent, ResourceLocation eyeType) {
		super(parent);
		this.eyes = eyeType;
	}

	@Override
	public RenderType renderType() {
		return RenderType.eyes(this.eyes);
	}
}
