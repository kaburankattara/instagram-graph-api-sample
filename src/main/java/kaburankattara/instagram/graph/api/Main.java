package kaburankattara.instagram.graph.api;

import kaburankattara.instagram.graph.api.utils.InputQueryUtils;
import kaburankattara.instagram.graph.api.utils.PrintUtils;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // ターミナル経由でユーザーからのユーザートークンを返します。
        String shortLivedUserAccessToken = InputQueryUtils.getInputQuery("");

        String longLivedAccessToken = (String) new CreateLongLivedAccessToken().execute(shortLivedUserAccessToken).get("access_token");

        String userId = (String) new GetUserId().execute(longLivedAccessToken).get("id");

        CreateAccessToken createAccessToken = new CreateAccessToken();
        Map<String, Object> accessTokenMap = createAccessToken.execute(userId, longLivedAccessToken);
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) accessTokenMap.get("data");
        Map<String, Object> data = dataList.get(0);
        String accessToken = (String) data.get("access_token");

        Map<String, Object> InstagramBusinessIdMap = new GetInstagramBusinessId().execute(accessToken);
        Map<String, Object> instagramBusinessAccountMap = (Map<String, Object>) InstagramBusinessIdMap.get("instagram_business_account");
        String instagramBusinessId = (String) instagramBusinessAccountMap.get("id");

        // ターミナル経由でユーザーからの検索するインスタグラムユーザーを返します。
        String searchUserName = InputQueryUtils.getInputQuery("moaiskitchen");
        Map<String, Object> businessDiscoveryMap = new BusinessDiscovery().execute(instagramBusinessId, accessToken, searchUserName);
        PrintUtils.printMap(businessDiscoveryMap);
    }
}
