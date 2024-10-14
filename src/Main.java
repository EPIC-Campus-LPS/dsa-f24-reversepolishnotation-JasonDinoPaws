public class Main {
    public static void main(String[] args) {
        String prefix = "/ + A B * + C * E B + C A";
        String infix = ReversePolishNotation.PrefixtoInfix(prefix);
        String Post = ReversePolishNotation.infixToPostfix("10 + ( 12 ^ ( 25 + 2 ) + 2 )");

        System.out.println("Prefix: " + prefix);
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + Post);
        System.out.println("Postfix Solved: " + ReversePolishNotation.evalulatePostfix(Post));
    }
}