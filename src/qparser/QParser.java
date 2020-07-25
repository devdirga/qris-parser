/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qparser;

import db.DbAdapter;
import db.Mutation;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.UncheckedIOException;
import util.ConfigReader;
import util.Log;
import util.Link;

/**
 *
 * @author Dirga
 */
public class QParser {

    public static Map<String, String> MapHasInserted = new HashMap();
    public static DbAdapter DB;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DB = new DbAdapter();
        new Thread(() -> {
            while (true) {
                Log.Logger("[INFO]", "Started...");
                List<Mutation> mutations = new ArrayList<>();
                MapHasInserted = DbAdapter.GetMapWasInserted();
                try {
                    JSONArray jsonArray = new JSONArray(Jsoup.connect(Link.GetUrlQuery()).ignoreContentType(true).execute().body());
                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            if (!MapHasInserted.containsKey( MessageFormat.format("{0}:{1}:{2}:{3}", 
                                    jsonArray.getJSONObject(i).getString("qrtransid"), 
                                    Link.GetNominal(jsonArray.getJSONObject(i).getString("amount") ), 
                                    Link.EMPTY, 
                                    Link.NOL))) {
                                mutations.add(new Mutation(obj.getString("qrtransid"), "QRIS", Link.GetNominal(obj.getString("amount")), "0", obj.getString("invoiceid")));
                            } else {
                                Log.Logger("[INFO]", "Record already inserted...");
                            }
                        }
                        DB.InsertMutation(mutations);
                    } else {
                        Log.Logger("[INFO]", "No data...");
                    }
                    Thread.sleep(ConfigReader.GetLoopWait() * 60 * 1000);
                } catch (IOException ioEx) {
                    Log.Logger("[ERROR]", ioEx.getMessage());
                    try {
                        Thread.sleep(ConfigReader.GetLoopWait() * 60 * 1000);
                    } catch (InterruptedException exThread) {
                        Log.Logger("[ERROR]", exThread.getMessage());
                    }
                } catch (JSONException | InterruptedException | UncheckedIOException e) {
                    Log.Logger("[ERROR]", e.getMessage());
                } 
            }
        }).start();
    }
}