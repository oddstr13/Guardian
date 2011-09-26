package org.guardian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.config.Configuration;
import org.guardian.tools.Tool;
import org.guardian.tools.ToolBehavior;
import org.guardian.tools.ToolMode;

public class Config
{
	public final String url, user, password;
	public final HashMap<String, Tool> toolsByName;
	public final HashMap<Integer, Tool> toolsByType;

	Config(Guardian guardian) throws IOException {
		final Map<String, Object> def = new HashMap<String, Object>(), tooldef = new HashMap<String, Object>(), tbdef = new HashMap<String, Object>(), tdef = new HashMap<String, Object>();
		def.put("version", guardian.getDescription().getVersion());
		def.put("mysql.host", "localhost");
		def.put("mysql.port", 3306);
		def.put("mysql.database", "minecraft");
		def.put("mysql.user", "username");
		def.put("mysql.password", "pass");
		tdef.put("aliases", Arrays.asList("t"));
		tdef.put("leftClickBehavior", "NONE");
		tdef.put("rightClickBehavior", "TOOL");
		tdef.put("defaultEnabled", true);
		tdef.put("item", 270);
		tdef.put("params", "area 0 all sum none limit 15 desc silent");
		tdef.put("mode", "LOOKUP");
		tdef.put("permissionDefault", "TRUE");
		tbdef.put("aliases", Arrays.asList("tb"));
		tbdef.put("leftClickBehavior", "TOOL");
		tbdef.put("rightClickBehavior", "BLOCK");
		tbdef.put("defaultEnabled", true);
		tbdef.put("item", 7);
		tbdef.put("params", "area 0 all sum none limit 15 desc silent");
		tbdef.put("mode", "LOOKUP");
		tbdef.put("permissionDefault", "TRUE");
		tooldef.put("tool", tdef);
		tooldef.put("toolblock", tbdef);
		def.put("tools", tooldef);
		final Configuration config = guardian.getConfiguration();
		config.load();
		for (final Entry<String, Object> e : def.entrySet())
			if (config.getProperty(e.getKey()) == null)
				config.setProperty(e.getKey(), e.getValue());
		if (!config.save())
			throw new IOException("Error while writing to config.yml");
		url = "jdbc:mysql://" + config.getString("mysql.host") + ":" + config.getString("mysql.port") + "/" + config.getString("mysql.database");
		user = config.getString("mysql.user");
		password = config.getString("mysql.password");
		final List<String> toolNames = config.getKeys("tools");
		final List<Tool> tools = new ArrayList<Tool>();
		for (final String toolName : toolNames)
			try {
				final String path = "tools." + toolName;
				final List<String> aliases = config.getStringList(path + ".aliases", null);
				final ToolBehavior leftClickBehavior = ToolBehavior.valueOf(config.getString(path + ".leftClickBehavior").toUpperCase());
				final ToolBehavior rightClickBehavior = ToolBehavior.valueOf(config.getString(path + ".rightClickBehavior").toUpperCase());
				final boolean defaultEnabled = config.getBoolean(path + ".defaultEnabled", false);
				final int item = config.getInt(path + ".item", 0);
				// final QueryParams params = new QueryParams();
				// params.prepareToolQuery = true;
				// params.parseArgs(Bukkit.getConsoleSender(), Arrays.asList(config.getString(path + ".params").split(" ")));
				final ToolMode mode = ToolMode.valueOf(config.getString(path + ".mode").toUpperCase());
				final PermissionDefault pdef = PermissionDefault.valueOf(config.getString(path + ".permissionDefault").toUpperCase());
				tools.add(new Tool(toolName, aliases, leftClickBehavior, rightClickBehavior, defaultEnabled, item, null, mode, pdef));
			} catch (final Exception ex) {
				Bukkit.getLogger().log(Level.WARNING, "Error at parsing tool '" + toolName + "':)", ex);
			}
		toolsByName = new HashMap<String, Tool>();
		toolsByType = new HashMap<Integer, Tool>();
		for (final Tool tool : tools) {
			toolsByType.put(tool.item, tool);
			toolsByName.put(tool.name, tool);
			for (final String alias : tool.aliases)
				toolsByName.put(alias, tool);
		}
	}
}
