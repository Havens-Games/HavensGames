package net.whg.havensgames;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import net.whg.havensgames.cmdformat.CommandHandler;
import net.whg.havensgames.logging.LoggingHandler;
import net.whg.havensgames.utils.SystemCommand;

/**
 * The HavensGames official plugin.
 */
public class HavensGames extends JavaPlugin {
    public static LoggingHandler log = new LoggingHandler(Logger.getLogger(HavensGames.class.toString()));

    /**
     * Sets the logging handler that is assigned to this plugin instance.
     * 
     * @param loggingHandler - The new logging handler.
     */
    private static void setLoggingHandler(LoggingHandler loggingHandler) {
        HavensGames.log = loggingHandler;
    }

    /**
     * Called when the plugin is enabled to initialize all managers, handlers, and
     * load resources.
     */
    @Override
    public void onEnable() {
        setLoggingHandler(new LoggingHandler(getLogger()));

        loadCommand("system", new SystemCommand());

        HavensGames.log.logInfo("Enabled HavensGames plugin.");
    }

    /**
     * Loads a command by name and registers the command executor.
     * 
     * @param commandName - The command name.
     * @param handler     - The command handler.
     */
    private void loadCommand(String commandName, CommandHandler handler) {
        HavensGames.log.logInfo("Loading %s command.", commandName);
        getCommand(commandName).setExecutor(handler);
    }

    /**
     * Called when the plugin is disabled to clear all resources.
     */
    @Override
    public void onDisable() {
        HavensGames.log.logInfo("Disabled HavensGames plugin.");
        setLoggingHandler(new LoggingHandler(Logger.getLogger(HavensGames.class.toString())));
    }
}