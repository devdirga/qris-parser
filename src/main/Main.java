/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import util.Connect;
import model.Mutation;
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
import util.Conf;
import util.Tool;

/**
 *
 * @author Dirga
 */
public class Main {

    public static Map<String, String> MapHasInserted = new HashMap();
    public static Connect DB;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tool.Logger("[INFO]", "Started...");
        DB = new Connect();
        new Thread(() -> {
            while (true) {
                List<Mutation> mutations = new ArrayList<>();
                MapHasInserted = Connect.GetMapWasInserted();
                Tool.Logger("[INFO]" + Main.class.getName() + " LINE-43 ", "Start parsing...");
                try {
                    JSONArray jsonArray = new JSONArray(Jsoup.connect(Tool.GetUrlQuery()).ignoreContentType(true).execute().body());
                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            if (!MapHasInserted.containsKey(MessageFormat.format("{0}:{1}:{2}:{3}", 
                                    jsonArray.getJSONObject(i).getString("qrtransid"), 
                                    Tool.GetNominal(jsonArray.getJSONObject(i).getString("amount") ), 
                                    Tool.EMPTY,Tool.NOL))) {
                                mutations.add(new Mutation(obj.getString("qrtransid"), "QRIS", Tool.GetNominal(obj.getString("amount")), "0", obj.getString("invoiceid")));
                            } else {
//                                Tool.Logger("[ERROR]" + Main.class.getName() + " LINE-55 ", "X Data already exist...");
                            }
                        }
                        DB.InsertMutation(mutations);
                    } else {
                        Tool.Logger("[INFO]" + Main.class.getName() + " LINE-59 ", "Finish parsing... no data found");
                    }
                    Thread.sleep(Conf.GetLoopWait() * 60 * 1000);
                } catch (IOException ioEx) {
                    Tool.Logger("[ERROR]" + Main.class.getName() + " LINE-63 ", ioEx.getMessage());
                    try {
                        Thread.sleep(Conf.GetLoopWait() * 60 * 1000);
                    } catch (InterruptedException exThread) {
                        Tool.Logger("[ERROR]" + Main.class.getName() + " LINE-67 ", exThread.getMessage());
                    }
                } catch (JSONException | InterruptedException | UncheckedIOException e) {
                    Tool.Logger("[ERROR]" + Main.class.getName() + " LINE-70 ", e.getMessage());
                } 
            }
        }).start();
    }
}