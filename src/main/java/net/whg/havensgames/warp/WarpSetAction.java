package net.whg.havensgames.warp;

import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.whg.havensgames.HavensGames;
import net.whg.havensgames.cmdformat.CommandException;
import net.whg.havensgames.cmdformat.Subcommand;

public class WarpSetAction extends Subcommand {
    private final WarpList warpList;

    public WarpSetAction(WarpList warpList) {
        this.warpList = warpList;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        var player = (Player) sender;
        var warpPoint = new WarpPoint(args[0], player.getLocation());

        try {
            warpList.updateWarpPoint(warpPoint);
            HavensGames.log.sendMessage(sender, "Saved warp point '%s'.", warpPoint.name());
        } catch (IOException e) {
            HavensGames.log.sendError(sender, "Failed to save warp list! See console for more information.");
            e.printStackTrace();
        }
    }

    @Override
    public String getUsage() {
        return "<name>";
    }

    @Override
    public String getName() {
        return "set";
    }

    @Override
    public boolean requiresNoConsole() {
        return true;
    }

    @Override
    public boolean requiresOp() {
        return true;
    }
}
