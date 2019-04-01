package com.jambit.maluku.android.maluku.android.malukuandroidapp.network;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.Foosball;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.User;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MalukuOkHttpClient {

    // OkHttp performs best when you create a single OkHttpClient instance and reuse it for all of your HTTP calls
    private OkHttpClient okHttpClient = new OkHttpClient();

    // Media Type, appropriate to describe the content type of an HTTP request or response body.
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    // The URL of the endpoints to get data from backend
    private final static String URL_SENSOR_DATA = "http://192.168.56.1:8080/api/v1/ktmock/status";
    private final static String URL_USER_DATA = "http://192.168.56.1:8080/api/user-liste";

    // The ObjectMapper provides functionality for reading and writing MEDIA_TYPE_JSON
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * This method is used to get the current sonic sensor data
     *
     * @return A List filled with the sonic sensor data
     * @throws IOException
     */

    // TODO 1 ist das 3
    public List<Foosball> getSonicSensorData() throws IOException {

        // To use OkHttp we need to create a Request object
        final Request request = new Request.Builder().url(URL_SENSOR_DATA).build();

        // An HTTP response
        final Response response = okHttpClient.newCall(request).execute();

        // Convert the MEDIA_TYPE_JSON response to List
        List<Foosball> kickerTables = null;
        if (response.isSuccessful()) {
            kickerTables = mapper.readValue(response.body().string(), new TypeReference<List<Foosball>>() {
            });
        } else {
            Log.e("OkHttp3 GET Error", response.body().string());
        }
        return kickerTables;
    }

    /**
     * This method is used to post a new user
     *
     * @return TODO -> Adapt comment
     * @throws IOException
     */
    public User postPerson(String personName) throws IOException {
        final String url = URL_USER_DATA + "/add-user";

        RequestBody formBody = new FormBody.Builder()
                .add("name", personName)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();

        final User user = mapper.readValue(response.body().string(), User.class);

        return user;
    }

    /* This method will be used in the future to post a new person. The REST API should be adapted.
    public String createPerson(User person) throws IOException {

        final String url = URL_USER_DATA + "/add-user";

        final String jsonPerson = mapper.writeValueAsString(person);

        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, jsonPerson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
    */

    /**
     * This method is used to get all users
     *
     * @return TODO -> Adapt comment
     * @throws IOException
     */
    public List<User> getUsers() throws IOException {
        final String url = URL_USER_DATA + "/users";

        final Request request = new Request.Builder().url(url).build();
        final Response response = okHttpClient.newCall(request).execute();

        List<User> users = null;
        if (response.isSuccessful()) {
            users = mapper.readValue(response.body().string(), new TypeReference<List<User>>() {
            });
        } else {
            Log.e("OkHttp3 GET error", response.body().string());
        }
        return users;
    }

    /**
     * This method is used to delete an user
     *
     * @return TODO -> Adapt comment
     * @throws IOException
     */
    public String deletePerson(User user) throws IOException {
        final String url = URL_USER_DATA + "/users";
        final String jsonUser = mapper.writeValueAsString(user);

        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, jsonUser);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
