package thebetweenlands.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import thebetweenlands.client.model.MowzieModelBase;
import thebetweenlands.client.state.SludgeWormRenderState;

public class TinySludgeWormModel extends MowzieModelBase<SludgeWormRenderState> {
	public final ModelPart head;
	public final ModelPart beak_right;
	public final ModelPart beak_left;
	public final ModelPart dat_detailed_hot_bod;
	public final ModelPart cute_lil_butt;
	public final ModelPart spoopy_stinger;

	public TinySludgeWormModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.beak_right = head.getChild("beak_right");
		this.beak_left = head.getChild("beak_left");
		this.dat_detailed_hot_bod = root.getChild("body");
		this.cute_lil_butt = root.getChild("butt");
		this.spoopy_stinger = cute_lil_butt.getChild("stinger");
	}

	public static LayerDefinition create() {
		MeshDefinition definition = new MeshDefinition();
		PartDefinition partDefinition = definition.getRoot();

		partDefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(13, 0)
				.addBox(-1.5F, -1.5F, 0.F, 3, 3, 3),
			PartPose.offset(0.0F, 22.5F, 0.0F));

		var head = partDefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3),
			PartPose.offset(0.0F, 22.5F, 0.0F));

		head.addOrReplaceChild("beak_left", CubeListBuilder.create()
				.texOffs(0, 14).addBox(-2F, -2F, -2F, 2, 3, 3, new CubeDeformation(-0.01F)),
			PartPose.offsetAndRotation(1.5F, 0.5F, -1.5F, 0F, -0.31869712141416456F, 0F));

		head.addOrReplaceChild("beak_right", CubeListBuilder.create()
				.texOffs(0, 7).addBox(0F, -1.5F, -2F, 2, 3, 3, new CubeDeformation(-0.01F)),
			PartPose.offsetAndRotation(-1.5F, 0F, -1.5F, 0F, 0.31869712141416456F, 0F));

		var cute_lil_butt = partDefinition.addOrReplaceChild("butt", CubeListBuilder.create().
				texOffs(13, 7)
				.addBox(-1F, -1F, 0F, 2, 2, 2),
			PartPose.offset(0.0F, 23.0F, 0.0F));

		cute_lil_butt.addOrReplaceChild("stinger", CubeListBuilder.create()
				.texOffs(13, 11)
				.addBox(-0.5F, 0F, 0F, 1, 2, 2),
			PartPose.offsetAndRotation(0F, -1.3F, 1F, -0.18203784098300857F, 0F, 0F));

		return LayerDefinition.create(definition, 32, 32);
	}

	public void renderHead(SludgeWormRenderState.WormPartInfo state, PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, float xRot, float wibbleStrength) {
		float wibble = (float) (Math.sin(1F + state.smoothedFrame() * 0.25F) * 0.125F * wibbleStrength);
		float jaw_wibble = (float) (Math.sin(1F + state.smoothedFrame() * 0.5F) * 0.5F);
		stack.translate(0.0F, -0.0625F - wibble * 0.5F, 0.0F + wibble * 2.0F);
		this.head.xRot = xRot / Mth.RAD_TO_DEG;
		this.beak_left.yRot = -jaw_wibble;
		this.beak_right.yRot = jaw_wibble;
		this.head.render(stack, consumer, light, overlay, color);
	}

	public void renderBody(SludgeWormRenderState.WormPartInfo state, PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, float wibbleStrength) {
		float wibble = (float) (Math.sin(1.0F + state.smoothedFrame() * 0.25F) * 0.125F * wibbleStrength);
		stack.translate(0F, -0.125F - wibble, -0.125F - wibble * 2.0F);
		stack.scale(1F + wibble * 2.0F, 1.0F + wibble, 1.25F - wibble * 1.5F);
		this.dat_detailed_hot_bod.render(stack, consumer, light, overlay, color);
	}

	public void renderTail(SludgeWormRenderState.WormPartInfo state, PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, float wibbleStrength) {
		float wibble = (float) (Math.sin(1F + state.smoothedFrame() * 0.25F) * 0.125F * wibbleStrength);
		stack.translate(0.0F, -0.0625F - wibble * 0.5F, -0.0625F + wibble * 2.0F);
		this.cute_lil_butt.render(stack, consumer, light, overlay, color);
	}
}