package com.inventory.controller;

import com.inventory.model.Product;
import com.inventory.database.ProductDAO;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ReportController {
//    @FXML private TextField nameField;
//    @FXML private ComboBox<String> categoryField;
//    @FXML private TextArea descriptionField;
//    @FXML private TextField unitPriceField;
//    @FXML private Button saveBtn;
//
//    @FXML private TableView<Product> productTable;
//    @FXML private TableColumn<Product, Integer> idColumn;
//    @FXML private TableColumn<Product, String> nameColumn;
//    @FXML private TableColumn<Product, String> categoryColumn;
//    @FXML private TableColumn<Product, Double> unitPriceColumn;
//    @FXML private TableColumn<Product, Void> actionColumn;
//
//    private Product selectedProduct = null;
//
//    public void refreshTable() {
//        try {
//            ObservableList<Product> products = FXCollections.observableArrayList(ProductDAO.getAllProducts());
//            productTable.setItems(products);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void validateForm() {
//        boolean isValid =
//                !nameField.getText().trim().isEmpty() &&
//                        categoryField.getValue() != null &&
//                        !unitPriceField.getText().trim().isEmpty();
//
//        saveBtn.setDisable(!isValid);
//    }
//
//
//    @FXML
//    public void initialize() {
//        categoryField.setItems(FXCollections.observableArrayList(
//                "Electronics",
//                "Grocery",
//                "Clothing",
//                "Stationery",
//                "Furniture",
//                "Home Appliances",
//                "Toys",
//                "Sports",
//                "Books",
//                "Health & Beauty"
//        ));
//
//        nameField.textProperty().addListener((obs, oldVal, newVal) -> validateForm());
//        categoryField.valueProperty().addListener((obs, oldVal, newVal) -> validateForm());
//        unitPriceField.textProperty().addListener((obs, oldVal, newVal) -> validateForm());
//
//        unitPriceField.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue.matches("\\d*(\\.\\d*)?")) {
//                unitPriceField.setText(oldValue);
//            }
//        });
//
//        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
//        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
//        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
//        unitPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getUnitPrice()).asObject());
//
//        actionColumn.setCellFactory(col -> new TableCell<>() {
//            private final Button editButton = new Button("Edit");
//            private final Button deleteButton = new Button("Del");
//            private final HBox actionBox = new HBox(5, editButton, deleteButton);
//
//            {
//                editButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
//                deleteButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
//
//                editButton.setOnAction(e -> {
//                    Product product = getTableView().getItems().get(getIndex());
//                    productTable.getSelectionModel().select(product);
//                    handleEdit();
//                });
//
//                deleteButton.setOnAction(e -> {
//                    Product product = getTableView().getItems().get(getIndex());
//                    productTable.getSelectionModel().select(product);
//                    handleDelete();
//                });
//            }
//
//            @Override
//            protected void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    HBox actionBox = new HBox(10, editButton, deleteButton);
//                    actionBox.setAlignment(Pos.CENTER);
//                    StackPane wrapper = new StackPane(actionBox);
//                    wrapper.setAlignment(Pos.CENTER);
//                    setGraphic(wrapper);
//                }
//            }
//        });
//
//        refreshTable();
//    }
//
//    @FXML
//    private void handleSave() {
//        String name = nameField.getText();
//        String category = categoryField.getValue();
//        String description = descriptionField.getText();
//        double price = Double.parseDouble(unitPriceField.getText());
//
//        System.out.println(name + " | " + category + " | " + description + " | " + price);
//
//        if (selectedProduct == null) {
//            Product newProduct = new Product(name, category, description, price);
//            try {
//                ProductDAO.addProduct(newProduct);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            selectedProduct.setName(name);
//            selectedProduct.setCategory(category);
//            selectedProduct.setDescription(description);
//            selectedProduct.setUnitPrice(price);
//            try {
//                ProductDAO.updateProduct(selectedProduct);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        clearForm();
//        refreshTable();
//    }
//
//    @FXML
//    private void handleEdit() {
//        selectedProduct = productTable.getSelectionModel().getSelectedItem();
//        if (selectedProduct != null) {
//            nameField.setText(selectedProduct.getName());
//            categoryField.setValue(selectedProduct.getCategory());
//            descriptionField.setText(selectedProduct.getDescription());
//            unitPriceField.setText(String.valueOf(selectedProduct.getUnitPrice()));
//        }
//    }
//
//    @FXML
//    private void handleDelete() {
//        Product product = productTable.getSelectionModel().getSelectedItem();
//        if (product != null) {
//            try {
//                ProductDAO.deleteProduct(product.getId());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            refreshTable();
//        }
//    }
//
//    private void clearForm() {
//        nameField.clear();
//        categoryField.setValue(null);
//        descriptionField.clear();
//        unitPriceField.clear();
//        selectedProduct = null;
//    }
}
