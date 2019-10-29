import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class LoadVideo {

    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

    public LoadVideo() {
    }

    public BorderPane loadVideo(File file) {

        BorderPane pane = new BorderPane();

        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);

        Button playButton = new Button(">");
        playButton.setOnAction(e -> {
            if (playButton.getText().equals(">")) {
                mediaPlayer.play();
                playButton.setText("||");
            } else {
                mediaPlayer.pause();
                playButton.setText(">");
            }
        });

        Button rewindButton = new Button("<<");
        rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));
        Slider slVolume = new Slider();
        slVolume.setPrefWidth(150);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(50);
        mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100));
        mediaPlayer.setCycleCount(0);


        Label timeLabel = new Label("Time: ");

        Slider timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);

        HBox hBoxC = new HBox(10);
        hBoxC.setAlignment(Pos.CENTER);
        hBoxC.getChildren().addAll(playButton, rewindButton,
                new Label("Volume"), slVolume, timeLabel, timeSlider);

        mediaView.fitHeightProperty().bind(pane.heightProperty().divide(1.2));
        mediaView.fitWidthProperty().bind(pane.widthProperty().divide(1));


        pane.setCenter(mediaView);
        pane.setBottom(hBoxC);
        pane.setStyle("-fx-background-color: black");

        return pane;
    }

    public void stopMediaPlayer() {
        this.mediaPlayer.stop();
    }

}
