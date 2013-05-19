package me.acidorg.AcidicFreedom;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.acidorg.AcidicFreedom.Commands.AF_Command;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
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
    public static final String SUPERADMIN_FILE = "superadmin.yml";
    
    public static final String MSG_NO_PERMS = ChatColor.YELLOW + "You do not have permission to use this command.";
    public static final String YOU_ARE_OP = ChatColor.YELLOW + "You are now op!";
    public static final String YOU_ARE_NOT_OP = ChatColor.YELLOW + "You are no longer op!";
    public static final String CAKE_LYRICS = "But there's no sense crying over every mistake. You just keep on trying till you run out of cake.";
    public static final String NOT_FROM_CONSOLE = "This command may not be used from the console.";
    
    public static List<String> host_sender_names = Arrays.asList("rcon", "remotebukkit");
    
    public static String pluginVersion = "";
    public static String buildNumber = "";
    public static String buildDate = "";
    public static String pluginName = "";

    public static AcidicFreedom plugin = null;
    public static File plugin_file = null;
    
    @Override
    public void onEnable()
    {
        AF_Util.deleteFolder(new File("./_deleteme"));
    	
    	AcidicFreedom.plugin = this;
    	AcidicFreedom.plugin_file = getFile();

    	AcidicFreedom.pluginName = this.getDescription().getName();
    	
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
                AF_Log.info(String.format("[PLAYER_COMMAND] %s(%s): /%s %s",
                        sender_p.getName(),
                        ChatColor.stripColor(sender_p.getDisplayName()),
                        commandLabel,
                        StringUtils.join(args, " ")), true);
            }
            else
            {
                senderIsConsole = true;
                AF_Log.info(String.format("[CONSOLE_COMMAND] %s: /%s %s",
                        sender.getName(),
                        commandLabel,
                        StringUtils.join(args, " ")), true);
            }

            AF_Command dispatcher;
            try
            {
                ClassLoader classLoader = AcidicFreedom.class.getClassLoader();
                dispatcher = (AF_Command) classLoader.loadClass(String.format("%s.%s%s", COMMAND_PATH, COMMAND_PREFIX,
                        cmd.getName().toLowerCase())).newInstance();
                dispatcher.setup(this, sender, dispatcher.getClass());
            }
            catch (Throwable ex)
            {
                AF_Log.severe("Command not loaded: " + cmd.getName() + "\n" + ExceptionUtils.getStackTrace(ex));
                sender.sendMessage(ChatColor.RED + "Command Error: Command not loaded: " + cmd.getName());
                return true;
            }

            try
            {
                if (dispatcher.senderHasPermission())
                {
                    return dispatcher.run(sender, sender_p, cmd, commandLabel, args, senderIsConsole);
                }
                else
                {
                    sender.sendMessage(AcidicFreedom.MSG_NO_PERMS);
                }
            }
            catch (Throwable ex)
            {
                sender.sendMessage(ChatColor.RED + "Command Error: " + ex.getMessage());
            }

            dispatcher = null;
        }
        catch (Throwable ex)
        {
            AF_Log.severe("Command Error: " + commandLabel + "\n" + ExceptionUtils.getStackTrace(ex));
            sender.sendMessage(ChatColor.RED + "Unknown Command Error.");
        }

        return true;
    }
    

}
