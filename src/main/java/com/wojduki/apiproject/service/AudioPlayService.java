package com.wojduki.apiproject.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
@Service
public class AudioPlayService {
    private static final Logger LOGGER = Logger.getLogger(AudioPlayService.class.getName());
    private static final int BUFFER_SIZE = 4096;

    void play(InputStream inputStream) {
        LOGGER.info("play sound");
        try {
//            InputStream inputStream = getClass().getClassLoader()
//                    .getResourceAsStream(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

            AudioFormat audioFormat = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

            LOGGER.info("Playback Started.");

            byte[] bufferBytes = new byte[BUFFER_SIZE];
            int readBytes = -1;
            while ((readBytes = audioStream.read(bufferBytes)) != -1) {
                sourceDataLine.write(bufferBytes, 0, readBytes);
            }
            sourceDataLine.drain();
            sourceDataLine.close();
            audioStream.close();

            LOGGER.info("Playback has been finished.");

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to play sound", ex);
            LOGGER.info("Error occured during playback process:" + ex.getMessage());
        }
        LOGGER.info("play(...)");
    }

//        String audioFilePath = "AudioFileWithWavFormat.wav";
//
//        // Clip can not play mpeg/mp3 format audio. We'll get exception if we run with below commented mp3 and mpeg format audio.
//        // String audioFilePath = "AudioFileWithMpegFormat.mpeg";
//        // String audioFilePath = "AudioFileWithMp3Format.mp3";
//
//        SoundPlayerUsingSourceDataLine player = new SoundPlayerUsingSourceDataLine();
//        player.play(audioFilePath);
//    }
}
