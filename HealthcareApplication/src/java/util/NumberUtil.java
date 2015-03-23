/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author huang
 */
public class NumberUtil {
    public static boolean isInteger(String number)
    {
        try
        {
           int i = Integer.parseInt(number);
           return true;
        }
        catch (NumberFormatException ex)
        {
           return false;
        }

    }
    
    public static boolean isDouble(String number)
    {
        try
        {
           double i = Double.parseDouble(number);
           return true;
        }
        catch (NumberFormatException ex)
        {
           return false;
        }

    }
}
