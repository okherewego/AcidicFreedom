package me.acidorg.AcidicFreedom;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;
    	
		return false;
    }

}
