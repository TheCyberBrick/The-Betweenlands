package thebetweenlands.client.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.phys.Vec3;

public class ShamblerRenderState extends LivingEntityRenderState {
	public boolean jawsOpen;
	public float tongueLength;
	public float jawAngle;
	public ShamblerTongueInfo[] tongueParts;

	public record ShamblerTongueInfo(Vec3 pos, boolean end) {

	}
}
