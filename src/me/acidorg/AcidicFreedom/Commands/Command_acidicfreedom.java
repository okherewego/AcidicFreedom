package me.acidorg.AcidicFreedom.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
public class Command_acidicfreedom extends AF_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
      sender.sendMessage(ChatColor.AQUA + "Hello " + sender.getName() + " this is AcidicFreedom 1.5! Made by AcidicCyanide and xXWilee999Xx.");
    }

}
