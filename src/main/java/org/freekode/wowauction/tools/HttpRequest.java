package org.freekode.wowauction.tools;

import org.codehaus.plexus.util.IOUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    public static String sendGet(String url) {
        BufferedReader in = null;
        StringBuilder response = null;

        try {
            HttpURLConnection con;

            if ("https".equals(url.split(":")[0])) {
                con = (HttpsURLConnection) new URL(url).openConnection();
            } else {
                con = (HttpURLConnection) new URL(url).openConnection();
            }


            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            } else {
                throw new IOException("Response code = " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                IOUtil.close(in);
            }
        }


        return response.toString();
    }
}
