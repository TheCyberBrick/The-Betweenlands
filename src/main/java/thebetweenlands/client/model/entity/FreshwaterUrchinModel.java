package thebetweenlands.client.model.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import thebetweenlands.client.model.MowzieModelBase;
import thebetweenlands.client.state.FreshwaterUrchinRenderState;

import java.util.Arrays;

public class FreshwaterUrchinModel extends MowzieModelBase<FreshwaterUrchinRenderState> {

	private final ModelPart analSac;
	private final ModelPart[] spikes;

	public FreshwaterUrchinModel(ModelPart root) {
		super(root);
		var base = root.getChild("base");
		this.analSac = base.getChild("anal_sac");
		ModelPart spikePoint = base.getChild("spike_rotationpoint");

		this.spikes = new ModelPart[]{
			spikePoint.getChild("spike_f1"), base.getChild("spike_f2"), spikePoint.getChild("spike_f3"), base.getChild("spike_f4"), spikePoint.getChild("spike_f5"),
			spikePoint.getChild("spike_b1"), base.getChild("spike_b2"), spikePoint.getChild("spike_b3"), base.getChild("spike_b4"), spikePoint.getChild("spike_b5"),
			spikePoint.getChild("spike_l1"), base.getChild("spike_l2"), spikePoint.getChild("spike_l3"), base.getChild("spike_l4"), spikePoint.getChild("spike_l5"),
			spikePoint.getChild("spike_r1"), base.getChild("spike_r2"), spikePoint.getChild("spike_r3"), base.getChild("spike_r4"), spikePoint.getChild("spike_r5")
		};
	}

