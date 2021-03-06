package com.example.s;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private Text seconde;

    @FXML
    private Text minute;

    @FXML
    private Button bouton_solution;

    @FXML
    private SplitMenuButton bouton_difficulte;

    @FXML
    private Label notif;

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

        bouton_jouer.setOnMouseClicked(btnaction -> { // bouton pour start la génération du labyrinthe 
            HboxLaby.getChildren().removeAll(gridPane); 
            HelloApplication.board.clear();
            HelloApplication.startRow = (int)(0 + (Math.random() * ((HelloApplication.totalRow - 1) - 0 + 1)));
            HelloApplication.endRow = (int)(0 + (Math.random() * ((HelloApplication.totalRow - 1) - 0 + 1))); 
            HelloApplication.mazeGeneration(HelloApplication.board);
            gridPane.getChildren().removeAll(gridPane.getChildren());
            windowLaby.setPrefWidth(800);
            boolean up, down, left, right;
            int iteration = 99999;
            notif.setVisible(true);
            String text;
            boolean willBreak = false;
            for (int column = 0; column < HelloApplication.totalColumn; column++) {
                for (int row = 0; row < HelloApplication.totalRow; row++) {
                    Node node = HelloApplication.board.get(column).get(row);
                    up = node.isLinkedUp(HelloApplication.board, column, row);
                    down = node.isLinkedDown(HelloApplication.board, column, row);
                    left = node.isLinkedLeft(HelloApplication.board, column, row);
                    right = node.isLinkedRight(HelloApplication.board, column, row);
                    
                    if((column == 0 && row == HelloApplication.startRow) || (column == HelloApplication.totalColumn-1 && row == HelloApplication.endRow)) text = "image/start-end";
                    else text = "image";

                    Image image;
                    if (up && down && right && left) {
                        image = new Image("File:ressources/"+text+"/carre_all.png"); // 4 intersection
                    }

                    else if (!up && !down && right && left) {
                        image = new Image("File:ressources/"+text+"/carre_horizontal_pass.png"); // intersection
                                                                                              // horizontale
                    } else if (up && down && !right && !left) {
                        image = new Image("File:ressources/"+text+"/carre_vertical_pass.png"); // intersection verticale
                    }

                    else if (!up && down && right && !left) {
                        image = new Image("File:ressources/"+text+"/carre_down_right.png"); // coude -> haut a gauche
                    } else if (!up && down && !right && left) {
                        image = new Image("File:ressources/"+text+"/carre_left_down.png"); // coude -> haut a droite
                    } else if (up && !down && right && !left) {
                        image = new Image("File:ressources/"+text+"/carre_right_up.png"); // coude -> bas a gauche
                    } else if (up && !down && !right && left) {
                        image = new Image("File:ressources/"+text+"/carre_up_left.png"); // coude -> bas a droite
                    }

                    else if (!up && !down && right && !left) {
                        image = new Image("File:ressources/"+text+"/carre_right.png"); // fin droite
                    } else if (!up && !down && !right && left) {
                        image = new Image("File:ressources/"+text+"/carre_left.png"); // fin gauche
                    } else if (up && !down && !right && !left) {
                        image = new Image("File:ressources/"+text+"/carre_up.png"); // fin haut
                    } else if (!up && down && !right && !left) {
                        image = new Image("File:ressources/"+text+"/carre_down.png"); // fin bas
                    }

                    else if (down && right && left && !up) {
                        image = new Image("File:ressources/"+text+"/carre_right_left_down.png"); // 3 branches -> bas
                    } else if (up && right && left && !down) {
                        image = new Image("File:ressources/"+text+"/carre_right_up_left.png"); // 3 branches -> haut
                    } else if (up && down && left && !right) {
                        image = new Image("File:ressources/"+text+"/carre_up_left_down.png"); // 3 branches -> gauche
                    } else if (up && down && right && !left) {
                        image = new Image("File:ressources/"+text+"/carre_down_right_up.png"); // 3 branches -> droite
                    }

                    else {
                        image = new Image("File:ressources/image/carre.png"); // pas d'intersection
                    }

                    gridPane.add(new ImageView(image), column, row); // Ajout de nos images dans la grille
                    iteration++;
                    if (iteration == 1) {
                        willBreak = true;
                        break;
                    }
                }
                if (willBreak)
                    break;
            }
            HboxLaby.getChildren().addAll(gridPane);
            
           
        });

        gridPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, evt -> { //Event pour tracer le chemin
            notif.setVisible(false); // Désactive le label de notification après que l'on commence
            boolean up, down, left, right;
            double x = evt.getX();
            double y = evt.getY();
            double width = gridPane.getWidth();

            double widthCol = width / HelloApplication.totalColumn;

            int xPos = (int) (x / widthCol);
            int yPos = (int) (y / widthCol);

            Node node = HelloApplication.board.get(xPos).get(yPos);
            up = node.isLinkedUp(HelloApplication.board, xPos, yPos);
            down = node.isLinkedDown(HelloApplication.board, xPos, yPos);
            left = node.isLinkedLeft(HelloApplication.board, xPos, yPos);
            right = node.isLinkedRight(HelloApplication.board, xPos, yPos);

            Image image;
            if (up && down && right && left) {
                image = new Image("File:ressources/image/bad/carre_all.png"); // 4 intersection
            }

            else if (!up && !down && right && left) {
                image = new Image("File:ressources/image/bad/carre_horizontal_pass.png"); // intersection horizontale
            } else if (up && down && !right && !left) {
                image = new Image("File:ressources/image/bad/carre_vertical_pass.png"); // intersection verticale
            }

            else if (!up && down && right && !left) {
                image = new Image("File:ressources/image/bad/carre_down_right.png"); // coude -> haut a gauche
            } else if (!up && down && !right && left) {
                image = new Image("File:ressources/image/bad/carre_left_down.png"); // coude -> haut a droite
            } else if (up && !down && right && !left) {
                image = new Image("File:ressources/image/bad/carre_right_up.png"); // coude -> bas a gauche
            } else if (up && !down && !right && left) {
                image = new Image("File:ressources/image/bad/carre_up_left.png"); // coude -> bas a droite
            }

            else if (!up && !down && right && !left) {
                image = new Image("File:ressources/image/bad/carre_right.png"); // fin droite
            } else if (!up && !down && !right && left) {
                image = new Image("File:ressources/image/bad/carre_left.png"); // fin gauche
            } else if (up && !down && !right && !left) {
                image = new Image("File:ressources/image/bad/carre_up.png"); // fin haut
            } else if (!up && down && !right && !left) {
                image = new Image("File:ressources/image/bad/carre_down.png"); // fin bas
            }

            else if (down && right && left && !up) {
                image = new Image("File:ressources/image/bad/carre_right_left_down.png"); // 3 branches -> bas
            } else if (up && right && left && !down) {
                image = new Image("File:ressources/image/bad/carre_right_up_left.png"); // 3 branches -> haut
            } else if (up && down && left && !right) {
                image = new Image("File:ressources/image/bad/carre_up_left_down.png"); // 3 branches -> gauche
            } else if (up && down && right && !left) {
                image = new Image("File:ressources/image/bad/carre_down_right_up.png"); // 3 branches -> droite
            }

            else {
                image = new Image("File:ressources/image/bad/carre.png"); // pas d'intersection
            }

            gridPane.add(new ImageView(image), xPos, yPos);
        });

        bouton_historique.setOnMouseClicked(btnAction -> { //bouton historique
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

        bouton_solution.setOnMouseClicked(btnAction -> {
            ArrayList<Node> itineraire = new ArrayList<>();
            HelloApplication.pathFinding(HelloApplication.board, 0, HelloApplication.startRow, HelloApplication.totalColumn - 1, HelloApplication.endRow, itineraire);
            boolean up, down, left, right;
            int column, row;
            Image image;
            for (Node node : itineraire) {
                column = node.getColumn();
                row = node.getRow();
                up = node.isLinkedUp(HelloApplication.board, column, row);
                down = node.isLinkedDown(HelloApplication.board, column, row);
                left = node.isLinkedLeft(HelloApplication.board, column, row);
                right = node.isLinkedRight(HelloApplication.board, column, row);

                if (up && down && right && left) {
                    image = new Image("File:ressources/image/good/carre_all.png"); // 4 intersection
                }

                else if (!up && !down && right && left) {
                    image = new Image("File:ressources/image/good/carre_horizontal_pass.png"); // intersection
                                                                                               // horizontale
                } else if (up && down && !right && !left) {
                    image = new Image("File:ressources/image/good/carre_vertical_pass.png"); // intersection verticale
                }

                else if (!up && down && right && !left) {
                    image = new Image("File:ressources/image/good/carre_down_right.png"); // coude -> haut a gauche
                } else if (!up && down && !right && left) {
                    image = new Image("File:ressources/image/good/carre_left_down.png"); // coude -> haut a droite
                } else if (up && !down && right && !left) {
                    image = new Image("File:ressources/image/good/carre_right_up.png"); // coude -> bas a gauche
                } else if (up && !down && !right && left) {
                    image = new Image("File:ressources/image/good/carre_up_left.png"); // coude -> bas a droite
                }

                else if (!up && !down && right && !left) {
                    image = new Image("File:ressources/image/good/carre_right.png"); // fin droite
                } else if (!up && !down && !right && left) {
                    image = new Image("File:ressources/image/good/carre_left.png"); // fin gauche
                } else if (up && !down && !right && !left) {
                    image = new Image("File:ressources/image/good/carre_up.png"); // fin haut
                } else if (!up && down && !right && !left) {
                    image = new Image("File:ressources/image/good/carre_down.png"); // fin bas
                }

                else if (down && right && left && !up) {
                    image = new Image("File:ressources/image/good/carre_right_left_down.png"); // 3 branches -> bas
                } else if (up && right && left && !down) {
                    image = new Image("File:ressources/image/good/carre_right_up_left.png"); // 3 branches -> haut
                } else if (up && down && left && !right) {
                    image = new Image("File:ressources/image/good/carre_up_left_down.png"); // 3 branches -> gauche
                } else if (up && down && right && !left) {
                    image = new Image("File:ressources/image/good/carre_down_right_up.png"); // 3 branches -> droite
                }

                else {
                    image = new Image("File:ressources/image/good/carre.png"); // pas d'intersection
                }

                gridPane.add(new ImageView(image), column, row);
            }
        });
    };

}