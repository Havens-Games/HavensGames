package net.whg.havensgames;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import net.whg.havensgames.logging.LoggingHandler;

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
    }

    /**
     * Called when the plugin is disabled to clear all resources.
     */
    @Override
    public void onDisable() {
        setLoggingHandler(new LoggingHandler(Logger.getLogger(HavensGames.class.toString())));
    }
}