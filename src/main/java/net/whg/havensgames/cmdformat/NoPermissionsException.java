package net.whg.havensgames.cmdformat;

/**
 * Thrown when the command sender does not have permission to execute the given
 * command.
 */
public class NoPermissionsException extends CommandException {
    public NoPermissionsException() {
        this("You do not have permission to use this command!");
    }

    public NoPermissionsException(String message) {
        super(message);
    }
}
