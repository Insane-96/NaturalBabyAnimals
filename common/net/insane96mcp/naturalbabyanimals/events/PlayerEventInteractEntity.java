package net.insane96mcp.naturalbabyanimals.events;

import java.util.List;

import net.insane96mcp.naturalbabyanimals.NaturalBabyAnimals;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemShears;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NaturalBabyAnimals.MOD_ID)
public class PlayerEventInteractEntity {

	@SubscribeEvent
	public static void playerInteractEvent(PlayerInteractEvent.EntityInteract event) {
		if (!(event.getEntityPlayer() instanceof EntityPlayerMP))
			return;
		
		EntityPlayerMP playerMP = (EntityPlayerMP) event.getEntityPlayer();
		
		if (!(event.getTarget() instanceof EntityMooshroom))
			return;
		
		EntityMooshroom mooshroom = (EntityMooshroom)event.getTarget();

		if (!(event.getItemStack().getItem() instanceof ItemShears)) {
			List<EntityCow> cows = event.getWorld().getEntitiesWithinAABB(EntityCow.class, new AxisAlignedBB(mooshroom.getPositionVector().add(-.1, -.1, -.1), mooshroom.getPositionVector().add(.1, .1, .1)));
						
			for (EntityCow entityCow : cows) {
				if (entityCow.equals(mooshroom)) {
					continue;
				}
				
				entityCow.setGrowingAge(0);
				break;
			}
		}
	}
}
