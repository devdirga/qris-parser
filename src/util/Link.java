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

/**
 *
 * @author Dirga
 */
public class Link {
    
    public static String URL = "https://interactiveholic.com/qris/dashboard/getdatafromqristrx.php?";
    public static String MID = "QRIS-GENERATED-MID";
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
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format( finishdate.minus(Period.ofDays(1)) ), 
                DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(finishdate));
    }

    /**
     * @param text
     * @return
     */
    public static String GetNominal(String text) {
        text = text.replace(".00", "");
        return text;
    }
    
}
