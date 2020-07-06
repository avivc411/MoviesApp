package com.example.moviesapp.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONObject;

import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpConnector {
    private static HttpConnector connector;
    private OkHttpClient okHttpClient;
    private final String TAG = "http";

    private HttpConnector() {
        okHttpClient = new OkHttpClient();
    }

    public static HttpConnector getInstance() {
        if (connector == null)
            connector = new HttpConnector();
        return connector;
    }

    public JSONObject CallGetRequestForJSON(String url) {
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            switch (response.code()) {
                case 200:
                case 201:
                    Log.d(TAG, "run: success"+response.code());
                    return new JSONObject(response.body().string());
                case 401:
                case 402:
                case 403:
                case 404:
                    Log.d(TAG, "callGetRequest: bad request"+response.code());
                    return new JSONObject("{\"error\":\"bad request\"," +
                            "\"code\":\""+response.code()+"\"}");
                default:
                    Log.d(TAG, "callGetRequest: "+response.code() + " : " + response);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
