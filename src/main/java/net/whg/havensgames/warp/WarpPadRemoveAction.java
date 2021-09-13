package net.whg.havensgames.warp;

import java.io.IOException;

import org.bukkit.command.CommandSender;

import net.whg.havensgames.HavensGames;
import net.whg.havensgames.cmdformat.CommandException;
import net.whg.havensgames.cmdformat.Subcommand;
import net.whg.havensgames.cmdformat.UnknownArgumentException;

public class WarpPadRemoveAction extends Subcommand {
    private final WarpList warpList;

    public WarpPadRemoveAction(WarpList warpList) {
        this.warpList = warpList;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        var warpPad = warpList.getWarpPad(args[0]);
        if (warpPad == null)
            throw new UnknownArgumentException("Unknown warp pad: '%s'", args[0]);

        try {
            warpList.removeWarpPad(warpPad.name());
            HavensGames.log.sendError(sender, "Removed warp pad '%s'.", warpPad.name());
        } catch (IOException e) {
            HavensGames.log.sendError(sender, "Failed to save warp pad list! See console for more information.");
            e.printStackTrace();
        }
    }

    @Override
    public String getUsage() {
        return "<name>";
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public boolean requiresOp() {
        return true;
    }
}
