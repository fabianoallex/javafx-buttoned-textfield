package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextFieldDelayApplier extends TextFieldApplier {
    private final HashMap<Long, TimelineDelay> timelineHashMap = new HashMap<>();
    private String silentlyText = "";

    private final ChangeListener<String> changeListener = (observable, oldValue, newValue) -> {
        if (!silentlyText.equals(newValue)) {
            timelineHashMap.forEach((aLong, timelineDelay) -> timelineDelay.update(newValue));
            silentlyText = newValue;
        }
    };

    public void addDelayListener(long delay, DelayListener listener) {
        timelineHashMap
                .computeIfAbsent(delay, aLong -> new TimelineDelay(delay))
                .getDelayListenerList()
                .add(listener);
    }

    public void suspend() {
        timelineHashMap.forEach((aLong, timelineDelay) -> timelineDelay.suspend());
    }

    public TextFieldDelayApplier(TextField textField) {
        this(textField, true);
    }

    public TextFieldDelayApplier(TextField textField, boolean enableDelayEvent) {
        super(textField);
        if (enableDelayEvent) {
            enableDelayEvent();
        } else {
            disableDelayEvent();
        }
    }

    public void enableDelayEvent() {
        this.textField.textProperty().addListener(changeListener);
    }

    public void disableDelayEvent() {
        this.textField.textProperty().removeListener(changeListener);
    }

    public void setTextSilently(String text) {
        silentlyText = text;
        this.textField.setText(text);
    }
}

class TimelineDelay {
    private final long delay;
    private final List<DelayListener> delayListenerList = new ArrayList<>();
    private String oldText = "";
    private Timeline timeline = null;

    TimelineDelay(long delay) {
        this.delay = delay;
    }

    void update(String newValue) {
        if (delayListenerList.isEmpty()) return;
        if (timeline != null) timeline.stop();

        timeline = new Timeline(
                new KeyFrame(Duration.millis(delay), event -> {
                    delayListenerList.forEach(delayListener -> delayListener.onDelayFinish(oldText, newValue));
                    oldText = newValue;
                })
        );

        timeline.setCycleCount(1);
        timeline.play();
    }

    List<DelayListener> getDelayListenerList() {
        return delayListenerList;
    }

    public void suspend() {
        if (timeline != null) timeline.stop();
    }
}