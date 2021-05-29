/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic.pkgfloat._final;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class ArithmeticFloat_final {
    
    
    ArrayList<element> comp_list = new ArrayList<>();
    ArrayList<element> decomp_list = new ArrayList<>();
    public void cumm_prob_calculation (ArrayList<element> temp)
    {
        double low=0;
        for(int i=0;i<temp.size();i++)
        {
            double high = temp.get(i).getProb()+low;
            temp.get(i).setLower(low);
            temp.get(i).setUpper(high);
            low=high;
            
        }
    }
    
    public void calculate_prob(String text)
    {
        int n = text.length();
        for(int i=0;i<text.length();i++)
        {
            String curr=text.charAt(i)+"";
            boolean exist=false;
            for(int j=0;j<comp_list.size();j++)
            {
                if(comp_list.get(j).getSymbol().equals(curr)) //letter exists in the list 
                {
                    double probability = (comp_list.get(j).getProb())+1;
                    comp_list.get(j).setProb(probability);
                    exist=true;
                    break;
                }
            }
            
            if(!exist) //letter doesn't exist
            {
                comp_list.add(new element(curr,1,0,0));
            }
            
            
            
        }
        
        for(int a=0;a<comp_list.size();a++)
        {
            double temp_prob = (comp_list.get(a).getProb()) / n;
            comp_list.get(a).setProb(temp_prob);
        }
        
        
        cumm_prob_calculation(comp_list);
        
        
    }
    
    public element fetchBySymbol (String symbol , ArrayList<element> temp)
    {
        String temp_symb = symbol;
        element value= new element();
        for(int i=0;i< temp.size();i++)
        {
            if(temp.get(i).getSymbol().equals(temp_symb))
            {
                value=temp.get(i);
                break;
            }
        }
        
        return value;
    }
    
    public double compress(String sentence)
    {
        calculate_prob(sentence);
       // Print();
       for(int i=0;i<comp_list.size();i++)
       {
           element temp = new element(comp_list.get(i).getSymbol(),comp_list.get(i).getProb(),comp_list.get(i).getLower(),comp_list.get(i).getUpper());
           decomp_list.add(temp);
       }
       //Print();
       
       double lower=0;
       double upper=1;
       double old_lower=0;
       double old_upper=1;
       String curr="";
       for(int k=0;k<sentence.length();k++)
       {
           element curr_element = new element();
           curr=sentence.charAt(k)+"";
           curr_element = fetchBySymbol(curr, comp_list);
           lower=old_lower+((old_upper-old_lower)*curr_element.getLower());
           upper=old_lower+((old_upper-old_lower)*curr_element.getUpper());
           old_lower=lower;
           old_upper=upper;
          // System.out.println("current lower" + lower);
          // System.out.println("current upper: "+upper);
           
       }
       // System.out.println(lower);
        // System.out.println(upper);
       double compress_ratio = (lower+upper)/2;
       return compress_ratio;
        
    }
    
    public void Print()
    {
        for(int k=0;k<decomp_list.size();k++)
        {
            System.out.println(decomp_list.get(k).toString());
        }
    }
    
    
    
    
    public ArithmeticFloat_final()
    {
        
    }
    
    public String decompress(double ratio , int text_size)
    {
        double lower=0;
        double upper=1;
        double old_lower=0;
        double old_higher=1;
        double code=ratio;
        double cur_code;
        String original_sentence="";
        for(int i=0;i<text_size;i++)
        {
            cur_code=(code-old_lower)/(old_higher-old_lower);
            
            for(int x=0;x<decomp_list.size();x++)
            {
                if((cur_code > decomp_list.get(x).getLower()) && (cur_code < decomp_list.get(x).getUpper()))
                {
                    original_sentence+=decomp_list.get(x).getSymbol();
                    lower = old_lower + ((old_higher-old_lower)*decomp_list.get(x).getLower());
                    upper = old_lower +((old_higher-old_lower)*decomp_list.get(x).getUpper());
                    old_lower = lower;
                    old_higher = upper;
                    break;
                }
            }
        }
        
        return original_sentence;
    }
    

   
    public static void main(String[] args) {
        
        ArithmeticFloat_final af1 = new ArithmeticFloat_final();
         Scanner in = new Scanner(System.in);
        String text;
        
        System.out.println("enter the string");
        text = in.nextLine();
       
        double temp = af1.compress(text);
        System.out.println("compression ratio is: " + temp);
        String y= af1.decompress(temp, text.length());
        System.out.println("decompressed sentence: " + y);
        
        
    }
    
}
