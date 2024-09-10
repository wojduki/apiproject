package com.wojduki.apiproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class VoiceRssServiceTest {

    @Test
    void speakWeather() throws IOException {
        //given
        AudioPlayService audioPlayService= new AudioPlayService();
        VoiceRssService voiceRssService = new VoiceRssService(audioPlayService);
        //when
        boolean spoken = voiceRssService.speakWeather("Hello Weather!");
        //then
        Assertions.assertTrue(spoken, "Spoken is NOT TRUE");
    }
}