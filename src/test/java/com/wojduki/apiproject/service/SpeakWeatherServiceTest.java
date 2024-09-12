package com.wojduki.apiproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class SpeakWeatherServiceTest {

    @Test
    void speakWeatherForecast() throws IOException {
        //given
        AudioPlayService audioPlayService = new AudioPlayService();
        VoiceRssService voiceRssService = new VoiceRssService(audioPlayService);
        WeatherService weatherService = new WeatherService();
        SpeakWeatherService speakWeatherService = new SpeakWeatherService(weatherService, voiceRssService);
        //when
        boolean spoken = speakWeatherService.speakWeatherForecast();
        //then
        Assertions.assertTrue(spoken, "Joke not spoken");
    }
}