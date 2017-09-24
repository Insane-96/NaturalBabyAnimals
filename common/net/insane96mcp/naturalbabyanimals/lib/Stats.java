package net.insane96mcp.naturalbabyanimals.lib;

import net.insane96mcp.naturalbabyanimals.Config;

public class Stats {
	public static int minAgeTicks;
	public static int maxAgeTicks;
	public static float chance;
	public static boolean babyMobs;
	
	public static void Init() {
		minAgeTicks = Config.LoadIntProperty("general", "min_age_ticks", "Minium random value to set the baby animals age", 6000);
		maxAgeTicks = Config.LoadIntProperty("general", "max_age_ticks", "Maximum random value to set the baby animals age", 24000);
		chance = Config.LoadFloatProperty("general", "chance", "Chance for an animal to become a baby animal", 50f);

		babyMobs = Config.LoadBoolProperty("mod_support", "baby_mobs", "Whenever the Zombie Chicken should be made baby in the Baby Mobs mod", false);
	}
}
