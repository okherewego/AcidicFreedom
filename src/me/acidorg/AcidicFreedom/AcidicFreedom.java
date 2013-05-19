package me.acidorg.AcidicFreedom;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.acidorg.AcidicFreedom.Commands.AF_Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AcidicFreedom extends JavaPlugin
{
    private static final Logger log = Logger.getLogger("Minecraft");
	
	public static final String COMMAND_PATH = "me.acidorg.AcidicFreedom.Commands";
    public static final String COMMAND_PREFIX = "Command_";
    
    @Override
    public void onEnable()
    {
    	log.log(Level.INFO, "AcidicFreedom has been enabled! yay!");
    }
    
    @Override
    public void onDisable()
    {
    	log.log(Level.INFO, "AcidicFreedom has been disabled! :(!");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        try
        {
            Player sender_p = null;
            boolean senderIsConsole = false;
            if (sender instanceof Player)
            {
                sender_p = (Player) sender;
                log.info(String.format("[PLAYER_COMMAND] %s(%s): /%s %s",
                        sender_p.getName(),
                        ChatColor.stripColor(sender_p.getDisplayName()),
                        commandLabel,
                        AF_Util.implodeStringList(" ", Arrays.asList(args))));
            }
            else
            {
                senderIsConsole = true;
                log.info(String.format("[CONSOLE_COMMAND] %s: /%s %s",
                        sender.getName(),
                        commandLabel,
                        AF_Util.implodeStringList(" ", Arrays.asList(args))));
            }

            AF_Command dispatcher;
            try
            {
                ClassLoader classLoader = AcidicFreedom.class.getClassLoader();
                dispatcher = (AF_Command) classLoader.loadClass(String.format("%s.%s%s", COMMAND_PATH, COMMAND_PREFIX, cmd.getName().toLowerCase())).newInstance();
                dispatcher.setPlugin(this);
            }
            catch (Throwable ex)
            {
                log.log(Level.SEVERE, "[" + getDescription().getName() + "] Command not loaded: " + cmd.getName(), ex);
                sender.sendMessage(ChatColor.RED + "Command Error: Command not loaded: " + cmd.getName());
                return true;
            }

            try
            {
                return dispatcher.run(sender, sender_p, cmd, commandLabel, args, senderIsConsole);
            }
            catch (Throwable ex)
            {
                sender.sendMessage(ChatColor.RED + "Command Error: " + ex.getMessage());
            }

            dispatcher = null;
        }
        catch (Throwable ex)
        {
            log.log(Level.SEVERE, "[" + getDescription().getName() + "] Command Error: " + commandLabel, ex);
            sender.sendMessage(ChatColor.RED + "Unknown Command Error.");
        }

        return true;
    }
    

}
