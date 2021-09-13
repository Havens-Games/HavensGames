package net.whg.havensgames.warp;

import net.whg.havensgames.cmdformat.CommandHandler;

public class WarpCommand extends CommandHandler {

    public WarpCommand(WarpList warpList) {
        actions.add(new WarpToAction(warpList));
        actions.add(new WarpSetAction(warpList));
        actions.add(new WarpListAction(warpList));
        actions.add(new WarpRemoveAction(warpList));
    }

    @Override
    public String getName() {
        return "warp";
    }
}
