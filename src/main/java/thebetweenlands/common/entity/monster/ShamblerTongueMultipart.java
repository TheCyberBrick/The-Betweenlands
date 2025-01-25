package thebetweenlands.common.entity.monster;

import net.minecraft.world.entity.Entity;
import thebetweenlands.common.entity.GenericPartEntity;

public class ShamblerTongueMultipart extends GenericPartEntity<Shambler> {

    public ShamblerTongueMultipart(Shambler parentMob, float width, float height) {
        super(parentMob, width, height);
    }

	@Override
    public boolean canCollideWith(Entity entity) {
        return entity.canBeCollidedWith() && !(entity == this.getParent());
    }
}

