package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.BarrisheeModel;
import thebetweenlands.client.renderer.entity.layers.GenericEyesLayer;
import thebetweenlands.client.shader.LightSource;
import thebetweenlands.client.shader.ShaderHelper;
import thebetweenlands.client.state.BarrisheeRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.boss.Barrishee;

public class BarrisheeRenderer extends MobRenderer<Barrishee, BarrisheeRenderState, BarrisheeModel> {

	private static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/barrishee.png");
	private int fudge = 0;

	public BarrisheeRenderer(EntityRendererProvider.Context context) {
		super(context, new BarrisheeModel(context.bakeLayer(BLModelLayers.BARRISHEE)), 1.0F);
		this.addLayer(new GenericEyesLayer<>(this, TheBetweenlands.prefix("textures/entity/barrishee_face.png")));
	}

	@Override
	public BarrisheeRenderState createRenderState() {
		return new BarrisheeRenderState();
	}

	@Override
	public void extractRenderState(Barrishee entity, BarrisheeRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.ambushSpawn = entity.isAmbushSpawn();
		state.slamming = entity.isSlamming();
		state.screaming = entity.isScreaming();
		state.screamTimer = entity.screamTimer;
		state.standingAngle = Mth.lerp(partialTick, entity.prevStandingAngle, entity.standingAngle);
	}

	@Override
	protected void scale(BarrisheeRenderState state, PoseStack stack) {
		if (state.ambushSpawn || state.slamming) {
			if (state.screaming) {
				stack.translate(0F, -0.5F + state.standingAngle * 0.5F - this.getTimerFudge(state) * 0.00625F - 0.0625F, 0F);
				this.lightUpStuff(state, state.partialTick);
			} else {
				stack.translate(0F, -0.5F + state.standingAngle * 0.5F, 0F);
			}
		} else {
			if (state.screaming) {
				stack.translate(0F, 0F - this.getTimerFudge(state) * 0.00625F - 0.0625F, 0F);
				this.lightUpStuff(state, state.partialTick);
			}
		}
	}

	public void lightUpStuff(BarrisheeRenderState state, float partialTick) {
		if (ShaderHelper.INSTANCE.isWorldShaderActive()) {
			ShaderHelper.INSTANCE.require();
			ShaderHelper.INSTANCE.getWorldShader().addLight(new LightSource(state.x, state.y + 1.25D, state.z, (this.fudge + partialTick) / 2F + 1F, 4.0F, 102f / 255.0f * 4.0F, 0f / 255.0f * 4.0F));
			ShaderHelper.INSTANCE.getWorldShader().addLight(new LightSource(state.x, state.y + 1.25D, state.z, (this.getTimerFudge(state) + partialTick) / 2F + 0.6F, 105f / 255.0f * 4.0F, 26f / 255.0f * 4.0F, 0f / 255.0f * 4.0F));
		}
	}

	public int getTimerFudge(BarrisheeRenderState state) {
		if (state.screamTimer >= 20 && state.screamTimer <= 30)
			this.fudge = state.screamTimer - 20;
		if (state.screamTimer > 30 && state.screamTimer < 40)
			this.fudge = 10;
		if (state.screamTimer >= 40)
			this.fudge = -state.screamTimer + 50;
		return this.fudge;
	}

	@Override
	public ResourceLocation getTextureLocation(BarrisheeRenderState state) {
		return TEXTURE;
	}
}
