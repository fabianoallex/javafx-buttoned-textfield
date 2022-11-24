package com.example;

import com.example.buttonapplier.IconButtonFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ExampleController implements Initializable  {
    @FXML private ButtonedDelayTextField buttonedDelayTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var leftCloseButton = buttonedDelayTextField.addLeftButton();
        var leftSearchButton = buttonedDelayTextField.addLeftButton();
        var rightCloseButton = buttonedDelayTextField.addRightButton();
        var rightSearchButton = buttonedDelayTextField.addRightButton();

        IconButtonFactory.closeIcon(leftCloseButton).applyIcon();
        IconButtonFactory.searchIcon(leftSearchButton).applyIcon();
        IconButtonFactory.closeIcon(rightCloseButton).applyIcon();
        IconButtonFactory.searchIcon(rightSearchButton).applyIcon();

        leftCloseButton.setOnAction(event -> {
            System.out.println("left close button click");
            buttonedDelayTextField.getTextFieldDelayApplier().textField.clear();
            buttonedDelayTextField.getTextFieldDelayApplier().textField.requestFocus();
        });

        leftSearchButton.setOnAction(event -> {
            buttonedDelayTextField.getTextFieldDelayApplier().suspend(); //stop running delays
            System.out.println("left search button click");
        });

        rightCloseButton.setOnAction(event -> {
            System.out.println("right close button click");
            buttonedDelayTextField.getTextFieldDelayApplier().setTextSilently("");
            buttonedDelayTextField.getTextFieldDelayApplier().textField.requestFocus();
        });

        rightSearchButton.setOnAction(event -> System.out.println("right search button click"));

        buttonedDelayTextField.getTextFieldDelayApplier().addDelayListener(100,
                (oldValue, newValue) ->
                        System.out.printf("Listener 1.0) --> it was \"%s\" now it is \"%s\"%n", oldValue, newValue));

        buttonedDelayTextField.getTextFieldDelayApplier().addDelayListener(100,
                (oldValue, newValue) ->
                        System.out.printf("Listener 1.1) --> it was \"%s\" now it is \"%s\"%n", oldValue, newValue));

        buttonedDelayTextField.getTextFieldDelayApplier().addDelayListener(2000, (oldValue, newValue) -> {
            System.out.printf("Listener 2.0) --> it was \"%s\" now it is \"%s\"%n", oldValue, newValue);
            //buttonedDelayTextField.getTextFieldDelayApplyer().setTextSilently(""); //setTextSilently prevent recursive changes
        });
    }
}