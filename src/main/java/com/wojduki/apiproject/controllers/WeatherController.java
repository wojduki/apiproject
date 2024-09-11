package com.wojduki.apiproject.controllers;

import com.wojduki.apiproject.service.VoiceRssService;
import com.wojduki.apiproject.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.util.logging.Logger;

@Controller
public class WeatherController {
    private static final Logger LOGGER= Logger.getLogger(WeatherController.class.getName());
    @Autowired
    WeatherService weatherService;
    @Autowired
    VoiceRssService voiceRssService;

    public WeatherController(WeatherService weatherService, VoiceRssService voiceRssService) {
        this.weatherService = weatherService;
        this.voiceRssService = voiceRssService;
    }
    @GetMapping
    public String randomJoke(Model model) throws IOException {
        LOGGER.info("weather forecast");
        String value = weatherService.weatherForecast().toString();
        model.addAttribute("weatherForecast", value);
        voiceRssService.speakWeather(value);
        LOGGER.info("weather forecast(...)"+ value);
        return "apiproject";
    }
}
