package net.whg.havensgames;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.whg.havensgames.spawn.levitationpads.LevitationPadList;
import net.whg.havensgames.spawn.levitationpads.LevitationPadListener;
import net.whg.havensgames.spawn.levitationpads.cmd.LevitationPadCommand;
import net.whg.havensgames.spawn.misc.DisablePortalsListener;
import net.whg.havensgames.spawn.misc.SpawnInvulnerabilityListener;
import net.whg.havensgames.spawn.misc.TeleportOutOfVoidListener;
import net.whg.havensgames.utils.SystemCommand;
import net.whg.utils.WraithLib;
import net.whg.utils.cmdformat.CommandHandler;
import net.whg.utils.events.location.LocationTriggerListener;

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
        var locationTriggers = new LocationTriggerListener();
        var levitationPadList = new LevitationPadList(locationTriggers);

        loadCommand("system", new SystemCommand());
        loadCommand("levitationpad", new LevitationPadCommand(levitationPadList));

        registerEvents(locationTriggers);
        registerEvents(new LevitationPadListener(levitationPadList));
        registerEvents(new TeleportOutOfVoidListener());
        registerEvents(new DisablePortalsListener());
        registerEvents(new SpawnInvulnerabilityListener());

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
     * Loads an event listener and registers it with Bukkit.
     * 
     * @param listener - The event listener to register.
     */
    private void registerEvents(Listener listener) {
        WraithLib.log.logInfo("Registered event listeners for %s.", listener.getClass().getSimpleName());
        Bukkit.getPluginManager().registerEvents(listener, this);
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