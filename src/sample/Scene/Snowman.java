package sample.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import sample.Main;

public class Snowman {
    public Circle[] body = new Circle[3];
    public Circle[] eyes = new Circle[2];
    public Line[] nose = new Line[2];
    private String colorWhite = "#FFFFFF";
    private String colorRed = "#FF0000";

    public void drawBody(Pane pane){
        double radius = 90;
        double x = Main.WIDTH/2+50;
        double y =  Main.HEIGHT-30 - radius;
        for (int i = 0; i < 3; i++) {
            if (i > 0){
                radius/=1.5;
                y = body[i-1].getCenterY() - body[i-1].getRadius() - radius;
                body[i] = new Circle(x, y, radius,
                        Paint.valueOf(colorWhite));

            } else {
                body[i] = new Circle(x, y, radius,
                        Paint.valueOf(colorWhite));
            }
            pane.getChildren().add(body[i]);
        }
        drawEyes(pane);
        drawNose(pane);
        drawHands(pane);
    }
    private void drawEyes(Pane pane){
        double r = body[2].getRadius()/8;
        double x = body[2].getCenterX() - body[2].getRadius()/3;
        double y = body[2].getCenterY() - body[2].getRadius()/3;
        eyes[0] = new Circle(x,y,r);
        x = body[2].getCenterX() + body[2].getRadius()/3;
        eyes[1] = new Circle(x,y,r);
        pane.getChildren().addAll(eyes[0],eyes[1]);
    }

    private void drawNose(Pane pane){
        nose[0] = prepareNose();
        nose[1] = prepareNose();
        nose[0].setStartY(nose[0].getStartY() + 10);
        pane.getChildren().addAll(nose[0], nose[1]);
    }

    private Line prepareNose(){
        Line line = new Line();
        double x = body[2].getCenterX()+3;
        double y = body[2].getCenterY();
        line.setStartX(x);
        line.setStartY(y);
        line.setEndX(x+20);
        line.setEndY(y+10);
        line.setStroke(Paint.valueOf(colorRed));
        line.setStrokeWidth(3);
        return line;
    }

    private void drawHands(Pane pane){
        Line rightHand = new Line();
        Line leftHand = new Line();
        double r = body[1].getRadius();
        double x = body[1].getCenterX();
        double y = body[1].getCenterY();

        //right hand
        rightHand.setStartX(x+r);
        rightHand.setStartY(y);
        rightHand.setEndX(x+100);
        rightHand.setEndY(y-100);
        rightHand.setStrokeWidth(3);

        //left hand
        leftHand.setStartX(x-r);
        leftHand.setStartY(y);
        leftHand.setEndX(x-100);
        leftHand.setEndY(y-100);
        leftHand.setStrokeWidth(3);
        pane.getChildren().addAll(leftHand, rightHand);
    }

    public void paintGradient(){
        Stop[] stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.GREY)};
        LinearGradient lg1 = new LinearGradient(0, 1, 0, 0, true,
                CycleMethod.NO_CYCLE, stops);
        stops = new Stop[] { new Stop(0, Color.GRAY), new Stop(1, Color.web("#DCDCDC"))};
        LinearGradient lg2 = new LinearGradient(0, 1, 0, 0, true,
                CycleMethod.NO_CYCLE, stops);
        stops = new Stop[] { new Stop(0, Color.web("#DCDCDC")), new Stop(1, Color.WHITE)};
        LinearGradient lg3 = new LinearGradient(0, 1, 0, 0, true,
                CycleMethod.NO_CYCLE, stops);

        body[0].setFill(lg1);
        body[1].setFill(lg2);
        body[2].setFill(lg3);
    }
}