package thebetweenlands.client.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.phys.Vec3;

public class JellyfishRenderState extends LivingEntityRenderState {
	public int color;
	public float jellyScale;
	public float jellyLength;
	public float swimAnimationPos;
	public Vec3 orientation;
}
