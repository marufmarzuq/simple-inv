package com.example.simpleinv;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    private final String[] greetings = {
            "Welcome to JavaFX Application!",
            "Hello World!",
            "Welcome to Simple Inv World!",
            "Keep coding!",
            "You are doing awesome!"
    };

    private int currIndex = 0;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(greetings[currIndex]);
        currIndex = (currIndex + 1) % greetings.length;
    }
}