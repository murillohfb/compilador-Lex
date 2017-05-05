/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.IOException;

/**
 *
 * @author Cliente
 */
public class Olp {
    Token lookahead = new Token();
    Symbols sym = new Symbols();
    Tokens tok = new Tokens();
    Lexer lex = new Lexer();
    
    void error(String msg)
    {
        System.out.println("Syntax error on" + lex.line + ":" + lex.char_pos);
        System.out.println(msg);
    }
    
    void match(int type)
    {
        if(lookahead.token == type)
        {
            lookahead = lex.next_Token();
        }
        else
        {
            error("Not Match");
        }
    }
    
    void prg()
    {
        cmd();
        pr2();
    }
    void pr2()
    {
        if(lookahead.token == Tokens.Covert("EOL"))
        {
            match(Tokens.Covert("EOL"));
            prg();
        }
        else if (lookahead.token != Tokens.Covert("EOF"))
        {
            error("EOL Expected");
        }
    }
    
    void cmd()
    {
        if(lookahead.token == Tokens.Covert("NUM"))
        {
            exp();
        }
        else if(lookahead.token == Tokens.Covert("VAR"))
        {
            atr();
        }
        else if(lookahead.token == Tokens.Covert("PRINT"))
        {
            out();
        }
        else if (lookahead.token != Tokens.Covert("EOF"))
        {
            error("Unrecongnized Command");
        }
    }
    
    int rst()
    {
        if (lookahead.token == Tokens.Covert("PLUS"))
        {
            match(Tokens.Covert("PLUS"));
            return exp();
        }
        return 0;
    }
    
    int exp()
    {
        if(lookahead.token == Tokens.Covert("NUM"))
        {
            int x = lookahead.value;
            match(Tokens.Covert("NUM"));
            int y = rst();
            return x+ y;
        }
        return 0;
    }
    
    void atr()
    {
        if (lookahead.token == Tokens.Covert("VAR"))
        {
            int v = lookahead.value;
            match(Tokens.Covert("VAR"));
            match(Tokens.Covert("EQUALS"));
            int x = val();
            if(!sym.update_symbol(v, x))
            {
                error("Impossible assign value");
            }
            else
            {
                error("VAR Expected");
            }
        }
    }
    void out()
    {
        if (lookahead.token == Tokens.Covert("PRINT"))
        {
            match(Tokens.Covert("PRINT"));
            int x = val();
            System.out.println(x);
        }
    }
    
    int val()
    {
        if(lookahead.token == Tokens.Covert("VAR"))
        {
            Token v = lookahead;
            match(Tokens.Covert("VAR"));
            Token s = sym.get_symbol(v.value);
            if(s.token != Tokens.Covert("ERR"))
                return s.value;
            else
                error("VAR not found");
            return 0;
        }
        else if(lookahead.token == Tokens.Covert("NUM")){
            return exp();
        }
        else
        {
            error("VAR or NUM expected");
            return -1;
        }    
    }
    
    public void main(String[] args, int argc, String filename) throws IOException
    {
        if (argc > 1)
        {
            String input = lex.read_all_lines(filename);
            
        }
        else
        {
            System.out.println("Error on read file");
            
        }
        lookahead = lex.next_Token();
        prg();
        sym.print_symbol_table();
    }
}
