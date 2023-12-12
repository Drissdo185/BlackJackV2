import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SE {

    static Clip clip;

    public static void setFile(String soundFileName){

        try{
            File file = new File(soundFileName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        }
        catch(Exception e){

        }
    }

    public static void play(){

        clip.setFramePosition(0);
        clip.start();
    }
    public static void stop() {

        clip.stop();
    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

}
