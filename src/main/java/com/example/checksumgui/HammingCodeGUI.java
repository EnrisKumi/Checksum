package com.example.checksumgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HammingCodeGUI extends Application {

    private TextField inputField;
    private Label outputLabel;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label inputLabel = new Label("Enter the data word:");
        grid.add(inputLabel, 0, 1);

        inputField = new TextField();
        grid.add(inputField, 1, 1);

        Label outputLabel = new Label();
        grid.add(outputLabel, 1, 2);

        Button btn = new Button("Generate Hamming Code");
        btn.setOnAction(event -> {
            String str = inputField.getText();
            int M = str.length();
            int r = 1;

            while (Math.pow(2, r) < (M + r + 1)) {
                r++;
            }
            int[] ar = generateCode(str, M, r);

            StringBuilder sb = new StringBuilder();
            ar = calculation(ar, r);
            for (int i = 1; i < ar.length; i++) {
                sb.append(ar[i]);
            }

            outputLabel.setText("Generated hamming code: " + sb.toString());
        });
        grid.add(btn, 1, 3);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hamming Code Generator");
        primaryStage.show();
    }

    // calculating value of redundant bits
    private int[] calculation(int[] ar, int r) {
        for (int i = 0; i < r; i++) {
            int x = (int) Math.pow(2, i);
            for (int j = 1; j < ar.length; j++) {
                if (((j >> i) & 1) == 1) {
                    if (x != j)
                        ar[x] = ar[x] ^ ar[j];
                }
            }
        }
        return ar;
    }

    private int[] generateCode(String str, int M, int r) {
        int[] ar = new int[r + M + 1];
        int j = 0;
        for (int i = 1; i < ar.length; i++) {
            if ((Math.ceil(Math.log(i) / Math.log(2))
                    - Math.floor(Math.log(i) / Math.log(2)))
                    == 0) {
                ar[i] = str.charAt(j++) - '0';
            }
        }
        return ar;
    }

    public static void main(String[] args) {
        launch(args);
    }

}