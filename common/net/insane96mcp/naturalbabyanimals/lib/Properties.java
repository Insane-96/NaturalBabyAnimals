package net.insane96mcp.naturalbabyanimals.lib;

import net.insane96mcp.naturalbabyanimals.NaturalBabyAnimals;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
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
			"minecraft:villager,25.0"
		};
		
		@Comment("Minium random value to set the animals' age")
		public static int minAgeTicks = 6000;
		@Comment("Maximum random value to set the animals' age\n1200 is one minute to grow up. 24000 is 20 minutes")
		public static int maxAgeTicks = 24000;
		
		@Comment("List of mobs that can spawn as baby and chance for them to become baby\nThe format is modid:entityname,percentageChance (e.g. babymobs:zombiechicken,50.0). Get to a new line to add more mobs")
		public static String[] mobsAffected = mobs_chance_default;
	}

	@Mod.EventBusSubscriber(modid = NaturalBabyAnimals.MOD_ID)
	private static class EventHandler{
	    public static void onConfigChangedEvent(OnConfigChangedEvent event)
	    {
	        if (event.getModID().equals(NaturalBabyAnimals.MOD_ID))
	        {
	            ConfigManager.sync(NaturalBabyAnimals.MOD_ID, Type.INSTANCE);
	        }
	    }
	    
	    public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
	    	if (event.player.world.isRemote)
	    		return;
	    	
	    	
	    }
	}
}
