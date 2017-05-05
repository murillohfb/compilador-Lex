/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Symbols {
    Token symbol = new Token();
    ArrayList<Token> symbols = new ArrayList();
    
    public int add_symbol(Token symbol)
    {
        
        for (int i = 0; i < symbols.size(); i++)
        {
            Token s = symbols.get(i);
            if (s.lexem.compareTo(symbol.lexem)==0)
            {
                symbols.set(i, symbol);
                return i;
            }
        }
        symbols.add(symbol);
        return symbols.size()-1;
        
    }
    public Token get_symbol(int id)
    {
        if (id < symbols.size())
            return symbols.get(id);
        Token s = new Token();
        s.token = Tokens.Covert("ERR");
        return s;
    }
    
    public boolean update_symbol(int index, int value)
    {
        if(index > symbols.size())
            return false;
        symbols.get(index).value = value;
        return true;
    }
    
    void print_symbol_table()
    {
        for (int i = 0; i < symbols.size(); i++)
        {
            Token s = symbols.get(i);
            System.out.println("#" + i + " : " + s.token + " : " + s.value + " : " + s.lexem);
        }
    }
}
