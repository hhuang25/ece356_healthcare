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
public class StringUtil {
    public static boolean IsValidString(int maxLength, String... names) {
        if(names.length > 0){
            for(String name : names){
                if((name != null) && name.length() > maxLength){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean IsValidString(int maxLength, String name) {
        if((name == null) || (name.isEmpty()) || (name.length() <= maxLength)){
            return true;
        }
        return false;
    }
}
