package app;

import app.wav.Wav;
import com.sin.java.plot.PlotPanle;
import com.sin.java.plot.model.DrawableObject;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class WavController {
    private FileChooser fileChooser;
    private Stage stage;
    private SwingNode swingNode;

    public void init() {
        this.fileChooser = new FileChooser();
        //this.fileChooser.setInitialDirectory(new File("E:\\Project\\JavaProjects\\lab1\\src\\music"));
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public SwingNode getSwingNode() {
        return swingNode;
    }

    public void setSwingNode(SwingNode swingNode) {
        this.swingNode = swingNode;
    }

    @FXML
    protected void loadAudio(ActionEvent actionEvent) throws UnsupportedAudioFileException, IOException {
        File file = this.fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            Wav wav = new Wav(file);
            String[] pamss = new String[]{"-r", "-g", "-b"};
            AudioFormat format = wav.getStream().getFormat();
            PlotPanle pane = new PlotPanle();
            pane.setHoldOn(true);
            for (int i = 0; i < format.getChannels(); ++i) {
                // 获取i声道数据
                // 绘图
                pane.plot(new DrawableObject(wav.getData()[i], pamss[i % pamss.length]));
                pane.suit();
            }
            pane.setHoldOn(false);
            this.swingNode.setContent(pane);
        }
    }
}
