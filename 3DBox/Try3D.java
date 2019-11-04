/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package try3d;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Camera;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Box;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

/**
 *
 * @author reuben
 */
public class Try3D extends Application {
    
    private static final int WIDTH = 600;
    private static final int HEIGHT = 450;

    @Override
    public void start(Stage primaryStage) {

        MyGroup group = new MyGroup();
        
        Box box = new Box(100, 20, 50);
        
        group.getChildren().add(box);

        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.CYAN);
        
        /*group.translateYProperty().set(HEIGHT / 2);
        group.translateXProperty().set(WIDTH / 2);*/
        group.translateZProperty().set(-100);
        
        group.translateXProperty().bind(primaryStage.widthProperty().divide(2));
        group.translateYProperty().bind(primaryStage.heightProperty().divide(2));
        
        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);
        
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch(event.getCode()){
                case W:
                    group.translateZProperty().set(group.getTranslateZ() + 100);
                    break;
                case S:
                    group.translateZProperty().set(group.getTranslateZ() - 100);
                    break;
                case NUMPAD4:
                    group.rotateByX(10);
                    break;
                case NUMPAD5:
                    group.rotateByX(-10);
                    break;
                case NUMPAD6:
                    group.rotateByY(10);
                    break;
                case NUMPAD8:
                    group.rotateByY(-10);
                    break;
                case NUMPAD7:
                    group.rotateByZ(10);
                    break;
                case NUMPAD9:
                    group.rotateByZ(-10);
                    break;
                default:
                    break;
            }
        });
        
        primaryStage.setTitle("Box Rotation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class MyGroup extends Group {

        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int angle) {
            r = new Rotate(angle, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().add(t);
        }

        void rotateByY(int angle) {
            r = new Rotate(angle, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().add(t);
        }

        void rotateByZ(int angle) {
            r = new Rotate(angle, Rotate.Z_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().add(t);
        }
    }

}
