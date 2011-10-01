package org.guardian.entries;

import static org.guardian.util.BukkitUtils.materialName;
import java.util.List;
import org.bukkit.block.BlockState;

public class BlockEntry extends DataEntry
{
	private int typeBefore, typeAfter;
	private byte dataBefore, dataAfter;

	public int getTypeBefore() {
		return typeBefore;
	}

	public void setTypeBefore(int typeBefore) {
		this.typeBefore = typeBefore;
	}

	public int getTypeAfter() {
		return typeAfter;
	}

	public void setTypeAfter(int typeAfter) {
		this.typeAfter = typeAfter;
	}

	public byte getDataBefore() {
		return dataBefore;
	}

	public void setDataBefore(byte dataBefore) {
		this.dataBefore = dataBefore;
	}

	public byte getDataAfter() {
		return dataAfter;
	}

	public void setDataAfter(byte dataAfter) {
		this.dataAfter = dataAfter;
	}

	@Override
	public List<BlockState> getRollbackBlockStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlockState> getRebuildBlockStates() {
		return null;
	}

	@Override
	public String getMessage() {
		final StringBuilder msg = new StringBuilder();
		if (date > 0)
			msg.append(dateFormat.format(date) + " ");
		if (playerName != null)
			msg.append(playerName + " ");
		if (typeAfter == 0)
			msg.append("destroyed " + materialName(typeBefore, dataBefore));
		else if (typeBefore == 0)
			msg.append("created " + materialName(typeAfter, dataAfter));
		else
			msg.append("replaced " + materialName(typeBefore, dataBefore) + " with " + materialName(typeAfter, dataAfter));
		if (loc != null)
			msg.append(" at " + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ());
		return msg.toString();
	}
}
