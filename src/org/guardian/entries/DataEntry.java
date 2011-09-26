package org.guardian.entries;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.guardian.ActionType;

public abstract class DataEntry
{
	private int id;
	private ActionType action;
	private long date;
	private String playerName;
	private String worldName;
	private Location loc;
	private boolean rollbacked;
	private String pluginName;
	private List<DataEntry> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getWorldName() {
		return worldName;
	}

	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public boolean isRollbacked() {
		return rollbacked;
	}

	public void setRollbacked(boolean rollbacked) {
		this.rollbacked = rollbacked;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public List<DataEntry> getChildren() {
		return children;
	}

	public void setChildren(List<DataEntry> children) {
		this.children = children;
	}

	abstract List<BlockState> getRollbackBlockStates();

	abstract String getMessage();
}
