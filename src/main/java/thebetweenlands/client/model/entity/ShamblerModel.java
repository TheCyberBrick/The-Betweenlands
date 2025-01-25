package thebetweenlands.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import thebetweenlands.client.model.MowzieModelBase;
import thebetweenlands.client.state.ShamblerRenderState;

public class ShamblerModel extends MowzieModelBase<ShamblerRenderState> {

	private final ModelPart head1;
	private final ModelPart hindleg_right1;
	private final ModelPart hindleg_left1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;
	private final ModelPart tail5;
	private final ModelPart tail6;
	private final ModelPart hindleg_right2;
	private final ModelPart hindleg_right3;
	private final ModelPart foot_right1;
	private final ModelPart hindleg_left2;
	private final ModelPart hindleg_left3;
	private final ModelPart foot_left1;
	private final ModelPart mouth_arm1a;
	private final ModelPart mouth_arm2a;
	private final ModelPart mouth_arm3a;
	private final ModelPart mouth_arm4a;
	private final ModelPart mouth_arm1c;
	private final ModelPart mouth_arm2c;
	private final ModelPart mouth_arm3c;
	private final ModelPart mouth_arm4c;

	private final ModelPart tongue_part;

	private final ModelPart tongue_end;

	public ShamblerModel(ModelPart root) {
		super(root.getChild("body_base"));
		ModelPart body2 = root.getChild("body_base").getChild("body2");
		this.hindleg_right1 = body2.getChild("hindleg_right1");
		this.hindleg_left1 = body2.getChild("hindleg_left1");
		ModelPart body3 = body2.getChild("body3");
		ModelPart weird_butt = body3.getChild("weird_butt");
		ModelPart surprise_tail = weird_butt.getChild("surprise_tail");
		this.tail2 = surprise_tail.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.tail4 = this.tail3.getChild("tail4");
		this.tail5 = this.tail4.getChild("tail5");
		this.tail6 = this.tail5.getChild("tail6");
		this.head1 = root.getChild("body_base").getChild("head1");
		this.mouth_arm1a = this.head1.getChild("mouth_arm1a");
		this.mouth_arm2a = this.head1.getChild("mouth_arm2a");
		this.mouth_arm3a = this.head1.getChild("mouth_arm3a");
		this.mouth_arm4a = this.head1.getChild("mouth_arm4a");
		ModelPart mouth_arm1b = this.mouth_arm1a.getChild("mouth_arm1b");
		this.mouth_arm1c = mouth_arm1b.getChild("mouth_arm1c");
		ModelPart mouth_arm2b = this.mouth_arm2a.getChild("mouth_arm2b");
		this.mouth_arm2c = mouth_arm2b.getChild("mouth_arm2c");
		ModelPart mouth_arm3b = this.mouth_arm3a.getChild("mouth_arm3b");
		this.mouth_arm3c = mouth_arm3b.getChild("mouth_arm3c");
		ModelPart mouth_arm4b = this.mouth_arm4a.getChild("mouth_arm4b");
		this.mouth_arm4c = mouth_arm4b.getChild("mouth_arm4c");
		this.hindleg_right2 = this.hindleg_right1.getChild("hindleg_right2");
		this.hindleg_right3 = this.hindleg_right2.getChild("hindleg_right3");
		this.foot_right1 = this.hindleg_right3.getChild("foot_right1");
		this.hindleg_left2 = this.hindleg_left1.getChild("hindleg_left2");
		this.hindleg_left3 = this.hindleg_left2.getChild("hindleg_left3");
		this.foot_left1 = this.hindleg_left3.getChild("foot_left1");
		this.tongue_part = root.getChild("root").getChild("tongue_part");
		this.tongue_end = root.getChild("root").getChild("tongue_end");
	}

	public static LayerDefinition create() {
		MeshDefinition definition = new MeshDefinition();
		PartDefinition partDefinition = definition.getRoot();

		PartDefinition body_base = partDefinition.addOrReplaceChild("body_base", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 8.0F, 7.0F),
			PartPose.offset(0.0F, 8.2F, -3.0F));

