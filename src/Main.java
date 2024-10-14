public class Main {
    public static void main(String[] args) {
        String infix = "arr + + b + c a";
        String Post = ReversePolishNotation.infixToPostfix(infix);

        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + Post);
        System.out.println("Postfix Solved: " + ReversePolishNotation.evalulatePostfix(Post));
    }
}