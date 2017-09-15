package net.insane96mcp.naturalbabyanimals.lib;

import net.insane96mcp.naturalbabyanimals.Config;

public class Stats {
	public static int minAgeTicks = Config.LoadIntProperty("general", "min_age_ticks", "Minium random value to set the baby animals age", 6000);
	public static int maxAgeTicks = Config.LoadIntProperty("general", "max_age_ticks", "Maximum random value to set the baby animals age", 24000);
	public static float chance = Config.LoadFloatProperty("general", "chance", "Chance for an animal to become a baby animal", 50f);
}
