package org.freekode.wowauction.engine.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by freekode on 03/02/15.
 */
public class HttpRequest {
    public static String sendGet(String url) {
        BufferedReader in = null;
        StringBuilder response = new StringBuilder();

        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
//            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {

                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            } else {
                throw new IOException("Response code = " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return response.toString();
    }
}
