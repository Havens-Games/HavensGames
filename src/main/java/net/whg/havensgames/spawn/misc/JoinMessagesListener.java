package net.whg.havensgames.spawn.misc;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.whg.utils.WraithLib;

/**
 * Handles sending out the player join messages, configurable via config.
 */
public class JoinMessagesListener implements Listener {
    private final FileConfiguration config;

    /**
     * Creates a new JoinMessagesListener object and loads the configured join
     * message from the config file.
     */
    public JoinMessagesListener() {
        var plugin = Bukkit.getPluginManager().getPlugin("HavensGames");
        config = plugin.getConfig();

        config.addDefault("JoinMessages.ToPlayer", "Welcome to the server, %player_name%!");
        config.addDefault("JoinMessages.ToEveryoneElse", "%player_name% has joined the server!");
        config.addDefault("QuitMessages.ToEveryoneElse", "%player_name% has left the server!");
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    /**
     * Listens for player join events and then overrides the join message to send
     * the corresponding messages that were assigned from the config file.
     * 
     * @param e - The event.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.joinMessage(null);

        var messageToJoiner = config.getString("JoinMessages.ToPlayer");
        var messageToEveryoneElse = config.getString("JoinMessages.ToEveryoneElse");

        var player = e.getPlayer();

        WraithLib.log.sendPlaceholderMessage(player, player, messageToJoiner);

        for (var p : Bukkit.getOnlinePlayers()) {
            if (p == player)
                continue;

            WraithLib.log.sendPlaceholderMessage(p, player, messageToEveryoneElse);
        }
    }

    /**
     * Listens for player quit events and then overrides the quit message to send
     * the corresponding messages that were assigned from the config file.
     * 
     * @param e - The event.
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.quitMessage(null);

        var messageToEveryoneElse = config.getString("QuitMessages.ToEveryoneElse");

        var player = e.getPlayer();
        for (var p : Bukkit.getOnlinePlayers()) {
            if (p == player)
                continue;

            WraithLib.log.sendPlaceholderMessage(p, player, messageToEveryoneElse);
        }
    }
}
