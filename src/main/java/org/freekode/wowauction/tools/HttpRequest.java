package org.freekode.wowauction.tools;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    public static String sendGet(String url) {
        BufferedReader in = null;
        StringBuilder response = new StringBuilder();

        try {
            HttpURLConnection con = null;

            if ("http".equals(url.split(":")[0])) {
                con = (HttpURLConnection) new URL(url).openConnection();
            } else if ("https".equals(url.split(":")[0])) {
                con = (HttpsURLConnection) new URL(url).openConnection();
            }

            if (con == null) {
                return null;
            }

            con.setRequestMethod("GET");

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
