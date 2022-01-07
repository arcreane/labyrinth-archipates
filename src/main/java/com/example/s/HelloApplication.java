package com.example.s;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class HelloApplication extends Application {

    public static int totalColumn, totalRow;
    static ArrayList<ArrayList<Node>> board = new ArrayList<>();

    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

   
    public static ArrayList<ArrayList<Node>> getBoard() {
        return board;
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

        int nbrAlea = (int)(0 + (Math.random() * ((totalColumn * totalRow) - 0 + 1))); // nombre aléatoire entre 0 et le nombre de de noeuds sur le plateau

        int nodeColumn = nbrAlea % totalColumn;
        int nodeRow = nbrAlea / totalRow;

        board.get(nodeColumn).get(nodeRow).setColor(0);

        List<Node> processingNodes = new ArrayList<>(); // node pas encore traiter, mais en cour de traitement
        processingNodes.add(board.get(nodeColumn).get(nodeRow));

        while(processingNodes.size() != 0)
        {
            int targetNodeIndex = (int)(0 + (Math.random() * ((processingNodes.size() - 1) - 0 + 1)));
            Node activeNode = processingNodes.get(targetNodeIndex); // TODO Erreur car processingNodes.size vaut 1 donc si target = 1 -> out of index
            processingNodes.remove(targetNodeIndex);
            activeNode.getNeighbourNode(board, processingNodes); 
        }
    }
    
    public static void main(String[] args) {
        totalColumn = 25;
        totalRow = 25;
        mazeGeneration(board);
        launch();
    }
}