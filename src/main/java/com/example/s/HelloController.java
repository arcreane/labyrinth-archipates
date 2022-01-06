package com.example.s;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


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
    private ScrollPane windowLaby;

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

    @FXML private Text seconde;

    @FXML private Text minute;

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
            windowLaby.setPrefHeight(600);
            windowLaby.setPrefWidth(600);
           lab.setPrefWidth(600);
           lab.setPrefHeight(600);
           System.out.println(windowLaby.getWidth());
           System.out.println(lab.getWidth());
           
        });
        bouton_historique.setOnMouseClicked(btnAction ->{
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                int secondes = 0;
                int minutes = 0;
                @Override
                public void run() {
                    seconde.setText(String.valueOf(secondes));
                    minute.setText(String.valueOf(minutes));
                    secondes++;
                    if (secondes == 60) {
                        secondes = 0;
                        minutes++;
                    }

                }

            }, 1000, 1000);
        });
    }
}
