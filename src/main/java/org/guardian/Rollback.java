package org.guardian;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.guardian.entries.DataEntry;

public class Rollback implements Runnable
{
	private final Queue<DataEntry> entries;
	private int taskId = -1;

	Rollback(List<DataEntry> entries) {
		this.entries = new LinkedBlockingQueue<DataEntry>(entries);
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Guardian.getInstance(), this, 20, 20);
	}

	@Override
	public void run() {
		final int counter = 0;
		while (!entries.isEmpty() && counter < 1000) {
			final DataEntry entry = entries.poll();
			for (final BlockState state : entry.getRollbackBlockStates())
				state.update();
		}
		if (entries.isEmpty())
			Bukkit.getScheduler().cancelTask(taskId);
	}

}
