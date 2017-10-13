package net.insane96mcp.naturalbabyanimals.lib;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomEventHandler {

	private static int minAge = Stats.minAgeTicks;
	private static int maxAge = Stats.maxAgeTicks;
	
	private static boolean babyMobs = Stats.babyMobs;
	
	private static String[] mobs_affected = Stats.mobs_affected;
	private static float[] mobs_affected_chance = Stats.mobs_affected_chance;
	
	@SubscribeEvent
	public static void EntitySpawn(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		
		NBTTagCompound tags = entity.getEntityData();
		byte isAlreadyChecked = tags.getByte("babyAnimalsCheck");
	
		if (!(entity instanceof EntityAgeable)) 
			return;
		
		boolean affected = false;
		int index = 0;
		for (int i = 0; i < mobs_affected.length; i++) {
			if (EntityList.getKey(entity).toString().equals(mobs_affected[i])) {
				affected = true;
				index = i;
			}
		}
		if(!affected)
			return;
		
		if (isAlreadyChecked == 1)
			return;
	
		if (Loader.isModLoaded("babymobs")) {
			if (EntityList.getKey(entity).toString().equals("babymobs:zombiechicken")) {
				if (!babyMobs)
					return;
			}
		}
		
		EntityAgeable animal = (EntityAgeable)entity;

		
		if (animal.getGrowingAge() < 0)
			return;

		//Sets the check for the animal
		isAlreadyChecked = 1;
		tags.setByte("babyAnimalsCheck", isAlreadyChecked);
		
		if (event.getWorld().rand.nextFloat() < mobs_affected_chance[index]) {
			animal.setGrowingAge(-(animal.getEntityWorld().rand.nextInt(maxAge - minAge) + minAge));
		}
	}
	
}
