package com.example.buttonapplier;

import javafx.scene.control.Button;

public class IconButtonFactory {
    public static SearchIconApplier searchIcon(Button button) {
        return new SearchIconApplier(button);
    }

    public static CloseIconApplier closeIcon(Button button) {
        return new CloseIconApplier(button);
    }
}
