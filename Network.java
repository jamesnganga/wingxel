import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.File;

public class Network {

        
    private File file;
    Button tn;
    public Network(){
    }


    public VBox getNet(){
        VBox vBox = new VBox(10);
        TextField tf = new TextField();
        Label lbl = new Label("Enter Video URL");
        Button ty = new Button("Execute");
        LoadVideo lod = new LoadVideo();

        ty.setOnAction(e -> {
            String url = tf.getText();
            file = new File(url);
        });

        tn = new Button("Play");

        vBox.getChildren().addAll(lbl, tf, ty, tn);

        return vBox;
    }

    public File getFile(){
        return file;
    }
}
