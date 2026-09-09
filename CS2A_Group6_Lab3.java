/*
    Group 6
    Authors: Ramos, Aldrich              (Leader)
            Condes, Hope Gian           (Member1)
            Soriano, Kristina Cassandra (Member2)
    Laboratory Exercise 2
    Date: 9/1/2026
*/

import java.util.Scanner;

public class CS2A_Group6_Lab3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            int arraySize;

            mainMenu();

            // Get array size
            while (true) {

                System.out.print("Enter Array Size: ");
                arraySize = checkInteger(sc);

                if (arraySize < 5 || arraySize > 15) {
                    System.out.println("Invalid choice. Please pick from (5-15).\n");
                }
                else {
                    break;
                }
            }

            int[] arr = new int[arraySize];

            clearScreen();
            secMenu();

            System.out.println("Enter " + arraySize + " Array Elements:");

            int i = 0;

            while (i < arraySize) {
            
                System.out.print("[" + (i + 1) + "] → : ");
            
                String input = sc.nextLine().trim();
            
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please try again.\n");
                    continue;
                }
            
                try {
                    arr[i] = Integer.parseInt(input);
                    i++;  // Only move to the next index if input is valid
                }
            
                catch (NumberFormatException e) {
                    System.out.println("Please enter a valid integer. Try again.\n");
                }
            }

            // Save the original array
            int[] originalArr = arr.clone();

            while (true) {

                clearScreen();
                sortingMenu();

                System.out.print("Enter your choice: ");
                int choice = checkInteger(sc);

                // Bubble Sort
                if (choice == 1) {

                    int[] workingArr = originalArr.clone();

                    clearScreen();

                    System.out.println("════════════════════════════════════");
                    System.out.println(centerText("Bubble Sort", 36));
                    System.out.println("════════════════════════════════════");
                    System.out.println();

                    System.out.println("Original Array:");
                    displayArray(workingArr);

                    System.out.println();
                    System.out.println("Sorting Process:");

                    bubbleSort(workingArr);

                    System.out.println();
                    System.out.println("Sorted Array:");
                    displayArray(workingArr);

                    pressAnyKey(sc);
                }

                // Selection Sort
                else if (choice == 2) {

                    int[] workingArr = originalArr.clone();

                    clearScreen();

                    System.out.println("════════════════════════════════════");
                    System.out.println(centerText("Selection Sort", 36));
                    System.out.println("════════════════════════════════════");
                    System.out.println();

                    System.out.println("Original Array:");
                    displayArray(workingArr);

                    System.out.println();
                    System.out.println("Sorting Process:");

                    selectionSort(workingArr);

                    System.out.println();
                    System.out.println("Sorted Array:");
                    displayArray(workingArr);

                    pressAnyKey(sc);
                }

                // Insertion Sort
                else if (choice == 3) {

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

                // EXIT
                else if (choice == 4) {

                    clearScreen();

                    System.out.println("════════════════════════════════════");
                    System.out.println(centerText("EXIT", 36));
                    System.out.println("════════════════════════════════════");
                    System.out.println();

                    System.out.println(centerText(
                        "Thank you for using our program!", 36));

                    System.out.println();
                    System.out.println(centerText(
                        "Would you like to try again?", 36));

                    System.out.println();
                    System.out.println("════════════════════════════════════");
                    System.out.println();

                    // Validate Y/N input
                    while (true) {

                        System.out.print("(Y/N): ");
                        String again = sc.nextLine().trim();

                        if (again.equalsIgnoreCase("Y")) {

                            clearScreen();
                            break;
                        }

                        else if (again.equalsIgnoreCase("N")) {

                            System.out.println("\nExiting program...");
                            sc.close();
                            return;
                        }

                        else {

                            System.out.println(
                                "Invalid choice. Please enter Y or N.\n");
                        }
                    }

                    // Return to array-size menu
                    break;
                }

                else {

                    System.out.println("Invalid sorting choice.\n");
                    pressAnyKey(sc);
                }
            }
        }
    }


    // Main Menu
    public static void mainMenu() {

        String border = "════════════════════════════════════";

        System.out.println(border);
        System.out.println(centerText(
            "The Sorting Algorithm", border.length()));
        System.out.print(border);
        System.out.println();

        System.out.println(centerText(
            "Welcome to our Sorting Algorithm!", border.length()));
        System.out.println(centerText(
            "Please enter an array size", border.length()));
        System.out.println(centerText(
            "between 5 and 15.", border.length()));

        System.out.println();
        System.out.print(border);
        System.out.println();
    }


    // Second Menu
    public static void secMenu() {

        String border = "════════════════════════════════════";

        System.out.println(border);
        System.out.println(centerText(
            "The Sorting Algorithm", border.length()));
        System.out.print(border);
        System.out.println();

        System.out.println(centerText(
            "Welcome to our Sorting Algorithm!", border.length()));
        System.out.println(centerText(
            "Please enter array elements.", border.length()));

        System.out.println();
        System.out.print(border);
        System.out.println();
    }


    // Sorting Menu
    public static void sortingMenu() {

        String border = "════════════════════════════════════";

        System.out.println(border);
        System.out.println(centerText(
            "Sorting Algorithms", border.length()));
        System.out.println(centerText(
            "Menu", border.length()));
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


    // Centers text based on the given width
    public static String centerText(String text, int width) {

        int padding = (width - text.length()) / 2;

        if (padding <= 0) {
            return text;
        }

        return " ".repeat(padding) + text;
    }


    // Checks for empty and invalid integer inputs
    public static int checkInteger(Scanner sc) {

    while (true) {

        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Input cannot be empty.");
            return -1;
        }

        try {
            return Integer.parseInt(input);
        }

        catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer.");
            return -1;
        }
    }
}


    // Clears the console screen
    public static void clearScreen() {

        try {

            new ProcessBuilder("cmd", "/c", "cls")
                .inheritIO()
                .start()
                .waitFor();
        }

        catch (Exception e) {

            System.out.println("Unable to clear screen.");
        }
    }


    // Displays an array
    public static void displayArray(int[] arr) {

        System.out.print("Array: [ ");

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i]);

            if (i < arr.length - 1) {
                System.out.print(" | ");
            }
        }

        System.out.println(" ]");
    }


    // Displays a sorting pass
    public static void displayPass(int[] arr, int pass) {

        System.out.print("Pass " + pass + ": ");
        displayArray(arr);
    }


    // Checks if the array is already sorted
    public static boolean isSorted(int[] arr) {
    
        for (int i = 0; i < arr.length - 1; i++) {
    
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
    
        return true;
    }
    
    
    // Bubble Sort
    public static void bubbleSort(int[] arr) {
    
        int n = arr.length;
    
        // If already sorted, do not perform/display a pass
        if (isSorted(arr)) {
            System.out.println("Array is already sorted. No passes needed.");
            return;
        }
    
        for (int i = 0; i < n - 1; i++) {
    
            boolean swapped = false;
    
            for (int j = 0; j < n - 1 - i; j++) {
    
                if (arr[j] > arr[j + 1]) {
    
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
    
                    swapped = true;
                }
            }
    
            // If no swaps happened, array is sorted
            if (!swapped) {
                break;
            }
    
            // Display array after each actual pass
            displayPass(arr, i + 1);
        }
    }
    
    
    // Selection Sort
    public static void selectionSort(int[] arr) {
    
        // If already sorted, do not perform/display a pass
        if (isSorted(arr)) {
            System.out.println("Array is already sorted. No passes needed.");
            return;
        }
    
        for (int i = 0; i < arr.length - 1; i++) {
    
            int minIndex = i;
    
            for (int j = i + 1; j < arr.length; j++) {
    
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
    
            // Only count/display a pass if an actual swap occurs
            if (minIndex != i) {
    
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
    
                displayPass(arr, i + 1);
            }
        }
    }
    
    
    // Insertion Sort
    public static void insertionSort(int[] arr) {
    
        // If already sorted, do not perform/display a pass
        if (isSorted(arr)) {
            System.out.println("Array is already sorted. No passes needed.");
            return;
        }
    
        int pass = 0;
    
        for (int i = 1; i < arr.length; i++) {
    
            int key = arr[i];
            int j = i - 1;
    
            boolean moved = false;
    
            while (j >= 0 && arr[j] > key) {
    
                arr[j + 1] = arr[j];
                j--;
                moved = true;
            }
    
            arr[j + 1] = key;
    
            // Only display a pass if the array actually changed
            if (moved) {
                pass++;
                displayPass(arr, pass);
            }
        }
    }
    
    // Waits for the user to press Enter
    public static void pressAnyKey(Scanner sc) {

        System.out.print("Press Enter to continue... ");
        sc.nextLine();
    }
}

