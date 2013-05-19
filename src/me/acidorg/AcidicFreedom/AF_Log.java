package me.acidorg.AcidicFreedom;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AF_Log
{
    private static final Logger logger = Logger.getLogger("Minecraft-Server");

    private AF_Log()
    {
        throw new AssertionError();
    }

    private static void log(Level level, String message, boolean raw)
    {
        logger.log(level, (raw ? "" : "[" + AcidicFreedom.pluginName + "]: ") + message);
    }

    public static void info(String message)
    {
        AF_Log.info(message, false);
    }

    public static void info(String message, boolean raw)
    {
        AF_Log.log(Level.INFO, message, raw);
    }

    public static void warning(String message)
    {
        AF_Log.info(message, false);
    }

    public static void warning(String message, boolean raw)
    {
        AF_Log.log(Level.WARNING, message, raw);
    }

    public static void severe(String message)
    {
        AF_Log.info(message, false);
    }

    public static void severe(String message, boolean raw)
    {
        AF_Log.log(Level.SEVERE, message, raw);
    }

    public static void severe(Throwable ex)
    {
        logger.log(Level.SEVERE, null, ex);
    }
}