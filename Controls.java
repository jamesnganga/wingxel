import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class Controls {

   public Controls(){}

   public ScrollPane scrollPaneControl(){
       ScrollPane scrollControl = new ScrollPane();

       String info = "Click Media the Open new file to open a video file\n"
               + "Or drag a media file into the pane to play a video file\n"
               + "Click || to pause the video\n"
               + "Click >> to return the video to the starting point\n"
               + "Click or drag the slider to adjust the volume\n";

       scrollControl.setContent(new Label(info));

       return scrollControl;
   }
}
