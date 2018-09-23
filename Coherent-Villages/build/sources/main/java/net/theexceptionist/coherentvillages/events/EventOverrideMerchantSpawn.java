package net.theexceptionist.coherentvillages.events;

import java.util.Random;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.theexceptionist.coherentvillages.entity.EntityVillagerMerchant;
import net.theexceptionist.coherentvillages.main.Main;

public class EventOverrideMerchantSpawn {
	@SubscribeEvent
	public void initSpawnEvent(LivingSpawnEvent.CheckSpawn event)
	{
		Random rand = new Random();
		
		if(event.getEntityLiving() instanceof EntityVillagerMerchant)
		{
			EntityVillagerMerchant merchant = new EntityVillagerMerchant(event.getWorld());
			
			if(rand.nextInt(1000) > Main.merchant_spawn)
			{
				//merchant.
				//merchant.despawnGuards();
				merchant.setDead();
				//System.out.println("Working");
			}
		}
	}
	
}