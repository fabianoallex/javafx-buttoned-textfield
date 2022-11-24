package com.example.buttonapplier;

import javafx.scene.control.Button;

public class IconFactory {
    public static SearchIcon searchIcon(Button button) {
        return new SearchIcon(button);
    }

    public static CloseIcon closeIcon(Button button) {
        return new CloseIcon(button);
    }
}
