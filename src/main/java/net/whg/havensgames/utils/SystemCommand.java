package net.whg.havensgames.utils;

import net.whg.utils.cmdformat.CommandHandler;

/**
 * A handler for executing admin-only utility commands. The "system" command
 * namespace.
 */
public class SystemCommand extends CommandHandler {
    /**
     * Creates a new SystemCommand handler instance.
     */
    public SystemCommand() {
        actions.add(new ReloadModelsSubcommand());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "system";
    }
}
