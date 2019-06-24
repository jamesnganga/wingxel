import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.io.File;

public class VideoPlayer extends Application{
	//private static final String MEDIA_URL =
	 //"http://reuben-hp-probook-6570b/reuben/img/musicmatters_year_2018.mp4";
	@Override //Override the start method defined in Applixation class
	public void start(Stage primaryStage){
		Music msc = new Music();
		Media media = new Media(new File(msc.getPath()).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView(mediaPlayer);
		

		Button playButton = new Button(">");
		playButton.setOnAction(e -> {
			if (playButton.getText().equals(">")) {
				mediaPlayer.play();
				playButton.setText("||");
			}else{
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
		

		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(playButton, rewindButton, new Label("Volume"), slVolume);

		BorderPane pane = new BorderPane();
		pane.setCenter(mediaView);
		pane.setBottom(hBox);

		mediaView.fitHeightProperty().bind(pane.heightProperty().divide(1.2));
        mediaView.fitWidthProperty().bind(pane.widthProperty().divide(1));

		//Create a scene and place it on the stage
		Scene scene = new Scene(pane, 650, 500);
		primaryStage.setTitle("MediaDemo");//Set the stage title
		primaryStage.setScene(scene);//Palce the scene on the stage
		primaryStage.show();//Display the stage.
	}
}
class Music {

    private String path;

    public Music() {
        this.path = "/home/reuben/Documents/AllNight.mp4";
    }

    public void setPath(String s) {
        this.path = s;
    }

    public String getPath() {
        return path;
    }
}

