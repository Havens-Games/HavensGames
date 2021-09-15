package net.whg.havensgames.spawn.levitationpads.cmd;

import net.whg.havensgames.spawn.levitationpads.LevitationPadList;
import net.whg.utils.cmdformat.CommandHandler;

/**
 * A command namespace for managing levitation pads.
 */
public class LevitationPadCommand extends CommandHandler {
    /**
     * Creates a new command handler for levitation pad commands.
     * 
     * @param levitationPadList - The list of levitation pads that this command
     *                          manages.
     */
    public LevitationPadCommand(LevitationPadList levitationPadList) {
        actions.add(new LevitationPadSetAction(levitationPadList));
        actions.add(new LevitationPadListAction(levitationPadList));
        actions.add(new LevitationPadRemoveAction(levitationPadList));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "levitationpad";
    }
}
