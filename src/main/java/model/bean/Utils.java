/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vinic
 */
public class Utils {
    public static Date converter ( String s) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(s);
    }
    
    public static String converter ( Date d) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");                  
        return sdf.format(d);
    }
}
