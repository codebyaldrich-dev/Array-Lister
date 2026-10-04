/*
    Group 6
    Authors: Ramos, Aldrich              (Leader)
            Condes, Hope Gian           (Member1)
            Soriano, Kristina Cassandra (Member2)
    Laboratory Exercise 4
    Date: 10/4/2026
*/

import java.util.Scanner;
import java.util.Stack;

public class CS2A_Group6_Lab4 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            clearScreen();
            mainMenu();

            System.out.print("Enter your choice : ");

            int choice = checkInteger(sc);

            if (choice == 1) {

                clearScreen();
                infixToPostfixMenu();

            } else if (choice == 2) {

                clearScreen();
                postfixToInfixMenu();

            } else if (choice == 3) {

                clearScreen();
                infixToPrefixMenu();

            } else if (choice == 0) {
                System.out.println("\nThank you for using our system!!");
                System.out.println("Program terminated.");
                break;
                

            } else {

                System.out.println( "Invalid choice! Please select 1, 2, 3, or 0.");

                pressAnyKey(sc);
            }
        }

        sc.close();
    }

    public static void mainMenu() {

        String border = "════════════════════════════════════";

        System.out.println();
        System.out.println(border);
        System.out.println(
            centerText("STACK APPLICATION CONVERSION", border.length())
        );
        System.out.println(border);

        System.out.println("[1]. Infix to Postfix");
        System.out.println("[2]. Postfix to Infix");
        System.out.println("[3]. Infix to Prefix");
        System.out.println("[0]. Exit");

        System.out.println(border);
    }

    public static void infixToPostfixMenu() {

        while (true) {

            System.out.println();

            System.out.print("Enter the Infix Expression : ");
            String expression = sc.nextLine();

            try {

                String postfix = infixToPostfix(expression);

                System.out.println(
                    "Infix Expression : " + expression
                );

                System.out.println(
                    "Postfix Expression : " + postfix
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                    "Error: " + e.getMessage()
                );
            }

            while (true) {

                System.out.print("\nTry Again (Y/N) : ");

                String input = sc.nextLine().trim();

                if (input.equalsIgnoreCase("Y")) {

                    break;

                } else if (input.equalsIgnoreCase("N")) {

                    return;

                } else {

                    System.out.println("Invalid input! Please enter Y or N.");
                   
                   
               
                }
            }
        }
    }

    public static String infixToPostfix(String expression) {

        if (expression == null ||
            expression.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Expression cannot be empty."
            );
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        boolean expectingOperand = true;

        for (int i = 0; i < expression.length(); i++) {

            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) {

                continue;
            }

            if (Character.isLetterOrDigit(ch)) {

                if (!expectingOperand) {

                    throw new IllegalArgumentException(
                        "Two operands cannot be next to each other."
                    );
                }

                postfix.append(ch);
                expectingOperand = false;

            } else if (ch == '(') {

                if (!expectingOperand) {

                    throw new IllegalArgumentException(
                        "Missing operator before '('."
                    );
                }

                stack.push(ch);

            } else if (ch == ')') {

                if (expectingOperand) {

                    throw new IllegalArgumentException(
                        "Invalid expression near ')'."
                    );
                }

                while (!stack.isEmpty() &&
                       stack.peek() != '(') {

                    postfix.append(stack.pop());
                }

                if (stack.isEmpty()) {

                    throw new IllegalArgumentException(
                        "Mismatched parentheses."
                    );
                }

                stack.pop();
                expectingOperand = false;

            } else if (isOperator(ch)) {

                if (expectingOperand) {

                    throw new IllegalArgumentException(
                        "Operator '" + ch +
                        "' is in an invalid position."
                    );
                }

                while (!stack.isEmpty() &&
                       stack.peek() != '(' &&
                       precedence(stack.peek()) >= precedence(ch)) {

                    postfix.append(stack.pop());
                }

                stack.push(ch);
                expectingOperand = true;

            } else {

                throw new IllegalArgumentException(
                    "Invalid character '" + ch + "'."
                );
            }
        }

        if (expectingOperand) {

            throw new IllegalArgumentException(
                "Expression cannot end with an operator."
            );
        }

        while (!stack.isEmpty()) {

            if (stack.peek() == '(') {

                throw new IllegalArgumentException(
                    "Mismatched parentheses."
                );
            }

            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static void postfixToInfixMenu() {

        while (true) {

            System.out.println();

            System.out.print("Enter the Postfix Expression : ");
            String expression = sc.nextLine();

            try {

                String infix = postfixToInfix(expression);

                System.out.println(
                    "Postfix Expression : " + expression
                );

                System.out.println(
                    "Infix Expression : " + infix
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                    "Error: " + e.getMessage()
                );
            }

            while (true) {

                System.out.print("\nTry Again (Y/N) : ");

                String input = sc.nextLine().trim();

                if (input.equalsIgnoreCase("Y")) {

                    break;

                } else if (input.equalsIgnoreCase("N")) {

                    return;

                } else {

                     System.out.println("Invalid input! Please enter Y or N.");
                }
            }
        }
    }

    public static String postfixToInfix(String expression) {

        if (expression == null ||
            expression.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Expression cannot be empty."
            );
        }

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {

            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) {

                continue;
            }

            if (Character.isLetterOrDigit(ch)) {

                stack.push(String.valueOf(ch));

            } else if (isOperator(ch)) {

                if (stack.size() < 2) {

                    throw new IllegalArgumentException(
                        "Not enough operands for operator '"
                        + ch + "'."
                    );
                }

                String operand2 = stack.pop();
                String operand1 = stack.pop();

                String result =
                    "(" + operand1 + ch + operand2 + ")";

                stack.push(result);

            } else {

                throw new IllegalArgumentException(
                    "Invalid character '" + ch + "'."
                );
            }
        }

        if (stack.size() != 1) {

            throw new IllegalArgumentException(
                "Invalid postfix expression."
            );
        }

        return stack.pop();
    }

    public static void infixToPrefixMenu() {

        while (true) {

            System.out.println();

            System.out.print("Enter the Infix Expression : ");
            String expression = sc.nextLine();

            try {

                String prefix = infixToPrefix(expression);

                System.out.println(
                    "Infix Expression : " + expression
                );

                System.out.println(
                    "Prefix Expression : " + prefix
                );

            } catch (IllegalArgumentException e) {

                System.out.println(
                    "Error: " + e.getMessage()
                );
            }

            while (true) {

                System.out.print("\nTry Again (Y/N) : ");

                String input = sc.nextLine().trim();

                if (input.equalsIgnoreCase("Y")) {

                    break;

                } else if (input.equalsIgnoreCase("N")) {

                    return;

                } else {

                     System.out.println("Invalid input! Please enter Y or N.");
                }
            }
        }
    }

    public static String infixToPrefix(String expression) {

        infixToPostfix(expression);

        char[] reversed =
            reverse(expression).toCharArray();

        for (int i = 0; i < reversed.length; i++) {

            if (reversed[i] == '(') {

                reversed[i] = ')';

            } else if (reversed[i] == ')') {

                reversed[i] = '(';
            }
        }

        String reversedInfix =
            String.valueOf(reversed);

        String postfix =
            infixToPostfix(reversedInfix);

        return reverse(postfix);
    }

    public static boolean isOperator(char ch) {

        return ch == '+'
            || ch == '-'
            || ch == '*'
            || ch == '/'
            || ch == '%'
            || ch == '^';
    }

    public static int precedence(char operator) {

        switch (operator) {

            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
            case '%':
                return 2;

            case '^':
                return 3;

            default:
                return -1;
        }
    }

    public static String reverse(String text) {

        return new StringBuilder(text)
            .reverse()
            .toString();
    }

    public static String centerText(
        String text,
        int width
    ) {

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

    public static int checkInteger(Scanner sc) {

        while (true) {

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {

                System.out.println("Input cannot be empty.");
                pressAnyKey(sc);
                clearScreen();
                mainMenu();

                System.out.print("Enter your choice : ");

            } else {

                try {

                    return Integer.parseInt(input);

                } catch (NumberFormatException e) {

                    System.out.println("Please enter a valid integer.");
                    pressAnyKey(sc);
                    clearScreen();
                    mainMenu();
                    System.out.print("Enter your choice : ");
                    
                }
            }
        }
    }
}