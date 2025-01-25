package thebetweenlands.client.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import thebetweenlands.client.model.ControlledAnimation;

public class SludgeRenderState extends LivingEntityRenderState {
	public float squishFactor;
	public final ControlledAnimation scale = new ControlledAnimation(5);
}
