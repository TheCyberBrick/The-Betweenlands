package thebetweenlands.client.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class FrogRenderState extends LivingEntityRenderState {
	public ResourceLocation texture;
	public float jumpTicks;
	public boolean onGround;
}
