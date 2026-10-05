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

    // Pairs a partial infix expression with the precedence of its main operator.
    // Operands are "atomic" and get the highest value (4).
    private static class Node {
        String expr;
        int prec;

        Node(String expr, int prec) {
            this.expr = expr;
            this.prec = prec;
        }
    }

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
                System.out.println("\nThank you for using our system!");
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

            clearScreen();
            screenHeader("INFIX TO POSTFIX");


            System.out.println("Enter the Infix Expression : ");


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
        boolean isFirst = true;
        int tokenCount = 0;

        for (int i = 0; i < expression.length(); i++) {

            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) {

                continue;
            }

            if (Character.isLetter(ch)) {

                if (!expectingOperand) {

                    throw new IllegalArgumentException(
                        "Operands may only be preceded by an operator or an open parenthesis."
                    );
                }

                if (++tokenCount > 15){

                    throw new IllegalArgumentException(
                        "The expression may have a maximum of 15 operators and operands."
                );

                }

                postfix.append(ch);
                expectingOperand = false;

            } else if (ch == '(') {

                if (!expectingOperand) {

                    throw new IllegalArgumentException(
                        "An open parenthesis may only be preceded by an operator or another open parenthesis."
                    );
                }

                stack.push(ch);

            } else if (ch == ')') {

                if (isFirst) {

                    throw new IllegalArgumentException(
                        "The expression may only begin with an operand or an open parenthesis."
                    );
                }

                if (expectingOperand) {

                    throw new IllegalArgumentException(
                        "A close parenthesis may only be preceded by an operand or another close parenthesis."
                    );
                }

                while (!stack.isEmpty() &&
                       stack.peek() != '(') {

                    postfix.append(stack.pop());
                }

                if (stack.isEmpty()) {

                    throw new IllegalArgumentException(
                        "All close parentheses must have matching open parentheses."
                    );
                }

                stack.pop();
                expectingOperand = false;

            } else if (isOperator(ch)) {

                if (isFirst) {

                    throw new IllegalArgumentException(
                        "The expression may only begin with an operand or an open parenthesis."
                    );
                }

                if (expectingOperand) {

                    throw new IllegalArgumentException(
                        "Operators may only be preceded by an operand or a close parenthesis."
                    );
                }

                if (++tokenCount > 15) {

                    throw new IllegalArgumentException(
                        "The expression may have a maximum of 15 operators and operands."
                    );
                }

                while (!stack.isEmpty() &&
                       stack.peek() != '(' &&
                       precedence(stack.peek()) >= precedence(ch)) {

                    postfix.append(stack.pop());
                }

                stack.push(ch);
                expectingOperand = true;

            } else if (Character.isDigit(ch)) {

                    throw new IllegalArgumentException(
                        "Each operand is denoted as a single alphabetic character."
                );

            } else {

                throw new IllegalArgumentException(
                    "Invalid character '" + ch + "'."
                );
            }

            isFirst = false;
        }


            if (expectingOperand) {

            throw new IllegalArgumentException(
                "The expression may only end with an operand or a close parenthesis."
            );
        }

        while (!stack.isEmpty()) {

            if (stack.peek() == '(') {

                throw new IllegalArgumentException(
                    "All open parentheses must have matching close parentheses."
                );
            }

            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static void postfixToInfixMenu() {

        while (true) {

            clearScreen();
            screenHeader("POSTFIX TO INFIX");

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

        Stack<Node> stack = new Stack<>();

        boolean isFirst = true;
        int tokenCount = 0;

        for (int i = 0; i < expression.length(); i++) {

            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) {

                continue;
            }

            if (Character.isLetter(ch)) {

                if (++tokenCount > 15) {

                    throw new IllegalArgumentException(
                        "The expression may have a maximum of 15 operators and operands."
                    );
                }

                // Operand: atomic, never needs parentheses
                stack.push(new Node(String.valueOf(ch), 4));

            } else if (ch == '(' || ch == ')') {

                throw new IllegalArgumentException(
                    "Parentheses are not valid in a postfix expression."
                );

            } else if (isOperator(ch)) {

                if (isFirst) {

                    throw new IllegalArgumentException(
                        "The expression may only begin with an operand."
                    );
                }

                if (++tokenCount > 15) {

                    throw new IllegalArgumentException(
                        "The expression may have a maximum of 15 operators and operands."
                    );
                }

                if (stack.size() < 2) {

                    throw new IllegalArgumentException(
                        "Operators may only be preceded by two operands."
                    );
                }

                Node right = stack.pop();
                Node left = stack.pop();
                int p = precedence(ch);

                String l = left.expr;
                String r = right.expr;

                if (ch == '^') {

                    // Right-associative: a^b^c = a^(b^c)
                    if (left.prec <= p) {
                        l = "(" + l + ")";
                    }

                    if (right.prec < p) {
                        r = "(" + r + ")";
                    }

                } else {

                    // Left-associative
                    if (left.prec < p) {
                        l = "(" + l + ")";
                    }

                    boolean commutative = (ch == '+' || ch == '*');

                    if (right.prec < p ||
                        (right.prec == p && !commutative)) {
                        r = "(" + r + ")";
                    }
                }

                stack.push(new Node(l + ch + r, p));

            } else if (Character.isDigit(ch)) {

                throw new IllegalArgumentException(
                    "Each operand is denoted as a single alphabetic character."
                );

            } else {

                throw new IllegalArgumentException(
                    "Invalid character '" + ch + "'."
                );
            }

            isFirst = false;
        }

        if (stack.size() != 1) {

            throw new IllegalArgumentException(
                "There are too many operands for the number of operators."
            );
        }

        return stack.pop().expr;
    }

    public static void infixToPrefixMenu() {

        while (true) {

            clearScreen();
            screenHeader("INFIX TO PREFIX");

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

    public static void screenHeader(String title){
        String border = "════════════════════════════════════";

        System.out.println(border);
        System.out.println(centerText(title, border.length()));
        System.out.print(border);
        System.out.println();
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
