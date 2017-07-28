package utils;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import sample.Main;

public class Elements {
    private final int X = 180;

    private Pane pane = new Pane();

    public Elements(Pane pane){
        this.pane = pane;
    }

    public Pane addElements(){
        pane.getChildren().addAll(addLabel(10,15, "Circles amount:"),
                addLabel(10,45, "Min radius:"),
                addLabel(10,75, "Max radius:"),
                addLabel(60,210, "SNOWMAN"),
                addLabel(70,310, "STAR"),
                addLabel(5,335, "Star center coordinates:"),
                addLabel(95,365, "x:"),
                addLabel(95,395, "y:"),
                addLabel(61,425, "Radius:"),
                addVerticalSeparator(),
                addHorizontalSeparator(300),
                addHorizontalSeparator(200),
                addHorizontalSeparator(500));
        return pane;
    }

    private Label addLabel(int x, int y, String text) {
        Label label1 = new Label(text);
        label1.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
        label1.setTranslateX(x);
        label1.setTranslateY(y);
        return label1;
    }

    private Separator addVerticalSeparator() {
        Separator separator = new Separator();
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setTranslateX(X);
        separator.setMinHeight(Main.HEIGHT);
        return separator;
    }

    private Separator addHorizontalSeparator(int y) {
        Separator separator = new Separator();
        separator.setMinWidth(X);
        separator.setTranslateY(y);
        return separator;
    }

    public TextField addTextField(String text, double x, double y){
        TextField tf = new TextField();
        tf.setPromptText(text);
        tf.setMaxSize(60,10);
        tf.setTranslateX(x);
        tf.setTranslateY(y);
        pane.getChildren().add(tf);
        return tf;
    }
}