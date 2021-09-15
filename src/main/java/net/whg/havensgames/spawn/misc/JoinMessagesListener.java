package net.whg.havensgames.spawn.misc;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.clip.placeholderapi.PlaceholderAPI;
import net.whg.utils.WraithLib;

/**
 * Handles sending out the player join messages, configurable via config.
 */
public class JoinMessagesListener implements Listener {
    private final String messageToJoiner;
    private final String messageToEveryoneElse;

    /**
     * Creates a new JoinMessagesListener object and loads the configured join
     * message from the config file.
     */
    public JoinMessagesListener() {
        var plugin = Bukkit.getPluginManager().getPlugin("HavensGames");
        var config = plugin.getConfig();

        config.addDefault("JoinMessages.ToPlayer", "Welcome to the server!");
        config.addDefault("JoinMessages.ToEveryoneElse", "{} has joined the server!");
        config.options().copyDefaults(true);
        plugin.saveConfig();

        messageToJoiner = config.getString("JoinMessages.ToPlayer");
        messageToEveryoneElse = config.getString("JoinMessages.ToEveryoneElse");
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

        var player = e.getPlayer();
        WraithLib.log.sendMessage(player, PlaceholderAPI.setPlaceholders(player, messageToJoiner));

        for (var p : Bukkit.getOnlinePlayers()) {
            if (p == player)
                continue;

            WraithLib.log.sendMessage(player, PlaceholderAPI.setPlaceholders(p, messageToEveryoneElse));
        }
    }
}
