package net.whg.havensgames.utils;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import net.whg.havensgames.HavensGames;
import net.whg.havensgames.cmdformat.CommandException;
import net.whg.havensgames.cmdformat.InternalCommandException;
import net.whg.havensgames.cmdformat.Subcommand;

public class ReloadModelsSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        HavensGames.log.logInfo("Reloading all model files.");

        var console = Bukkit.getConsoleSender();
        Bukkit.dispatchCommand(console, "meg reload");

        try {
            copyModelResources();
        } catch (IOException e) {
            throw new InternalCommandException(e);
        }

        Bukkit.dispatchCommand(console, "iazip");
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getName() {
        return "reload_models";
    }

    @Override
    public boolean requiresOp() {
        return true;
    }

    /**
     * Copies all resources generated by the ModelEngine plugin into the locally
     * hosted ItemsAdder plugin folder.
     * 
     * @throws IOException If there is an IO error while attempting to copy over the
     *                     resource files.
     */
    private void copyModelResources() throws IOException {
        var modelEnginePlugin = Bukkit.getPluginManager().getPlugin("ModelEngine");
        var itemsAdderPlugin = Bukkit.getPluginManager().getPlugin("ItemsAdder");

        var modelEngineAssets = new File(modelEnginePlugin.getDataFolder(),
                "resource pack" + File.separatorChar + "assets");

        var itemsAdderAssets = new File(itemsAdderPlugin.getDataFolder(),
                "data" + File.separatorChar + "resource_pack" + File.separatorChar + "assets");

        var modifiedFiles = mergeFolders(modelEngineAssets, itemsAdderAssets, 0);
        HavensGames.log.logInfo("Transferred %s files from ModelEngine to ItemsAdder.", modifiedFiles);
    }

    /**
     * Copies all files from the source folder, recursively, and pastes them in the
     * destination folder with the same file structure. If the file already exists
     * in the destination folder, it is overridden.
     * 
     * @param src  - The source folder.
     * @param dest - The destination folder.
     * @throws IOException If there is an IO error while attempting to copy over the
     *                     resource files.
     */
    private int mergeFolders(File src, File dest, int modified) throws IOException {
        if (src.isFile()) {
            dest.getParentFile().mkdirs();
            dest.createNewFile();
            Files.copy(src, dest);
            return modified + 1;
        }

        for (var file : src.listFiles()) {
            var corresponding = new File(dest, file.getName());
            modified = mergeFolders(file, corresponding, modified);
        }

        return modified;
    }
}
