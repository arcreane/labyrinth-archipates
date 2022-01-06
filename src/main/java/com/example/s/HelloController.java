package com.example.s;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloController implements Initializable {
    @FXML
    private Button bouton_historique;

    @FXML
    private Button bouton_jouer;

    @FXML
    private Button bouton_quitter;

    @FXML
    private Button bouton_regles;

    @FXML
    private SplitMenuButton menu_algo;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private AnchorPane lab;

    public void informationNotif(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Are you ready ?");
        alert.setHeaderText("Pensez-vous pouvoir en sortir ?");
        alert.setContentText("La partie est sur le point de commencer !! Bonne chance !");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bouton_jouer.setOnMouseClicked(btnaction -> {
           lab.setMinWidth(600);
           lab.setMinHeight(600);
        });

    }
}
