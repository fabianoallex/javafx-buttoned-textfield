package com.example.buttonapplier;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;

public abstract class ButtonIconApplier extends ButtonApplier {
    public ButtonIconApplier(Button button) {
        super(button);
    }

    protected void applyIcon(Button button, ArrayList<SVGPath> paths) {
        Group svg = new Group();
        svg.getChildren().addAll(paths);

        Bounds bounds = svg.getBoundsInParent();
        double scale = Math.min(16 / bounds.getWidth(), 16 / bounds.getHeight());
        svg.setScaleX(scale);
        svg.setScaleY(scale);

        button.setGraphic(svg);
        button.setMaxSize(30, 30);
        button.setMinSize(30, 30);
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    protected SVGPath createPath(String d) {
        SVGPath path = new SVGPath();
        path.getStyleClass().add("svg");
        path.setContent(d);
        return path;
    }

    public abstract void applyIcon();
}

