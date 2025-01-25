package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.shader.LightSource;
import thebetweenlands.client.shader.ShaderHelper;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.projectile.arrow.OctineArrow;

public class OctineArrowRenderer extends ArrowRenderer<OctineArrow, ArrowRenderState> {
	public OctineArrowRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(ArrowRenderState state, PoseStack stack, MultiBufferSource buffer, int light) {
		if (ShaderHelper.INSTANCE.isWorldShaderActive()) {
			ShaderHelper.INSTANCE.require();
			ShaderHelper.INSTANCE.getWorldShader().addLight(new LightSource(state.x, state.y, state.z, 3.0F, 2.3F, 0.5F, 0));
		}
		super.render(state, stack, buffer, light);
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(ArrowRenderState state) {
		return TheBetweenlands.prefix("textures/entity/arrow/octine_arrow.png");
	}
}
