package thebetweenlands.client.model.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import thebetweenlands.client.model.MowzieModelBase;
import thebetweenlands.client.state.JellyfishRenderState;

public class JellyfishModel extends MowzieModelBase<JellyfishRenderState> {

	private final ModelPart frontTentacles;
	private final ModelPart leftTentacles;
	private final ModelPart rightTentacles;
	private final ModelPart backTentacles;

	public JellyfishModel(ModelPart root) {
		super(root, RenderType::entityTranslucent);
		this.frontTentacles = root.getChild("mesogloea_base").getChild("front_tentacles_1");
		this.backTentacles = root.getChild("mesogloea_base").getChild("back_tentacles_1");
		this.leftTentacles = root.getChild("mesogloea_base").getChild("left_tentacles_1");
		this.rightTentacles = root.getChild("mesogloea_base").getChild("right_tentacles_1");
	}

	public static LayerDefinition create() {
		MeshDefinition definition = new MeshDefinition();
		PartDefinition partDefinition = definition.getRoot();

		var mesogloea_base = partDefinition.addOrReplaceChild("mesogloea_base", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-1.5F, -10.0F, -1.5F, 3, 10, 3),
			PartPose.offset(0.0F, 12.0F, 0.0F));
		var mouth = mesogloea_base.addOrReplaceChild("mouth", CubeListBuilder.create()
				.texOffs(0, 14).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2),
			PartPose.offset(0.0F, 0.0F, 0.0F));

		mouth.addOrReplaceChild("oral_arm_left", CubeListBuilder.create()
				.texOffs(0, 22).addBox(0.0F, 0.0F, -1.5F, 0, 5, 3),
			PartPose.offset(1.0F, 2.0F, 0.0F));
		mouth.addOrReplaceChild("oral_arm_right", CubeListBuilder.create()
				.texOffs(7, 16).addBox(0.0F, 0.0F, -1.5F, 0, 5, 3),
			PartPose.offset(-1.0F, 2.0F, 0.0F));
		mouth.addOrReplaceChild("oral_arm_front", CubeListBuilder.create()
				.texOffs(0, 19).addBox(-1.5F, 0.0F, 0.0F, 3, 5, 0),
			PartPose.offset(0.0F, 2.0F, -1.0F));
		mouth.addOrReplaceChild("oral_arm_back", CubeListBuilder.create()
				.texOffs(7, 25).addBox(-1.5F, 0.0F, 0.0F, 3, 5, 0),
			PartPose.offset(0.0F, 1.5F, 1.0F));

		var tentacles_left1a = mesogloea_base.addOrReplaceChild("left_tentacles_1", CubeListBuilder.create()
				.texOffs(14, 12).addBox(0.0F, 0.0F, -1.5F, 0, 4, 3),
			PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.091106186954104F));
		var tentacles_left1b = tentacles_left1a.addOrReplaceChild("left_tentacles_2", CubeListBuilder.create()
				.texOffs(14, 17).addBox(0.0F, 0.0F, -1.5F, 0, 4, 3),
			PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.136659280431156F));
		tentacles_left1b.addOrReplaceChild("left_tentacles_3", CubeListBuilder.create()
				.texOffs(14, 22).addBox(0.0F, 0.0F, -1.5F, 0, 4, 3),
			PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, -0.091106186954104F));

		var tentacles_right1a = mesogloea_base.addOrReplaceChild("right_tentacles_1", CubeListBuilder.create()
				.texOffs(21, -3).addBox(0.0F, 0.0F, -1.5F, 0, 4, 3),
			PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.091106186954104F));
		var tentacles_right1b = tentacles_right1a.addOrReplaceChild("right_tentacles_2", CubeListBuilder.create()
				.texOffs(21, 2).addBox(0.0F, 0.0F, -1.5F, 0, 4, 3),
			PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, -0.136659280431156F));
		tentacles_right1b.addOrReplaceChild("right_tentacles_3", CubeListBuilder.create()
				.texOffs(21, 7).addBox(0.0F, 0.0F, -1.5F, 0, 4, 3),
			PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, -0.0017453292519943296F, 0.091106186954104F));

		var tentacles_front1a = mesogloea_base.addOrReplaceChild("front_tentacles_1", CubeListBuilder.create()
				.texOffs(14, 0).addBox(-1.5F, 0.0F, 0.0F, 3, 4, 0),
			PartPose.offsetAndRotation(0.0F, 0.0F, -1.5F, -0.091106186954104F, 0.0F, 0.0F));
		var tentacles_front1b = tentacles_front1a.addOrReplaceChild("front_tentacles_2", CubeListBuilder.create()
				.texOffs(14, 5).addBox(-1.5F, 0.0F, 0.0F, 3, 4, 0),
			PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.136659280431156F, 0.0F, 0.0F));
		tentacles_front1b.addOrReplaceChild("front_tentacles_3", CubeListBuilder.create()
				.texOffs(14, 10).addBox(-1.5F, 0.0F, 0.0F, 3, 4, 0),
			PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.091106186954104F, 0.0F, 0.0F));

		var tentacles_back1a = mesogloea_base.addOrReplaceChild("back_tentacles_1", CubeListBuilder.create()
				.texOffs(21, 15).addBox(-1.5F, 0.0F, 0.0F, 3, 4, 0),
			PartPose.offsetAndRotation(0.0F, 0.0F, 1.5F, 0.091106186954104F, 0.0F, 0.0F));
		var tentacles_back1b = tentacles_back1a.addOrReplaceChild("back_tentacles_2", CubeListBuilder.create()
				.texOffs(21, 20).addBox(-1.5F, 0.0F, 0.0F, 3, 4, 0),
			PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.136659280431156F, 0.0F, 0.0F));
		tentacles_back1b.addOrReplaceChild("back_tentacles_3", CubeListBuilder.create()
				.texOffs(21, 25).addBox(-1.5F, 0.0F, 0.0F, 3, 4, 0),
			PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.091106186954104F, 0.0F, 0.0F));

		return LayerDefinition.create(definition, 32, 32);
	}

	@Override
	public void setupAnim(JellyfishRenderState state) {
		float adjustedLimbSwingAmount = Math.min(state.walkAnimationSpeed, 0.2F);

		this.leftTentacles.zRot = -0.136659280431156F + Mth.sin(state.walkAnimationPos) * adjustedLimbSwingAmount;
		this.rightTentacles.zRot = 0.136659280431156F - Mth.sin(state.walkAnimationPos) * adjustedLimbSwingAmount;
		this.frontTentacles.xRot = -0.136659280431156F + Mth.sin(state.walkAnimationPos) * adjustedLimbSwingAmount;
		this.backTentacles.xRot = 0.136659280431156F - Mth.sin(state.walkAnimationPos) * adjustedLimbSwingAmount;
	}
}
