/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Dirga
 */
public class ConfigReader {
    
    private static final String DBHOST = "localhost";
    private static final String DBPORT = "3306";
    private static final String DBUSER = "root";
    private static final String DBPASS = "admin123";
    private static final String DBNAME = "ibank_bca";
    private static final String USER = "QRIS-USERNAME";
    private static final String PASS = "QRIS-PASSWORD";
    private static final long LOOPWAIT = 1;
    
    /**
     * @return 
     */
    public static String GetDbUser() {
        return DBUSER;
    }

    /**
     * @return 
     */
    public static String GetDbPass() {
        return DBPASS;
    }

    /**
     * @return 
     */
    public static String GetDbPort() {
        return DBPORT;
    }

    /**
     * @return 
     */
    public static String GetDbHost() {
        return DBHOST;
    }

    /**
     * @return 
     */
    public static String GetDbName() {
        return DBNAME;
    }

    /**
     * @return 
     */
    public static String GetUser() {
        return USER;
    }

    /**
     * @return 
     */
    public static String GetPass() {
        return PASS;
    }
    
    /**
     * @return 
     */
    public static long GetLoopWait() {
        return LOOPWAIT;
    }
    
}