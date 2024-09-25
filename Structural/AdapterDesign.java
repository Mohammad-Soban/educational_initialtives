package Structural;

interface MediaPlayer{
    public void play(String type, String Name);
}

class AdvanceMediaPlayer{
    public void playVLC(String fileName){
        System.out.println("playing VLC media player");
    }

    public void playMP3(String fileName){
        System.out.println("Playing MP3 type of file");
    }

    public void playMP4(String fileName){
        System.out.println("Playing MP4 type of file");
    }

    public void playAVI(String fileName){
        System.out.println("Playing AVI type of file");
    }
}

class MediaAdapter implements MediaPlayer{
    AdvanceMediaPlayer adv = new AdvanceMediaPlayer();

    public void play(String type, String fileName){
        if (type.equalsIgnoreCase("VLC")) {
            adv.playVLC(fileName);
        } 
        
        else if(type.equalsIgnoreCase("MP3")){
            adv.playMP3(fileName);
        }

        else if(type.equalsIgnoreCase("MP4")){
            adv.playMP4(fileName);
        }

        else{
            System.out.println("Incompatilble media player");
        }
    }
}

class AudioPlayer implements MediaPlayer{
    @Override
    public void play(String type, String Name) {
        MediaPlayer mp = new MediaAdapter();
        mp.play(type, Name);
    }
}

public class AdapterDesign {
    public static void main(String[] args) {
        MediaPlayer m = new AudioPlayer();
		m.play("vlc", "music1.vlc");
		m.play("mp3", "music2.mp3");
		m.play("mp4", "music3.mp4");
    }    
}
