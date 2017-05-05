/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author Cliente
 */
public class Tokens {
   
    public static int Covert(String type)
    {
        if (type.equals("ERR"))
        {
            return -2;
        }
        else if (type.equals("EOL"))
        {
            return  256;
        }
        else if (type.equals("PLUS"))
        {
            return 257;
        }
        else if (type.equals("EQUALS"))
        {
            return 258;
        }
        else if (type.equals("NUM"))
        {
            return 259;
        }
        else if (type.equals("VAR"))
        {
            return 260;
        }
        else if (type.equals("PRINT"))
        {
            return 261;
        }

        return -1;
    }
}
