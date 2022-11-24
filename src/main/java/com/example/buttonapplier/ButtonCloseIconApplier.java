package com.example.buttonapplier;

import javafx.scene.control.Button;
import javafx.scene.shape.SVGPath;
import java.util.ArrayList;
import java.util.List;

public class ButtonCloseIconApplier extends ButtonIconApplier {
    private static final List<String> stringPaths = List.of(
                "M25,25.5c-0.1,0-0.3,0-0.4-0.1l-10-10c-0.2-0.2-0.2-0.5,0-0.7s0.5-0.2,0.7,0l10,10c0.2,0.2,0.2,0.5,0,0.7 C25.3,25.5,25.1,25.5,25,25.5z",
                "M15,25.5c-0.1,0-0.3,0-0.4-0.1c-0.2-0.2-0.2-0.5,0-0.7l10-10c0.2-0.2,0.5-0.2,0.7,0s0.2,0.5,0,0.7l-10,10 C15.3,25.5,15.1,25.5,15,25.5z"
            );


    public ButtonCloseIconApplier(Button button) {
        super(button);
    }

    @Override
    public void applyIcon() {
        ArrayList<SVGPath> paths = new ArrayList<>();
        paths.add(createPath(stringPaths.get(0)));
        paths.add(createPath(stringPaths.get(1)));
        applyIcon(button, paths);
    }
}


