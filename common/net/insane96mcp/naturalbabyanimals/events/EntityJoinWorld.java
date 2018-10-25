package net.insane96mcp.naturalbabyanimals.events;

import java.util.Random;

import net.insane96mcp.naturalbabyanimals.NaturalBabyAnimals;
import net.insane96mcp.naturalbabyanimals.lib.Properties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NaturalBabyAnimals.MOD_ID)
public class EntityJoinWorld {	
	@SubscribeEvent
	public static void EntityJoinWorldEvent(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
	
		if (!(entity instanceof EntityAgeable)) 
			return;
		
		EntityAgeable animal = (EntityAgeable)entity;
		
		NBTTagCompound tags = entity.getEntityData();
		byte isAlreadyChecked = tags.getByte("naturalbabyanimals:check");
		
		if (isAlreadyChecked == 1)
			return;
		
		String[] mobsAffected = new String[Properties.config.mobsAffected.length];
		float[] mobsAffectedChance = new float[Properties.config.mobsAffected.length];
		
		for (int i = 0; i < Properties.config.mobsAffected.length; i++) {
			String[] affected_and_chance = Properties.config.mobsAffected[i].split(",");
			mobsAffected[i] = affected_and_chance[0];
			mobsAffectedChance[i] = Float.parseFloat(affected_and_chance[1]) / 100f;
		}
		
		boolean affected = false;
		int index = 0;
		for (int i = 0; i < mobsAffected.length; i++) {
			if (EntityList.getKey(entity).toString().equals(mobsAffected[i])) {
				affected = true;
				index = i;
			}
		}
		if(!affected)
			return;

		tags.setByte("naturalbabyanimals:check", (byte)1);

		if (animal.getGrowingAge() < 0)
			return;
		
		if (event.getWorld().rand.nextFloat() < mobsAffectedChance[index]) {
			Random random = animal.getRNG();
			int age = random.nextInt(Properties.config.maxAgeTicks - Properties.config.minAgeTicks) + Properties.config.minAgeTicks;
			animal.setGrowingAge(-age);
		}
	}

}
