package com.example.buttonapplier;

import javafx.scene.control.Button;

public class ButtonIconApplierFactory {
    public static ButtonSearchIconApplier searchIcon(Button button) {
        return new ButtonSearchIconApplier(button);
    }

    public static ButtonCloseIconApplier closeIcon(Button button) {
        return new ButtonCloseIconApplier(button);
    }
}
