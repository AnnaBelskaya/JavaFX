package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Scene.SnowField;

public class Main extends Application {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setTitle("Let it snow!");
        primaryStage.setResizable(false);
        new SnowField(primaryStage);
}

    public static void main(String[] args) {
        launch(args);
    }
}