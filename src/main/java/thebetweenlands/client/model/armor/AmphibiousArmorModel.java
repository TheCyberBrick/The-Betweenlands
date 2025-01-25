package thebetweenlands.client.model.armor;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class AmphibiousArmorModel extends HumanoidArmorModel<HumanoidRenderState> {
	private final ModelPart left_whisker;
	private final ModelPart left_whisker_2;
	private final ModelPart left_whisker_3;
	private final ModelPart left_whisker_4;
	private final ModelPart right_whisker;
	private final ModelPart right_whisker_2;
	private final ModelPart right_whisker_3;
	private final ModelPart right_whisker_4;
	private final ModelPart left_gill_1;
	private final ModelPart left_gill_2;
	private final ModelPart left_gill_3;
	private final ModelPart right_gill_1;
	private final ModelPart right_gill_2;
	private final ModelPart right_gill_3;
	private final ModelPart middle_fin;
	private final ModelPart left_fin_tip;
	private final ModelPart right_fin_tip;
	private final ModelPart back_fin_tip;
	private final ModelPart left_belt_fin;
	private final ModelPart right_belt_fin;
	private final ModelPart left_boot_fin;
	private final ModelPart left_boot_toe_fin;
	private final ModelPart left_back_boot_fin;
	private final ModelPart right_boot_fin;
	private final ModelPart right_boot_toe_fin;
	private final ModelPart right_back_boot_fin;

	public AmphibiousArmorModel(ModelPart root) {
		super(root);
		this.left_whisker = this.head.getChild("helmet").getChild("front").getChild("nasal_plate").getChild("left_whisker");
		this.left_whisker_2 = this.left_whisker.getChild("left_whisker_2");
		this.left_whisker_3 = this.left_whisker_2.getChild("left_whisker_3");
		this.left_whisker_4 = this.left_whisker_3.getChild("left_whisker_4");
		this.right_whisker = this.head.getChild("helmet").getChild("front").getChild("nasal_plate").getChild("right_whisker");
		this.right_whisker_2 = this.right_whisker.getChild("right_whisker_2");
		this.right_whisker_3 = this.right_whisker_2.getChild("right_whisker_3");
		this.right_whisker_4 = this.right_whisker_3.getChild("right_whisker_4");
		this.left_gill_1 = this.head.getChild("helmet").getChild("front").getChild("left_helmet_plate").getChild("left_connector").getChild("front_plate").getChild("left_gill_1");
		this.left_gill_2 = this.left_gill_1.getChild("left_gill_2");
		this.left_gill_3 = this.head.getChild("helmet").getChild("front").getChild("left_helmet_plate").getChild("left_connector").getChild("front_plate").getChild("left_gill_3");
		this.right_gill_1 = this.head.getChild("helmet").getChild("front").getChild("right_helmet_plate").getChild("right_connector").getChild("front_plate").getChild("right_gill_1");
		this.right_gill_2 = this.right_gill_1.getChild("right_gill_2");
		this.right_gill_3 = this.head.getChild("helmet").getChild("front").getChild("right_helmet_plate").getChild("right_connector").getChild("front_plate").getChild("right_gill_3");
		this.middle_fin = this.body.getChild("base").getChild("middle_fin");
		this.left_fin_tip = this.body.getChild("base").getChild("left_fin").getChild("left_fin_tip");
		this.right_fin_tip = this.body.getChild("base").getChild("right_fin").getChild("right_fin_tip");
		this.back_fin_tip =  this.body.getChild("base").getChild("back_fin").getChild("back_fin_tip");
		this.left_belt_fin = this.body.getChild("belt").getChild("left_belt").getChild("left_belt_fin");
		this.right_belt_fin = this.body.getChild("belt").getChild("right_belt").getChild("right_belt_fin");
		this.left_boot_fin = this.leftLeg.getChild("left_boot").getChild("left_main_boot").getChild("left_boot_fin");
		this.left_boot_toe_fin = this.leftLeg.getChild("left_boot").getChild("left_main_boot").getChild("left_front_boot").getChild("left_boot_toe").getChild("left_boot_toe_fin");
		this.left_back_boot_fin = this.leftLeg.getChild("left_boot").getChild("left_main_boot").getChild("left_back_boot_fin");
		this.right_boot_fin = this.rightLeg.getChild("right_boot").getChild("right_main_boot").getChild("right_boot_fin");
		this.right_boot_toe_fin = this.rightLeg.getChild("right_boot").getChild("right_main_boot").getChild("right_front_boot").getChild("right_boot_toe").getChild("right_boot_toe_fin");
		this.right_back_boot_fin = this.rightLeg.getChild("right_boot").getChild("right_main_boot").getChild("right_back_boot_fin");
	}

	public static LayerDefinition makeModel() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		var head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

		var helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(0, 8).addBox(-4.5F, -6.0F, 0.0F, 9.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -27.0F, -0.25F, -0.0873F, 0.0F, 0.0F));

		helmet.addOrReplaceChild("back_plate", CubeListBuilder.create().texOffs(36, 0).addBox(-4.5F, -1.0F, -5.0F, 9.0F, 5.0F, 5.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -1.0F, 5.0F, 0.1309F, 0.0F, 0.0F));

		var front = helmet.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0.0F, -5.0F, 9.0F, 3.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		var nasal_plate = front.addOrReplaceChild("nasal_plate", CubeListBuilder.create().texOffs(28, 8).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -5.0F, -0.0873F, 0.0F, 0.0F));

		var left_whisker = nasal_plate.addOrReplaceChild("left_whisker", CubeListBuilder.create().texOffs(25, 0).mirror().addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 3.0F, 0.0F, -0.1309F, 0.2618F, 0.3927F));

		var left_whisker_2 = left_whisker.addOrReplaceChild("left_whisker_2", CubeListBuilder.create().texOffs(26, 1).mirror().addBox(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		var left_whisker_3 = left_whisker_2.addOrReplaceChild("left_whisker_3", CubeListBuilder.create().texOffs(26, 2).mirror().addBox(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		left_whisker_3.addOrReplaceChild("left_whisker_4", CubeListBuilder.create().texOffs(26, 3).mirror().addBox(0.0F, -2.0F, -0.005F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		var right_whisker = nasal_plate.addOrReplaceChild("right_whisker", CubeListBuilder.create().texOffs(25, 0).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 3.0F, 0.0F, -0.1309F, -0.2618F, -0.3927F));

		var right_whisker_2 = right_whisker.addOrReplaceChild("right_whisker_2", CubeListBuilder.create().texOffs(26, 1).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		var right_whisker_3 = right_whisker_2.addOrReplaceChild("right_whisker_3", CubeListBuilder.create().texOffs(26, 2).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		right_whisker_3.addOrReplaceChild("right_whisker_4", CubeListBuilder.create().texOffs(26, 3).addBox(-2.0F, -2.0F, -0.005F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		var left_helmet_plate = front.addOrReplaceChild("left_helmet_plate", CubeListBuilder.create().texOffs(55, 15).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.555F, 3.0F, -2.0F, 0.0F, 0.0F, -0.1309F));

		var left_connector = left_helmet_plate.addOrReplaceChild("left_connector", CubeListBuilder.create().texOffs(55, 11).addBox(-1.005F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 2.0F, -0.2182F, 0.0F, 0.0F));

		var front_plate = left_connector.addOrReplaceChild("front_plate", CubeListBuilder.create().texOffs(54, 21).addBox(0.0F, 0.0F, -1.99F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.005F, 1.0F, -2.0F, 0.0F, 0.0F, -0.1745F));

		var left_gill_1 = front_plate.addOrReplaceChild("left_gill_1", CubeListBuilder.create().texOffs(40, 13).mirror().addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.505F, 1.0F, 2.0F, 0.0F, 0.1309F, 0.0F));

		left_gill_1.addOrReplaceChild("left_gill_2", CubeListBuilder.create().texOffs(36, 13).mirror().addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.3491F, 0.0F));

		front_plate.addOrReplaceChild("left_gill_3", CubeListBuilder.create().texOffs(32, 13).mirror().addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.005F, 1.0F, 2.0F, 0.0F, 0.1745F, 0.0F));

		left_helmet_plate.addOrReplaceChild("left_gill_4", CubeListBuilder.create().texOffs(28, 14).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 2.0F, 0.0F, 0.0436F, 0.0F));

		var right_helmet_plate = front.addOrReplaceChild("right_helmet_plate", CubeListBuilder.create().texOffs(45, 15).addBox(0.0F, -1.0F, -1.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.555F, 3.0F, -2.0F, 0.0F, 0.0F, 0.1309F));

		var right_connector = right_helmet_plate.addOrReplaceChild("right_connector", CubeListBuilder.create().texOffs(45, 11).addBox(0.005F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 2.0F, -0.2182F, 0.0F, 0.0F));

		var front_plate2 = right_connector.addOrReplaceChild("front_plate2", CubeListBuilder.create().texOffs(44, 21).addBox(-1.0F, 0.0F, -1.99F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.005F, 1.0F, -2.0F, 0.0F, 0.0F, 0.1745F));

		var right_gill_1 = front_plate2.addOrReplaceChild("right_gill_1", CubeListBuilder.create().texOffs(40, 13).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.505F, 1.0F, 2.0F, 0.0F, -0.1309F, 0.0F));

		right_gill_1.addOrReplaceChild("right_gill_2", CubeListBuilder.create().texOffs(36, 13).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, -0.3491F, 0.0F));

		front_plate2.addOrReplaceChild("right_gill_3", CubeListBuilder.create().texOffs(32, 13).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.005F, 1.0F, 2.0F, 0.0F, -0.1745F, 0.0F));

		right_helmet_plate.addOrReplaceChild("right_gill_4", CubeListBuilder.create().texOffs(28, 14).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 2.0F, 0.0F, -0.0436F, 0.0F));

		var body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		var base = body.addOrReplaceChild("base", CubeListBuilder.create().texOffs(34, 43).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.5F, -2.5F, 0.0873F, 0.0F, 0.0F));

		base.addOrReplaceChild("middle_fin", CubeListBuilder.create().texOffs(39, 54).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, -0.1745F, 0.0F, 0.0F));

		var left_fin = base.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(0, 32).addBox(0.0F, -4.0F, 0.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 2.0F, -1.0F, 0.0F, -0.1309F, -0.2182F));

		left_fin.addOrReplaceChild("left_fin_tip", CubeListBuilder.create().texOffs(39, 62).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		var right_fin = base.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(20, 32).addBox(-4.0F, -4.0F, 0.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, -1.0F, 0.0F, 0.1309F, 0.2182F));

		right_fin.addOrReplaceChild("right_fin_tip", CubeListBuilder.create().texOffs(39, 62).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		var back_fin = base.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(41, 35).addBox(-3.0F, -2.0F, -1.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.5F, -0.1309F, 0.0F, 0.0F));

		back_fin.addOrReplaceChild("back_fin_tip", CubeListBuilder.create().texOffs(39, 58).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

		body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 18).addBox(-4.45F, -2.0F, -1.5F, 9.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, -1.0F));

		var belt = body.addOrReplaceChild("belt", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		var left_belt = belt.addOrReplaceChild("left_belt", CubeListBuilder.create().texOffs(84, 0).addBox(0.0F, 0.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.1309F));

		left_belt.addOrReplaceChild("left_belt_fin", CubeListBuilder.create().texOffs(105, -6).mirror().addBox(0.0F, 0.0F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		var right_belt = belt.addOrReplaceChild("right_belt", CubeListBuilder.create().texOffs(64, 0).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.1309F));

		right_belt.addOrReplaceChild("right_belt_fin", CubeListBuilder.create().texOffs(105, -6).addBox(0.0F, 0.0F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		var front_buckle = belt.addOrReplaceChild("front_buckle", CubeListBuilder.create().texOffs(106, 28).addBox(-1.005F, -2.0F, -1.0F, 2.01F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.5F, -0.2182F, 0.0F, 0.0F));

		front_buckle.addOrReplaceChild("front_buckle_2", CubeListBuilder.create().texOffs(106, 25).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.2618F, 0.0F, 0.0F));

		var back_buckle = belt.addOrReplaceChild("back_buckle", CubeListBuilder.create().texOffs(116, 29).addBox(-2.005F, -1.0F, -1.0F, 4.01F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 2.5F));

		back_buckle.addOrReplaceChild("back_buckle_2", CubeListBuilder.create().texOffs(116, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

		var right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		var right_plate_1 = right_arm.addOrReplaceChild("right_plate_1", CubeListBuilder.create().texOffs(0, 55).addBox(-3.0F, -1.0F, -2.505F, 5.0F, 3.0F, 5.01F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		var right_plate_2 = right_plate_1.addOrReplaceChild("right_plate_2", CubeListBuilder.create(), PartPose.offset(-3.0F, 2.0F, 0.0F));

		right_plate_2.addOrReplaceChild("shoulderplate_right1b_r1", CubeListBuilder.create().texOffs(20, 55).addBox(0.0F, -2.0F, -2.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		var left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		var left_plate_1 = left_arm.addOrReplaceChild("left_plate_1", CubeListBuilder.create().texOffs(0, 45).addBox(-2.0F, -1.0F, -2.505F, 5.0F, 3.0F, 5.01F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		var left_plate_2 = left_plate_1.addOrReplaceChild("left_plate_2", CubeListBuilder.create(), PartPose.offset(3.0F, 2.0F, 0.0F));

		left_plate_2.addOrReplaceChild("shoulderplate_left1b_r1", CubeListBuilder.create().texOffs(20, 45).addBox(-4.0F, -2.0F, -2.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		var right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(84, 17).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		var right_boot = right_leg.addOrReplaceChild("right_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		var right_boot_main = right_boot.addOrReplaceChild("right_boot_main", CubeListBuilder.create().texOffs(110, 48).addBox(-2.51F, -2.0F, -0.99F, 5.02F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.5F, -0.5F));

		right_boot_main.addOrReplaceChild("right_boot_fin", CubeListBuilder.create().texOffs(71, 49).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.51F, -2.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		var right_front_boot = right_boot_main.addOrReplaceChild("right_front_boot", CubeListBuilder.create().texOffs(96, 46).addBox(-2.515F, -7.0F, -2.0F, 5.03F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -0.99F, -0.0873F, 0.0F, 0.0F));

		var right_boot_toe = right_front_boot.addOrReplaceChild("right_boot_toe", CubeListBuilder.create().texOffs(82, 54).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.3054F, 0.0F, 0.0F));

		right_boot_toe.addOrReplaceChild("right_boot_toe_fin", CubeListBuilder.create().texOffs(68, 57).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, 0.3054F, 0.0F, 0.0F));

		right_boot_main.addOrReplaceChild("right_back_boot_fin", CubeListBuilder.create().texOffs(71, 61).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 3.01F, 0.1745F, 0.0F, 0.0F));

		var left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(64, 17).addBox(-2.5F, -0.01F, -2.51F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		var left_boot = left_leg.addOrReplaceChild("left_boot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		var left_main_boot = left_boot.addOrReplaceChild("left_main_boot", CubeListBuilder.create().texOffs(110, 56).addBox(-2.51F, -2.0F, -0.99F, 5.02F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.5F, -0.5F));

		left_main_boot.addOrReplaceChild("left_boot_fin", CubeListBuilder.create().texOffs(71, 49).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.51F, -2.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		var left_front_boot = left_main_boot.addOrReplaceChild("left_front_boot", CubeListBuilder.create().texOffs(96, 55).addBox(-2.515F, -7.0F, -2.0F, 5.03F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -0.99F, -0.0873F, 0.0F, 0.0F));

		var left_boot_toe = left_front_boot.addOrReplaceChild("left_boot_toe", CubeListBuilder.create().texOffs(82, 59).addBox(-2.5F, -3.0F, -1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.3054F, 0.0F, 0.0F));

		left_boot_toe.addOrReplaceChild("left_boot_toe_fin", CubeListBuilder.create().texOffs(68, 57).mirror().addBox(-2.5F, 0.0F, -3.0F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, 0.3054F, 0.0F, 0.0F));

		left_main_boot.addOrReplaceChild("left_back_boot_fin", CubeListBuilder.create().texOffs(71, 61).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 3.01F, 0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(HumanoidRenderState state) {
		float blend1 = (float)Math.max(0, Math.pow(((float)Math.sin(state.ageInTicks * 0.0666F) + 1.0F) * 0.5F, 10.0F) - 0.95F) * 0.5F;
		float blend2 = (float)Math.max(0, Math.pow(((float)Math.sin(state.ageInTicks * 0.0555F) + 1.0F) * 0.5F, 10.0F) - 0.95F) * 0.5F;
		float blend3 = (float)Math.max(0, Math.pow(((float)Math.sin(state.ageInTicks * 0.0333F) + 1.0F) * 0.5F, 10.0F) - 0.95F) * 0.5F;

		float angle0 = (float)Math.sin(state.ageInTicks * 0.091F) * 0.025F;
		float angle1 = ((float)Math.sin(state.ageInTicks * 0.05F) + 1.0F) * 0.15F + angle0;
		float angle2 = ((float)Math.sin(state.ageInTicks * 0.05F + 0.35F) + 1.0F) * 0.15F + angle0;
		float angle3 = ((float)Math.sin(state.ageInTicks * 3.0F) + 1.0F) + angle0;

		float whisker1 = (1 - blend1) * angle1 + blend1 * angle3;
		float whisker2 = (1 - blend2) * angle2 + blend2 * angle3;
		float whisker3 = (1 - blend3) * angle1 + blend3 * angle3;

		this.left_whisker.zRot += whisker1 - 0.16F;
		this.left_whisker_2.yRot -= (whisker1 - whisker2) * 0.75F;
		this.left_whisker_3.yRot -= (whisker1 - whisker2) * 0.75F;
		this.left_whisker_4.yRot -= (whisker1 - whisker2) * 0.75F;

		this.right_whisker.zRot -= whisker2 - 0.16F;
		this.right_whisker_2.yRot += (whisker2 - whisker1) * 0.75F;
		this.right_whisker_3.yRot += (whisker2 - whisker1) * 0.75F;
		this.right_whisker_4.yRot += (whisker2 - whisker1) * 0.75F;

		this.right_boot_fin.zRot -= whisker2 * 0.5F;
		this.left_boot_fin.zRot += whisker1 * 0.5F;

		this.right_boot_toe_fin.xRot -= whisker2 * 0.5F;
		this.left_boot_toe_fin.xRot -= whisker1 * 0.5F;

		this.right_back_boot_fin.xRot += whisker2 * 0.5F;
		this.left_back_boot_fin.xRot += whisker1 * 0.5F;

		this.right_belt_fin.zRot += whisker2 * 0.5F;
		this.left_belt_fin.zRot -= whisker1 * 0.5F;

		this.middle_fin.xRot -= whisker3 * 0.5F - 0.15F;
		this.left_fin_tip.xRot -= whisker1 * 0.5F - 0.15F;
		this.right_fin_tip.xRot -= whisker2 * 0.5F - 0.15F;
		this.back_fin_tip.xRot += whisker3 * 0.5F - 0.15F;

		this.left_gill_1.yRot -= whisker1 * 0.5F;
		this.left_gill_2.yRot -= whisker1 * 0.5F;
		this.left_gill_3.yRot -= whisker1 * 0.5F;
		this.left_gill_1.zRot += whisker1 * 0.25F;
		this.left_gill_2.zRot += whisker1 * 0.25F;
		this.left_gill_3.zRot += whisker1 * 0.25F;

		this.right_gill_1.yRot += whisker2 * 0.5F;
		this.right_gill_2.yRot += whisker2 * 0.5F;
		this.right_gill_3.yRot += whisker2 * 0.5F;
		this.right_gill_1.zRot -= whisker2 * 0.25F;
		this.right_gill_2.zRot -= whisker2 * 0.25F;
		this.right_gill_3.zRot -= whisker2 * 0.25F;
	}
}