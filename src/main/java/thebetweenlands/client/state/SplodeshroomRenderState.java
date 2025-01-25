package thebetweenlands.client.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.RandomSource;

public class SplodeshroomRenderState extends LivingEntityRenderState {
	public final RandomSource random = RandomSource.create();
	public boolean exploded;
	public float swellCount;
}
