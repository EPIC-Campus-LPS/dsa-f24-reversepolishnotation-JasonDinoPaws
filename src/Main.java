public class Main {
    public static void main(String[] args) {
        String infix = "(1 + 2 * 3) + (4 * 5 ^ 6) / 7";
        String Post = ReversePolishNotation.infixToPostfix(infix);

        System.out.println("Infix: "+infix);
        System.out.println("Postfix: "+Post); // 7 2 + 9 / 3 5 * -
        System.out.println("Postfix Solved: "+ReversePolishNotation.evalulatePostfix("5 7 + 8 * * *")); // -14
    }
}