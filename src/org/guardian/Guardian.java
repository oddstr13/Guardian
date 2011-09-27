package org.guardian;

import static org.guardian.util.BukkitUtils.info;
import static org.guardian.util.BukkitUtils.severe;
import java.io.IOException;
import java.util.List;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.guardian.entries.DataEntry;

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

	/**
	 * Returns all log matching specified parameters. Also intern methods should use this.
	 **/
	public List<DataEntry> getLog(QueryParams params) {
		// TODO
		return null;
	}

	/**
	 * Performs a rollback on all log matching specified parameters. Also intern methods should use this.
	 **/
	public void rollback(QueryParams params) {
		// TODO
	}

	/**
	 * Redoes all changes matching parameters, basically a undo of a rollback. Also intern methods should use this.
	 **/
	public void rebuild(QueryParams params) {
		// TODO
	}

	/**
	 * Deletes all log matching specified parameters. Also intern methods should use this.
	 **/
	public void clearLog(QueryParams params) {
		// TODO
	}
}
