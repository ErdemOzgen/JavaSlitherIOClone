import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;




public class Music {
    private File f;
    private AudioInputStream sound;
    private DataLine.Info info;
    private Clip clip;
    private FloatControl gainControl;


    private static Music instance = null;


    public void playMusic(){
        try{
            URL url = getClass().getResource("music.wav");
            sound = AudioSystem.getAudioInputStream(url);

            // 
            info = new DataLine.Info(Clip.class, sound.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(sound);


            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e){
            System.out.println("Exception occurred while playing music:");
            e.printStackTrace();
        }
    }


    public void stopMusic(){
        clip.stop();
    }





    public static Music getInstance(){
        if (instance == null) instance = new Music();
        return instance;
    }

}