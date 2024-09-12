package com.wojduki.apiproject.service;

import com.google.gson.Gson;
import com.wojduki.apiproject.weather.WeatherApiResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class WeatherService {
    private static final Logger LOGGER = Logger.getLogger(WeatherService.class.getName());
    private static final String API_URL = "https://open-weather13.p.rapidapi.com/city/london/EN";
    private OkHttpClient okHttpClient = new OkHttpClient();

    public WeatherApiResponse weatherForecast() {
        LOGGER.info("weatherForecast");
        try {
            String responseBody= getResponse(API_URL);
            WeatherApiResponse weatherApiResponse= convert(responseBody);
            LOGGER.info("weatherForecast"+weatherApiResponse);
            return weatherApiResponse;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to connect to API", e);
//            throw new RuntimeException(e);
        }
        LOGGER.info("weatherForecast(...)" + null);
        return null;
    }

    public String getResponse(String url) throws IOException {
        LOGGER.info("getResponse(" + url + ")");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", "4d02b60f69msh02846cdf112f661p1bdc7ajsn9580b3f27d98")
                .addHeader("x-rapidapi-host", "open-weather13.p.rapidapi.com")
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            String body = response.body().string();
            LOGGER.info("getResponse(...)" + body);
            return body;
        }
    }

    //convert from json
    public WeatherApiResponse convert(String responseBody) {
        LOGGER.info("convert body(" + responseBody + ")koniec body");
        Gson gson = new Gson();
        WeatherApiResponse weatherApiResponse = gson.fromJson(responseBody, WeatherApiResponse.class);
        LOGGER.info("converted" + weatherApiResponse);
        return weatherApiResponse;
    }
}

