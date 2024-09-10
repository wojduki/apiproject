package com.wojduki.apiproject.service;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AudioPlayServiceTest {

    @Test
    void play() {
        //given
        AudioPlayService audioPlayService = new AudioPlayService();
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("files/hello.wav");
        //when
        audioPlayService.play(inputStream);
        //then

    }
}