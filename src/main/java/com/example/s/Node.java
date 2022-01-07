package com.example.s;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public Node(int nodeId, int y, int x)
    {
        column = y;
        row = x;
        id = nodeId;
        color = -1;
    }

    int getId()
    {
        return id;
    }

    int getColor()
    {
        return color;
    }

    int getColumn()
    {
        return column;
    }

    int getRow()
    {
        return row;
    }

    Node getLastNode()
    {
        return lastNode;
    }

    void setColor(int newColor)
    {
        color = newColor;
    }

    void setLastNode(Node newNode)
    {
        lastNode = newNode;
    }

    ArrayList<Integer> getLink()
    {
        return link;
    }

    void addLink(int nodeId)
    {
        link.add(nodeId);
    }


    void generateLink(ArrayList<ArrayList<Node>> board, List<Node> processingNodes)
    {
        board.get(column).get(row).setColor(1);
        if(column -1 >= 0) // Sud
        {
            if(board.get(column-1).get(row).getColor() == -1)
            {
                board.get(column-1).get(row).setColor(0);
                processingNodes.add(board.get(column-1).get(row));
                board.get(column).get(row).link.add(board.get(column-1).get(row).getId()); 
                board.get(column-1).get(row).addLink(this.id);
            }
        }
        if(column + 1 < board.size()) // Nord
        {
            if(board.get(column+1).get(row).getColor() == -1)
            {
                board.get(column+1).get(row).setColor(0);
                processingNodes.add(board.get(column+1).get(row));
                board.get(column).get(row).link.add(board.get(column+1).get(row).getId());
                board.get(column+1).get(row).addLink(this.id);
            }
        }
        if(row + 1 < board.get(column).size()) // Est
        {
            if(board.get(column).get(row+1).getColor() == -1)
            {
                board.get(column).get(row+1).setColor(0);
                processingNodes.add(board.get(column).get(row+1));
                board.get(column).get(row).link.add(board.get(column).get(row+1).getId());
                board.get(column).get(row+1).addLink(this.id);
            }
        }
        if(row - 1 >= 0) // Ouest
        {
            if(board.get(column).get(row-1).getColor() == -1)
            {
                board.get(column).get(row-1).setColor(0);
                processingNodes.add(board.get(column).get(row-1));
                board.get(column).get(row).link.add(board.get(column).get(row-1).getId()); 
                board.get(column).get(row-1).addLink(this.id);
            }
        }
    }

    boolean isElementPresentInLink(int element)
    {
        for(int value : link)
        {
            if(value == element) return true;
        }
        return false;
    }

    boolean isLinkedDown(ArrayList<ArrayList<Node>> board, int column, int row)
    {
        if(row != board.get(column).size() - 1)
        {
            if(isElementPresentInLink(board.get(column).get(row+1).getId())) return true;
            else return false;
        }
        else
        {
            return false;
        }
    }

    boolean isLinkedUp(ArrayList<ArrayList<Node>> board, int column, int row)
    {
        if(row != 0)
        {
            if(isElementPresentInLink(board.get(column).get(row-1).getId())) return true;
            else return false;
        }
        else
        {
            return false;
        }
    }

    boolean isLinkedRight(ArrayList<ArrayList<Node>> board, int column, int row)
    {
        if(column != board.size() - 1)
        {
            if(isElementPresentInLink(board.get(column+1).get(row).getId())) return true;
            else return false;
        }
        else
        {
            return false;
        }
    }

    boolean isLinkedLeft(ArrayList<ArrayList<Node>> board, int column, int row)
    {
        if(column != 0)
        {
            if(isElementPresentInLink(board.get(column-1).get(row).getId())) return true;
            else return false;
        }
        else
        {
            return false;
        }
    }

    private int column, row, id, color;
    private ArrayList<Integer> link = new ArrayList<>();
    Node lastNode;
}