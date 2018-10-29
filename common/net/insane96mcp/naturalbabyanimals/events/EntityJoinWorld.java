package net.insane96mcp.naturalbabyanimals.events;

import java.util.List;
import java.util.Random;

import net.insane96mcp.naturalbabyanimals.NaturalBabyAnimals;
import net.insane96mcp.naturalbabyanimals.lib.Properties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NaturalBabyAnimals.MOD_ID)
public class EntityJoinWorld {
	@SubscribeEvent
	public static void EntityJoinWorldEvent(EntityJoinWorldEvent event) {
		if (event.getWorld().isRemote)
			return;
		
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
			String[] affectedAndChance = Properties.config.mobsAffected[i].split(",");
			if (affectedAndChance[0].equals("")) {
				NaturalBabyAnimals.logger.warn("Found empty line ({}) in Mobs List", i);
				continue;
			}
			mobsAffected[i] = affectedAndChance[0];
			if (affectedAndChance.length == 1) {	
				mobsAffectedChance[i] = 50.0f / 100f;
			}
			else { 
				mobsAffectedChance[i] = Float.parseFloat(affectedAndChance[1]) / 100f;
			}
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
		
		AxisAlignedBB axisAlignedBB = new AxisAlignedBB(animal.getPosition().add(-4, -4, -4), animal.getPosition().add(4, 4, 4));
		List<? extends EntityAgeable> animalsInAABB = animal.world.getEntitiesWithinAABB(animal.getClass(), axisAlignedBB);
		int baby = 0, adult = 0;
		for (EntityAgeable entityAgeable : animalsInAABB) {
			if (entityAgeable.getGrowingAge() < 0)
				baby++;
			else
				adult++;
		}
		
		Random random = animal.getRNG();
			
		float ratio = random.nextFloat();
		
		if (baby != adult)
		{
			if (adult > 0)
				ratio = (float)baby / (float)adult;
			else if (adult <= 0)
				ratio = 1;
		}
		
		if (ratio > mobsAffectedChance[index])
			ratio = random.nextFloat();
		
		if (baby + adult == 0)
			ratio = 1;
		
		if (ratio <= mobsAffectedChance[index]) {
			int age = random.nextInt(Properties.config.maxAgeTicks - Properties.config.minAgeTicks) + Properties.config.minAgeTicks;
			animal.setGrowingAge(-age);
		}
	}

}