	public static LayerDefinition create() {
		MeshDefinition definition = new MeshDefinition();
		PartDefinition partDefinition = definition.getRoot();

		var base = partDefinition.addOrReplaceChild("base", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -4.0F, -3.0F, 6, 4, 6),
			PartPose.offset(0.0F, 24.0F, 0.0F));
		base.addOrReplaceChild("anal_sac", CubeListBuilder.create()
				.texOffs(0, 11).addBox(-1.5F, -1.0F, -1.5F, 3, 1, 3),
			PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 0.18203784098300857F, 0.0F));
		var spike_rotationpoint = base.addOrReplaceChild("spike_rotationpoint", CubeListBuilder.create()
				.texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0, 0, 0),
			PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7853981633974483F, 0.0F));

		spike_rotationpoint.addOrReplaceChild("spike_f1", CubeListBuilder.create()
				.texOffs(0, 29).addBox(-3.0F, -3.0F, 0.0F, 6, 3, 0),
			PartPose.offsetAndRotation(0.0F, -4.0F, -1.0F, 0.40980330836826856F, 0.0F, 0.0F));
		base.addOrReplaceChild("spike_f2", CubeListBuilder.create()
				.texOffs(0, 33).addBox(-3.0F, -2.0F, 0.0F, 6, 2, 0),
			PartPose.offsetAndRotation(0.0F, -4.0F, 3.0F, -0.7853981633974483F, 0.0F, 0.0F));
		spike_rotationpoint.addOrReplaceChild("spike_f3", CubeListBuilder.create()
				.texOffs(0, 36).addBox(-3.5F, -6.0F, 0.0F, 7, 6, 0),
			PartPose.offsetAndRotation(0.0F, -2.5F, -1.0F, 1.2747884856566583F, 0.0F, 0.0F));
		base.addOrReplaceChild("spike_f4", CubeListBuilder.create()
				.texOffs(0, 43).addBox(-3.0F, -2.0F, 0.0F, 6, 2, 0),
			PartPose.offsetAndRotation(0.0F, -2.0F, -3.0F, 1.4570008595648662F, 0.0F, 0.0F));
		spike_rotationpoint.addOrReplaceChild("spike_f5", CubeListBuilder.create()
				.texOffs(0, 46).addBox(-3.5F, -6.0F, 0.0F, 7, 6, 0),
			PartPose.offsetAndRotation(0.0F, -1.5F, -1.0F, 1.6845917940249266F, 0.0F, 0.0F));

		spike_rotationpoint.addOrReplaceChild("spike_b1", CubeListBuilder.create()
				.texOffs(45, 29).addBox(-3.0F, -3.0F, 0.0F, 6, 3, 0),
			PartPose.offsetAndRotation(0.0F, -4.0F, 1.0F, -0.40980330836826856F, 0.0F, 0.0F));
		base.addOrReplaceChild("spike_b2", CubeListBuilder.create()
				.texOffs(45, 33).addBox(-3.0F, -2.0F, 0.0F, 6, 2, 0),
			PartPose.offsetAndRotation(0.0F, -4.0F, -3.0F, 0.7853981633974483F, 0.0F, 0.0F));
		spike_rotationpoint.addOrReplaceChild("spike_b3", CubeListBuilder.create()
				.texOffs(45, 36).addBox(-3.5F, -6.0F, 0.0F, 7, 6, 0),
			PartPose.offsetAndRotation(0.0F, -2.5F, 1.0F, -1.2747884856566583F, 0.0F, 0.0F));
		base.addOrReplaceChild("spike_b4", CubeListBuilder.create()
				.texOffs(45, 43).addBox(-3.0F, -2.0F, 0.0F, 6, 2, 0),
			PartPose.offsetAndRotation(0.0F, -2.0F, 3.0F, -1.4570008595648662F, 0.0F, 0.0F));
		spike_rotationpoint.addOrReplaceChild("spike_b5", CubeListBuilder.create()
				.texOffs(45, 46).addBox(-3.5F, -6.0F, 0.0F, 7, 6, 0),
			PartPose.offsetAndRotation(0.0F, -1.5F, 1.0F, -1.6845917940249266F, 0.0F, 0.0F));

		spike_rotationpoint.addOrReplaceChild("spike_r1", CubeListBuilder.create()
				.texOffs(30, 23).addBox(0.0F, -3.0F, -3.0F, 0, 3, 6),
			PartPose.offsetAndRotation(-1.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.40980330836826856F));
		base.addOrReplaceChild("spike_r2", CubeListBuilder.create()
				.texOffs(30, 27).addBox(0.0F, -2.0F, -3.0F, 0, 2, 6),
			PartPose.offsetAndRotation(-3.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.7853981633974483F));
		spike_rotationpoint.addOrReplaceChild("spike_r3", CubeListBuilder.create()
				.texOffs(30, 29).addBox(0.0F, -6.0F, -3.5F, 0, 6, 7),
			PartPose.offsetAndRotation(-1.0F, -2.5F, 0.0F, 0.0F, 0.0F, -1.2747884856566583F));
		base.addOrReplaceChild("spike_r4", CubeListBuilder.create()
				.texOffs(30, 37).addBox(0.0F, -2.0F, -3.0F, 0, 2, 6),
			PartPose.offsetAndRotation(-3.0F, -2.0F, 0.0F, 0.0F, 0.0F, -1.4570008595648662F));
		spike_rotationpoint.addOrReplaceChild("spike_r5", CubeListBuilder.create()
				.texOffs(30, 39).addBox(0.0F, -6.0F, -3.5F, 0, 6, 7),
			PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, -1.6845917940249266F));

		spike_rotationpoint.addOrReplaceChild("spike_l1", CubeListBuilder.create()
				.texOffs(15, 23).addBox(0.0F, -3.0F, -3.0F, 0, 3, 6),
			PartPose.offsetAndRotation(1.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.40980330836826856F));
		base.addOrReplaceChild("spike_l2", CubeListBuilder.create()
				.texOffs(15, 27).addBox(0.0F, -2.0F, -3.0F, 0, 2, 6),
			PartPose.offsetAndRotation(3.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.7853981633974483F));
		spike_rotationpoint.addOrReplaceChild("spike_l3", CubeListBuilder.create()
				.texOffs(15, 29).addBox(0.0F, -6.0F, -3.5F, 0, 6, 7),
			PartPose.offsetAndRotation(1.0F, -2.5F, 0.0F, 0.0F, 0.0F, 1.2747884856566583F));
		base.addOrReplaceChild("spike_l4", CubeListBuilder.create()
				.texOffs(15, 37).addBox(0.0F, -2.0F, -3.0F, 0, 2, 6),
			PartPose.offsetAndRotation(3.0F, -2.0F, 0.0F, 0.0F, 0.0F, 1.4570008595648662F));
		spike_rotationpoint.addOrReplaceChild("spike_l5", CubeListBuilder.create()
				.texOffs(15, 39).addBox(0.0F, -6.0F, -3.5F, 0, 6, 7),
			PartPose.offsetAndRotation(1.0F, -1.5F, 0.0F, 0.0F, 0.0F, 1.6845917940249266F));

		return LayerDefinition.create(definition, 64, 64);
	}

	@Override
	public void setupAnim(FreshwaterUrchinRenderState state) {
		super.setupAnim(state);
		float scaleSpikes = 1.0F / 80.0F * state.spikeGrowTimer;
		float pulse = Mth.sin(state.ageInTicks * 0.125F) * 0.03125F;
		Arrays.stream(this.spikes).toList().forEach(modelPart -> {
			modelPart.yRot += pulse;
			modelPart.xRot += pulse;
			modelPart.xScale = scaleSpikes;
			modelPart.yScale = scaleSpikes;
			modelPart.zScale = scaleSpikes;
		});
		float analPulse = Mth.sin(state.ageInTicks * 0.0625F) * 0.125F;
		this.analSac.xScale = 0.75F + analPulse;
		this.analSac.yScale = 1.0F + pulse * 0.25F;
		this.analSac.zScale = 0.75F + analPulse;
	}
}
