package net.whg.havensgames.warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.whg.havensgames.cmdformat.CommandException;
import net.whg.havensgames.cmdformat.Subcommand;
import net.whg.havensgames.cmdformat.UnknownArgumentException;

public class WarpToAction extends Subcommand {
    private final WarpList warpList;

    public WarpToAction(WarpList warpList) {
        this.warpList = warpList;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        var warpPoint = warpList.getWarpPoint(args[0]);
        if (warpPoint == null)
            throw new UnknownArgumentException("Unknown warp point: %s", args[0]);

        var player = (Player) sender;
        player.teleport(warpPoint.location());
    }

    @Override
    public String getUsage() {
        return "<name>";
    }

    @Override
    public String getName() {
        return "to";
    }

    @Override
    public boolean requiresNoConsole() {
        return true;
    }
}
