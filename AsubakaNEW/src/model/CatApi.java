package model;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import okhttp3.OkHttpClient;

public class CatApi {

	private static final String BASE_URL = "https://api.thecatapi.com/v1/images/search";
	 /**
     * ランダムな猫の画像のURLを取得します。
     * 
     * @return 画像のURL
     * @throws IOException APIのリクエストやレスポンスに問題があった場合にスローされます。
     */
    public String getRandomCatImage() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(BASE_URL).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API response was not successful. Code: " + response.code());
            }

            String jsonResponse = response.body().string();
            return extractImageUrlFromJson(jsonResponse);
        }
    }

    /**
     * JSONレスポンスから画像のURLを抽出します。
     * 
     * @param jsonResponse APIからのJSONレスポンス
     * @return 画像のURL
     * @throws JsonSyntaxException JSONの解析に失敗した場合にスローされます。
     */
    private String extractImageUrlFromJson(String jsonResponse) throws JsonSyntaxException {
        JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
        JsonElement imageUrlElement = jsonArray.get(0).getAsJsonObject().get("url");
        if (imageUrlElement == null) {
            throw new JsonSyntaxException("URL not found in JSON response");
        }
        return imageUrlElement.getAsString();
    }
}