/*
    Group 6
    Authors: Ramos, Aldrich              (Leader)
            Condes, Hope Gian           (Member1)
            Soriano, Kristina Cassandra (Member2)
    Laboratory Exercise 5
    Date: 10/18/2026
*/

import java.util.Scanner;

public class CS2A_Group6_Lab5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        mainMenu();

        while(true){

            System.out.print("Enter your choice: ");
            String choice = checkInput(sc);

            if(choice.equalsIgnoreCase("S") || choice.equalsIgnoreCase("SHOW")){
            
            }else if(choice.equalsIgnoreCase("I") || choice.equalsIgnoreCase("INSERT")){
                
            }else if(choice.equalsIgnoreCase("D") || choice.equalsIgnoreCase("DELETE")){
                
            }else if(choice.equalsIgnoreCase("T") || choice.equalsIgnoreCase("TRAVERSE")){
                
            }else if(choice.equalsIgnoreCase("Q") || choice.equalsIgnoreCase("QUIT")){
                
            }else{
                System.out.println("Invalid choice try again.");
                pressAnyKey(sc);
                clearScreen();
                mainMenu();
            }
        }
        
        

    }


    /*===========================
              MAIN MENU
    =============================*/

     public static void mainMenu() {

        String border = "════════════════════════════════════";

        System.out.println();
        System.out.println(border);
        System.out.println(centerText("BST TREE OPERATIONS", border.length()));
        System.out.println(centerText("MENU", border.length()));
        System.out.println(border);

        System.out.println("[S]. SHOW");
        System.out.println("[I]. INSERT");
        System.out.println("[D]. DELETE");
        System.out.println("[T]. TRAVERSE");
        System.out.println("[Q]. QUIT");

        System.out.println(border);
    }

    /*===========================
             TRAVERSAL MENU
     ===========================*/

     public static void traversalMenu() {

        String border = "════════════════════════════════════";

        System.out.println();
        System.out.println(border);
         System.out.println(centerText("TREE TRAVERSAL", border.length()));
        System.out.println(centerText("MENU", border.length()));
        System.out.println(border);

        System.out.println("[1]. INORDER");
        System.out.println("[2]. PREORDER");
        System.out.println("[3]. POSTORDER");
        System.out.println("[4]. EXIT");

        System.out.println(border);
    }

    public static String centerText(String text, int width) {

        int padding =
            (width - text.length()) / 2;

        if (padding <= 0) {

            return text;
        }

        return " ".repeat(padding) + text;
    }

    public static void pressAnyKey(Scanner sc) {
        System.out.print("Press Enter to continue... ");
        sc.nextLine();
    }

    public static void clearScreen() {

        try {

            new ProcessBuilder("cmd", "/c", "cls")
                .inheritIO()
                .start()
                .waitFor();

        } catch (Exception e) {

            System.out.println(
                "Unable to clear screen."
            );
        }
    }

    public static String checkInput(Scanner sc){
        while(true){

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {

                System.out.println("Input cannot be empty.\n");
                System.out.print("Enter your choice: ");
                continue;
            } 

            return input;
        }
    }




    
}
