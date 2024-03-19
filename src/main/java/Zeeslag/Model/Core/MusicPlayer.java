package Zeeslag.Model.Core;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class MusicPlayer {
    private MediaPlayer mediaPlayer;

    public void playBackgroundMusic() {
        String audioFilePath = "resources/awesomeness.wav";
        File file = new File(audioFilePath);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
