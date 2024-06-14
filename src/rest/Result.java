package rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

class Result {

    public static String getCapitalCity(String country) {
        try {
            String apiUrl = "https://jsonmock.hackerrank.com/api/countries?name=" + country;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());

                JSONArray dataArray = jsonResponse.getJSONArray("data");

                if (dataArray.length() == 1) {
                    JSONObject countryData = dataArray.getJSONObject(0);
                    return countryData.getString("capital");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

}
