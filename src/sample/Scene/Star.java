package sample.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class Star {
    private Pane pane = new Pane();
    private String starColor = Color.LIGHTGOLDENRODYELLOW.toString();
    private double centerX;
    private double centerY;
    private double radius;

    public Star(Pane pane, double centerX, double centerY, double radius){
        this.pane = pane;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public void drawStar(){
        double alpfa = Math.PI*2/10;
        Line[] line = new Line[11];
        int j = -1;
        for (int i=11; i >0; i--){
            double r = radius*(i % 2 + 1)/2;
            double omega = alpfa * i;
            j++;
            line[j] = new Line();
            line[j].setStrokeWidth(3);
            line[j].setStroke(Paint.valueOf(starColor));
            if (i == 11){
                line[j].setStartX(r * Math.sin(omega) + centerX);
                line[j].setStartY(r * Math.cos(omega) + centerY);
            } else {
                line[j-1].setEndX(r * Math.sin(omega) + centerX);
                line[j-1].setEndY(r * Math.cos(omega) + centerY);
                pane.getChildren().add(line[j-1]);
                line[j].setStartX(r * Math.sin(omega) + centerX);
                line[j].setStartY(r * Math.cos(omega) + centerY);
            }
        }
    }
}
