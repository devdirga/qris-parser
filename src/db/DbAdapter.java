/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.ConfigReader;
import util.Log;

/**
 *
 * @author Dirga
 */
public class DbAdapter {
    
    Connection cn = null;
    Statement st = null;
    ResultSet hasilResultSet;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy // HH-mm-ss");
    
    public void connectionDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + ConfigReader.GetDbHost() + ":" + ConfigReader.GetDbPort() + "/" + ConfigReader.GetDbName();
            String user = ConfigReader.GetDbUser();
            String password = ConfigReader.GetDbPass();
            cn = (Connection) DriverManager.getConnection(url, user, password);
            st = cn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            Log.Logger("[ERROR]", e.getMessage());
        }
    }
    
    /**
     * @param mutation
     * @return 
     */
    public Boolean GetMutation(Mutation mutation) {
       ResultSet results;
        try {
            results = st.executeQuery(MessageFormat.format(
                    "SELECT * FROM BANKMUTASI "
                            + "WHERE KODEBANK = ''{4}'' "
                            + "AND KETERANGAN = ''{0}'' "
                            + "AND NOMINAL = {1,number,#.#} "
                            + "AND DBKR = ''{2}'' "
                            + "AND SALDO = {3,number,#.#} "
                            + "AND TANGGAL BETWEEN (DATE_SUB(CURDATE(), INTERVAL 3 DAY)) AND CURDATE()"
                    ,mutation.getKeterangan()
                    ,Double.parseDouble(mutation.getNominal())
                    ,mutation.getDbkr()
                    ,Double.parseDouble(mutation.getSaldo())
                    ,"QRIS"));
            return results.next();
        } catch (SQLException e) {
            Log.Logger("[ERROR59]", e.getMessage());
            return false;
        }
    }
    
    /**
     * @return 
     */
    public Boolean GetData(){
        ResultSet results;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + ConfigReader.GetDbHost() + ":" + ConfigReader.GetDbPort() + "/" + ConfigReader.GetDbName();
            String user = ConfigReader.GetDbUser();
            String password = ConfigReader.GetDbPass();
            try (Connection con = (Connection) DriverManager.getConnection(url, user, password); Statement stmt = con.createStatement()) {
                results = stmt.executeQuery(
                        "SELECT KETERANGAN,NOMINAL,DBKR,SALDO FROM BANKMUTASI "
                                + "WHERE KODEBANK='QRIS' "
                                + "AND TANGGAL BETWEEN (DATE_SUB(CURDATE(), INTERVAL 3 DAY)) AND CURDATE()");
                if (results != null) {
                    while (results.next()) {
                    }
                }
                if(results != null){
                    results.close();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("[ERROR] " + Log.GetTime() + " : " + e);
        }
        return null;
    }
    
    /**
     * @return 
     */
    public static Map<String, String> GetMapWasInserted() {
        ResultSet results;
        Map<String, String> res = new HashMap();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + ConfigReader.GetDbHost() + ":" + ConfigReader.GetDbPort() + "/" + ConfigReader.GetDbName();
            String user = ConfigReader.GetDbUser();
            String password = ConfigReader.GetDbPass();
            try (Connection con = (Connection) DriverManager.getConnection(url, user, password); Statement stmt = con.createStatement()) {
                results = stmt.executeQuery("SELECT KETERANGAN,NOMINAL,DBKR,SALDO FROM BANKMUTASI WHERE KODEBANK = 'QRIS' AND TANGGAL BETWEEN (DATE_SUB(CURDATE(), INTERVAL 3 DAY)) AND CURDATE()");
                if (results != null) {
                    while (results.next()) {
                        res.put(MessageFormat.format("{0}:{1}:{2}:{3}",
                                results.getString("KETERANGAN"),
                                results.getString("NOMINAL"),
                                results.getString("DBKR"),
                                results.getString("SALDO")), results.getString("KETERANGAN"));
                    }
                }
                if(results!=null){
                    results.close();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            Log.Logger("[ERROR]", e.getMessage());
        }
        return res;
    }
    
    /**
     * @param mutations
     * @return 
     */
    public String InsertMutation(List<Mutation> mutations) {
        connectionDB();
        String error = "Error";
        try {
            for (Mutation mutation : mutations) {
                if (!GetMutation(mutation)) {
                    Mutation mut = mutation;                    
                    if (GetData() == null) {
                         st.execute(MessageFormat.format(
                                 "INSERT INTO BANKMUTASI (KODEBANK, TANGGAL, KETERANGAN, NOMINAL, DBKR, SALDO, TGLSYS, WAKTUSYS, CATATAN1, CATATAN2) "
                                         + "VALUES (''{0}'',CURDATE(),''{1}'',{2,number,#.#},''{3}'',{4},CURDATE(),CURTIME(),''{5}'',''{6}'')"
                                 ,mut.getKodebank()
                                 ,mut.getKeterangan()
                                 ,Double.parseDouble(mut.getNominal())
                                 ,mut.getDbkr()
                                 ,mut.getSaldo()
                                 ,mut.getKeterangan()
                                 ,mut.getKeterangan()));
                         error = "Success";
                         Log.Logger("[SUCCESS]", "Data inserted...");
                    }
                }
            }
        }catch(NumberFormatException | SQLException e){
            System.out.println("[ERROR] " + Log.GetTime() + " : " + e);
        }        
        finally {
            CloseDB();
        }
        return error;
    }
    
    /**
     * CloseDB
     */
    public void CloseDB() {
        try {
            if (!cn.isClosed()) {
                cn.close();
            }
            if (!st.isClosed()) {
                st.close();
            }
        } catch (SQLException e) {
            Log.Logger("[ERROR]", e.getMessage());
        }
    }
    
}
