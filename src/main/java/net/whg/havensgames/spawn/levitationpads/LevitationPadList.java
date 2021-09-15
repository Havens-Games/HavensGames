package net.whg.havensgames.spawn.levitationpads;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import net.whg.utils.SafeArrayList;
import net.whg.utils.events.location.LocationTriggerListener;

/**
 * Contains a list of levitation pads that is automatically synchronized with a
 * config file within the plugin data folder.
 */
public class LevitationPadList {
    private static final String LEVITATION_PAD_CONFIG_PREFIX = "LevitationPads.";

    private final SafeArrayList<LevitationPad> levitationPads = new SafeArrayList<>();
    private final LocationTriggerListener locationTriggers;
    private final YamlConfiguration config;
    private final File configFile;

    /**
     * Creates a new LevitationPadList object and loads all existing levitation pads
     * from the config file.
     * 
     * @param locationTriggers - The location triggers handler that this levitation
     *                         pad list should use for managing warp pads.
     */
    public LevitationPadList(LocationTriggerListener locationTriggers) {
        this.locationTriggers = locationTriggers;

        var plugin = Bukkit.getPluginManager().getPlugin("HavensGames");
        configFile = new File(plugin.getDataFolder(), "levitation_pads.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        loadList();
    }

    /**
     * Loads all levitation pads from the config file.
     */
    private void loadList() {
        var savedLevitationPads = config.getConfigurationSection("LevitationPads");
        if (savedLevitationPads != null) {
            for (var levitationPadName : savedLevitationPads.getKeys(false)) {
                var levitationPad = (LevitationPad) savedLevitationPads.get(levitationPadName);
                levitationPads.add(levitationPad);
            }
        }
    }

    /**
     * Gets the levitation pad in this list with the given name.
     * 
     * @param name - The name of the levitation pad.
     * @return The levitation pad with the matching name, or null if there is no
     *         levitation pad with the given name.
     */
    public LevitationPad getLevitationPad(String name) {
        for (var levitationPad : levitationPads) {
            if (levitationPad.name().equals(name))
                return levitationPad;
        }

        return null;
    }

    /**
     * Adds a new levitation pad to this list and updates the config file.
     * 
     * @param warpPoint - The levitation pad to add.
     * @throws IllegalArgumentException If there is already a levitation pad with a
     *                                  matching name.
     * @throws IOException              If there is an IO issue while updating the
     *                                  config file.
     */
    public void addLevitationPad(LevitationPad levitationPad) throws IOException {
        if (getLevitationPad(levitationPad.name()) != null)
            throw new IllegalArgumentException("There is already a levitation pad with that name!");

        levitationPads.add(levitationPad);
        locationTriggers.registerLocationTrigger(levitationPad.locationTrigger());

        config.set(LEVITATION_PAD_CONFIG_PREFIX + levitationPad.name(), levitationPad);
        config.save(configFile);
    }

    /**
     * Removes a levitation pad from this list and updates the config file. This
     * function preforms no action if the levitation pad is not in this list.
     * 
     * @param levitationPad - The levitation pad to remove.
     * @throws IOException If there is an IO issue while updating the config file.
     */
    public void removeLevitationPad(LevitationPad levitationPad) throws IOException {
        if (!levitationPads.contains(levitationPad))
            return;

        levitationPads.remove(levitationPad);
        locationTriggers.unregisterLocationTrigger(levitationPad.locationTrigger());

        config.set(LEVITATION_PAD_CONFIG_PREFIX + levitationPad.name(), null);
        config.save(configFile);
    }

    /**
     * Gets a read-only list of all levitation pads in this list.
     * 
     * @return A list of all levitation pads.
     */
    public List<LevitationPad> getLevitationPads() {
        return levitationPads.asReadOnly();
    }
}
