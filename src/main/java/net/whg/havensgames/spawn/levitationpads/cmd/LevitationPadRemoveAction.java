package net.whg.havensgames.spawn.levitationpads.cmd;

import java.io.IOException;

import org.bukkit.command.CommandSender;

import net.whg.havensgames.spawn.levitationpads.LevitationPadList;
import net.whg.utils.WraithLib;
import net.whg.utils.cmdformat.CommandException;
import net.whg.utils.cmdformat.InternalCommandException;
import net.whg.utils.cmdformat.Subcommand;
import net.whg.utils.cmdformat.UnknownArgumentException;

/**
 * Used to remove existing levitation pads.
 */
public class LevitationPadRemoveAction extends Subcommand {
    private final LevitationPadList levitationPadList;

    /**
     * Creates a new LevitationPadRemoveAction subcommand.
     * 
     * @param levitationPadList - The levitation pad list that this subcommand
     *                          modifies.
     */
    public LevitationPadRemoveAction(LevitationPadList levitationPadList) {
        this.levitationPadList = levitationPadList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        var levitationPad = levitationPadList.getLevitationPad(args[0]);
        if (levitationPad == null)
            throw new UnknownArgumentException("Unknown levitation pad: %s.", args[0]);

        try {
            levitationPadList.removeLevitationPad(levitationPad);
            WraithLib.log.sendMessage(sender, "Removed levitation pad %s.", levitationPad.name());
        } catch (IOException e) {
            throw new InternalCommandException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "remove";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsage() {
        return "<name>";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean requiresOp() {
        return true;
    }
}
