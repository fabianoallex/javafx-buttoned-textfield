package com.example;

public class ButtonedDelayTextField extends ButtonedTextField {
    private final TextFieldDelayApplier textFieldDelayApplyer;

    public ButtonedDelayTextField() {
        textFieldDelayApplyer = new TextFieldDelayApplier(this.getTextField());
    }

    public TextFieldDelayApplier getTextFieldDelayApplier() {
        return textFieldDelayApplyer;
    }
}