		PartDefinition body2 = body_base.addOrReplaceChild("body2", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 8.0F, 5.0F),
			PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, -0.3643F, 0.0F, 0.0F));

		PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create()
				.texOffs(0, 30).addBox(-4.01F, 0.0F, 0.0F, 8F, 8F, 3F),
			PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, -0.2731F, 0.0F, 0.0F));

		PartDefinition weird_butt = body3.addOrReplaceChild("weird_butt", CubeListBuilder.create()
				.texOffs(0, 42).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 6.0F, 4.0F),
			PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, -0.5009F, 0.0F, 0.0F));

		PartDefinition surprise_tail = weird_butt.addOrReplaceChild("surprise_tail", CubeListBuilder.create()
				.texOffs(0, 53).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 3.0F),
			PartPose.offsetAndRotation(0.0F, 6.0F, 4.0F, 0.6829F, 0.0F, 0.0F));

		PartDefinition tail2 = surprise_tail.addOrReplaceChild("tail2", CubeListBuilder.create()
				.texOffs(0, 61).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 5.0F),
			PartPose.offsetAndRotation(-0.015F, 0.0F, 3.0F, 0.5009F, 0.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create()
				.texOffs(0, 71).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 5.0F),
			PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, 0.7741F, 0.0F, 0.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create()
				.texOffs(0, 80).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 4.0F),
			PartPose.offsetAndRotation(-0.015F, 0.0F, 5.0F, 0.8652F, 0.0F, 0.0F));

		PartDefinition tail5 = tail4.addOrReplaceChild("tail5", CubeListBuilder.create()
				.texOffs(0, 88).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 4.0F),
			PartPose.offsetAndRotation(-0.015F, 0.0F, 4.0F, 0.8652F, 0.0F, 0.0F));

		tail5.addOrReplaceChild("tail6", CubeListBuilder.create()
				.texOffs(0, 96).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 3.0F),
			PartPose.offsetAndRotation(-0.015F, 0.0F, 4.0F, 0.8196F, 0.0F, 0.0F));

		PartDefinition hindleg_right1 = body2.addOrReplaceChild("hindleg_right1", CubeListBuilder.create()
				.texOffs(40, 0).addBox(-2.0F, -3.0F, -5.0F, 3.0F, 6.0F, 7.0F),
			PartPose.offsetAndRotation(-4.0F, 5.0F, 3.0F, 0.5235987755982988F, 0F, 0F));

		PartDefinition hindleg_right2 = hindleg_right1.addOrReplaceChild("hindleg_right2", CubeListBuilder.create()
				.texOffs(40, 14).addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 4.0F),
			PartPose.offsetAndRotation(-1.99F, 3.0F, -5.0F, 0.4363323129985824F, 0.0F, 0.0F));

		PartDefinition hindleg_right3 = hindleg_right2.addOrReplaceChild("hindleg_right3", CubeListBuilder.create()
				.texOffs(40, 25).addBox(0.02F, 0.0F, -4.0F, 3F, 4F, 4F),
			PartPose.offsetAndRotation(0.0F, 6.0F, 4.0F, -1.0471975511965976F, 0.0F, 0.0F));

		PartDefinition foot_right1 = hindleg_right3.addOrReplaceChild("foot_right1", CubeListBuilder.create()
				.texOffs(40, 34).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 4.0F),
			PartPose.offsetAndRotation(1.5F, 4.0F, -4.0F, 0.4886921905584123F, 0.0F, 0.0F));

		foot_right1.addOrReplaceChild("toe_right1", CubeListBuilder.create()
				.texOffs(40, 41).addBox(-2.0F, 0.0F, -4.0F, 2F, 2F, 5F),
			PartPose.offsetAndRotation(-0.5F, 0.0F, 2.0F, -0.045553093477052F, 0.18203784098300857F, -0.091106186954104F));

		foot_right1.addOrReplaceChild("toe_right2", CubeListBuilder.create()
				.texOffs(40, 49).addBox(-1.0F, 0.0F, -2.5F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(0.0F, -0.2F, 0.0F, -0.045553093477052F, 0.0F, 0.0F));

		foot_right1.addOrReplaceChild("toe_right3", CubeListBuilder.create()
				.texOffs(40, 55).addBox(0.0F, 0.0F, -5.0F, 2F, 2F, 6F),
			PartPose.offsetAndRotation(0.5F, 0.0F, 2.0F, -0.045553093477052F, -0.18203784098300857F, 0.091106186954104F));

		PartDefinition hindleg_left1 = body2.addOrReplaceChild("hindleg_left1", CubeListBuilder.create()
				.texOffs(61, 0).addBox(-1.0F, -3.0F, -5.0F, 3F, 6F, 7F),
			PartPose.offsetAndRotation(4.0F, 5.0F, 3.0F, 0.5235987755982988F, 0.0F, 0.0F));

		PartDefinition hindleg_left2 = hindleg_left1.addOrReplaceChild("hindleg_left2", CubeListBuilder.create()
				.texOffs(61, 14).addBox(0.0F, 0.0F, 0.0F, 3F, 6F, 4F),
			PartPose.offsetAndRotation(-1.02F, 3.0F, -5.0F, 0.4363323129985824F, 0.0F, 0.0F));

		PartDefinition hindleg_left3 = hindleg_left2.addOrReplaceChild("hindleg_left3", CubeListBuilder.create()
				.texOffs(61, 25).addBox(-3.02F, 0.0F, -4.0F, 3F, 4F, 4F),
			PartPose.offsetAndRotation(3.0F, 6.0F, 4.0F, -1.0471975511965976F, 0.0F, 0.0F));

		PartDefinition foot_left1 = hindleg_left3.addOrReplaceChild("foot_left1", CubeListBuilder.create()
				.texOffs(61, 34).addBox(-1.5F, 0.0F, 0.0F, 3F, 2F, 4F),
			PartPose.offsetAndRotation(-1.5F, 4.0F, -4.0F, 0.4886921905584123F, 0.0F, 0.0F));

		foot_left1.addOrReplaceChild("toe_left1", CubeListBuilder.create()
				.texOffs(61, 41).addBox(0.0F, 0.0F, -4.0F, 2F, 2F, 5F),
			PartPose.offsetAndRotation(0.5F, 0.0F, 2.0F, -0.045553093477052F, -0.18203784098300857F, 0.091106186954104F));

		foot_left1.addOrReplaceChild("toe_left2", CubeListBuilder.create()
				.texOffs(61, 49).addBox(-1.0F, 0.0F, -2.5F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(0.0F, -0.2F, 0.0F, -0.045553093477052F, 0.0F, 0.0F));

		foot_left1.addOrReplaceChild("toe_left3", CubeListBuilder.create()
				.texOffs(61, 55).addBox(-2.0F, 0.0F, -5.0F, 2F, 2F, 6F),
			PartPose.offsetAndRotation(-0.5F, 0.0F, 2.0F, -0.045553093477052F, 0.18203784098300857F,
				-0.091106186954104F));

		PartDefinition head1 = body_base.addOrReplaceChild("head1", CubeListBuilder.create()
				.texOffs(85, 0).addBox(-4.0F, -3.5F, -6.0F, 8F, 8F, 7F),
			PartPose.offset(0.0F, 1.0F, 2.0F));

		head1.addOrReplaceChild("mouth", CubeListBuilder.create()
				.texOffs(85, 16).addBox(-3.0F, 0.0F, -2.0F, 6F, 6F, 2F),
			PartPose.offset(0.0F, -2.5F, -6.0F));

		PartDefinition mouth_arm1a = head1.addOrReplaceChild("mouth_arm1a", CubeListBuilder.create()
				.texOffs(85, 25).addBox(0.0F, 0.0F, -4.0F, 3F, 3F, 5F),
			PartPose.offsetAndRotation(-3.9F, -3.3F, -5.0F, 0.08726646259971647F, 0.0F, 0.0F));

		PartDefinition mouth_arm1b = mouth_arm1a.addOrReplaceChild("mouth_arm1b", CubeListBuilder.create()
				.texOffs(85, 34).addBox(0.0F, 0.0F, -3.0F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(0.5F, 0.5F, -4.0F, 0.36425021489121656F, -0.36425021489121656F, -0.045553093477052F));

		mouth_arm1b.addOrReplaceChild("mouth_arm1c", CubeListBuilder.create()
				.texOffs(85, 40).addBox(0.0F, 0.0F, -3.0F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(0.0F, 0.01F, -3.0F, 0.0F, -0.5462880558742251F, 0.0F));

		PartDefinition mouth_arm2a = head1.addOrReplaceChild("mouth_arm2a", CubeListBuilder.create()
				.texOffs(102, 25).addBox(-3.0F, 0.0F, -4.0F, 3F, 3F, 5F),
			PartPose.offsetAndRotation(3.9F, -3.3F, -5.0F, 0.08726646259971647F, 0.0F, 0.0F));

		PartDefinition mouth_arm2b = mouth_arm2a.addOrReplaceChild("mouth_arm2b", CubeListBuilder.create()
				.texOffs(102, 34).addBox(-2.0F, 0.0F, -3.0F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(-0.5F, 0.5F, -4.0F, 0.36425021489121656F, 0.36425021489121656F, 0.045553093477052F));

		mouth_arm2b.addOrReplaceChild("mouth_arm2c", CubeListBuilder.create()
				.texOffs(102, 40).addBox(-2.0F, 0.0F, -3.0F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(0.0F, 0.01F, -3.0F, 0.0F, 0.5462880558742251F, 0.0F));

		PartDefinition mouth_arm3a = head1.addOrReplaceChild("mouth_arm3a", CubeListBuilder.create()
				.texOffs(85, 46).addBox(0.0F, -3.0F, -4.0F, 3F, 3F, 5F),
			PartPose.offsetAndRotation(-3.9F, 4.3F, -5.0F, -0.08726646259971647F, 0.0F, 0.0F));

		PartDefinition mouth_arm3b = mouth_arm3a.addOrReplaceChild("mouth_arm3b", CubeListBuilder.create()
				.texOffs(85, 55).addBox(0.0F, -2.0F, -3.0F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(0.5F, -0.5F, -4.0F, -0.36425021489121656F, -0.36425021489121656F, 0.045553093477052F));

		mouth_arm3b.addOrReplaceChild("mouth_arm3c", CubeListBuilder.create()
				.texOffs(85, 61).addBox(0.0F, -2.0F, -3.0F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(0.0F, -0.01F, -3.0F, 0.0F, -0.5462880558742251F, 0.0F));

		PartDefinition mouth_arm4a = head1.addOrReplaceChild("mouth_arm4a", CubeListBuilder.create()
				.texOffs(102, 46).addBox(-3.0F, -3.0F, -4.0F, 3F, 3F, 5F),
			PartPose.offsetAndRotation(3.9F, 4.3F, -5.0F, -0.0890117918517108F, 0.0F, 0.0F));

		PartDefinition mouth_arm4b = mouth_arm4a.addOrReplaceChild("mouth_arm4b", CubeListBuilder.create()
				.texOffs(102, 55).addBox(-2.0F, -2.0F, -3.0F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(-0.5F, -0.5F, -4.0F, -0.36425021489121656F, 0.36425021489121656F, -0.045553093477052F));

		mouth_arm4b.addOrReplaceChild("mouth_arm4c", CubeListBuilder.create()
				.texOffs(102, 61).addBox(-2.0F, -2.0F, -3.0F, 2F, 2F, 3F),
			PartPose.offsetAndRotation(0.0F, -0.01F, -3.0F, 0.0F, 0.5462880558742251F, 0.0F));

		PartDefinition cranialthing1 = head1.addOrReplaceChild("cranialthing1", CubeListBuilder.create()
				.texOffs(85, 74).addBox(-4.0F, -2.0F, 0.0F, 8F, 2F, 3F),
			PartPose.offsetAndRotation(0.0F, -1.5F, 1.0F, 0.18203784098300857F, 0.0F, 0.0F));

		cranialthing1.addOrReplaceChild("cranialthing2", CubeListBuilder.create()
				.texOffs(85, 80).addBox(-3.0F, -2.0F, 0.0F, 6F, 2F, 2F),
			PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.18203784098300857F, 0.0F, 0.0F));

		partDefinition.addOrReplaceChild("tongue_part", CubeListBuilder.create()
				.texOffs(85, 86).addBox(-1.0F, -1.0F, -2.0F, 2F, 2F, 4F),
			PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition tongue_end = partDefinition.addOrReplaceChild("tongue_end", CubeListBuilder.create()
				.texOffs(85, 86).addBox(-1.0F, -1.0F, -2.0F, 2F, 2F, 4F),
			PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition tongue1 = tongue_end.addOrReplaceChild("tongue1", CubeListBuilder.create()
				.texOffs(85, 93).addBox(-1.5F, 0.0F, -2.0F, 3F, 2F, 2F),
			PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.5009094953223726F, 0.0F, 0.0F));

		tongue1.addOrReplaceChild("teeth1", CubeListBuilder.create()
				.texOffs(85, 98).addBox(-1.5F, -2.0F, -2.0F, 3F, 2F, 2F),
			PartPose.offset(0.0F, 2.0F, -2.0F));

		PartDefinition tongue2 = tongue_end.addOrReplaceChild("tongue2", CubeListBuilder.create()
				.texOffs(96, 93).addBox(-1.5F, -2.0F, -2.0F, 3F, 2F, 2F),
			PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, -0.5009094953223726F, 0.0F, 0.0F));

		tongue2.addOrReplaceChild("teeth2", CubeListBuilder.create()
				.texOffs(96, 98).addBox(-1.5F, 0.0F, -2.0F, 3F, 2F, 2F),
			PartPose.offset(0.0F, -2.0F, -2.0F));

		return LayerDefinition.create(definition, 128, 128);
	}

	public void renderTonguePart(PoseStack stack, VertexConsumer consumer, int light, int overlay, int color, boolean end) {
		stack.mulPose(Axis.YP.rotationDegrees(180.0F));
		if (end) {
			this.tongue_end.render(stack, consumer, light, overlay, color);
		} else {
			this.tongue_part.render(stack, consumer, light, overlay, color);
		}
	}

	@Override
	public void setupAnim(ShamblerRenderState state) {
		float animation = (Mth.cos((state.walkAnimationPos * 1.2F) * 0.75F) * 0.3F * state.walkAnimationSpeed * 0.5F);
		float animation2 = (Mth.sin((state.walkAnimationPos * 1.2F) * 0.75F) * 0.3F * state.walkAnimationSpeed * 0.5F);
		float flap = (Mth.sin(state.ageInTicks * 0.3F) * 0.8F);
		float smoothedAngle = state.jawAngle;
		float headX = state.xRot / Mth.RAD_TO_DEG;

		hindleg_left1.xRot = 0.5235987755982988F - (animation2 * 14.0F) + flap * 0.1F - flap * 0.075F / Mth.RAD_TO_DEG;
		hindleg_right1.xRot = 0.5235987755982988F - (animation * 14.0F) + flap * 0.1F - flap * 0.075F / Mth.RAD_TO_DEG;

		hindleg_left2.xRot = 0.4363323129985824F + (animation2 * 8.0F) - flap * 0.05F + flap * 0.075F / Mth.RAD_TO_DEG;
		hindleg_right2.xRot = 0.4363323129985824F + (animation * 8.0F) - flap * 0.05F + flap * 0.075F / Mth.RAD_TO_DEG;

		hindleg_left3.xRot = -1.0471975511965976F + (animation2 * 4.0F) + flap * 0.05F - flap * 0.075F / Mth.RAD_TO_DEG;
		hindleg_right3.xRot = -1.0471975511965976F + (animation * 4.0F) + flap * 0.05F - flap * 0.075F / Mth.RAD_TO_DEG;

		this.foot_left1.xRot = 0.4886921905584123F - (animation2 * 2.0F) - flap * 0.05F - flap * 0.075F / Mth.RAD_TO_DEG;
		this.foot_right1.xRot = 0.4886921905584123F - (animation * 2.0F) - flap * 0.05F - flap * 0.075F / Mth.RAD_TO_DEG;

		this.root.xRot = 0.0F - (animation2 * 3.0F) - flap * 0.05F;
		this.head1.xRot = headX + (animation2 * 4.0F) + flap * 0.1F;

		this.root.zRot = -(animation2 * 2.0F);
		this.head1.zRot = animation2 * 4.0F;

		this.hindleg_left1.zRot = animation2 * 2.0F;
		this.hindleg_right1.zRot = animation * 2.0F;

		this.mouth_arm1a.yRot = smoothedAngle / Mth.RAD_TO_DEG * 4.0F - (!state.jawsOpen ? 0.0F : flap * 0.3F);
		this.mouth_arm1a.xRot = 0.08726646259971647F - smoothedAngle / Mth.RAD_TO_DEG * 3.0F - (!state.jawsOpen ? 0.0F : flap * 0.1F);

		this.mouth_arm2a.yRot = -smoothedAngle / Mth.RAD_TO_DEG * 4.0F + (!state.jawsOpen ? 0.0F : flap * 0.3F);
		this.mouth_arm2a.xRot = 0.08726646259971647F - smoothedAngle / Mth.RAD_TO_DEG * 3.0F - (!state.jawsOpen ? 0.0F : flap * 0.1F);

		this.mouth_arm3a.yRot = smoothedAngle / Mth.RAD_TO_DEG * 4.0F - (!state.jawsOpen ? 0.0F : flap * 0.3F);
		this.mouth_arm3a.xRot = -0.08726646259971647F + smoothedAngle / Mth.RAD_TO_DEG * 3.0F - (!state.jawsOpen ? 0.0F : flap * 0.1F);

		this.mouth_arm4a.yRot = -smoothedAngle / Mth.RAD_TO_DEG * 4.0F + (!state.jawsOpen ? 0.0F : flap * 0.3F);
		this.mouth_arm4a.xRot = -0.08726646259971647F + smoothedAngle / Mth.RAD_TO_DEG * 3.0F - (!state.jawsOpen ? 0.0F : flap * 0.1F);

		this.mouth_arm1c.yRot = -0.5462880558742251F + smoothedAngle / Mth.RAD_TO_DEG * 3.0F - (!state.jawsOpen ? 0.0F : flap * 0.5F);
		this.mouth_arm2c.yRot = 0.5462880558742251F - smoothedAngle / Mth.RAD_TO_DEG * 3.0F + (!state.jawsOpen ? 0.0F : flap * 0.5F);
		this.mouth_arm3c.yRot = -0.5462880558742251F + smoothedAngle / Mth.RAD_TO_DEG * 3.0F - (!state.jawsOpen ? 0.0F : flap * 0.5F);
		this.mouth_arm4c.yRot = 0.5462880558742251F - smoothedAngle / Mth.RAD_TO_DEG * 3.0F + (!state.jawsOpen ? 0.0F : flap * 0.5F);

		tail2.xRot = (0.5009094953223726F - Mth.sin((animation) * 0.5009094953223726F) - 1.0F / 9 * state.tongueLength * 0.5F);
		tail3.xRot = (0.7740535232594852F - Mth.sin((animation) * 0.7740535232594852F) - 1.0F / 9 * state.tongueLength * 0.5F);
		tail4.xRot = (0.8651597102135892F - Mth.sin((animation) * 0.8651597102135892F) - 1.0F / 9 * state.tongueLength);
		tail5.xRot = (0.8651597102135892F - Mth.sin((animation) * 0.8651597102135892F) - 1.0F / 9 * state.tongueLength);
		tail6.xRot = (0.8196066167365371F - Mth.sin((animation) * 0.8196066167365371F) - 1.0F / 9 * state.tongueLength * 0.75F);
	}

}