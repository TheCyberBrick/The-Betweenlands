package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import thebetweenlands.client.state.ClimberRenderState;
import thebetweenlands.common.entity.ClimbingMob;
import thebetweenlands.common.entity.Orientation;

import java.util.List;

public abstract class ClimberRenderer<T extends ClimbingMob, S extends ClimberRenderState, M extends EntityModel<S>> extends MobRenderer<T, S, M> {

	public ClimberRenderer(EntityRendererProvider.Context context, M model, float shadowRadius) {
		super(context, model, shadowRadius);
	}

	@Override
	public void render(S state, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		Orientation orientation = state.orientation;

		double x = state.attachmentOffset.x();
		double y = state.attachmentOffset.y();
		double z = state.attachmentOffset.z();

		stack.translate(x, y, z);

		stack.pushPose();

		stack.mulPose(Axis.YP.rotationDegrees(orientation.yaw()));
		stack.mulPose(Axis.XP.rotationDegrees(orientation.pitch()));
		stack.mulPose(Axis.YP.rotationDegrees(Math.signum(0.5f - orientation.componentY() - orientation.componentZ() - orientation.componentX()) * orientation.yaw()));

		stack.translate(0.0D, -1.5D, 0.0D);

		super.render(state, stack, buffer, packedLight);

		stack.translate(0.0D, 1.5D, 0.0D);

		stack.popPose();

		if (Minecraft.getInstance().getEntityRenderDispatcher().shouldRenderHitBoxes()) {
			stack.pushPose();
			RenderSystem.enableBlend();

			double rx = state.x;
			double ry = state.y;
			double rz = state.z;

			DebugRenderer.renderFilledBox(stack, buffer, new AABB(0, 0, 0, 0, 0, 0).inflate(0.1f).move(state.attachment), 1.0F, 0.0F, 0.0F, 0.75F);

			VertexConsumer consumer = buffer.getBuffer(RenderType.lines());
			Vec3 forward = orientation.getGlobal(state.yRot, 0);

			consumer.addVertex(stack.last(), (float) orientation.normal().x, (float) orientation.normal().y, (float) orientation.normal().z).setColor(0.0F, 0.0F, 1.0F, 1.0F).setNormal(stack.last(), 0.0F, 0.0F, 1.0F);
			consumer.addVertex(stack.last(), (float) (orientation.normal().x * 2 + forward.x * 2), (float) (orientation.normal().y + forward.y * 2), (float) (orientation.normal().z + forward.z * 2)).setColor(0.0F, 0.0F, 1.0F, 1.0F).setNormal(stack.last(), 0.0F, 0.0F, 1.0F);

			consumer = buffer.getBuffer(RenderType.lines());
			forward = orientation.getGlobal(state.yRot, -90);

			consumer.addVertex(stack.last(), (float) orientation.normal().x, (float) orientation.normal().y, (float) orientation.normal().z).setColor(0.0F, 1.0F, 0.0F, 1.0F).setNormal(stack.last(), 0.0F, 1.0F, 0.0F);
			consumer.addVertex(stack.last(), (float) (orientation.normal().x * 2 + forward.x * 2), (float) (orientation.normal().y + forward.y * 2), (float) (orientation.normal().z + forward.z * 2)).setColor(0.0F, 1.0F, 0.0F, 1.0F).setNormal(stack.last(), 0.0F, 1.0F, 0.0F);

			consumer = buffer.getBuffer(RenderType.lines());
			forward = orientation.getGlobal(state.yRot - 90, 0);

			consumer.addVertex(stack.last(), (float) orientation.normal().x, (float) orientation.normal().y, (float) orientation.normal().z).setColor(1.0F, 0.0F, 0.0F, 1.0F).setNormal(stack.last(), 1.0F, 0.0F, 0.0F);
			consumer.addVertex(stack.last(), (float) (orientation.normal().x * 2 + forward.x * 2), (float) (orientation.normal().y + forward.y * 2), (float) (orientation.normal().z + forward.z * 2)).setColor(1.0F, 0.0F, 0.0F, 1.0F).setNormal(stack.last(), 1.0F, 0.0F, 0.0F);

			BlockPos target = state.target;
			if (target != null) {
				ShapeRenderer.renderLineBox(stack, buffer.getBuffer(RenderType.lines()), new AABB(target).move(-rx - x, -ry - y, -rz - z), 0.0F, 1.0F, 1.0F, 1.0F);
			}

			List<ClimbingMob.PathingTarget> pathingTargets = state.targets;

			if(pathingTargets != null) {
				int i = 0;

				for(ClimbingMob.PathingTarget pathingTarget : pathingTargets) {
					BlockPos pos = pathingTarget.pos();

					ShapeRenderer.renderLineBox(stack, buffer.getBuffer(RenderType.lines()), new AABB(pos).move(-rx - x, -ry - y, -rz - z), 1.0f, i / (float) (pathingTargets.size() - 1), 0.0f, 0.15f);

					stack.pushPose();
					stack.translate(pos.getX() + 0.5D - rx - x, pos.getY() + 0.5D - ry - y, pos.getZ() + 0.5D - rz - z);

					stack.mulPose(pathingTarget.side().getOpposite().getRotation());

					ShapeRenderer.renderLineBox(stack, buffer.getBuffer(RenderType.lines()), new AABB(-0.501D, -0.501D, -0.501D, 0.501D, -0.45D, 0.501D), 1.0f, i / (float) (pathingTargets.size() - 1), 0.0f, 1.0f);
					DebugRenderer.renderFilledBox(stack, buffer, new AABB(-0.501D, -0.501D, -0.501D, 0.501D, -0.45D, 0.501D), 1.0f, i / (float) (pathingTargets.size() - 1), 0.0f, 0.15F);

					PoseStack.Pose matrix4f = stack.last();
					VertexConsumer builder = buffer.getBuffer(RenderType.lines());

					builder.addVertex(matrix4f, -0.501f, -0.45f, -0.501f).setColor(1.0f, i / (float) (pathingTargets.size() - 1), 0.0f, 1.0f).setNormal(matrix4f, pathingTarget.side().getUnitVec3i().getX(), pathingTarget.side().getUnitVec3i().getY(), pathingTarget.side().getUnitVec3i().getZ());
					builder.addVertex(matrix4f, 0.501f, -0.45f, 0.501f).setColor(1.0f, i / (float) (pathingTargets.size() - 1), 0.0f, 1.0f).setNormal(matrix4f, pathingTarget.side().getUnitVec3i().getX(), pathingTarget.side().getUnitVec3i().getY(), pathingTarget.side().getUnitVec3i().getZ());
					builder.addVertex(matrix4f, -0.501f, -0.45f, 0.501f).setColor(1.0f, i / (float) (pathingTargets.size() - 1), 0.0f, 1.0f).setNormal(matrix4f, pathingTarget.side().getUnitVec3i().getX(), pathingTarget.side().getUnitVec3i().getY(), pathingTarget.side().getUnitVec3i().getZ());
					builder.addVertex(matrix4f, 0.501f, -0.45f, -0.501f).setColor(1.0f, i / (float) (pathingTargets.size() - 1), 0.0f, 1.0f).setNormal(matrix4f, pathingTarget.side().getUnitVec3i().getX(), pathingTarget.side().getUnitVec3i().getY(), pathingTarget.side().getUnitVec3i().getZ());

					stack.popPose();
					i++;
				}
			}


			stack.popPose();
			RenderSystem.disableBlend();
		}

		stack.translate(-x, -y, -z);
	}

	@Override
	public void extractRenderState(T entity, S state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.orientation = entity.getOrientation();
		state.attachment = entity.attachmentNormal;
		state.target = entity.getPathingTarget();
		state.targets = entity.getTrackedPathingTargets();

		var renderOrientation = entity.calculateOrientation(partialTick);
		float verticalOffset = 0.075F;
		double x = entity.getAttachmentOffset(Direction.Axis.X, partialTick) - (float) renderOrientation.normal().x() * verticalOffset;
		double y = entity.getAttachmentOffset(Direction.Axis.Y, partialTick) - (float) renderOrientation.normal().y() * verticalOffset;
		double z = entity.getAttachmentOffset(Direction.Axis.Z, partialTick) - (float) renderOrientation.normal().z() * verticalOffset;
		state.attachmentOffset = new Vec3(x, y, z);
	}
}
