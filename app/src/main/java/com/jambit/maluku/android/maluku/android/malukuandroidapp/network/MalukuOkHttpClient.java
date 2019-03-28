package com.jambit.maluku.android.maluku.android.malukuandroidapp.network;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.model.Foosball;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MalukuOkHttpClient {

    // The URL of the endpoints to get data from backend
    private final static String URL_SENSOR_DATA = "http://192.168.56.1:8080/api/v1/ktmock/status";
    private final static String URL_LIST_DATA = "";

    // OkHttp performs best when you create a single OkHttpClient instance and reuse it for all of your HTTP calls
    private OkHttpClient okHttpClient = new OkHttpClient();

    // The ObjectMapper provides functionality for reading and writing JSON
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * This method is used to get the current sonic sensor data
     *
     * @return A List filled with the sonic sensor data
     * @throws IOException
     */
    public List<Foosball> getSonicSensorData() throws IOException {

        // To use OkHttp we need to create a Request object
        final Request request = new Request.Builder().url(URL_SENSOR_DATA).build();

        // An HTTP response
        final Response response = okHttpClient.newCall(request).execute();

        // Convert the JSON response to List
        List<Foosball> kickerTables = null;
        if (response.isSuccessful()) {
            kickerTables = mapper.readValue(response.body().string(), new TypeReference<List<Foosball>>() {
            });
        } else {
            Log.e("OkHttp3 GET Error", response.body().string());
        }
        return kickerTables;
    }


}
