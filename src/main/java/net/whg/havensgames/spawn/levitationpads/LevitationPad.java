package net.whg.havensgames.spawn.levitationpads;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import net.whg.utils.events.location.LocationTrigger;

/**
 * Represents a location trigger that will cause a player to gain a levitation
 * status effect for a configurable amplitude and time period.
 */
@SerializableAs("LevitationPad")
public record LevitationPad(LocationTrigger locationTrigger, int amplitude, int ticks)
        implements Cloneable, ConfigurationSerializable {
    /**
     * Deserializes a WarpPad instance from a provided argument map.
     * 
     * @param args - The serialized map.
     * @return The new instance.
     */
    public static LevitationPad deserialize(Map<String, Object> args) {
        var locationTrigger = (LocationTrigger) args.get("trigger");
        var amplitude = (int) args.get("amplitude");
        var ticks = (int) args.get("ticks");
        return new LevitationPad(locationTrigger, amplitude, ticks);
    }

    /**
     * Serializes this LevitationPad instance into an argument map.
     * 
     * @return The serialized version of this object.
     */
    @Override
    public @NotNull Map<String, Object> serialize() {
        var map = new LinkedHashMap<String, Object>();
        map.put("trigger", locationTrigger);
        map.put("amplitude", amplitude);
        map.put("ticks", ticks);
        return map;
    }

    /**
     * Gets the name of this WarpPad. This value is taken from the location trigger
     * name.
     * 
     * @return The warp pad name.
     */
    public String name() {
        return locationTrigger.name();
    }
}
