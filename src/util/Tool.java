/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dirga
 */
public class Tool {
    
    public static String URL = "https://interactiveholic.com/qris/dashboard/getdatafromqristrx.php?";
    public static String MID = "195241173626";
    public static String EMPTY = "";
    public static String NOL = "0";
    
    /**
     * @return
     */
    public static String GetUrlQuery() {
        LocalDateTime finishdate = LocalDateTime.now();
        return MessageFormat.format("{0}getmid={1}&dtBeg={2}&dtEnd={3}",
                URL,
                MID, 
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format( finishdate.minus(Period.ofDays(30)) ), 
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(finishdate));
    }

    /**
     * @param text
     * @return
     */
    public static String GetNominal(String text) {
        return text.replace(".00", "");
    }
    
    /**
     * @return 
     */
    public static String GetTime() {
        return (new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format(new Date());
    }
    
    /**
     * @param Header     
     * @param Log     
     */
    public static void Logger(String Header, String Log){
        System.out.println(MessageFormat.format("{0} {1} : {2}", Header, GetTime(), Log));
    }
    
    /**
     * @param Header     
     * @param Log     
     */
    public static void Logger(String Header, Exception Log){
        System.out.println(MessageFormat.format("{0} {1} : {2}", Header, GetTime(), Log));
    }
    
}
