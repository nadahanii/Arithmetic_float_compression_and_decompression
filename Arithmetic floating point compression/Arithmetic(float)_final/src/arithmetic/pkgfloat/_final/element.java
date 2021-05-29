/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic.pkgfloat._final;



/**
 *
 * @author dell
 */
public class element {
    
    
    private String symbol;
    private double prob;
    private double lower;
    private double upper;
    
    public element()
    {
        
    }
    
    public element(String symbol , double prob , double lower , double upper)
    {
        this.symbol=symbol;
        this.prob=prob;
        this.lower=lower;
        this.upper=upper;
    }
    
    public void setSymbol (String temp)
    {
        this.symbol=temp;
    }
    
    public String getSymbol()
    {
        return symbol;
    }
    
    public void setProb(double temp)
    {
        this.prob=temp;
    }
    
    public double getProb()
    {
        return prob;
    }
    
    public void setLower(double temp)
    {
        this.lower=temp;
    }
    
    public double getLower()
    {
        return lower;
    }
    
    public void setUpper(double temp)
    {
        this.upper=temp;
    }
    
    public double getUpper()
    {
        return upper;
    }
    
    @Override
    public String toString()
    {
        return ("symbol: "+getSymbol()+"  probability: "+getProb()+"  upper range: "+getUpper()+"  lower range: "+getLower());
    }
    
}

