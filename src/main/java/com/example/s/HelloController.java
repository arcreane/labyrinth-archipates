package com.example.s;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
// import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


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

    @FXML private Text seconde;

    @FXML private Text minute;

    @FXML
    private Button bouton_solution;

    @FXML
    private SplitMenuButton bouton_difficulte;

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
        alert.setContentText("Pour commencer à jouer, sélectionnez votre DIFFICULTE et appuyer sur le bouton JOUER. Ainsi un labyrinthe aléatoire sera généré et il vous sera possible de tracer votre chemin en survolant les cases avec le curseur de la souris. Le départ et l'arrivée seront repéré par la couleur bleue. Le temps de réalisation sera chronométré dés le départ, jusqu'au franchissement de la ligne d'arrivée. Il est possible de voir la solution en cliquant sur le bouton SOLUTION dans le menu.");
        alert.showAndWait();
    }

    GridPane gridPane = new GridPane();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bouton_historique.setOnMouseClicked(btnaction -> {
            System.out.println("MEEEEEHHHHHHHHHHHHHH");
        });

        bouton_jouer.setOnMouseClicked(btnaction -> {

            windowLaby.setPrefWidth(800);
            Image image = new Image("File:ressources/image/carre.png");

            for (int i = 0; i < HelloApplication.totalColumn; i++) {
                for (int j = 0; j < HelloApplication.totalRow; j++) {
                    gridPane.add(new ImageView(image), i, j);
                }
            }
            HboxLaby.getChildren().addAll(gridPane);
        });

        gridPane.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, evt -> {
            
        });

        bouton_historique.setOnMouseClicked(btnAction -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                int secondes = 0;
                int minutes = 0;
                @Override
                public void run()
                {
                    seconde.setText(String.valueOf(secondes));
                    minute.setText(String.valueOf(minutes));
                    secondes++;
                    if (secondes == 60)
                    {
                        secondes = 0;
                        minutes++;
                    }
                }

            }, 1000, 1000);
        });
    }
}