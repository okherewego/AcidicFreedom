package me.acidorg.AcidicFreedom;

import java.util.ArrayList;
import java.util.List;

public class AF_Util
{
    public static final List<String> stop_commands = new ArrayList<String>();
<<<<<<< HEAD
	
=======
  
>>>>>>> b01ccb50a054e124e849139dbb84ed1732856c6a
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
