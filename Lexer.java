
package compilador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

/**
 *
 * @author Cliente
 */
public class Lexer {
    int line = 1;
    char peek = ' ';
    String input;
    int input_pos = -1;
    int char_pos = -1;
    
    public char next_char()
    {
        input_pos++;
        char_pos++;
        char c = '\0';
        if(input_pos < input.length())
            c = input.charAt(input_pos);
        return c;
    }
    
    Token next_Token()
    {
        Token t = new Token();
        Symbols symbol = new Symbols();
        t.token = -1;
        peek = next_char();
        
        do
        {
            if(peek == ' ' || peek == '\t')continue;
            else if (peek == '\n')
            {
                line++;
                char_pos = -1;
                
            }
            else break;
        }while(peek == next_char());
        
        if(peek == '$')
        {
            String var = "$";
            peek = next_char();
            while(isLetter(peek))
            {
                var += peek;
                peek = next_char();
            }
            input_pos--;
            t.token = Tokens.Covert("VAR");
            Token s = new Token();
            s.token = Tokens.Covert("VAR");
            s.value = 0;
            s.lexem = var;
            t.value = symbol.add_symbol(s);
            return t;
        }
        else if (isDigit(peek))
        {
            int x = 0;
            do
            {
                x = 10 * x + System.identityHashCode(peek);
                peek = next_char();
            }while (isDigit(peek));
            input_pos--;
            t.token = Tokens.Covert("NUM");
            t.value = x;
            return t;
        }
        else if (peek == 'p')
        {
            String print = "print";
            for (int i = 0; i < print.length(); i++)
            {
                if (print.charAt(i) == peek)
                {
                    peek = next_char();
                }
                else
                {
                    t.token = Tokens.Covert("ERR");
                    return t;
                }
            }
            t.token = Tokens.Covert("PRINT");
            return t;
        
        }
        else if (peek == ';')
        {
            t.token = Tokens.Covert("EOL");
            t.value = 0;
        }
        else if (peek == '+')
        {
            t.token = Tokens.Covert("PLUS");
            t.value = 0;
        }
        else if (peek == '=')
        {
            t.token = Tokens.Covert("EQUALS");
            t.value = 0;
        }
        return t;
        
    }
    
    public void print_token (Token t)
    {
        if (t.token == Tokens.Covert("ERR"))
        {
            System.out.println("<ERR>");
        }
        else if(t.token == Tokens.Covert("PLUS"))
        {
            System.out.println("<PLUS>");
        }
        else if(t.token == Tokens.Covert("EQUALS"))
        {
            System.out.println("<EQUALS>");
        }
        else if(t.token == Tokens.Covert("NUM"))
        {
            System.out.println("<NUM," + t.value + ">");
        }
        else if(t.token == Tokens.Covert("VAR"))
        {
            System.out.println("<VAR," + t.value + ">");
        }
        else if(t.token == Tokens.Covert("PRINT"))
        {
            System.out.println("<PRINT>");
        }
    }
    
    String read_all_lines(String filename) throws FileNotFoundException, IOException
    {
        String all_lines = "";
        try
        {
            FileReader file = new FileReader(filename);
            BufferedReader readFile = new BufferedReader(file);
            String linha = null;
            
            while((linha = readFile.readLine()) != null)
            {
                all_lines += linha;
            }
            readFile.close();
            return all_lines;
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Unable to read file " + filename);
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file " + filename);
        }
        

        return "Error";
    }
    
    
}
