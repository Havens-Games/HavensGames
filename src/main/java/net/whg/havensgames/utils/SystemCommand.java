package net.whg.havensgames.utils;

import net.whg.havensgames.cmdformat.CommandHandler;

/**
 * A handler for executing admin-only utility commands. The "system" command
 * namespace.
 */
public class SystemCommand extends CommandHandler {
    public SystemCommand() {
        actions.add(new ReloadModelsSubcommand());
    }

    @Override
    public String getName() {
        return "system";
    }
}
