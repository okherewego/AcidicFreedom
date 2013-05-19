package me.acidorg.AcidicFreedom;

import me.StevenLawson.TotalFreedomMod.TFM_SuperadminList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class AF_SuperAdmin
{
    public static boolean isSeniorAdmin(CommandSender user)
    {
        try
        {
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("TotalFreedomMod"))
            {
                return TFM_SuperadminList.isSeniorAdmin(user);
            }
        }
        catch (Exception ex)
        {
        }

        return false;
    }

    public static boolean isUserSuperadmin(CommandSender user)
    {
        try
        {
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("TotalFreedomMod"))
            {
                return TFM_SuperadminList.isUserSuperadmin(user);
            }
        }
        catch (Exception ex)
        {
        }

        return false;
    }
}
