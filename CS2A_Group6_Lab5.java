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

    /*===========================
            NODE AND ROOT
      (shared by the whole group)
    =============================*/

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static Node root = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        mainMenu();

        while(true){

            System.out.print("Enter your choice: ");
            String choice = checkInput(sc);

            if(choice.equalsIgnoreCase("S") || choice.equalsIgnoreCase("SHOW")){
                show(root, sc);
                clearScreen();
                mainMenu();
            }else if(choice.equalsIgnoreCase("I") || choice.equalsIgnoreCase("INSERT")){
                // TODO: teammate's insert method goes here
                System.out.println("Insert is not available yet.");
                pressAnyKey(sc);
                clearScreen();
                mainMenu();
            }else if(choice.equalsIgnoreCase("D") || choice.equalsIgnoreCase("DELETE")){
                // TODO: teammate's delete method goes here
                System.out.println("Delete is not available yet.");
                pressAnyKey(sc);
                clearScreen();
                mainMenu();
            }else if(choice.equalsIgnoreCase("T") || choice.equalsIgnoreCase("TRAVERSE")){
                traverse(sc);
                clearScreen();
                mainMenu();
            }else if(choice.equalsIgnoreCase("Q") || choice.equalsIgnoreCase("QUIT")){
                quit(sc);
                break;
            }else{
                System.out.println("Invalid choice try again.");
                pressAnyKey(sc);
                clearScreen();
                mainMenu();
            }
        }

    }




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

  
    public static void traverse(Scanner sc) {
        while (true) {
            clearScreen();
            traversalMenu();

            System.out.print("Enter your choice: ");
            String choice = checkInput(sc);

            if (choice.equals("1")) {
                inorderTraversal(root, sc);
            } else if (choice.equals("2")) {
                // TODO: teammate's preorder method goes here
                System.out.println("Preorder is not available yet.");
                pressAnyKey(sc);
            } else if (choice.equals("3")) {
                // TODO: teammate's postorder method goes here
                System.out.println("Postorder is not available yet.");
                pressAnyKey(sc);
            } else if (choice.equals("4")) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 to 4.");
                pressAnyKey(sc);
            }
        }
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
        System.out.print("Press any key to continue... ");
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


  

    public static void show(Node root, Scanner sc) {
        System.out.println();
        System.out.println("CURRENT BINARY SEARCH TREE");
        System.out.println("(Tree is sideways: top = right side, bottom = left side)");
        System.out.println("----------------------------------------");

        if (root == null) {
            System.out.println("The tree is empty. Insert some values first.");
        } else {
            printTree(root, 0);
        }

        System.out.println("----------------------------------------");
        pressAnyKey(sc);
    }

   
    public static void printTree(Node node, int level) {
        if (node == null) {
            return;
        }

        printTree(node.right, level + 1);

        System.out.println("    ".repeat(level) + node.data);

        printTree(node.left, level + 1);
    }


   

    public static void inorderTraversal(Node root, Scanner sc) {
        System.out.println();

        if (root == null) {
            System.out.println("The tree is empty. Nothing to traverse.");
        } else {
            System.out.print("Inorder: ");
            inorder(root);
            System.out.println();
        }

        pressAnyKey(sc);
    }

   
    public static void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }


    

    public static void quit(Scanner sc) {
        System.out.println();
        System.out.println("Thank you for using the BST program. Goodbye!");
        sc.close();
    }

}
