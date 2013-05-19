package me.acidorg.AcidicFreedom;

import java.io.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AF_Util
{
    private AF_Util()
    {
        throw new AssertionError();
    }

    public static void bcastMsg(String message, ChatColor color)
    {
        AF_Log.info(message, true);

        for (Player p : Bukkit.getOnlinePlayers())
        {
            p.sendMessage((color == null ? "" : color) + message);
        }
    }

    public static void bcastMsg(String message)
    {
        AF_Util.bcastMsg(message, null);
    }
	
    public static void adminAction(String adminName, String action, boolean isRed)
    {
        AF_Util.bcastMsg(adminName + " - " + action, (isRed ? ChatColor.RED : ChatColor.AQUA));
    }
	
    public static String implodeStringList(String glue, List<String> pieces)
    {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < pieces.size(); i++)
        {
            if (i != 0)
            {
                output.append(glue);
            }
            output.append(pieces.get(i));
        }
        return output.toString();
    }
    
    public static String DATE_STORAGE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";
    
    public static String dateToString(Date date)
    {
        return new SimpleDateFormat(DATE_STORAGE_FORMAT, Locale.ENGLISH).format(date);
    }

    public static Date stringToDate(String date_str)
    {
        try
        {
            return new SimpleDateFormat(DATE_STORAGE_FORMAT, Locale.ENGLISH).parse(date_str);
        }
        catch (ParseException ex)
        {
            return new Date(0L);
        }
    }
        public static void createDefaultConfiguration(String name, File plugin_file)
        {
            AcidicFreedom af = AcidicFreedom.plugin;

            File actual = new File(af.getDataFolder(), name);
            if (!actual.exists())
            {
                AF_Log.info("Installing default configuration file template: " + actual.getPath());
                InputStream input = null;
                try
                {
                    JarFile file = new JarFile(plugin_file);
                    ZipEntry copy = file.getEntry(name);
                    if (copy == null)
                    {
                        AF_Log.severe("Unable to read default configuration: " + actual.getPath());
                        return;
                    }
                    input = file.getInputStream(copy);
                }
                catch (IOException ioex)
                {
                    AF_Log.severe("Unable to read default configuration: " + actual.getPath());
                }
                if (input != null)
                {
                    FileOutputStream output = null;

                    try
                    {
                        af.getDataFolder().mkdirs();
                        output = new FileOutputStream(actual);
                        byte[] buf = new byte[8192];
                        int length;
                        while ((length = input.read(buf)) > 0)
                        {
                            output.write(buf, 0, length);
                        }

                        AF_Log.info("Default configuration file written: " + actual.getPath());
                    }
                    catch (IOException ioex)
                    {
                        AF_Log.severe("Unable to write default configuration: " + actual.getPath() + "\n" + ExceptionUtils.getStackTrace(ioex));
                    }
                    finally
                    {
                        try
                        {
                            if (input != null)
                            {
                                input.close();
                            }
                        }
                        catch (IOException ioex)
                        {
                        }

                        try
                        {
                            if (output != null)
                            {
                                output.close();
                            }
                        }
                        catch (IOException ioex)
                        {
                        }
                    }
                }
            }
        }

        @Deprecated
        public static boolean isUserSuperadmin(CommandSender user)
        {
            return AF_SuperadminList.isUserSuperadmin(user);
        }
        
        public static boolean deleteFolder(File file)
        {
            if (file.exists())
            {
                if (file.isDirectory())
                {
                    for (File f : file.listFiles())
                    {
                        if (!AF_Util.deleteFolder(f))
                        {
                            return false;
                        }
                    }
                }
                file.delete();
                return !file.exists();
            }
            else
            {
                return false;
            }
        }
        
        public static boolean isFromHostConsole(String sender_name)
        {
            return AcidicFreedom.host_sender_names.contains(sender_name.toLowerCase());
        }
        
        public static List<String> removeDuplicates(List<String> old_list)
        {
            List<String> new_list = new ArrayList<String>();
            for (String entry : old_list)
            {
                if (!new_list.contains(entry))
                {
                    new_list.add(entry);
                }
            }
            return new_list;
        }

        public static boolean fuzzyIpMatch(String a, String b, int required_octets)
        {
            boolean is_match = true;

            String[] a_parts = a.split("\\.");
            String[] b_parts = b.split("\\.");

            if (a_parts.length != 4 || b_parts.length != 4)
            {
                return false;
            }

            if (required_octets > 4)
            {
                required_octets = 4;
            }
            else if (required_octets < 1)
            {
                required_octets = 1;
            }

            for (int i = 0; i < required_octets && i < 4; i++)
            {
                if (a_parts[i].equals("*") || b_parts[i].equals("*"))
                {
                    continue;
                }

                if (!a_parts[i].equals(b_parts[i]))
                {
                    is_match = false;
                    break;
                }
            }

            return is_match;
        }
        
        //getField: Borrowed from WorldEdit
        @SuppressWarnings("unchecked")
        public static <T> T getField(Object from, String name)
        {
            Class<?> checkClass = from.getClass();
            do
            {
                try
                {
                    Field field = checkClass.getDeclaredField(name);
                    field.setAccessible(true);
                    return (T) field.get(from);
                }
                catch (NoSuchFieldException ex)
                {
                }
                catch (IllegalAccessException ex)
                {
                }
            }
            while (checkClass.getSuperclass() != Object.class && ((checkClass = checkClass.getSuperclass()) != null));
            return null;
        }
    }
        
    
