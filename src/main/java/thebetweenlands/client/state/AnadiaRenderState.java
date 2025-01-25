package thebetweenlands.client.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import thebetweenlands.common.entity.fishing.anadia.AnadiaParts;

public class AnadiaRenderState extends LivingEntityRenderState {
	public float fishSize;
	public AnadiaParts.AnadiaHeadParts headType;
	public AnadiaParts.AnadiaBodyParts bodyType;
	public AnadiaParts.AnadiaTailParts tailType;
	public AnadiaParts.AnadiaColor color;
}
