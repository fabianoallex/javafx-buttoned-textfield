package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ButtonedTextField extends AnchorPane implements Initializable {
    private final ArrayList<Button> rightButtons = new ArrayList<>();
    private final ArrayList<Button> leftButtons = new ArrayList<>();
    private int rightPadding = 5;
    private int leftPadding = 5;
    @FXML private TextField textField;

    public ButtonedTextField(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buttoned-text-field.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ignored) {

        }
    }

    private Button createButton() {
        Button button = new Button();
        button.minWidth(18);
        button.prefHeight(20);
        button.prefWidth(28);

        this.getChildren().add(button);

        setBottomAnchor(button, 3.0);
        setTopAnchor(button, 3.0);

        return button;
    }

    public Button addRightButton() {
        rightButtons.forEach(button -> setRightAnchor(button, getRightAnchor(button) + 30));

        Button button = createButton();
        setRightAnchor(button, 3.0);

        rightButtons.add(button);

        this.rightPadding = (int) rightButtons.size() * 30 + 3;

        textField.setStyle("-fx-padding: 5 %d 5 %d;".formatted(this.rightPadding, this.leftPadding));

        return button;
    }

    public Button addLeftButton() {
        Button button = createButton();
        setLeftAnchor(button, 3.0 + 30 * (long) leftButtons.size());

        leftButtons.add(button);

        this.leftPadding = leftButtons.size() * 30 + 6;

        textField.setStyle("-fx-padding: 5 %d 5 %d;".formatted(this.rightPadding, this.leftPadding));

        return button;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ArrayList<Button> getRightButtons() {
        return rightButtons;
    }

    public ArrayList<Button> getLeftButtons() {
        return leftButtons;
    }

    public TextField getTextField() {
        return textField;
    }
}
