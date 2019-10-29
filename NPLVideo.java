import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

public class NPLVideo extends Application {

    private File file;
    //String fileURL;

    boolean isPlaying = false;

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();

        VBox hBox = new VBox(10);

        MenuBar menuBar = new MenuBar();

        FileChooser fileChooser = new FileChooser();

        Menu menuFirst = new Menu("Media");
        Menu menuSecond = new Menu("Tools");
        Menu menuThird = new Menu("Help");
        Menu menu4 = new Menu("About");

        MenuItem menuItem1 = new MenuItem("Open new File");
        MenuItem menuItem2 = new MenuItem("Network Stream");
        MenuItem menuItem3 = new MenuItem("Open Muitipel Files");

        menuFirst.getItems().addAll(menuItem1, menuItem2, menuItem3);

        LoadVideo load = new LoadVideo();

        menuItem1.setOnAction(e -> {
            if (isPlaying == true) {
                load.stopMediaPlayer();
                file = fileChooser.showOpenDialog(new Stage());
                //fileURL = file.toURI().toString();
                pane.setCenter(load.loadVideo(file));
            } else {
                isPlaying = true;
                file = fileChooser.showOpenDialog(new Stage());
                //fileURL = file.toURI().toString();
                pane.setCenter(load.loadVideo(file));
            }
        });
        Network nt = new Network();



        menuItem2.setOnAction(e -> {
            if (isPlaying == true) {
                load.stopMediaPlayer();
                pane.setCenter(nt.getNet());
            } else {
                isPlaying = true;
                pane.setCenter(nt.getNet());
            }
        });
        /*nt.tn.setOnAction(e -> {
            if (isPlaying == true) {
                load.stopMediaPlayer();
                pane.setCenter(load.loadVideo(nt.getFile()));
            } else {
                isPlaying = true;
                pane.setCenter(load.loadVideo(nt.getFile()));
            }
        });*/
        MenuItem menuItem4 = new MenuItem("Controls");
        MenuItem menuItem5 = new MenuItem("Media");

        menuThird.getItems().addAll(menuItem4, menuItem5);

        Controls control = new Controls();

        menuItem4.setOnAction(e -> {
            pane.setCenter(control.scrollPaneControl());
        });

        /*pane.setOnDragOver((final DragEvent event) -> {
            mouseDragOver(event);
        });*/

        pane.setOnDragDropped((DragEvent event) -> {
            pane.setCenter(load.loadVideo(mouseDragDropped(event)));
        });

        menuBar.getMenus().addAll(menuFirst, menuSecond, menuThird, menu4);
        hBox.getChildren().add(menuBar);
        pane.setTop(hBox);

        Scene scene = new Scene(pane, 850, 600);
        primaryStage.setTitle("wingxel.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

   /* private void mouseDragOver(final DragEvent e) {
        final Dragboard db = e.getDragboard();

        final boolean isAccepted
                = db.getFiles().get(0).getName().toLowerCase().endsWith(".mp4");

        if (db.hasFiles()) {
            if (isAccepted) {
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            e.consume();
        }
    }*/

    private File mouseDragDropped(DragEvent e) {
        final Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            // Only get the first file from the list
            file = db.getFiles().get(0);

        }
        e.setDropCompleted(success);
        e.consume();

        return file;
    }

}
