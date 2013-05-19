package net.acidorg.AcidicFreedom;

import me.StevenLawson.TotalFreedomMod.TFM_SuperadminList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class SuperAdmins
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
   
   public static boolean isSuperAdmin(CommandSender user)
   {
     try
     {
       if (Bukkit.getServer().getPluginManager().isPluginEnabled("TotalFreedomMod"))
       {
           return TFM_SuperadminList.isSuperAdmin(user);
       }
    }
    catch (Exception ex)
    {
    }
    
    return false;
  }
}  
// Thanks to Madgeek for the structure of Superadmins
