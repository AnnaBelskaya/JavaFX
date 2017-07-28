package sample.Scene;

import sample.Main;
import utils.Buttons;
import utils.Elements;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class SnowField {
    private Pane pane = new Pane();
    private Scene scene = new Scene(pane);
    private Snowman snowman = new Snowman();
    private Star star;
    private Elements elements = new Elements(pane);
    private Circle[] circles;

    public SnowField(Stage primaryStage){
        createInterface();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createInterface(){
        pane.setStyle("-fx-background: #87CEEB;");
        Buttons b = new Buttons();

        Button buildButton = new Buttons().addCircleButton("Build\ntower",20,100,
                50, 50);
        Button ruinButton = new Buttons().addCircleButton("Ruin\ntower",100,100,
                50,50);
        Button colorRedButton = b.addButton("Make the tower red",25,160,
                20,110,true);
        Button createSnowman = b.addButton("Create\nsnowman",10,240,
                50, 70, true);
        Button addGradient = b.addButton("Add\ndirt",100,240,
                50, 70, false);
        Button createStar = b.addButton("Create star",35,460,
                20,110,true);
        Button nightMode = b.addCircleButton("Night\nmode",40,510,50, 100);

        TextField tf1 = elements.addTextField("1-10", 110,10);
        TextField tf2 = elements.addTextField("1", 110, 40);
        TextField tf3 = elements.addTextField("50", 110, 70);
        TextField centerX = elements.addTextField("300-600", 110, 360);
        TextField centerY = elements.addTextField(">R", 110, 390);
        TextField starRadius = elements.addTextField("1-100", 110, 420);

        buildButton.setOnMouseClicked(event -> {
            clearScreen();
            try {
                buildTower(Integer.parseInt(tf1.getText()),
                        Integer.parseInt(tf2.getText()),
                        Integer.parseInt(tf3.getText()));
            } catch (Exception e) { }
        });

        ruinButton.setOnMouseClicked(event -> {
            clearScreen();
        });

        colorRedButton.setOnMouseClicked(event -> {
            makeAllCirclesRed();
        });

        createSnowman.setOnMouseClicked(event -> {
           clearScreen();
           snowman.drawBody(pane);
           addGradient.setDisable(false);
        });

        addGradient.setOnMouseClicked(event -> {
            snowman.paintGradient();
        });

        createStar.setOnMouseClicked(event -> {
            clearScreen();
            try {
            star = new Star(pane, Double.parseDouble(centerX.getText()),
                    Double.parseDouble(centerY.getText()),
                    Double.parseDouble(starRadius.getText()));
                star.drawStar();
            } catch (NumberFormatException e){ }
        });

        nightMode.setOnMouseClicked(event -> {
            if (nightMode.getText().equals("Night\nmode")){
                pane.setStyle("-fx-background: #301095;");
                nightMode.setText("Day\nmode");
            } else {
                nightMode.setText("Night\nmode");
                pane.setStyle("-fx-background: #27408B;");
            }
        });

        pane.getChildren().addAll(buildButton, ruinButton,
                colorRedButton,createSnowman,addGradient, nightMode,
                createStar);
        elements.addElements();
    }

    private Color getColor(){
        Random rand = new Random();
        return Color.color(rand.nextDouble(),
                rand.nextDouble(),
                rand.nextDouble(),
                0.7f);
    }

    private void buildTower(int n, int min, int max){
        circles = new Circle[n+3];
        for (int i = 0; i < n; i++){
           int radius = ThreadLocalRandom.current().nextInt(min,max+1);
           if (i == 0) {
               float y =  Main.HEIGHT-30-radius;
               circles[i] = new Circle(Main.WIDTH/2+50, y, radius,
                       Paint.valueOf(getColor().toString()));
           } else {
               double y = circles[i-1].getCenterY() - circles[i-1].getRadius() - radius;
               circles[i] = new Circle(Main.WIDTH/2+50, y, radius,
                       Paint.valueOf(getColor().toString()));
           }
           pane.getChildren().add(circles[i]);
       }
        addNose();
        addEyes();
    }

    private void addNose(){
        int n = circles.length-3;
        double x = circles[n-1].getCenterX();
        double y = circles[n-1].getCenterY() + circles[n-1].getRadius()/8;
        double r = circles[n-1].getRadius()/10;
        circles[n] = new Circle(x,y,r);
        pane.getChildren().add(circles[n]);
    }

    private void addEyes(){
        int n = circles.length-3;
        double r = circles[n-1].getRadius()/8;
        double x = circles[n-1].getCenterX() - circles[n-1].getRadius()/3;
        double y = circles[n-1].getCenterY() - circles[n-1].getRadius()/3;
        circles[n+1] = new Circle(x,y,r);
        x = circles[n-1].getCenterX() + circles[n-1].getRadius()/3;
        circles[n+2] = new Circle(x,y,r);
        pane.getChildren().addAll(circles[n+1],circles[n+2]);
    }

    private void makeAllCirclesRed(){
        try {
            for (int i = 0; i < circles.length; i++) {
                circles[i].setFill(Paint.valueOf("#FF0000")); }
        } catch (NullPointerException e){
        }
    }

    private void clearScreen(){
        try {
            pane.getChildren().removeAll(circles);
        } catch (NullPointerException e){ }
        try {
            pane.getChildren().removeAll(snowman.body);
            pane.getChildren().removeAll(snowman.nose);
            pane.getChildren().removeAll(snowman.eyes);
        } catch (NullPointerException e){ }
        try {
            pane.getChildren().removeAll(star.line);
        } catch (NullPointerException e){ }
    }
}