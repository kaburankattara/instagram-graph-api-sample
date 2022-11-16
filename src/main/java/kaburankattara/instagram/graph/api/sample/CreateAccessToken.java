package kaburankattara.instagram.graph.api.sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kaburankattara.instagram.graph.api.utils.PrintUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class CreateAccessToken {
    public static void main(String[] args) {
        String longLivedAccessToken = "";
        String id = "";
        String target_url = "https://graph.facebook.com/v15.0/" + id + "/accounts?access_token=" + longLivedAccessToken;

        StringBuilder output = new StringBuilder();
        try {
            URL url = new URL(target_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(output.toString(), new TypeReference<Map<String, Object>>(){});
            PrintUtils.printMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
