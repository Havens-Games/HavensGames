package net.whg.havensgames.spawn.levitationpads.cmd;

import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.whg.havensgames.spawn.levitationpads.LevitationPad;
import net.whg.havensgames.spawn.levitationpads.LevitationPadList;
import net.whg.utils.WraithLib;
import net.whg.utils.cmdformat.CommandException;
import net.whg.utils.cmdformat.InternalCommandException;
import net.whg.utils.cmdformat.Subcommand;
import net.whg.utils.events.location.CylinderLocationTrigger;
import net.whg.utils.events.location.LocationTrigger;
import net.whg.utils.events.location.SphereLocationTrigger;

/**
 * Used to create new levitation pads or updating existing levitation pads.
 */
public class LevitationPadSetAction extends Subcommand {
    private final LevitationPadList levitationPadList;

    /**
     * Creates a new LevitationPadSetAction subcommand.
     * 
     * @param levitationPadList - The levitation pad list that this subcommand
     *                          modifies.
     */
    public LevitationPadSetAction(LevitationPadList levitationPadList) {
        this.levitationPadList = levitationPadList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        var player = (Player) sender;
        var location = player.getLocation();
        var name = args[0];
        var amplitude = getInteger(args[1]);
        var ticks = getInteger(args[2]);
        var radius = getFloat(args[3]);
        LocationTrigger locationTrigger = new SphereLocationTrigger(name, location, radius, true);

        if (args.length > 4 && getBoolean(args[4])) {
            var cylHeight = 1;

            if (args.length > 5)
                cylHeight = getInteger(args[5]);

            locationTrigger = new CylinderLocationTrigger(name, location, radius, cylHeight, true);
        }

        try {
            var oldLevitationPad = levitationPadList.getLevitationPad(args[0]);
            if (oldLevitationPad != null)
                levitationPadList.removeLevitationPad(oldLevitationPad);

            levitationPadList.addLevitationPad(new LevitationPad(locationTrigger, amplitude, ticks));
            WraithLib.log.sendMessage(sender, "Saved levitation pad %s.", name);
        } catch (IOException e) {
            throw new InternalCommandException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "set";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsage() {
        return "<name> <amplitude> <ticks> <radius> [cylinderMode] [cylinderHeight]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean requiresOp() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean requiresNoConsole() {
        return true;
    }
}
