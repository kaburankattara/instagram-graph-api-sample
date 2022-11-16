package kaburankattara.instagram.graph.api.sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kaburankattara.instagram.graph.api.utils.InputQueryUtils;
import kaburankattara.instagram.graph.api.shared.InstagramProperties;
import kaburankattara.instagram.graph.api.utils.PrintUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class CreateLongLivedAccessToken {
    public static void main(String[] args) {
        String shortLivedUserAccessToken = InputQueryUtils.getInputQuery();

        InstagramProperties properties = InstagramProperties.createInstance();
        String target_url = "https://graph.facebook.com/oauth/access_token?"
                + "grant_type=fb_exchange_token"
                + "&client_id=" + properties.getAppId()
                + "&client_secret=" + properties.getAppSecret()
                + "&fb_exchange_token=" + shortLivedUserAccessToken;

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
