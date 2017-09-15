package net.insane96mcp.naturalbabyanimals.lib;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomEventHandler {

	private static int minAge = Stats.minAgeTicks;
	private static int maxAge = Stats.maxAgeTicks;
	
	private static float chance = Stats.chance / 100f;
	
	@SubscribeEvent
	public static void EntitySpawn(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		
		NBTTagCompound tags = entity.getEntityData();
		byte isAlreadyChecked = tags.getByte("babyAnimalsCheck");
	
		if (isAlreadyChecked == 1)
			return;

		if (!(entity instanceof EntityAgeable)) 
			return;
		
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
