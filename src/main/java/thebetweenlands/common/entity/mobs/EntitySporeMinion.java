package thebetweenlands.common.entity.mobs;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import thebetweenlands.api.entity.IEntityBL;
import thebetweenlands.common.entity.EntityDropHeldCloud;
import thebetweenlands.common.entity.ai.EntityAIFollowTarget;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.SoundRegistry;

public class EntitySporeMinion extends EntityMob implements IEntityBL {
	private float jumpHeightOverride = -1;
	public int animation_1 = 0, prev_animation_1 = 0;
	public int animation_2 = 0, prev_animation_2 = 0;
	protected static final DataParameter<Boolean> IS_FALLING = EntityDataManager.<Boolean>createKey(EntitySporeMinion.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(EntitySporeMinion.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> INFLATE_SIZE = EntityDataManager.<Integer>createKey(EntitySporeMinion.class, DataSerializers.VARINT);

	protected float prevFloatingRotationTicks = 0;
	protected float floatingRotationTicks = 0;
	
	private EntityAIFollowTarget moveToTarget;
	private boolean canFollow = false;
	private EntityAIAttackMelee meleeAttack;
	private int aggroCooldown = 100;
	private boolean canAttack = false;
	private EntityAIWander wander;
	private EntityAIAvoidEntity<EntityPlayer> runAway;
	private EntityAINearestAttackableTarget<EntityPlayer> target;

	public EntitySporeMinion(World world) {
		super(world);
		setSize(0.75F, 0.75F);
		stepHeight = 1.0F;
		this.experienceValue = 1;
		
		this.setPathPriority(PathNodeType.WATER, -1.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(IS_FALLING, false);
		dataManager.register(TYPE, rand.nextInt(3));
		dataManager.register(INFLATE_SIZE, 0);
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		moveToTarget = new EntityAIFollowTarget(this, new EntityAIFollowTarget.FollowClosest(this, EntityPlayer.class, 32D), 1D, 1F, 32.0F, false);
		meleeAttack = new EntityAIAttackMelee(this, 0.6D, false);
		wander = new EntityAIWander(this, 0.5D);
		runAway = new EntityAIAvoidEntity<EntityPlayer>(this, EntityPlayer.class, 5.0F, 0.51D, 0.51D);
		target =  new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true);
		
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, meleeAttack);
		tasks.addTask(2, wander);
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(0, target);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		double attackBuff = 0D;
		double moveSpeedBuff = 0D;
		double healthBuff = 0D;
		switch (getType()) {
		case 0:
			attackBuff = 1.5D;
			moveSpeedBuff = 0.1D;
			break;
		case 1:
			healthBuff = 15D;
			break;
		case 2:
			moveSpeedBuff = -0.1D;
			healthBuff = 25D;
			attackBuff = -0.5D;
			break;
		}
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.5D + attackBuff);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D + moveSpeedBuff);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D + healthBuff);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
	}

	@Override
	public void onUpdate() {
		prev_animation_1 = animation_1;
		prev_animation_2 = animation_2;
		if (!getEntityWorld().isRemote) {
			if (!this.isInWater()) {
				boolean canSpin = (this.getRidingEntity() != null ? !this.getRidingEntity().onGround : !onGround) && !this.isInWeb && !this.isInWater() && !this.isInLava() && world.isAirBlock(getPosition().down());
				if (canSpin && (motionY < 0D || (this.getRidingEntity() != null && this.getRidingEntity().motionY < 0))) {
					if (!getIsFalling())
						setIsFalling(true);
					motionY *= 0.7D;
					if(this.getRidingEntity() != null) {
						this.getRidingEntity().motionY *= 0.7D;
						this.getRidingEntity().fallDistance = 0;
					}
				} else {
					if (!canSpin && getIsFalling()) {
						setIsFalling(false);
					}
				}
			}

			if (getType() == 0) {
				if (aggroCooldown == 100 && !canAttack) {
					tasks.removeTask(runAway);
					tasks.addTask(1, meleeAttack);
					targetTasks.addTask(0, target);
					canAttack = true;
				}

				if (aggroCooldown == 0 && canAttack) {
					tasks.removeTask(meleeAttack);
					targetTasks.removeTask(target);
					tasks.addTask(1, runAway);
					canAttack = false;
				}

				if (aggroCooldown <= 100)
					aggroCooldown++;
			}

			if (getType() == 2 && !canFollow) {
				tasks.removeTask(wander);
				tasks.addTask(1, moveToTarget);
				tasks.removeTask(meleeAttack);
				canFollow = true;
			}

			if (getType() == 2) {
				if (getInflateSize() <= 0)
					setInflateSize(0);
				if (getInflateSize() >= 100)
					explode();
				if (getAttackTarget() == null)
					setInflateSize(getInflateSize() - 4);
				if (getAttackTarget() != null) {
					float distance = (float) getDistance(getAttackTarget().posX, getAttackTarget().getEntityBoundingBox().minY, getAttackTarget().posZ);
					if (getInflateSize() < 100 && distance <= 2)
						setInflateSize(getInflateSize() + 4);
					if (getInflateSize() < 100 && distance > 2)
						setInflateSize(getInflateSize() - 4);
				}
			}
		}

		this.prevFloatingRotationTicks = this.floatingRotationTicks;
		if(this.getIsFalling()) {
			this.floatingRotationTicks += 30;
			float wrap = MathHelper.wrapDegrees(this.floatingRotationTicks) - this.floatingRotationTicks;
			this.floatingRotationTicks +=wrap;
			this.prevFloatingRotationTicks += wrap;
		} else {
			this.floatingRotationTicks = 0;
		}

		if(ticksExisted >= 20) {
			if (animation_1 <= 10)
				animation_1++;
			if (animation_1 > 10) {
				prev_animation_1 = animation_1 = 10;
				if (animation_2 <= 10)
					animation_2++;
				if (animation_2 > 10)
					prev_animation_2 = animation_2 = 10;
			}
		}
		super.onUpdate();
	}

	private void explode() {
		setDead();
		EntityDropHeldCloud cloud = new EntityDropHeldCloud(world);
		cloud.setPosition(posX, posY, posZ);
		world.spawnEntity(cloud);
	}

	@Override
	protected boolean isMovementBlocked() {
		return getType() == 2 && getInflateSize() > 0;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			if (super.attackEntityAsMob(entity)) {
				if (entity instanceof EntityLivingBase) {
					switch (getType()) {
					case 0:
						((EntityLivingBase)entity).knockBack(this, 0.5F, MathHelper.sin(this.rotationYaw * 3.141593F / 180.0F), -MathHelper.cos(this.rotationYaw * 3.141593F / 180.0F));
						if (!getEntityWorld().isRemote)
							aggroCooldown = 0;
						break;
					case 1:
						this.heal(2F);
						world.playSound(null, getPosition(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.HOSTILE, 0.5F, 2F);
						break;
					case 2:
					/*	if (entity instanceof EntityPlayer) {
							EntityPlayer player = (EntityPlayer) entity;
							if (!isWearingSilkMask(player)) {
								ItemStack stack = player.getHeldItemMainhand();
								if (!stack.isEmpty())
									player.dropItem(true);
							}
						}*/
						break;
					}
				}
			}
			return true;
		} else
			return false;
	}

    public boolean isWearingSilkMask(EntityLivingBase entity) {
    	if(entity instanceof EntityPlayer) {
        	ItemStack helmet = ((EntityPlayer)entity).getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        	if(!helmet.isEmpty() && helmet.getItem() == ItemRegistry.SILK_MASK) {
        		return true;
        	}
        }
    	return false;
    }

    public float smoothedAngle(float partialTicks) {
        return this.prevFloatingRotationTicks + (this.floatingRotationTicks - this.prevFloatingRotationTicks) * partialTicks;
    }

    private float updateRotation(float angle, float targetAngle, float maxIncrease) {
        float f = MathHelper.wrapDegrees(targetAngle - angle);

        if (f > maxIncrease)
        {
            f = maxIncrease;
        }

        if (f < -maxIncrease)
        {
            f = -maxIncrease;
        }

        return angle + f;
    }
    
	private void setIsFalling(boolean state) {
		dataManager.set(IS_FALLING, state);
	}

	public boolean getIsFalling() {
		return dataManager.get(IS_FALLING);
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegistry.SPORELING_LIVING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundRegistry.SPORELING_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundRegistry.SPORELING_DEATH;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return null;//LootTableRegistry.SPORELING;
	}
	
	public void setJumpHeightOverride(float jumpHeightOverride) {
		this.jumpHeightOverride = jumpHeightOverride;
	}
	
	@Override
	protected float getJumpUpwardsMotion() {
		if(this.jumpHeightOverride > 0) {
			float height = this.jumpHeightOverride;
			this.jumpHeightOverride = -1;
			return height;
		}
		return super.getJumpUpwardsMotion();
	}

	@Nullable
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		if (!getEntityWorld().isRemote) {
		}
		return livingdata;
	}

	public void setType(int skinType) {
		dataManager.set(TYPE, skinType);
	}

	public int getType() {
		return dataManager.get(TYPE);
	}
	
	public void setInflateSize(int size) {
		dataManager.set(INFLATE_SIZE, size);
	}

	public int getInflateSize() {
		return dataManager.get(INFLATE_SIZE);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("type", getType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setType(nbt.getInteger("type"));
	}
}
