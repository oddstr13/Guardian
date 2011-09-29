package org.guardian.entries;

import java.text.SimpleDateFormat;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.guardian.ActionType;

public abstract class DataEntry
{
	protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
	protected int id;
	protected ActionType action;
	protected long date;
	protected String playerName;
	protected String worldName;
	protected Location loc;
	protected boolean rollbacked;
	protected String pluginName;
	protected List<DataEntry> children;

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

	public Location getLocation() {
		return loc;
	}

	public void setLocation(Location loc) {
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

	abstract public List<BlockState> getRollbackBlockStates();

	abstract public List<BlockState> getRebuildBlockStates();

	abstract public String getMessage();
}
