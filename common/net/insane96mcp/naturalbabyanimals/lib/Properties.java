package net.insane96mcp.naturalbabyanimals.lib;

import net.insane96mcp.naturalbabyanimals.NaturalBabyAnimals;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Config(modid = NaturalBabyAnimals.MOD_ID, category = "", name = "NaturalBabyAnimals")
public class Properties {
	
	@Name("Config")
	public static final ConfigOptions config = new ConfigOptions();
	
	public static class ConfigOptions {
		
		private static String[] mobs_chance_default = new String[] {
			"minecraft:chicken,50.0",
			"minecraft:pig,50.0",
			"minecraft:cow,50.0",
			"minecraft:sheep,50.0",
			"minecraft:mooshroom,50.0",
			"brownmooshrooms:brown_mooshroom,50.0",
			"minecraft:villager,20.0"
		};
		
		@Name("Min Age")
		@Comment("Minium random value to set the animals' age")
		public int minAgeTicks = 6000;
		@Name("Max Age")
		@Comment("Maximum random value to set the animals' age\n1200 is one minute to grow up. 24000 is 20 minutes")
		public int maxAgeTicks = 24000;
		
		@Name("Mobs List")
		@Comment("List of mobs that can spawn as baby and chance for them to become baby. Chance can be omitted and will default to 50%\nThe format is modid:entityname,percentageChance (e.g. brownmooshrooms:brown_mooshroom,50.0). Use one line per mob!")
		public String[] mobsAffected = mobs_chance_default;
	}

	@Mod.EventBusSubscriber(modid = NaturalBabyAnimals.MOD_ID)
	private static class EventHandler{
		@SubscribeEvent
	    public static void onConfigChangedEvent(OnConfigChangedEvent event)
	    {
	        if (event.getModID().equals(NaturalBabyAnimals.MOD_ID))
	        {
	            ConfigManager.sync(NaturalBabyAnimals.MOD_ID, Type.INSTANCE);
	        }
	    }
	    @SubscribeEvent
	    public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
	    	if (event.player.world.isRemote)
	    		return;
	    	
	    	
	    }
	}
}
