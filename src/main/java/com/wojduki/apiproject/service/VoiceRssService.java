package com.wojduki.apiproject.service;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
@Service
public class VoiceRssService {
    @Autowired
    AudioPlayService audioPlayService;
    private static final Logger LOGGER = Logger.getLogger(VoiceRssService.class.getName());

    private final OkHttpClient okHttpClient;

    public VoiceRssService(AudioPlayService audioPlayService) {
        this.okHttpClient= new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        this.audioPlayService = audioPlayService;
    }

    public boolean speakJoke(String textToVoice) throws IOException {
        LOGGER.info("speak weather"+textToVoice);

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("api.voicerss.org")
                .addQueryParameter("key", "c4153d4242c74bd6b76916e187450eb5")
                .addQueryParameter("hl", "en-us")
                .addQueryParameter("src", textToVoice)
                .build();

        Request request= new Request.Builder().url(httpUrl).build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            LOGGER.info("getVoiceResponse(...)"+ response);
            if (response!=null){
                ResponseBody responseBody= response.body();
                if (responseBody!=null){
                    InputStream inputStream= new ByteArrayInputStream(responseBody.bytes());
//                    Files.copy(inputStream, Paths.get("hello.wav"));
                    if (inputStream!= null) {
                        audioPlayService.play(inputStream);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
