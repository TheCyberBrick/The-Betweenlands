package thebetweenlands.client;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;

public class BetweenlandsSpecialEffects extends DimensionSpecialEffects {

	public BetweenlandsSpecialEffects() {
		super(192, true, SkyType.OVERWORLD, false, false);
	}

	//TODO
	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 color, float light) {
		return new Vec3(0.753f, 0.847f, 1.0f).scale(light);
	}

	@Override
	public boolean isFoggyAt(int x, int y) {
		return false;
	}
}
