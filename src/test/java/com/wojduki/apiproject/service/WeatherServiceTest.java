package com.wojduki.apiproject.service;

import com.wojduki.apiproject.weather.WeatherApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    @Test
    void getResponse() throws IOException {
        //given
        WeatherService weatherService = new WeatherService();
        String url = "https://open-weather13.p.rapidapi.com/city/warsaw/PL";
        //when
        String response = weatherService.getResponse(url);
        //then
        Assertions.assertNotNull(response, "Response is NULL");
    }

    @Test
    void convert() throws IOException {
        //given
        WeatherService weatherService = new WeatherService();
        String url = "https://open-weather13.p.rapidapi.com/city/warsaw/PL";
        //when
        WeatherApiResponse weatherApiResponse = weatherService.convert(weatherService.getResponse(url));
        //then
        Assertions.assertNotNull(weatherApiResponse, "converted response is NULL");
    }

    @Test
    void weatherForecast() {
        //given
        WeatherService weatherService = new WeatherService();
        //when
        weatherService.weatherForecast();
        //then
    }
}