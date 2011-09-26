package org.guardian.util;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

public class BukkitUtils
{
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
}
