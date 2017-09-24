package net.insane96mcp.naturalbabyanimals.lib;

import java.util.Set;

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
	
	private static float chance = Stats.chance / 100f;
	
	private static boolean babyMobs = Stats.babyMobs;
	
	@SubscribeEvent
	public static void EntitySpawn(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		
		NBTTagCompound tags = entity.getEntityData();
		byte isAlreadyChecked = tags.getByte("babyAnimalsCheck");
	
		if (!(entity instanceof EntityAgeable)) 
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
		
		if (event.getWorld().rand.nextFloat() < chance) {
			animal.setGrowingAge(-(animal.getEntityWorld().rand.nextInt(maxAge - minAge) + minAge));
		}
	}
	
}
