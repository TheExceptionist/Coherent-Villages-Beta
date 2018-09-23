package net.theexceptionist.coherentvillages.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;

public class EntityVillagerHorse extends EntityHorse
{
  //  private final EntityAISkeletonRiders skeletonTrapAI = new EntityAISkeletonRiders(this);
    private boolean skeletonTrap;
    private int skeletonTrapTime;

    public EntityVillagerHorse(World worldIn)
    {
        super(worldIn);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25000000298023224D);
        this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());
    }
    
    

    protected SoundEvent getAmbientSound()
    {
        super.getAmbientSound();
        return SoundEvents.ENTITY_SKELETON_HORSE_AMBIENT;
    }

    protected SoundEvent getDeathSound()
    {
        super.getDeathSound();
        return SoundEvents.ENTITY_SKELETON_HORSE_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource source)
    {
        super.getHurtSound(source);
        return SoundEvents.ENTITY_SKELETON_HORSE_HURT;
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return super.getMountedYOffset() - 0.1875D;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_SKELETON_HORSE;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.isTrap() && this.skeletonTrapTime++ >= 18000)
        {
            this.setDead();
        }
    }

    public static void registerFixesSkeletonHorse(DataFixer fixer)
    {
        AbstractHorse.registerFixesAbstractHorse(fixer, EntitySkeletonHorse.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("SkeletonTrap", this.isTrap());
        compound.setInteger("SkeletonTrapTime", this.skeletonTrapTime);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setTrap(compound.getBoolean("SkeletonTrap"));
        this.skeletonTrapTime = compound.getInteger("SkeletonTrapTime");
    }

    public boolean isTrap()
    {
        return this.skeletonTrap;
    }

    public void setTrap(boolean trap)
    {
        if (trap != this.skeletonTrap)
        {
            this.skeletonTrap = trap;

            if (trap)
            {
             //   this.tasks.addTask(1, this.skeletonTrapAI);
            }
            else
            {
              //  this.tasks.removeTask(this.skeletonTrapAI);
            }
        }
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = !itemstack.isEmpty();

        if (flag && itemstack.getItem() == Items.SPAWN_EGG)
        {
            return super.processInteract(player, hand);
        }
        else if (!this.isTame())
        {
            return false;
        }
        else if (this.isChild())
        {
            return super.processInteract(player, hand);
        }
        else if (player.isSneaking())
        {
            this.openGUI(player);
            return true;
        }
        else if (this.isBeingRidden())
        {
            return super.processInteract(player, hand);
        }
        else
        {
            if (flag)
            {
                if (itemstack.getItem() == Items.SADDLE && !this.isHorseSaddled())
                {
                    this.openGUI(player);
                    return true;
                }

                if (itemstack.interactWithEntity(player, this, hand))
                {
                    return true;
                }
            }

            this.mountTo(player);
            return true;
        }
    }
    
    public void mountTo(AbstractVillagerSoldier player)
    {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        this.setEatingHaystack(false);
        this.setRearing(false);

        if (!this.world.isRemote)
        {
            player.startRiding(this);
        }
    }
}