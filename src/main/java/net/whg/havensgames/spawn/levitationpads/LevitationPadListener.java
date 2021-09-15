package net.whg.havensgames.spawn.levitationpads;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.whg.utils.events.location.LocationTriggeredEvent;

/**
 * A listener that listens for location triggered events and checks them against
 * the levitation pad list, triggering status effects on the causing player as
 * needed.
 */
public class LevitationPadListener implements Listener {
    private final LevitationPadList levitationPadList;

    /**
     * Creates a new LevitationPadListener.
     * 
     * @param levitationPadList - The levitation pad list that this listener
     *                          references.
     */
    public LevitationPadListener(LevitationPadList levitationPadList) {
        this.levitationPadList = levitationPadList;
    }

    /**
     * Listens for LocationTriggeredEvents and checks them against levitation pads
     * in the levitation pad list. If a match if found, the player that triggered
     * the event is given a levitation status effect.
     * 
     * @param e - The event.
     */
    @EventHandler
    public void onLocationTriggered(LocationTriggeredEvent e) {
        for (var levitationPad : levitationPadList.getLevitationPads()) {
            if (levitationPad.locationTrigger() != e.getLocationTrigger())
                continue;

            var amplitude = levitationPad.amplitude();
            var ticks = levitationPad.ticks();
            var statusEffect = new PotionEffect(PotionEffectType.LEVITATION, ticks, amplitude - 1, true, false, true);
            e.getPlayer().addPotionEffect(statusEffect);

            return;
        }
    }
}
