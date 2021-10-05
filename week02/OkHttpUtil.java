package org.ming;

import okhttp3.*;

import java.io.IOException;

public class OkHttpUtil {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    public static OkHttpClient  client = new OkHttpClient();

    //post 方法
    static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }

    }

    //get 方法
    static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        System.out.println(post("http://localhost:8081/","{test}"));

        System.out.println(get("http://localhost:8081/api/hello"));

    }
}
