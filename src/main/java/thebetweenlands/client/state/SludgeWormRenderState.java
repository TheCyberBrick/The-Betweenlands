package thebetweenlands.client.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.phys.Vec3;

public class SludgeWormRenderState extends LivingEntityRenderState {

	public WormPartInfo[] parts;

	public record WormPartInfo(Vec3 pos, float yRot, float smoothedFrame) {

	}
}
