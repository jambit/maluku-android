package com.jambit.maluku.android.maluku.android.malukuandroidapp.client;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.Foosball;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MalukuOkHttpClient {

    private OkHttpClient okHttpClient = new OkHttpClient();
    private ObjectMapper mapper = new ObjectMapper();

    private final static String URL_SENSOR_DATA = "http://kicker.jambit.com/api/sonic-sensors/table/status";
    private final static String BASE_URL_USER_DATA = "http://kicker.jambit.com/api/user-liste";

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    public List<Foosball> getSonicSensorData() throws IOException {
        final Request request = new Request.Builder().url(URL_SENSOR_DATA).build();

        final Response response = okHttpClient.newCall(request).execute();

        List<Foosball> kickerTables = null;
        if (response.isSuccessful()) {
            kickerTables = mapper.readValue(response.body().string(), new TypeReference<List<Foosball>>() {
            });
        } else {
            Log.e("OkHttp3 GET Error", response.body().string());
        }

        return kickerTables;
    }

    public User postPerson(String personName) throws IOException {
        final String url = BASE_URL_USER_DATA + "/add-user";

        RequestBody formBody = new FormBody.Builder()
                .add("user", personName)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();

        final User user = mapper.readValue(response.body().string(), User.class);

        return user;
    }

    public ArrayList<User> getUsers() throws IOException {
        final String url = BASE_URL_USER_DATA + "/users";

        final Request request = new Request.Builder().url(url).build();
        final Response response = okHttpClient.newCall(request).execute();

        ArrayList<User> users = null;
        if (response.isSuccessful()) {
            users = mapper.readValue(response.body().string(), new TypeReference<ArrayList<User>>() {
            });
        } else {
            Log.e("OkHttp3 GET error", response.body().string());
        }

        return users;
    }

    public String deletePerson(User user) throws IOException {
        final String url = BASE_URL_USER_DATA + "/delete-user";
        final String jsonUser = mapper.writeValueAsString(user);

        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, jsonUser);
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();
    }
}
