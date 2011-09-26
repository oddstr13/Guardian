package org.guardian;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Guardian extends JavaPlugin
{
	private static final Logger log = Bukkit.getLogger();
	private Config config;
	private boolean errorWhileLoading = false;

	@Override
	public void onLoad() {
		try {
			config = new Config(this);
		} catch (final IOException ex) {
			log.log(Level.SEVERE, "[Guardian] Failed to load configuration", ex);
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
		log.info("Guardian v" + getDescription().getVersion() + " enabled");
	}

	@Override
	public void onDisable() {
		log.info("Guardian disabled");
	}

	public Connection getConnection() {
		// TODO
		return null;
	}

	public Config getConfig() {
		return config;
	}
}
