import java.util.Scanner;

public class CS2A_Group6_Lab3{

    public static void main(String [] args){

    Scanner sc = new Scanner(System.in);

    while(true){

        int arraySize;

        mainMenu();

        while(true){

            System.out.print("Enter Array Size: ");

            arraySize = checkInteger(sc);

            if(arraySize < 5 || arraySize > 15){
                System.out.println("Invalid choice. Please pick from (5-15).");
            }
            else{
                break;
            }
        }

        int[] arr = new int[arraySize];

        clearScreen();
        mainMenu();

        System.out.println("Enter " + arraySize + " Array Elements:");

        for(int i = 0; i < arraySize; i++){
            System.out.print("[" + (i + 1) + "] → : ");
            arr[i] = checkInteger(sc);
        }

        // Save the original array
        int[] originalArr = arr.clone();

        while(true){

            clearScreen();
            sortingMenu();

            System.out.print("Enter your choice: ");
            int choice = checkInteger(sc);


            if(choice == 1){

                // Bubble Sort
                int[] workingArr = originalArr.clone();

                clearScreen();

                System.out.println("════════════════════════════════════");
                System.out.println(centerText("Bubble Sort", 36));
                System.out.println("════════════════════════════════════");
                System.out.println();

                System.out.println("Original Array:");
                displayArray(workingArr);

                // bubbleSort(workingArr);

                System.out.println();
                System.out.println("Sorted Array:");
                displayArray(workingArr);

                pressAnyKey(sc);

            }


            else if(choice == 2){

                // Selection Sort
                int[] workingArr = originalArr.clone();

                clearScreen();

                System.out.println("════════════════════════════════════");
                System.out.println(centerText("Selection Sort", 36));
                System.out.println("════════════════════════════════════");
                System.out.println();

                System.out.println("Original Array:");
                displayArray(workingArr);

                // selectionSort(workingArr);

                System.out.println();
                System.out.println("Sorted Array:");
                displayArray(workingArr);

                pressAnyKey(sc);

            }


            else if(choice == 3){

                // Insertion Sort
                int[] workingArr = originalArr.clone();

                clearScreen();

                System.out.println("════════════════════════════════════");
                System.out.println(centerText("Insertion Sort", 36));
                System.out.println("════════════════════════════════════");
                System.out.println();

                System.out.println("Original Array:");
                displayArray(workingArr);

                System.out.println();
                System.out.println("Sorting Process:");

                insertionSort(workingArr);

                System.out.println();
                System.out.println("Sorted Array:");
                displayArray(workingArr);

                pressAnyKey(sc);

            }


            else if(choice == 4){

                // EXIT
                clearScreen();

                System.out.println("════════════════════════════════════");
                System.out.println(centerText("EXIT", 36));
                System.out.println("════════════════════════════════════");
                System.out.println();

                System.out.println(centerText(
                    "Thank you for using our program!", 36));

                System.out.println();
                System.out.println(centerText("Would you like to try again?\n", 36));
                System.out.println("════════════════════════════════════");
                System.out.println();

                System.out.print("(Y/N): ");

                String again = sc.nextLine().trim();


                if(again.equalsIgnoreCase("Y")){
                    clearScreen();
                    break;

                }
                else if(again.equalsIgnoreCase("N")){

                    System.out.println("\nExiting program...");
                    sc.close();
                    return;

                }
                else{

                    System.out.println("\nInvalid choice. Exiting program...");
                    sc.close();
                    return;

                }

            }


            else{

                System.out.println("Invalid sorting choice.");
                pressAnyKey(sc);

            }
        }

    }
}

    public static void mainMenu(){

        String border = "════════════════════════════════════";

        System.out.println(border);
        System.out.println(centerText("The Sorting Algorithm", border.length()));
        System.out.print(border);
        System.out.println();

        System.out.println(centerText("Welcome to our Sorting Algorithm!", border.length()));
        System.out.println(centerText("Please enter an array size", border.length()));
        System.out.println(centerText("between 5 and 15.", border.length()));

        System.out.println();
        System.out.print(border);
        System.out.println();
    }

    public static void sortingMenu(){

        String border = "════════════════════════════════════";

        System.out.println(border);
        System.out.println(centerText("Sorting Algorithms", border.length()));
        System.out.println(centerText("Menu", border.length()));
        System.out.print(border);
        System.out.println();

        System.out.println("\n[1]. Bubble Sort");
        System.out.println("[2]. Selection Sort");
        System.out.println("[3]. Insertion Sort");
        System.out.println("[4]. Exit");

        System.out.println();
        System.out.print(border);
        System.out.println();
    }

    public static String centerText(String text, int width){

        int padding = (width - text.length()) / 2;

        if(padding <= 0)
            return text;

        return " ".repeat(padding) + text;
    }

    // Checks both null and invalid integer inputs
    public static int checkInteger(Scanner sc){

        while(true){

            String input = sc.nextLine().trim();

            if(input.isEmpty()){
                System.out.println("Input cannot be empty.\n");
                System.out.print("Enter an integer: ");
                continue;
            }

            try{
                return Integer.parseInt(input);
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a valid integer.\n");
                System.out.print("Enter an integer: ");
            }
        }
    }

    public static void clearScreen(){

        try{
            new ProcessBuilder("cmd", "/c", "cls")
                .inheritIO()
                .start()
                .waitFor();
        }
        catch(Exception e){
            System.out.println("Unable to clear screen.");
        }
    }

    //Displays Array
    public static void displayArray(int[] arr){

    System.out.print("Array: [ ");

    for(int i = 0; i < arr.length; i++){
        System.out.print(arr[i]);

        if(i < arr.length - 1){
            System.out.print(" | ");
        }
    }

    System.out.println(" ]");
    }

    //Insertion Sort
    public static void insertionSort(int[] arr){

    for(int i = 1; i < arr.length; i++){

        int key = arr[i];
        int j = i - 1;

        while(j >= 0 && arr[j] > key){
            arr[j + 1] = arr[j];
            j--;
        }

        arr[j + 1] = key;

        // Display array after each pass
        System.out.print("Pass " + i + ": [ ");

        for(int k = 0; k < arr.length; k++){
            System.out.print(arr[k]);

            if(k < arr.length - 1){
                System.out.print(" | ");
            }
        }

        System.out.println(" ]");
    }
}
 public static void pressAnyKey(Scanner sc){
        System.out.print("Press Enter to continue... ");
        sc.nextLine();
    }
}