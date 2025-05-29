package com.inventory.controller;

import com.inventory.database.ProductDAO;
import com.inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProductController {
    @FXML private TextField nameField;
    @FXML private TextField categoryField;
    @FXML private TextArea descriptionField;
    @FXML private TextField unitPriceField;
    @FXML private TableView<Product> productTable;

    @FXML private TableColumn<Product, Integer> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> categoryColumn;
    @FXML private TableColumn<Product, Double> unitPriceColumn;

    private Product selectedProduct = null;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        categoryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory()));
        unitPriceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getUnitPrice()).asObject());

        refreshTable();
    }

    public void refreshTable() {
        try {
            ObservableList<Product> products = FXCollections.observableArrayList(ProductDAO.getAllProducts());
            productTable.setItems(products);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleSave() {
        String name = nameField.getText();
        String category = categoryField.getText();
        String description = descriptionField.getText();
        double price = Double.parseDouble(unitPriceField.getText());

        if (selectedProduct == null) {
            Product newProduct = new Product(name, category, description, price);
            try {
                ProductDAO.addProduct(newProduct);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            selectedProduct.setName(name);
            selectedProduct.setCategory(category);
            selectedProduct.setDescription(description);
            selectedProduct.setUnitPrice(price);
            try {
                ProductDAO.updateProduct(selectedProduct);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        clearForm();
        refreshTable();
    }

    @FXML
    private void handleEdit() {
        selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            nameField.setText(selectedProduct.getName());
            categoryField.setText(selectedProduct.getCategory());
            descriptionField.setText(selectedProduct.getDescription());
            unitPriceField.setText(String.valueOf(selectedProduct.getUnitPrice()));
        }
    }

    @FXML
    private void handleDelete() {
        Product product = productTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            try {
                ProductDAO.deleteProduct(product.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            refreshTable();
        }
    }

    private void clearForm() {
        nameField.clear();
        categoryField.clear();
        descriptionField.clear();
        unitPriceField.clear();
        selectedProduct = null;
    }
}
