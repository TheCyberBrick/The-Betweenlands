package thebetweenlands.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import thebetweenlands.client.model.MowzieModelBase;
import thebetweenlands.client.state.SludgeRenderState;
import thebetweenlands.common.world.event.SpoopyEvent;

public class SmolSludgeModel extends MowzieModelBase<SludgeRenderState> {

	private final ModelPart jaw;
	private final ModelPart slime;

	public SmolSludgeModel(ModelPart root) {
		super(root.getChild("skullbase"));
		this.jaw = root.getChild("skullbase").getChild("skull2").getChild("jaw");
		this.slime = root.getChild("sludge1");
	}

	public static LayerDefinition create() {
		MeshDefinition definition = new MeshDefinition();
		PartDefinition partDefinition = definition.getRoot();

		var skullbase = partDefinition.addOrReplaceChild("skullbase", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -3.0F, -3.0F, 4, 3, 4),
			PartPose.offsetAndRotation(0.0F, 20.5F, 0.0F, -0.5462880558742251F, 0.0F, -0.18203784098300857F));
		var skull2 = skullbase.addOrReplaceChild("skull2", CubeListBuilder.create()
				.texOffs(0, 8).addBox(-1.5F, 0.0F, 0.0F, 3, 1, 1),
			PartPose.offset(0.0F, 0.0F, 0.0F));
		skull2.addOrReplaceChild("jaw", CubeListBuilder.create()
				.texOffs(0, 11).addBox(-2.0F, 0.0F, -3.0F, 4, 1, 3),
			PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.40980330836826856F, 0.0F, 0.091106186954104F));

		var sludge1 = partDefinition.addOrReplaceChild("sludge1", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-4.5F, -3.5F, -4.5F, 9, 7, 9),
			PartPose.offset(0.0F, 19.5F, 0.0F));
		sludge1.addOrReplaceChild("sludge2", CubeListBuilder.create()
				.texOffs(36, 24).addBox(-3.5F, -4.5F, -3.5F, 7, 1, 7),
			PartPose.offset(0.0F, 0.0F, 0.0F));
		sludge1.addOrReplaceChild("sludge3", CubeListBuilder.create()
				.texOffs(36, 15).addBox(-3.5F, 3.5F, -3.5F, 7, 1, 7),
			PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(definition, 64, 32);
	}

	public void renderSlime(PoseStack stack, VertexConsumer consumer, int light, int overlay) {
		this.slime.render(stack, consumer, light, overlay);
	}

	@Override
	public void setupAnim(SludgeRenderState state) {
		super.setupAnim(state);
		this.root.visible = !SpoopyEvent.isSpoooopy(Minecraft.getInstance().level);
		float controller = (0.5F * Mth.sin(state.ageInTicks * 0.1F) * Mth.sin(state.ageInTicks * 0.1F)) + 0.5F;
		this.root.y += 1.5F;
		this.walk(this.jaw, 1.0F, 0.3f * controller, false, 0.0F, -0.2F * controller, state.ageInTicks, 1.0F);
		this.bob(this.root, 0.25F, controller, false, state.ageInTicks, 1.0F);
		this.root.x += 1.25F * Mth.sin(state.ageInTicks * 0.25F) * controller;
		this.flap(this.root, 0.25F, 0.2F * controller, false, 0.0F, 0.0F, state.ageInTicks, 1.0F);
		this.root.x += 0.15F;
	}
}
