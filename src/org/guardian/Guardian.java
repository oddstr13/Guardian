package org.guardian;

import static org.guardian.util.BukkitUtils.info;
import static org.guardian.util.BukkitUtils.severe;
import java.io.IOException;
import java.sql.Connection;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Guardian extends JavaPlugin
{
	private boolean errorWhileLoading = false;

	@Override
	public void onLoad() {
		try {
			Config.load(this);
		} catch (final IOException ex) {
			severe("Failed to load configuration", ex);
			errorWhileLoading = true;
		}
	}

	@Override
	public void onEnable() {
		final PluginManager pm = getServer().getPluginManager();
		if (errorWhileLoading) {
			pm.disablePlugin(this);
			return;
		}
		info("Guardian v" + getDescription().getVersion() + " enabled");
	}

	@Override
	public void onDisable() {
		info("Guardian disabled");
	}

	public Connection getConnection() {
		// TODO
		return null;
	}
}
