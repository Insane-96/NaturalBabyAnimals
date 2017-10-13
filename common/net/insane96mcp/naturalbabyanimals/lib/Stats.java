package net.insane96mcp.naturalbabyanimals.lib;

import net.insane96mcp.naturalbabyanimals.Config;

public class Stats {
	public static int minAgeTicks;
	public static int maxAgeTicks;
	public static float chance;
	
	public static boolean babyMobs;
	
	public static String[] mobs_chance;
	public static String[] mobs_affected;
	public static float[] mobs_affected_chance;
	
	private static String[] mobs_chance_default;
	
	public static void Init() {
		minAgeTicks = Config.LoadIntProperty("general", "min_age_ticks", "Minium random value to set the baby animals age", 6000);
		maxAgeTicks = Config.LoadIntProperty("general", "max_age_ticks", "Maximum random value to set the baby animals age", 24000);
		chance = Config.LoadFloatProperty("general", "chance", "Chance for an animal to become a baby animal", 50f);
		
		babyMobs = Config.LoadBoolProperty("mod_support", "baby_mobs", "Whenever the Zombie Chicken should be made baby in the Baby Mobs mod", false);
	
		mobs_chance_default = new String[] {
			"minecraft:chicken,50.0",
			"minecraft:pig,50.0",
			"minecraft:cow,50.0",
			"minecraft:sheep,50.0",
			"minecraft:mooshroom,50.0",
			"minecraft:villager,15.0"
		};
		
		mobs_chance = Config.LoadStringListProperty("general", "mobs_chance", "List of mobs that can spawn as baby and chance for them to become baby\nThe format is modid:entityname,percentagechance. Get to a new line to add more mobs\n", mobs_chance_default);

		mobs_affected = new String[mobs_chance.length];
		mobs_affected_chance = new float[mobs_chance.length];
		
		for (int i = 0; i < mobs_chance.length; i++) {
			String[] p = mobs_chance[i].split(",");
			mobs_affected[i] = p[0];
			mobs_affected_chance[i] = Float.parseFloat(p[1]) / 100f;
		}
	}
}
