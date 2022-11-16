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

/**
 * 他のビジネスインスタグラムユーザー情報を取得するサンプル
 */
public class BusinessDiscovery {
    public static void main(String[] args) {
        String instagram_business_id = "";
        String access_token = "";
        String user_id = "bluebottle"; // 取得するユーザー
        String limit = "10"; // 取得する投稿数
        String media = "id,caption,media_url,permalink,media_type,comments_count,like_count,timestamp"; //取得する投稿の情報
        String fields = "business_discovery.username(" + user_id + "){profile_picture_url,follows_count,biography,name,username,followers_count,media_count,website,media.limit(" + limit + "){" + media + "}}"; // 取得する情報
        String target_url = "https://graph.facebook.com/v15.0/" + instagram_business_id + "?fields=" + fields + "&access_token=" + access_token;

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
