/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import try3d.MyGroup; 

public class Program extends Application {
    
    private static final int WIDTH = 650;
    private static final int HEIGHT = 450;
    
    @Override
    public void start(Stage primaryStage) {
        
        MyGroup group = new MyGroup();
        
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.BURLYWOOD);
        
        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);
        
        Cylinder cylinder = new Cylinder();
        cylinder.setHeight(100);
        cylinder.setRadius(50);
        
        group.getChildren().add(cylinder);
        
        group.translateXProperty().bind(primaryStage.widthProperty().divide(2));
        group.translateYProperty().bind(primaryStage.heightProperty().divide(2));
        group.translateZProperty().set(-100);
        
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
                case NUMPAD1:
                    group.rotateByY(10);
                    break;
                case NUMPAD2:
                    group.rotateByY(-10);
                    break;
                case NUMPAD7:
                    group.rotateByZ(10);
                    break;
                case NUMPAD8:
                    group.rotateByZ(-10);
                    break;
                default:
                    break;
            }
        });
        
        primaryStage.setTitle("Cylinder Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
