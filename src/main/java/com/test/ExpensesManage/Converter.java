package com.test.ExpensesManage;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Converter {
    private static JSONObject data_obj;
    private static JSONObject obj;
    private static Converter converterObj;


    private Converter() {
        try {
            URL url = new URL("http://data.fixer.io/api/latest?access_key=e065f65f54f42d660ebe9611a36528ba&format=1&base=EUR");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            StringBuilder inline = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline.append(sc.nextLine());
                }
                sc.close();
            conn.disconnect();
            JSONParser parse = new JSONParser();
            data_obj = (JSONObject) parse.parse(String.valueOf(inline));
            obj = (JSONObject) data_obj.get("rates");



        } catch (IOException ioException) {
            System.out.println("Connection problem with API");

        } catch (ParseException parseException) {
            System.out.println("JSON parse exception");

        }
    }
    public static Converter getConverter(){
       if (converterObj !=null){
         if (!(((data_obj.get("date"))).equals(java.time.LocalDate.now().toString()))) {
             System.out.println("true");
           converterObj = new Converter();
       }
       }else converterObj = new Converter();
       return converterObj;
    }

    public double getInEUR(String base, double amount){
        Object o =  obj.get(base);
        double rate;
        if(o instanceof Long)
             rate= ((Long) o).doubleValue();
     else   rate = (double) o;
    return amount/rate;

    }

    public double convertTo(String base, double amount){
        Object o =  obj.get(base);
        double rate;
        if(o instanceof Long)
            rate= ((Long) o).doubleValue();
        else  rate = (double) o;
        return amount*rate;

    }

    public boolean isBaseValid(String base){
        return obj.containsKey(base);
    }

}
