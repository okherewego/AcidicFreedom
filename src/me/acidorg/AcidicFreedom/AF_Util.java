package me.acidorg.AcidicFreedom;

import java.util.ArrayList;
import java.util.List;

public class AF_Util
{
    public static final List<String> stop_commands = new ArrayList<String>();
  
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



}
