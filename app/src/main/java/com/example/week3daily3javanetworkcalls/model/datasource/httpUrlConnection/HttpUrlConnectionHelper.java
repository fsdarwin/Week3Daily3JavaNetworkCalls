package com.example.week3daily3javanetworkcalls.model.datasource.httpUrlConnection;

import com.example.week3daily3javanetworkcalls.model.User.Result;
import com.example.week3daily3javanetworkcalls.model.User.UserResponse;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.week3daily3javanetworkcalls.model.Constants.BASE_URL;

public class HttpUrlConnectionHelper {

    public static void makeAPICallWithHttpConn() {

        //DECLARE variables
        HttpURLConnection httpURLConnection = null;
        URL apiURL;
        Gson gson;
        String jsonResponse = "";
        ArrayList<Result> results;
        UserResponse userResponse;
        //ANCIENT JAVA API call hieroglyphs
        try {
            apiURL = new URL(BASE_URL);
            httpURLConnection = (HttpURLConnection) apiURL.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int currentReadCharAsciiValue = inputStream.read();
            while (currentReadCharAsciiValue != -1) {
                char currentChar = (char) currentReadCharAsciiValue;
                currentReadCharAsciiValue = inputStreamReader.read();
                jsonResponse = jsonResponse + currentChar;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                //GSON parse of JSON
                gson = new Gson();
                userResponse = gson.fromJson(jsonResponse, UserResponse.class);
                //STORE results in arrayList and send by eventBus to MainActivity
                results = new ArrayList<>(userResponse.getResults());
                EventBus.getDefault().post(results);
            }
        }

    }
}
