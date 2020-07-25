/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dirga
 */
public class Log {
    
    /**
     * @return 
     */
    public static String GetTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
    
    /**
     * @param Header     
     * @param Log     
     */
    public static void Logger(String Header, String Log){
        System.out.println(MessageFormat.format("{0} {1} : {2}", Header, GetTime(), Log));
    }
}
