    /*
        Group 6
        Authors: Soriano, Kristina Cassandra (Leader)
                 Condes, Hope Gian           (Member1)
                 Ramos, Aldrich Andrei C.    (Member2)
        Laboratory Exercise 2
        Date: 8/27/2026
    */
    import java.util.Scanner;

    public class CS_2A_Group6_Lab2{
        public static void main(String [] args){
            Scanner sc = new Scanner(System.in);
            int[] arr = null;
            int count = 0;

            while(true){
                Menu();
                
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
                        System.out.println("Array already exists");
                        pressAnyKey(sc);
                        clearScreen();
                    }else{
                        clearScreen();
                    while(true){
                        System.out.print("Enter array size (5-20): ");
                    
                        int arraySize = checkInteger(sc);

                        if(arraySize <5 || arraySize >20 ){
                            System.out.println("Array Size must be between 5 and 20.");
                            continue;
                        }else{
                            arr = new int[arraySize];
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
                    }
                       

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
        public static void Menu(){
            System.out.println("Array Operations \n     Menu");
            System.out.println("\n1. Create Array");
            System.out.println("2. Insert Elements");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Delete");
            System.out.println("0. Stop");
            System.out.print("Enter Choice: ");
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
}

        