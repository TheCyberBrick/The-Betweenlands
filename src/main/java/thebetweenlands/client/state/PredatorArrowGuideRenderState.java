package thebetweenlands.client.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.phys.Vec3;

public class PredatorArrowGuideRenderState extends EntityRenderState {
	public Vec3 targetEye = Vec3.ZERO;
	public double ridingOffset;
}
