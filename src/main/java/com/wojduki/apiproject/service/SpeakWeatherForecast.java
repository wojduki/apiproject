package com.wojduki.apiproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class SpeakWeatherForecast {
    private static final Logger LOGGER = Logger.getLogger(SpeakWeatherForecast.class.getName());
    @Autowired
    WeatherService weatherService;
    @Autowired
    VoiceRssService voiceRssService;

    public SpeakWeatherForecast(WeatherService weatherService, VoiceRssService voiceRssService) {
        this.weatherService = weatherService;
        this.voiceRssService = voiceRssService;
    }
    public boolean speakWeatherForecast() throws IOException {
        LOGGER.info("speak forecast");
        String value = weatherService.weatherForecast().getBase();
        boolean spoken = voiceRssService.speakWeather(value);
        LOGGER.info("end of forecast");
        return spoken;
    }
}
