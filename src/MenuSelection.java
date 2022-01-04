import java.util.Scanner;

public class MenuSelection {

    public static void main(String[] args) {
        int userSelected;
        do{
            userSelected = MenuData();
            switch(userSelected){
            case 1:
                System.out.println("Vous avez choisis de JOUER ");
                break;
            case 2:
                System.out.println("Vous avez choisis de REGARDER L'HISTORIQUE ");
                break;
            case 3:
                System.out.println("Vous avez choisis de LIRE LES REGLES DU JEU ");
                break;
            case 4:
                System.out.println("Vous avez choisis de QUITTER ");
                break;
            default:
                break;
            }
        }
        while(userSelected > 4);
        
    }

    public static int MenuData() {
        int selection;
        Scanner sc = new Scanner (System.in);
        System.out.println("Selectionnez votre choix:");
        System.out.println("-------------------\n");
        System.out.println("1 - Jouer");
        System.out.println("2 - Historique");
        System.out.println("3 - Règles du jeu");
        System.out.println("4 - Quitter\n")

        System.out.println("Your selected option is: ");
        selection = sc.nextInt();
        return selection;

    }

}