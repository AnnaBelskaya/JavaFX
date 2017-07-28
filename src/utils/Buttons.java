package utils;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.TextAlignment;

public class Buttons {
    public Button addButton(String text, int x, int y,
                             int W, int H, boolean enabled) {
        Button button = new Button();
        button.setText(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setFont(Font.font("Verdana", FontPosture.REGULAR, 11));
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setMinSize(H, W);
        button.setDisable(!enabled);
        return button;
    }
    public Button addCircleButton(String text, int x, int y, double r, double minW){
        Button button = new Button();
        button.setText(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setFont(Font.font("Verdana", FontPosture.REGULAR, 11));
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setShape(new Circle(r));
        button.setMinSize(minW, r);
        return button;
    }
}