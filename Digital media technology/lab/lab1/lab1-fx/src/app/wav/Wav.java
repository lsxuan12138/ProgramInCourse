package app.wav;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Wav {
    private AudioInputStream stream;
    private double[][] data;

    public Wav() {
    }
    public Wav(File file) throws UnsupportedAudioFileException, IOException {
        this.stream = AudioSystem.getAudioInputStream(file);
        AudioFormat format = stream.getFormat();
        long len = stream.getFrameLength();
        data = new double[format.getChannels()][(int) len];
        boolean isBigEndian = format.isBigEndian();

        for (int i = 0; i < len; i++) {
            byte[] tmp = new byte[format.getFrameSize()];
            if(stream.read(tmp)<=0)throw new RuntimeException();
            for (int j = 0; j < format.getChannels(); j++) {
                int unit = format.getFrameSize()/ format.getChannels();
                byte[] tmp2 = new byte[unit];
                System.arraycopy(tmp,j*unit,tmp2,0,unit);
                data[j][i]=bytesToDouble(tmp2,isBigEndian);
            }
        }
    }
    public static double bytesToDouble(byte[] bytes,boolean isBigEndian){
        long l = 0;
        if(isBigEndian){
            for (byte aByte : bytes) {
                l = l << 8;
                l += aByte;
            }
        }else {
            for (int i = bytes.length-1; i >=0; i--) {
                l=l<<8;
                l+=bytes[i];
            }
        }
        return l;
    }
    public AudioInputStream getStream() {
        return stream;
    }

    public void setStream(AudioInputStream stream) {
        this.stream = stream;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }
}
