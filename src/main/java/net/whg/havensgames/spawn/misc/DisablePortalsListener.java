package net.whg.havensgames.spawn.misc;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

/**
 * Listens for when a player steps through a portal and teleports to a new
 * world. Then promptly cancels the event if the player is trying to leave the
 * main world.
 */
public class DisablePortalsListener implements Listener {
    private final World world;

    /**
     * Creates a new PortalListener object.
     */
    public DisablePortalsListener() {
        world = Bukkit.getWorld("world");
    }

    /**
     * Listens for player portal events and attempts to cancel the event if the
     * player is using a portal to anywhere except for the main world.
     * 
     * @param e - The event.
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerPortal(PlayerPortalEvent e) {
        if (e.getTo().getWorld() != world)
            e.setCancelled(true);
    }
}
