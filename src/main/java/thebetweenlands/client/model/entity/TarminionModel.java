package thebetweenlands.client.model.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import thebetweenlands.client.model.MowzieModelBase;

public class TarminionModel extends MowzieModelBase<LivingEntityRenderState> {

	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public TarminionModel(ModelPart root) {
		super(root);
		this.leftArm = root.getChild("left_arm");
		this.rightArm = root.getChild("right_arm");
		this.leftLeg = root.getChild("left_leg");
		this.rightLeg = root.getChild("right_leg");
	}

	public static LayerDefinition create() {
		MeshDefinition definition = new MeshDefinition();
		PartDefinition partDefinition = definition.getRoot();

		partDefinition.addOrReplaceChild("torso", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F),
			PartPose.offsetAndRotation(0.0F, 18.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(5, 22).addBox(0.0F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
			PartPose.offsetAndRotation(1.0F, 20.0F, 0.0F, 0.0F, -0.0873F, -0.6109F));

		partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
				.texOffs(0, 22).addBox(-1.0F, -0.5F, -0.5F, 1.0F, 3.0F, 1.0F),
			PartPose.offsetAndRotation(-1.0F, 20.0F, 0.0F, 0.0F, 0.0873F, 0.6109F));

		partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(5, 12).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F),
			PartPose.offset(1.0F, 22.0F, 0.0F));

		partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F),
			PartPose.offset(-1.0F, 22.0F, 0.0F));


		return LayerDefinition.create(definition, 32, 32);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		this.leftArm.xRot = Mth.cos(state.walkAnimationPos * 1.5F + Mth.PI) * 2.0F * state.walkAnimationSpeed * 0.5F;
		this.rightArm.xRot = Mth.cos(state.walkAnimationPos * 1.5F) * 2.0F * state.walkAnimationSpeed * 0.5F;

		this.leftLeg.xRot = Mth.cos(state.walkAnimationPos * 1.5F) * 1.4F * state.walkAnimationSpeed;
		this.rightLeg.xRot = Mth.cos(state.walkAnimationPos * 1.5F + Mth.PI) * 1.4F * state.walkAnimationSpeed;
	}
}
