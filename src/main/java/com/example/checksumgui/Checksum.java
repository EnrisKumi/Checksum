package com.example.checksumgui;

import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Checksum extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label inputLabel = new Label("Enter the input string: ");
        TextField inputField = new TextField();
        gridPane.add(inputLabel, 0, 0);
        gridPane.add(inputField, 1, 0);

        Label dataLabel = new Label("Enter the data for sending to the receiver: ");
        TextField dataField = new TextField();
        gridPane.add(dataLabel, 0, 1);
        gridPane.add(dataField, 1, 1);

        Label checksumLabel = new Label("Enter the checksum for sending to the receiver: ");
        TextField checksumField = new TextField();
        gridPane.add(checksumLabel, 0, 2);
        gridPane.add(checksumField, 1, 2);

        Button generateButton = new Button("Generate Checksum");
        Button receiveButton = new Button("Receive");
        gridPane.add(generateButton, 0, 3);
        gridPane.add(receiveButton, 1, 3);

        generateButton.setOnAction(event -> {
            String input = inputField.getText();
            int checkSum = generateCheckSum(input);
            System.out.println("The generated checksum is: " + Integer.toHexString(checkSum));
        });

        receiveButton.setOnAction(event -> {
            String input = dataField.getText();
            int checkSum = Integer.parseInt(checksumField.getText(), 16);
            receive(input, checkSum);
        });

        Scene scene = new Scene(gridPane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static int generateCheckSum(String s) {
        String hexadecimalValue = new String();
        int x, i, checkSum = 0;

        for (i = 0; i < s.length() - 2; i = i + 2) {
            x = (int) (s.charAt(i));
            hexadecimalValue = Integer.toHexString(x);

            x = (int) (s.charAt(i + 1));
            hexadecimalValue = hexadecimalValue + Integer.toHexString(x);
            checkSum = checkSum + Integer.parseInt(hexadecimalValue, 16);
        }
        if (s.length() % 2 != 0) {
            x = (int) (s.charAt(i));
            hexadecimalValue = Integer.toHexString(x);
            checkSum = checkSum + Integer.parseInt(hexadecimalValue, 16);
        }

        checkSum = (65535 - checkSum) + 1;
        return checkSum;
    }

    static void receive(String data, int checkSum) {
        int generatedCheckSum = generateCheckSum(data);
        if (generatedCheckSum == checkSum) {
            System.out.println("The received data is correct.");
        } else {
            System.out.println("The received data is incorrect.");
        }
    }
}





