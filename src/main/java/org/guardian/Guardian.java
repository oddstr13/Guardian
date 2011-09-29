package org.guardian;

import static org.guardian.util.BukkitUtils.info;
import static org.guardian.util.BukkitUtils.severe;
import java.io.IOException;
import java.util.List;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.guardian.entries.DataEntry;
import org.guardian.params.QueryParams;

public class Guardian extends JavaPlugin
{
	private static Guardian guardian;
	private boolean errorWhileLoading = false;
	private DatabaseBridge database = null;

	public static Guardian getInstance() {
		return guardian;
	}

	@Override
	public void onLoad() {
		guardian = this;
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
	 * Sets the database. Should be called once by the database bridge plugin in onLoad event.
	 **/
	public void setDatabase(DatabaseBridge database) {
		if (this.database == null)
			this.database = database;
	}

	/**
	 * Returns all log matching specified parameters. Also intern methods should use this.
	 **/
	public List<DataEntry> getLog(QueryParams params) {
		return database.getEntries(params);
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
		database.deleteEntries(params);
	}
}
