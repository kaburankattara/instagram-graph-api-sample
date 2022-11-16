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
 * ビジネスアカウントIDを取得するサンプル
 *
 * 【ブラウザで取得する場合の操作】
 * グラフエクスプローラを開く
 * アクセストークンに三番目のアクセストークンを設定し、
 * 「me?fields=instagram_business_account」で送信する
 *
 */
public class GetInstagramBusinessId {
    public static void main(String[] args) {
        String accessToken = "";
        String target_url = "https://graph.facebook.com/v15.0/me?"
                + "&fields=instagram_business_account"
                + "&access_token=" + accessToken;

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
