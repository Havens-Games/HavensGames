package net.whg.havensgames.spawn.levitationpads.cmd;

import org.bukkit.command.CommandSender;

import net.whg.havensgames.spawn.levitationpads.LevitationPad;
import net.whg.havensgames.spawn.levitationpads.LevitationPadList;
import net.whg.utils.WraithLib;
import net.whg.utils.cmdformat.CommandException;
import net.whg.utils.cmdformat.Subcommand;

/**
 * Lists all existing levitation pads.
 */
public class LevitationPadListAction extends Subcommand {
    private final LevitationPadList levitationPadList;

    /**
     * Creates a new LevitationPadListAction subcommand.
     * 
     * @param levitationPadList - The levitation pad list that this subcommand
     *                          modifies.
     */
    public LevitationPadListAction(LevitationPadList levitationPadList) {
        this.levitationPadList = levitationPadList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        var sortedList = levitationPadList.getLevitationPads().stream().map(LevitationPad::name).toList();
        sortedList.sort((a, b) -> a.compareToIgnoreCase(b));
        WraithLib.log.sendMessage(sender, "Levitation Pads: %s", (Object) sortedList.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "list";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsage() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean requiresOp() {
        return true;
    }
}
