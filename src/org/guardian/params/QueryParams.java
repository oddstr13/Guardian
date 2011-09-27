package org.guardian.params;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import org.guardian.ActionType;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class QueryParams
{
	private List<ActionType> actions;
	private List<String> players;
	private boolean excludePlayersMode;
	private List<Integer> blocks;
	private long before, since;
	private Selection sel;
	private Location loc;
	private int radius;
	private List<World> worlds;
	private Order order;
	private SummarizationMode sum;
	private String textMatch;
	private int limit;
	private boolean silent;

	private boolean needId, needDate, needPlayer, needCoords, needSubTableInfo;

	// TODO
}
