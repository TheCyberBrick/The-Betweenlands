package thebetweenlands.common.item.shield;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

public class BaseShieldItem extends ShieldItem {

	private final ToolMaterial material;

	public BaseShieldItem(ToolMaterial material, Properties properties) {
		super(properties.durability(material.durability() * 2));
		this.material = material;
	}

	/**
	 * Returns the blocking cooldown
	 */
	public int getShieldBlockingCooldown(ItemStack stack, LivingEntity attacked, float damage, DamageSource source) {
		return 0;
	}

	/**
	 * Called when an attack was successfully blocked
	 */
	public void onAttackBlocked(ItemStack stack, LivingEntity attacked, float damage, DamageSource source) {
		if(!attacked.level().isClientSide()) {
			//damage = CircleGemHelper.handleAttack(source, attacked, damage);

			if(source.getEntity() instanceof LivingEntity attacker) {
				ItemStack attackerItem = attacker.getMainHandItem();
				if(!attackerItem.isEmpty() && attackerItem.getItem().canDisableShield(attackerItem, stack, attacked, attacker)) {
					if(attacked instanceof Player player) {
						player.getCooldowns().addCooldown(stack, 100);
						attacked.stopUsingItem();
					}
					//Shield break sound effect
					attacked.level().broadcastEntityEvent(attacked, (byte)30);
				}
			}
		}
	}

	/**
	 * Returns the damage for the blocked attack
	 */
	public float getBlockedDamage(ItemStack stack, LivingEntity attacked, float damage, DamageSource source) {
		//float multiplier = 0.4F - Math.min(this.material.getAttackDamage() / 3.0F, 1.0F) * 0.4F;
		//return Math.min(damage * multiplier, 8.0F);
		return 0.0F;
	}

	/**
	 * Returns the knockback multiplier for defender
	 */
	public float getDefenderKnockbackMultiplier(ItemStack stack, LivingEntity attacked, float damage, DamageSource source) {
		//Uses durability as "weight"
		return 0.6F - Math.min(this.material.durability() / 2500.0F, 1.0F) * 0.6F;
	}

	/**
	 * Returns the knockback multiplier for the attacker
	 */
	public float getAttackerKnockbackMultiplier(ItemStack stack, LivingEntity attacked, float damage, DamageSource source) {
		return 0.6F;
	}

	/**
	 * Returns whether this shield can block the specified damage source
	 */
	public boolean canBlockDamageSource(ItemStack stack, LivingEntity attacked, InteractionHand hand, DamageSource source) {
		if (attacked.isUsingItem() && attacked.getUsedItemHand() == hand && !source.is(DamageTypeTags.BYPASSES_SHIELD) && attacked.isBlocking()) {
			Vec3 vec3d = source.getSourcePosition();
			if (vec3d != null) {
				Vec3 vec3d1 = attacked.getViewVector(1.0F);
				Vec3 vec3d2 = vec3d.vectorTo(attacked.position()).normalize();
				vec3d2 = new Vec3(vec3d2.x, 0.0D, vec3d2.z);
				return vec3d2.dot(vec3d1) < 0.0D;
			}
		}
		return false;
	}

	/**
	 * Called when the shield breaks
	 */
	public void onShieldBreak(ItemStack stack, LivingEntity attacked, InteractionHand hand, DamageSource source) {
		InteractionHand enumhand = attacked.getUsedItemHand();
		if(attacked instanceof Player player)
			EventHooks.onPlayerDestroyItem(player, stack, enumhand);
		attacked.setItemInHand(enumhand, ItemStack.EMPTY);
		//Shield break sound effect
		attacked.level().broadcastEntityEvent(attacked, (byte)30);
	}
}
