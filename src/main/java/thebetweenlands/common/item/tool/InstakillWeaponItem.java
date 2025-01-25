package thebetweenlands.common.item.tool;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;

public class InstakillWeaponItem extends SwordItem {

	private final TagKey<EntityType<?>> instakillMobs;

	public InstakillWeaponItem(ToolMaterial material, TagKey<EntityType<?>> instakillMobs, Properties properties) {
		super(material, 3.0F, -2.4F, properties);
		this.instakillMobs = instakillMobs;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (target.getType().is(this.instakillMobs)) {
			target.hurt(attacker.damageSources().indirectMagic(attacker, attacker), target.getMaxHealth());
		}
		return super.hurtEnemy(stack, target, attacker);
	}
}
