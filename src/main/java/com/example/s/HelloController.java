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

    public void informationNotif(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Are you ready ?");
        alert.setHeaderText("Pensez-vous pouvoir en sortir ?");
        alert.setContentText("La partie est sur le point de commencer !! Bonne chance !");
        alert.showAndWait();
    }

    public void quitterNotif(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Oh bah non.... =( ");
        alert.setHeaderText("Vous nous quittez déja ?");
        alert.setContentText("Vous êtes sur le point de nous quitter, êtes vous sur ?");
        alert.showAndWait();
    }

    public void reglesNotif(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Règles du Jeu");
        alert.setHeaderText("Voici les règles du jeu Archi-Laby");
        alert.setContentText("A COMPLETER !!! A COMPLETER !!! A COMPLETER!!!");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bouton_jouer.setOnMouseClicked(btnaction -> {
           
        });

    }
}
