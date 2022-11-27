package com.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ExampleController implements Initializable  {
    @FXML
    private SearchField<CepDisplay> searchField1;
    @FXML
    private SearchField<CepDisplay> searchField2;
    @FXML
    private SearchField<CepDisplay> searchField3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SearchField.SearchListner<CepDisplay> searchListner = (searchField, searchText) -> {
            if (searchText.isEmpty()) {
                System.out.println("aqui poderá ser implementada a busca por uma tela.");
            } else {
                searchField.setValue(new CepDisplay(Cep.createCep(searchText)));
            }
        };

        SearchField.ClearListner<CepDisplay> clearListner = (searchField, oldValue) -> {
            if (oldValue == null)
                return;

            System.out.printf("o Cep %s foi removido.%n", oldValue.cep.getCep());
        };

        searchField1.getTextField().promptTextProperty().set("Informe o cep.");
        searchField2.getTextField().promptTextProperty().set("Informe o cep.");
        searchField3.getTextField().promptTextProperty().set("Informe o cep");
        searchField1.setSearchListner(searchListner);
        searchField2.setSearchListner(searchListner);
        searchField3.setSearchListner(searchListner);
        searchField1.setClearListner(clearListner);
        searchField2.setClearListner(clearListner);
        searchField3.setClearListner(clearListner);
    }
}

class CepDisplay {
    Cep cep;
    CepDisplay(Cep cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "[%s - %s/%s - %s]".formatted(cep.getCep(), cep.getLocalidade(), cep.getUf(), cep.getLogradouro());
    }
}