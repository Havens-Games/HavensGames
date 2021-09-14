package net.whg.havensgames;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import net.whg.havensgames.utils.SystemCommand;
import net.whg.utils.WraithLib;
import net.whg.utils.cmdformat.CommandHandler;
import net.whg.utils.warp.WarpCommand;
import net.whg.utils.warp.WarpList;
import net.whg.utils.warp.WarpPadCommand;

/**
 * The HavensGames official plugin.
 */
public class HavensGames extends JavaPlugin {
    /**
     * Called when the plugin is enabled to initialize all managers, handlers, and
     * load resources.
     */
    @Override
    public void onEnable() {
        var warpList = new WarpList(this);
        loadCommand("system", new SystemCommand());
        loadCommand("warp", new WarpCommand(warpList));
        loadCommand("warppad", new WarpPadCommand(warpList));

        WraithLib.log.logInfo("Enabled HavensGames plugin.");
    }

    /**
     * Loads a command by name and registers the command executor.
     * 
     * @param commandName - The command name.
     * @param handler     - The command handler.
     */
    private void loadCommand(String commandName, CommandHandler handler) {
        WraithLib.log.logInfo("Loading %s command.", commandName);
        getCommand(commandName).setExecutor(handler);
    }

    /**
     * Called when the plugin is disabled to clear all resources.
     */
    @Override
    public void onDisable() {
        WraithLib.log.logInfo("Unregistering all event listeners.");
        HandlerList.unregisterAll(this);

        WraithLib.log.logInfo("Disabled HavensGames plugin.");
    }
}