/*
    Group 6
    Authors: Soriano, Kristina Cassandra (Leader)
             Condes, Hope Gian           (Member1)
             Ramos, Aldrich Andrei C.    (Member2)
    Laboratory Exercise 2
    Date: 8/27/2026
*/
import java.util.Scanner;

public class CS2A_Group6_Lab2{

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int[] arr = null;
        int count = 0;

        while(true){
            Menu(arr, count);

            int menuChoice = checkInteger(sc);

            if (menuChoice <0 || menuChoice >5){
                clearScreen();
                System.out.println("Invalid choice. Please pick from (0-5).");
                pressAnyKey(sc);
                clearScreen();
                continue;
            }

            if(arr == null && menuChoice != 1 && menuChoice != 0){
                clearScreen();
                System.out.println("Please create an array first.");
                pressAnyKey(sc);
                clearScreen();
                continue;
                }

            if(menuChoice == 1){
                clearScreen();
                if(arr!= null){
                    System.out.println("Array already exists.");
                    pressAnyKey(sc);
                    clearScreen();
                }else{
                    clearScreen();
                while(true){
                    System.out.print("Enter array size (5-20): ");

                    int arraySize = checkInteger(sc);

                    if(arraySize <5 || arraySize >20 ){
                        System.out.println("Array Size must be between 5 and 20.");
                        System.out.println();
                        continue;
                    }else{
                        arr = new int[arraySize];
                        System.out.println("Array of size " + arraySize + " created successfully.");
                        pressAnyKey(sc);
                        clearScreen();
                        break;
                    }
                }
                }


            }else if (menuChoice == 2){
                clearScreen();
                if(count == arr.length){
                    System.out.println("Array is already full.");
                }
                else{
                    while(count < arr.length){
                    System.out.print("Enter integer values to insert: ");
                       int elementValue = checkInteger(sc);

                    //Sentinel
                    if(elementValue == -99){
                        break;
                     }

                    boolean duplicate = false;

                    for (int i = 0; i < count; i++){
                        if(arr[i] == elementValue){
                            duplicate = true;
                        break;
                        }
                    }

                    if (duplicate){
                        System.out.println("Value already exists in the array.");
                        continue;
                    }

                    arr[count] = elementValue;
                    count++;

                    System.out.println(elementValue + " has been added.");
                    }
                    if(count == arr.length){
                        System.out.println();
                        System.out.println("Array is now full.");
                    }
                }


                pressAnyKey(sc);
                clearScreen();
            }else if (menuChoice == 3){
                clearScreen();
                if(count == 0){
                    System.out.println("Array is empty. Nothing to search.");
                }else{
                    System.out.print("Enter search key: ");
                    int searchKey = checkInteger(sc);
                    int index = searchElement(arr, count, searchKey);
                    
                    if(index == -1){
                        System.out.println(searchKey + " not found in the array.");
                    }else{
                        System.out.println(searchKey + " found at index " + index + ".");
                    }
                }
                
                System.out.println();
                pressAnyKey(sc);
                clearScreen();
            }else if(menuChoice == 4){
                clearScreen();
                displayArray(arr, count);
                System.out.println();
                pressAnyKey(sc);
                clearScreen();
            
            }else if (menuChoice == 5){
                clearScreen();
                if(count == 0){
                    System.out.println("Array is empty. Nothing to delete.");
                }else{
                    System.out.print("Enter value to delete: ");
                    int deleteValue = checkInteger(sc);
                    int index = searchElement(arr, count, deleteValue);

                    if(index == -1){
                        System.out.println(deleteValue + " not found in the array.");
                    }else{
                        count = deleteElement(arr, count, index);
                        System.out.println(deleteValue + " has been deleted.");
                    }
                }
                
                System.out.println();
                pressAnyKey(sc);
                clearScreen();
                


            }else if (menuChoice == 0){
                clearScreen();
                System.out.println("Program has been terminated.");
                pressAnyKey(sc);
                sc.close();
                return;
            }
        }
    }

    //Show Menu
    public static void Menu(int[] arr, int count){
        String border = "------------------------------------";
        System.out.println(border);
        System.out.println(centerText("Array Operations", border.length()));
        System.out.println(centerText("Menu", border.length()));
        System.out.print(border);
        System.out.println();
        System.out.println("\n1. Create Array");
        System.out.println("2. Insert Elements");
        System.out.println("3. Search");
        System.out.println("4. Display");
        System.out.println("5. Delete");
        System.out.println("0. Stop");
        System.out.println();
        System.out.print(border);
        System.out.println();
        if(arr != null){
            System.out.println("Array Status: " + count + "/" + arr.length + " filled");
        }
        System.out.print("Enter Choice: ");
    }
    public static String centerText(String text, int width){
        int padding = (width - text.length()) / 2;
        if(padding <= 0) return text;
        return " ".repeat(padding) + text;
    }

    //Clears screen after transaction
    public static void clearScreen() {
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch (Exception e) {
        System.out.println("Unable to clear screen.");
        }
    }

    public static void pressAnyKey(Scanner sc){
        System.out.print("Press Enter to continue... ");
        sc.nextLine();
    }

    //Checks both null and invalid integer inputs
    public static int checkInteger(Scanner sc){
        while(true){
            String input = sc.nextLine().trim();

            if(input.isEmpty()){
                System.out.println("Input cannot be empty.");
                System.out.print("Enter an integer: ");
                continue;
            }

        try{
            return Integer.parseInt(input);
        }catch(NumberFormatException e){
            System.out.println("Please enter a valid integer.");
            System.out.print("Enter an integer: ");
    }
}
}

    //Finds the index of a value in the array, returns -1 if not found
    public static int searchElement(int[] arr, int count, int value){
        for(int i = 0; i < count; i++){
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }
    
    //Displays all elements in the array, 5 per line, right-justified
    public static void displayArray(int[] arr, int count){
        if(count == 0){
            System.out.println("Array is empty. Nothing to display.");
            return;
        }
        
        for(int i = 0; i < count; i++){
            System.out.printf("%5d", arr[i]);
            
            if((i + 1) % 5 == 0 || i == count - 1){
                System.out.println();
            }
        }
    }
    
    //Shifts elements left to remove the element at the given index
    public static int deleteElement(int[] arr, int count, int index){
        for(int i = index; i < count - 1; i++){
            arr[i] = arr[i + 1];
        }
        arr[count - 1] = 0;
        return count - 1;
    }
}
