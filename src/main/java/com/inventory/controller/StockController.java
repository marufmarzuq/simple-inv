package com.inventory.controller;

import com.inventory.model.Stock;
import com.inventory.database.StockDAO;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockController {
    @FXML private ComboBox<String> actionField;
    @FXML private ComboBox<Stock> productField;
    @FXML private TextField quantityField;
    @FXML private Button saveBtn;

    @FXML private TableView<Stock> stockTable;
    @FXML private TableColumn<Stock, Integer> idColumn;
    @FXML private TableColumn<Stock, String> nameColumn;
    @FXML private TableColumn<Stock, String> categoryColumn;
    @FXML private TableColumn<Stock, Integer> stockColumn;
    @FXML private TableColumn<Stock, Double> priceColumn;
    @FXML private TableColumn<Stock, Double> totalPriceColumn;

    private int selectedProductId;

    public void refreshTable() {
        try {
            ObservableList<Stock> products = FXCollections.observableArrayList(StockDAO.getProductsWithStocks());
             stockTable.setItems(products);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void validateForm() {
        boolean isValid = actionField.getValue() != null && productField.getValue() != null && !quantityField.getText().trim().isEmpty();
        saveBtn.setDisable(!isValid);
    }

    public void updateProductField() {
        try {
            ObservableList<Stock> products = FXCollections.observableList(StockDAO.getProductsWithStocks());
            productField.getSelectionModel().clearSelection();
            productField.setItems(actionField.getValue() != null && actionField.getValue().equals("In") ? products : products.filtered(p -> p.getCurrentStock() > 0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void initialize() {
        actionField.setItems(FXCollections.observableArrayList(
                "In",
                "Out"
        ));
        updateProductField();

        productField.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedProductId = newVal.getProductId();
            }
        });

        actionField.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateProductField();
            validateForm();
        });
        productField.valueProperty().addListener((obs, oldVal, newVal) -> validateForm());
        quantityField.textProperty().addListener((obs, oldVal, newVal) -> validateForm());

        quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                quantityField.setText(oldValue);
            }
        });

        idColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getProductId()).asObject());
        nameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getProductName()));
        categoryColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getProductCategory()));
        stockColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getCurrentStock()).asObject());
        priceColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getUnitPrice()).asObject());
        totalPriceColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getCurrentStock() * d.getValue().getUnitPrice()).asObject());

        refreshTable();
    }

    @FXML
    private void handleSave() {
        String actionType = actionField.getValue();
        int qty = Integer.parseInt(quantityField.getText());

        System.out.println(actionType + " | " + selectedProductId + " | " + qty);

        try {
            StockDAO.stockInOut(actionType, selectedProductId, qty);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        clearForm();
        refreshTable();
    }

    private void clearForm() {
        actionField.setValue(null);
        productField.setValue(null);
        quantityField.clear();
    }
}
