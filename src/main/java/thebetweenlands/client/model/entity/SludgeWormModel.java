package thebetweenlands.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import thebetweenlands.client.model.MowzieModelBase;
import thebetweenlands.client.state.SludgeWormRenderState;

public class SludgeWormModel extends MowzieModelBase<SludgeWormRenderState> {
	public final ModelPart head1;
	public final ModelPart mouth_left;
	public final ModelPart mouth_bottom;
	public final ModelPart jaw_bottom_left;
	public final ModelPart jaw_bottom_right;
	public final ModelPart butt;
	public final ModelPart pincer_thingy_i_guess_a;
	public final ModelPart pincer_thingy_i_guess_b;

	public final ModelPart body1;

	public SludgeWormModel(ModelPart root) {

		super(root);
		this.head1 = root.getChild("head1");
		this.mouth_left = head1.getChild("mouth_left");
		this.mouth_bottom = head1.getChild("mouth_bottom");
		this.jaw_bottom_left = head1.getChild("jaw_bottom_left");
		this.jaw_bottom_right = head1.getChild("jaw_bottom_right");
		this.butt = root.getChild("butt");
		this.pincer_thingy_i_guess_a = butt.getChild("pincer_thingy_i_guess_a");
		this.pincer_thingy_i_guess_b = pincer_thingy_i_guess_a.getChild("pincer_thingy_i_guess_b");
		this.body1 = root.getChild("body1");
	}

	public static LayerDefinition create() {
		MeshDefinition definition = new MeshDefinition();
		PartDefinition partDefinition = definition.getRoot();

		var head1 = partDefinition.addOrReplaceChild("head1", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5),
			PartPose.offset(0.0F, 21.5F, 0.0F));

		head1.addOrReplaceChild("mouth_left", CubeListBuilder.create()
				.texOffs(0, 11)
				.addBox(-2.0F, -1.5F, -2.0F, 2, 3, 3),
			PartPose.offsetAndRotation(2.0F, -0.5F, -2.5F, 0.0F, -0.36425021489121656F, -0.22759093446006054F));

		head1.addOrReplaceChild("mouth_bottom", CubeListBuilder.create()
				.texOffs(13, 11)
				.addBox(0.0F, -1.5F, -2.0F, 2, 3, 3),
			PartPose.offsetAndRotation(-2.0F, -0.5F, -2.5F, 0.0F, 0.36425021489121656F, 0.22759093446006054F));

		head1.addOrReplaceChild("jaw_bottom_left", CubeListBuilder.create()
				.texOffs(0, 18)
				.addBox(-0.5F, 0.0F, -3.5F, 1, 2, 4),
			PartPose.offsetAndRotation(1.5F, 1.0F, -2.5F, 0.136659280431156F, 0.0F, -0.7740535232594852F));

		head1.addOrReplaceChild("jaw_bottom_right", CubeListBuilder.create()
				.texOffs(11, 18)
				.addBox(-0.5F, 0.0F, -3.5F, 1, 2, 4),
			PartPose.offsetAndRotation(-1.5F, 1.0F, -2.5F, 0.136659280431156F, 0.0F, 0.7740535232594852F));

		var butt = partDefinition.addOrReplaceChild("butt", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-2.0F, -1.5F, -1.5F, 4, 4, 4),
			PartPose.offset(0.0F, 21.5F, 0.0F));

		var pincer_thingy_i_guess_a = butt.addOrReplaceChild("pincer_thingy_i_guess_a", CubeListBuilder.create()
				.texOffs(0, 9)
				.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 2),
			PartPose.offsetAndRotation(0.0F, -0.2F, 2.5F, -0.22759093446006054F, 0.0F, 0.0F));

		pincer_thingy_i_guess_a.addOrReplaceChild("pincer_thingy_i_guess_b", CubeListBuilder.create()
				.texOffs(7, 9)
				.addBox(-0.5F, -2.0F, 0.0F, 1, 2, 3),
			PartPose.offsetAndRotation(0.0F, 2.0F, 2.0F, 0.18203784098300857F, 0.0F, 0.0F));

		partDefinition.addOrReplaceChild("body1", CubeListBuilder.create()
				.texOffs(0, 15)
				.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5),
			PartPose.offset(0.0F, 21.5F, 0.0F));

		return LayerDefinition.create(definition, 32, 32);
	}

	public void renderHead(SludgeWormRenderState.WormPartInfo state, PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, float xRot, float wibbleStrength) {
		float wibble = Mth.sin(1.0F + state.smoothedFrame() * 0.25F) * 0.125F * wibbleStrength;
		float jaw_wibble = Mth.sin(1.0F + state.smoothedFrame() * 0.5F) * 0.5F;
		stack.translate(0F, -0.0625F - wibble * 0.5F, 0F + wibble * 2F);
		this.head1.xRot = xRot / Mth.RAD_TO_DEG;
		this.jaw_bottom_left.yRot = -jaw_wibble;
		this.jaw_bottom_right.yRot = jaw_wibble;
		this.mouth_bottom.yRot = -jaw_wibble;
		this.mouth_left.yRot = jaw_wibble;
		this.head1.render(stack, consumer, light, overlay, color);
	}

	public void renderBody(SludgeWormRenderState.WormPartInfo state, PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, float wibbleStrength) {
		float wibble = (float) (Math.sin(1F + state.smoothedFrame() * 0.25F) * 0.125F * wibbleStrength);
		stack.translate(0.0F, -wibble, -wibble * 2.0F);
		stack.scale(1.0F + wibble * 2.0F, 1.0F + wibble, 1.25F - wibble * 1.5F);
		this.body1.render(stack, consumer, light, overlay, color);
	}

	public void renderTail(SludgeWormRenderState.WormPartInfo state, PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, float wibbleStrength) {
		float wibble = (float) (Math.sin(1.0F + state.smoothedFrame() * 0.25F) * 0.125F * wibbleStrength);
		stack.translate(0.0F, -0.0625F - wibble * 0.5F, -0.0625F + wibble * 2.0F);
		this.butt.render(stack, consumer, light, overlay, color);
	}
}
