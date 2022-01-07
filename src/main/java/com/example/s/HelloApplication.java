package com.example.s;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.io.IOException;

public class HelloApplication extends Application {

    public static int totalColumn, totalRow;
    public static int startRow, endRow;
    public static ArrayList<ArrayList<Node>> board = new ArrayList<>();

    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void pathFinding(ArrayList<ArrayList<Node>> board, int startColumn, int startRow, int endColumn, int endRow, ArrayList<Node> itineraire)
    {
        ArrayList<Node> processingNodes = new ArrayList<>(), nextNodesToProcess = new ArrayList<>(); // liste des nodes en traitement // liste des nodes voisines qui doivent etre traitees
        boolean finalNodeReached = false;

        processingNodes.add(board.get(startColumn).get(startRow));
        board.get(startColumn).get(startRow).setLastNode(board.get(startColumn).get(startRow));

        while(processingNodes.size() != 0 && !finalNodeReached)
        {
            for(int i=0; i < processingNodes.size(); i++) // node
            {
                Node activeNode = processingNodes.get(i);
                ArrayList<Integer> nodeLink = activeNode.getLink();

                for(int nearNodeId : nodeLink) // liaison de la node
                {
                    if(activeNode.getLastNode().getId() == nearNodeId) continue;
                    nextNodesToProcess.add(board.get(nearNodeId / totalColumn).get(nearNodeId % totalColumn));
                    board.get(nearNodeId / totalColumn).get(nearNodeId % totalColumn).setLastNode(activeNode);
                    if(nearNodeId / totalColumn == endColumn && nearNodeId % totalColumn == endRow)
                    {
                        finalNodeReached = true;
                        break;
                    }
                }
                if(finalNodeReached) break;
            }
            
            processingNodes.clear();
            processingNodes.addAll(nextNodesToProcess);
            nextNodesToProcess.clear();
        }

        Node currentNode = board.get(endColumn).get(endRow);
        do
        {
            itineraire.add(currentNode);
            currentNode = currentNode.getLastNode();
        }while(currentNode.getId() != itineraire.get(itineraire.size()-1).getId());
        Collections.reverse(itineraire);
    }

    public static void mazeGeneration(ArrayList<ArrayList<Node>> board)
    {
        int nodeId = 0;
        for(int i = 0; i < totalColumn; i++)
        {
            ArrayList<Node> temp = new ArrayList<>();
            for(int j = 0; j < totalRow; j++)
            {
                temp.add(new Node(nodeId, i, j));
                nodeId++;
            }
            board.add(temp);
        }

        int randId = (int)(0 + (Math.random() * ((totalColumn * totalRow) - 0 + 1))); // nombre aléatoire entre 0 et le nombre de de noeuds sur le plateau

        int nodeColumn = randId / totalColumn;
        int nodeRow = randId % totalColumn;

        board.get(nodeColumn).get(nodeRow).setColor(0);

        List<Node> processingNodes = new ArrayList<>(); // node pas encore traiter, mais en cour de traitement
        processingNodes.add(board.get(nodeColumn).get(nodeRow));

        while(processingNodes.size() != 0)
        {
            int targetNodeIndex = (int)(0 + (Math.random() * ((processingNodes.size() - 1) - 0 + 1)));
            Node activeNode = processingNodes.get(targetNodeIndex);
            processingNodes.remove(targetNodeIndex);
            activeNode.generateLink(board, processingNodes); 
        }
    }
    
    public static void main(String[] args) {
        totalColumn = 30; // Colonne de notre labyrinthe & grille
        totalRow = 30;  // Ligne de notre labyrinthe & grille
        launch();
    }
}