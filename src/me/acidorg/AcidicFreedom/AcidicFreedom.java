package me.acidorg.AcidicFreedom;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class AcidicFreedom extends JavaPlugin
{
    private Logger log = Logger.getLogger("Minecraft");

    public void onEnable()
    {
        log.info("AcidicFreedom has been enabled!");
    }

    public void onDisable()
    {
        log.info("AcidicFreedom has been disabled! :(");
    }

}
