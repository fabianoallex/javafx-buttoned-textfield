package com.example;

import com.example.buttonapplier.IconFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

enum SearchFieldMode {DISPLAY, SEARCH}

public class SearchField<T> extends ButtonedDelayTextField {
    public interface SearchListner<T> {
        void onSearch(SearchField<T> searchField, String searchText);
    }

    public interface ClearListner<T> {
        void onClose(SearchField<T> searchField, T oldValue);
    }

    private final TextField textField = this.getTextField();
    private SearchFieldMode mode = SearchFieldMode.SEARCH;
    private SearchListner<T> searchListner;
    private ClearListner<T> clearListner;
    private T value;
    private T oldValue;

    public SearchField() {
        Button searchButton = this.addRightButton();
        Button clearButton = this.addRightButton();
        IconFactory.searchIcon(searchButton).applyIcon();
        IconFactory.clearIcon(clearButton).applyIcon();

        this.getTextFieldDelayApplier().addDelayListener(1000, (oldValue, newValue) -> {
            if (newValue.isEmpty())
                return;

            if (searchListner != null)
                searchListner.onSearch(this, newValue);

            setMode(this.value == null ? SearchFieldMode.SEARCH : SearchFieldMode.DISPLAY);
        });

        clearButton.setOnAction(event -> {
            if (value == null && this.oldValue != null) {
                setValue(oldValue);
                return;
            }

            oldValue = value;
            setValue(null);

            if (clearListner != null)
                clearListner.onClose(this, oldValue);
        });

        searchButton.setOnAction(event -> {
            this.getTextFieldDelayApplier().suspend();

            if (!this.mode.equals(SearchFieldMode.SEARCH))
                return;

            if (searchListner == null)
                return;

            searchListner.onSearch(this, this.textField.getText());
        });

        update();
    }

    public void setClearListner(ClearListner<T> clearListner) {
        this.clearListner = clearListner;
    }

    public void setSearchListner(SearchListner<T> searchListner) {
        this.searchListner = searchListner;
    }

    private void setMode(SearchFieldMode mode) {
        if (this.mode.equals(mode))
            return;

        this.mode = mode;

        update();
    }

    private void update() {
        if (this.mode.equals(SearchFieldMode.DISPLAY)) {
            this.textField.setEditable(false);
        } else {
            this.textField.setEditable(true);
            this.textField.requestFocus();
        }
        this.getTextFieldDelayApplier().setTextSilently(value == null ? "" : value.toString());
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
        setMode(this.value == null ? SearchFieldMode.SEARCH : SearchFieldMode.DISPLAY);
        update();
    }
}
