package net.theexceptionist.coherentvillages.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.common.MinecraftForge;
import net.theexceptionist.coherentvillages.entity.EntityMerchantGuard;
import net.theexceptionist.coherentvillages.entity.EntityVillagerAlchemist;
import net.theexceptionist.coherentvillages.entity.EntityVillagerArcher;
import net.theexceptionist.coherentvillages.entity.EntityVillagerArrow;
import net.theexceptionist.coherentvillages.entity.EntityVillagerCreeperHunter;
import net.theexceptionist.coherentvillages.entity.EntityVillagerGuardian;
import net.theexceptionist.coherentvillages.entity.EntityVillagerHorse;
import net.theexceptionist.coherentvillages.entity.EntityVillagerHunter;
import net.theexceptionist.coherentvillages.entity.EntityVillagerKnight;
import net.theexceptionist.coherentvillages.entity.EntityVillagerMage;
import net.theexceptionist.coherentvillages.entity.EntityVillagerMerchant;
import net.theexceptionist.coherentvillages.entity.RenderMerchantGuard;
import net.theexceptionist.coherentvillages.entity.RenderVillagerAlchemist;
import net.theexceptionist.coherentvillages.entity.RenderVillagerArcher;
import net.theexceptionist.coherentvillages.entity.RenderVillagerArrow;
import net.theexceptionist.coherentvillages.entity.RenderVillagerCreeperHunter;
import net.theexceptionist.coherentvillages.entity.RenderVillagerGuardian;
import net.theexceptionist.coherentvillages.entity.RenderVillagerHorse;
import net.theexceptionist.coherentvillages.entity.RenderVillagerHunter;
import net.theexceptionist.coherentvillages.entity.RenderVillagerKnight;
import net.theexceptionist.coherentvillages.entity.RenderVillagerMage;
import net.theexceptionist.coherentvillages.entity.RenderVillagerMerchant;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerGuard;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerManAtArms;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerMilitia;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerPeasant;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerSergeant;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerWarrior;
import net.theexceptionist.coherentvillages.entity.soldier.RenderVillagerGuard;
import net.theexceptionist.coherentvillages.entity.soldier.RenderVillagerManAtArms;
import net.theexceptionist.coherentvillages.entity.soldier.RenderVillagerMilitia;
import net.theexceptionist.coherentvillages.entity.soldier.RenderVillagerPeasant;
import net.theexceptionist.coherentvillages.entity.soldier.RenderVillagerSergeant;
import net.theexceptionist.coherentvillages.entity.soldier.RenderVillagerWarrior;
import net.theexceptionist.coherentvillages.events.EventOverrideMerchantSpawn;
import net.theexceptionist.coherentvillages.events.EventOverrideVillages;

public class ClientProxy extends CommonProxy {
	public void registerRenderInformation(){
		
	}
	
	public void registerRenderers(){
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        
		//Soldiers
        renderManager.entityRenderMap.put(EntityVillagerGuard.class, new RenderVillagerGuard(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerPeasant.class, new RenderVillagerPeasant(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerSergeant.class, new RenderVillagerSergeant(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerWarrior.class, new RenderVillagerWarrior(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerManAtArms.class, new RenderVillagerManAtArms(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMilitia.class, new RenderVillagerMilitia(renderManager));
        
        
        renderManager.entityRenderMap.put(EntityVillagerArcher.class, new RenderVillagerArcher(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMage.class, new RenderVillagerMage(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerGuardian.class, new RenderVillagerGuardian(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerAlchemist.class, new RenderVillagerAlchemist(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerMerchant.class, new RenderVillagerMerchant(renderManager));
        renderManager.entityRenderMap.put(EntityMerchantGuard.class, new RenderMerchantGuard(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerKnight.class, new RenderVillagerKnight(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerHorse.class, new RenderVillagerHorse(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerHunter.class, new RenderVillagerHunter(renderManager));
        renderManager.entityRenderMap.put(EntityVillagerCreeperHunter.class, new RenderVillagerCreeperHunter(renderManager));
        //renderManager.entityRenderMap.put(EntityVillagerEvilMage.class, new RenderVillagerEvilMage(renderManager));
        
        renderManager.entityRenderMap.put(EntityVillagerArrow.class, new RenderVillagerArrow(renderManager));
}
	
	public void initEvents(){
		MinecraftForge.EVENT_BUS.register(new EventOverrideMerchantSpawn());	
		MinecraftForge.TERRAIN_GEN_BUS.register(new EventOverrideVillages());
	}
}
