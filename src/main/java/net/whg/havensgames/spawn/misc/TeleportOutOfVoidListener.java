package net.whg.havensgames.spawn.misc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Listens for player move events and checks if the player fallen into the void.
 * If so, they are teleported back to spawn.
 */
public class TeleportOutOfVoidListener implements Listener {
    /**
     * Listens for player move events and triggers the spawn action if a player
     * falls into the void while at spawn.
     * 
     * @param e - The event.
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerMove(PlayerMoveEvent e) {
        var player = e.getPlayer();
        var location = player.getLocation();

        if (location.getY() < 0 && isAtSpawn(player))
            Bukkit.dispatchCommand(player, "spawn");
    }

    /**
     * Checks if the player is currently at spawn.
     * 
     * @param player - The player.
     * @return True if the player is at spawn. False otherwise.
     */
    private boolean isAtSpawn(Player player) {
        // TODO
        return true;
    }
}
