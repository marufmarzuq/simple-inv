package com.inventory.controller;

import com.inventory.database.StockLogDAO;
import com.inventory.model.StockLog;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class ReportController {
    @FXML private DatePicker fromDateField;
    @FXML private DatePicker toDateField;
    @FXML private Button filterBtn;
    @FXML private Button resetBtn;

    @FXML private TableView<StockLog> reportTable;
    @FXML private TableColumn<StockLog, String> nameColumn;
    @FXML private TableColumn<StockLog, String> categoryColumn;
    @FXML private TableColumn<StockLog, String> actionTypeColumn;
    @FXML private TableColumn<StockLog, Integer> quantityColumn;
    @FXML private TableColumn<StockLog, LocalDateTime> dateColumn;

    public void refreshTable(String fromDate, String toDate) {
        try {
            ObservableList<StockLog> logs = FXCollections.observableArrayList(StockLogDAO.getLogs(fromDate, toDate));
            reportTable.setItems(logs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void validateForm() {
        boolean isValid = fromDateField.getValue() != null && toDateField.getValue() != null;
        filterBtn.setDisable(!isValid);
    }

    @FXML
    public void initialize() {
        fromDateField.valueProperty().addListener((obs, oldVal, newVal) -> validateForm());
        toDateField.valueProperty().addListener((obs, oldVal, newVal) -> validateForm());

        nameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getProductName()));
        categoryColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getProductCategory()));
        actionTypeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getActionType()));
        quantityColumn.setCellValueFactory(d ->new SimpleIntegerProperty(d.getValue().getQuantity()).asObject());
        dateColumn.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getDate()));

        refreshTable(null, null);
    }

    @FXML
    private void handleFilter() {
        String fromDate = fromDateField.getValue().toString();
        String toDate = toDateField.getValue().toString();
        filterBtn.setDisable(true);
        resetBtn.setDisable(false);

        refreshTable(fromDate, toDate);
    }

    @FXML
    private void handleReset() {
        fromDateField.setValue(null);
        toDateField.setValue(null);
        filterBtn.setDisable(true);
        resetBtn.setDisable(true);

        refreshTable(null, null);
    }
}
