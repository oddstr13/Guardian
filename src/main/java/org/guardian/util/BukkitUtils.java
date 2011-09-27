package org.guardian.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.material.MaterialData;
import org.guardian.Config;

public class BukkitUtils
{
	private static final Logger log = Bukkit.getLogger();

	public static String materialName(int type) {
		final Material mat = Material.getMaterial(type);
		return mat != null ? mat.toString().replace('_', ' ').toLowerCase() : String.valueOf(type);
	}

	public static String materialName(int type, byte rawData) {
		final Material mat = Material.getMaterial(type);
		if (mat != null) {
			if ((type == 6 || type == 17 || type == 18) && rawData > 0 || type == 35 || type == 43 || type == 44) {
				final MaterialData data = mat.getNewData(rawData);
				if (data != null)
					return data.toString().toLowerCase().replace('_', ' ').replaceAll("[^a-z ]", "");
			}
			return mat.toString().replace('_', ' ').toLowerCase();
		}
		return String.valueOf(type);
	}

	/**
	 * Send an info level log message to console
	 */
	public static void info(String msg) {
		log.info("[Guardian] " + msg);
	}

	/**
	 * Send a warn level log message to console
	 */
	public static void warning(String msg) {
		log.warning("[Guardian] " + msg);
	}

	/**
	 * Send a warn level stacktrace to console
	 */
	public static void warning(String msg, Exception ex) {
		log.log(Level.WARNING, "[Guardian] " + msg + ":", ex);
	}

	/**
	 * Send a severe level log message to console
	 */
	public static void severe(String msg) {
		log.severe("[Guardian] " + msg);
	}

	/**
	 * Send a severe level stacktrace to console
	 */
	public static void severe(String msg, Exception ex) {
		log.log(Level.SEVERE, "[Guardian] " + msg + ":", ex);
	}

	/**
	 * Send an debug message to console if debug is enabled
	 */
	public static void debug(String msg) {
		if (Config.debug)
			info("DEBUG: " + msg);
	}

	/**
	 * Returns the distance between two Locations
	 **/
	public static String getEntityName(Entity entity) {
		if (entity instanceof Player)
			return ((Player)entity).getName();
		if (entity instanceof TNTPrimed)
			return "TNT";
		if (entity.getClass().getSimpleName().startsWith("Craft"))
			return entity.getClass().getSimpleName().substring(5);
		return "Unknown";
	}

	/**
	 * Returns the distance between two Locations
	 **/
	public static double distance(Location from, Location to) {
		return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2) + Math.pow(from.getZ() - to.getZ(), 2));
	}
}
