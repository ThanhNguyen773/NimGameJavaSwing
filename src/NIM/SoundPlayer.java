package NIM;

import javax.sound.sampled.*;
import java.io.*;

public class SoundPlayer {
    private Clip clip;
    private boolean playing;

    public SoundPlayer(String filePath) {
        // Cách thức xử lý
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            playing = false;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSound() {
        if (clip != null) {
            clip.setFramePosition(0); // Đặt vị trí của clip về đầu
            clip.start(); // Bắt đầu phát âm thanh
            playing = true;
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Dừng phát âm thanh nếu đang phát
            playing = false;
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
