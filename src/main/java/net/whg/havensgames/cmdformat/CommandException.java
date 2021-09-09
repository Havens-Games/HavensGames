package net.whg.havensgames.cmdformat;

import org.bukkit.command.CommandSender;

import net.whg.havensgames.HavensGames;

/**
 * Thrown whenever a command could not be properly executed.
 */
public abstract class CommandException extends Exception {
    protected CommandException(String message) {
        super(message);
    }

    public void printToPlayer(CommandSender sender) {
        HavensGames.log.sendMessage(sender, getMessage());
    }
}
