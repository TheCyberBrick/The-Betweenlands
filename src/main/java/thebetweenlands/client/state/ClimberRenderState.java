package thebetweenlands.client.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import thebetweenlands.common.entity.ClimbingMob;
import thebetweenlands.common.entity.Orientation;

import java.util.List;

public class ClimberRenderState extends LivingEntityRenderState {
	public Orientation orientation;
	@Nullable
	public BlockPos target;
	@Nullable
	public List<ClimbingMob.PathingTarget> targets;
	public Vec3 attachment = Vec3.ZERO;
	public Vec3 attachmentOffset = Vec3.ZERO;
}
