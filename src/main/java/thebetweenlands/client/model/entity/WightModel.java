package thebetweenlands.client.model.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import thebetweenlands.client.model.MowzieModelBase;
import thebetweenlands.client.state.WightRenderState;

public class WightModel extends MowzieModelBase<WightRenderState> {

	private final ModelPart neck;
	private final ModelPart armright;
	private final ModelPart armleft;
	private final ModelPart legleft;
	private final ModelPart legright;
	private final ModelPart jaw;
	private final ModelPart[] headParts;

	public WightModel(ModelPart root) {
		super(root, RenderType::entityTranslucent);
		ModelPart body_base = root.getChild("body_base");
		this.neck = body_base.getChild("neck");

		this.armleft = body_base.getChild("armleft");
		this.legright = body_base.getChild("legright");
		this.legleft = body_base.getChild("legleft");
		this.armright = body_base.getChild("armright");

		this.jaw = this.neck.getChild("jaw");
		this.headParts = new ModelPart[]{this.neck.getChild("head1"), this.neck.getChild("head2"), this.neck.getChild("head3"), this.jaw};
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition body = meshdefinition.getRoot();

		PartDefinition body_base = body.addOrReplaceChild("body_base", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -1.3F, -2.1F, 8, 8, 5), PartPose.offsetAndRotation(0.0F, -2.5F, 1.7F, 0.045553093477052F, 0.0F, 0.0F));
		body_base.addOrReplaceChild("chest_left", CubeListBuilder.create().texOffs(0, 14).addBox(-0.4F, -6.3F, -2.8F, 5, 6, 6), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2832669375986797F, -0.136659280431156F, -0.03665191429188092F));
		body_base.addOrReplaceChild("chest_right", CubeListBuilder.create().texOffs(23, 14).addBox(-4.6F, -6.3F, -2.8F, 5, 6, 6), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2832669375986797F, 0.136659280431156F, 0.03665191429188092F));

		body_base.addOrReplaceChild("armleft", CubeListBuilder.create().texOffs(9, 28).addBox(0.0F, -1.0F, -1.0F, 2, 22, 2), PartPose.offsetAndRotation(4.6F, -4.6F, -1.5F, -0.045553093477052F, -0.136659280431156F, 0.0F));
		body_base.addOrReplaceChild("armright", CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, -1.0F, -1.0F, 2, 22, 2), PartPose.offsetAndRotation(-4.6F, -4.6F, -1.5F, -0.045553093477052F, 0.136659280431156F, 0.0F));
		body_base.addOrReplaceChild("legleft", CubeListBuilder.create().texOffs(18, 28).addBox(-1.0F, -0.2F, -1.0F, 2, 20, 2), PartPose.offsetAndRotation(2.3F, 6.7F, 0.0F, -0.045553093477052F, 0.0F, 0.0F));
		body_base.addOrReplaceChild("legright", CubeListBuilder.create().texOffs(27, 28).addBox(-1.0F, -0.2F, -1.0F, 2, 20, 2), PartPose.offsetAndRotation(-2.3F, 6.7F, 0.0F, -0.045553093477052F, 0.0F, 0.0F));

		PartDefinition neck = body_base.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(50, 0).addBox(-1.5F, -3.0F, -1.5F, 3, 4, 3), PartPose.offsetAndRotation(0.0F, -7.8F, -0.2F, 0.8196066167365371F, 0.0F, 0.0F));
		PartDefinition head1 = neck.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(50, 8).addBox(-4.0F, -3.7F, -4.8F, 8, 5, 8), PartPose.offsetAndRotation(0.0F, -5.5F, -0.8F, -0.36425021489121656F, 0.0F, 0.0F));
		PartDefinition jaw = neck.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(85, 0).addBox(-3.0F, -0.3F, -5.0F, 6, 1, 6), PartPose.offsetAndRotation(0.0F, -2.0F, -1.7F, 0.5009094953223726F, 0.0F, 0.0F));
		jaw.addOrReplaceChild("jawpieceleft", CubeListBuilder.create().texOffs(110, 0).addBox(2.0F, -1.3F, -5.0F, 1, 1, 3), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		jaw.addOrReplaceChild("jawpieceright", CubeListBuilder.create().texOffs(110, 5).addBox(-3.0F, -1.3F, -5.0F, 1, 1, 3), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		jaw.addOrReplaceChild("jawpiecemid", CubeListBuilder.create().texOffs(110, 10).addBox(-1.0F, -1.3F, -5.0F, 2, 1, 1), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		neck.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(85, 14).addBox(-4.0F, 1.3F, -0.8F, 8, 1, 4), PartPose.offsetAndRotation(0.0F, -5.5F, -0.8F, -0.36425021489121656F, 0.0F, 0.0F));
		neck.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(85, 8).addBox(-3.5F, -1.2F, -2.2F, 7, 2, 3), PartPose.offsetAndRotation(0.0F, -1.4F, 0.2F, -0.36425021489121656F, 0.0F, 0.0F));

		head1.addOrReplaceChild("headpieceleft1", CubeListBuilder.create().texOffs(50, 22).addBox(1.0F, 1.3F, -4.8F, 2, 1, 1), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		head1.addOrReplaceChild("headpieceright1", CubeListBuilder.create().texOffs(57, 22).addBox(-3.0F, 1.3F, -4.8F, 2, 1, 1), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		head1.addOrReplaceChild("headpieceleft2", CubeListBuilder.create().texOffs(64, 22).addBox(2.0F, 1.3F, -3.8F, 1, 1, 3), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		head1.addOrReplaceChild("headpieceright2", CubeListBuilder.create().texOffs(73, 22).addBox(-3.0F, 1.3F, -3.8F, 1, 1, 3), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		head1.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(50, 27).addBox(-4.5F, -5.0F, 0.0F, 9, 10, 9), PartPose.offsetAndRotation(0.0F, 0.75F, -5.3F, 0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(WightRenderState state) {
		if (state.isVolatile) {
			this.root().visible = false;
			for (ModelPart headPart : this.headParts) {
				headPart.visible = true;
			}
		} else {
			this.root().visible = true;
		}

		this.neck.xRot = 0.4F + state.hidingProgress;
		this.jaw.xRot = -0.4F + 1F - state.hidingProgress;

		this.armright.xRot = (float) Math.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
		this.armleft.xRot = (float) -Math.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
		this.legleft.xRot = (float) Math.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
		this.legright.xRot = (float) -Math.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
	}
}
