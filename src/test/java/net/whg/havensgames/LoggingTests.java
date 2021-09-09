package net.whg.havensgames;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.junit.jupiter.api.Test;

import net.whg.havensgames.logging.LoggingHandler;

class LoggingTests {
    @Test
    void logInfo_formatting_basicStringReplacement() {
        var logger = mock(Logger.class);
        var handler = new LoggingHandler(logger);

        handler.logInfo("My name is %s.", "Steve");

        var message = ChatColor.GRAY + "My name is " + ChatColor.DARK_AQUA + "Steve" + ChatColor.GRAY + ".";
        verify(logger).log(Level.INFO, message);
    }

    @Test
    void logWarn_formatting_basicStringReplacement() {
        var logger = mock(Logger.class);
        var handler = new LoggingHandler(logger);

        handler.logInfo("File %s is corrupted!.", "config.yml");

        var message = ChatColor.GOLD + "File " + ChatColor.YELLOW + "config.yml" + ChatColor.GOLD + " is corrupted!";
        verify(logger).log(Level.WARNING, message);
    }

    @Test
    void logError_formatting_basicStringReplacement() {
        var logger = mock(Logger.class);
        var handler = new LoggingHandler(logger);

        handler.logInfo("File %s could not be found!.", "database.db");

        var message = ChatColor.RED + "File " + ChatColor.GOLD + "database.db" + ChatColor.RED + " could not be found!";
        verify(logger).log(Level.SEVERE, message);
    }
}
