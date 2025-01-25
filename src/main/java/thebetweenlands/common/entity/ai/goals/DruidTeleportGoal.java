package thebetweenlands.common.entity.ai.goals;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import thebetweenlands.common.entity.monster.DarkDruid;

import java.util.EnumSet;
import java.util.List;

public class DruidTeleportGoal extends Goal {

	private final DarkDruid druid;

	@Nullable
	private Entity entityToTeleportTo;

	public DruidTeleportGoal(DarkDruid druid) {
		this.druid = druid;
		this.setFlags(EnumSet.of(Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.druid.canTeleport() && this.druid.getRandom().nextFloat() < 0.4F) {
			List<Player> nearPlayers = this.druid.level().getEntitiesOfClass(Player.class, this.druid.getBoundingBox().inflate(24, 10, 24), player -> player.onGround() && !player.getAbilities().invulnerable);
			if (!nearPlayers.isEmpty()) {
				this.entityToTeleportTo = nearPlayers.getFirst();
				return true;
			}
		}
		return false;
	}

	@Override
	public void start() {
		this.druid.teleportNearEntity(this.entityToTeleportTo);
	}

	@Override
	public void stop() {
		this.entityToTeleportTo = null;
	}

	@Override
	public boolean canContinueToUse() {
		return false;
	}
}
