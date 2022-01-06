package com.example.s;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelloController implements Initializable {
    @FXML
    private HBox HboxLaby;

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
    private ScrollPane windowLaby;

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
        bouton_historique.setOnMouseClicked(btnaction -> {
            System.out.println("MEEEEEHHHHHHHHHHHHHH");
        });

        bouton_jouer.setOnMouseClicked(btnaction -> {
            windowLaby.setPrefWidth(800);
            GridPane gridPane = new GridPane();
            for (int i = 0; i <HelloApplication.totalColumn; i++) {
                for (int j = 0; j < HelloApplication.totalRow; j++) {
                    gridPane.add(new Label("meh" + (i+j)), i, j);
                }
            }
            HboxLaby.getChildren().addAll(gridPane);
        });
    }

}
