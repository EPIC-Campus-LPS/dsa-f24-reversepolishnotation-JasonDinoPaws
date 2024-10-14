
public class ReversePolishNotation {

    private static int combine(String N1, String N2, String Op)
    {
        int n1 = Integer.parseInt(N1);
        int n2 = Integer.parseInt(N2);

        if (Op.equals("+"))
            return n1 + n2;
        else if (Op.equals("-"))
            return n1 - n2;
        else if (Op.equals("*"))
            return n1 * n2;
        else if (Op.equals("/"))
            return n1 / n2;
        else if (Op.equals("^"))
            return n1 ^ n2;
        else
            return 0;
    }

    /**
     * @param input Postfix
     *              <pre>
     *              Creates a new Stack
     *              Splits everything by the " "
     *
     *              For String Let : split
     *                  if Let == +, -, *, /, ^
     *                      if the length of the Stack is grather than 1
     *                          Pushes to Stack N1 Let N2
     *                      else
     *                          throws a IllegalArgumentException
     *                  else
     *                      Pushes Let to Stack
     *
     *              if Stack is grater than 1
     *                  throws a IllegalArgumentException
     *              </pre>
     * @throws IllegalArgumentException Invalid Postfix
     * @return Solved Number
     */
    public static int evalulatePostfix(String input)
    {
        Stack sta = new Stack();
        String[] split = input.split(" ");

        for (String let : split)
        {
            if (let.equals("+") || let.equals("-") || let.equals("*") || let.equals("/") || let.equals("^")) {
                if (sta.size() >= 2) {
                    String N2 = sta.pop();
                    String N1 = sta.pop();
                    sta.push("" + combine(N1, N2, let));
                }
                else
                    throw new IllegalArgumentException("invalid postfix expression");
            }
            else
                sta.push(let);
        }

        if (sta.size() > 1)
            throw new IllegalArgumentException("invalid postfix expression");
        return Integer.parseInt(sta.pop());
    }

    private static int GetOpNum(String Op)
    {
        if (Op.equals("^") || Op.equals("("))
            return 1;
        else if (Op.equals("*") || Op.equals("/"))
            return 2;
        else if (Op.equals("+") || Op.equals("-"))
            return 3;
        else
            return 0;
    }
    private static boolean OpIsHigher(String Op1, String Op2)
    {
        if (Op2.equals("("))
            return false;

        return GetOpNum(Op1) >= GetOpNum(Op2);
    }
    /**
     * @param input Infix
     *             <pre>
     *              Creates the out string
     *              Creates the Stack
     *              Creates the Split by " " for the input
     *
     *              try {
     *              Loops threw each element in the split
     *                  Sets let to the char at i
     *                  if let is not " " continues else go's to the next Char
     *
     *                  if let is ")" Dump all Opperations until it reaches "("\
     *
     *                  if Let is ^,*,/,+,-,(
     *                      Loops threw the stack until one is lower/Equal to
     *                      Pushes let to the Stack
     *
     *                  else 
     *                      Add Let to out
     *              }
     *
     *             Adds whats left in the stack to the Out
     *
     *              if Array Index Error throws a IllegalArgumentException
     *              checks if there is a "(" in the out and throws a IllegalArgumentException
     *             </pre>
     * @return Postfix
     */
    private static boolean isnum(String input)
    {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    public static String infixToPostfix(String input)
    {
        String out = "";
        Stack sta = new Stack();
        String[] split = input.split(" ");

        try {
            for (String let : split) {
                if (let.equals(")")) {
                    while (!sta.peek().equals("("))
                        out += sta.pop() + " ";
                    sta.pop();

                } else if (let.equals("+") || let.equals("-") || let.equals("*") || let.equals("/") || let.equals("^") || let.equals("(")) {
                    while (sta.size() > 0 && OpIsHigher(let, sta.peek()))
                        out += sta.pop() + " ";

                    sta.push(let);
                } else
                    out += let + " ";
            }

            while (sta.size() > 0)
                out += sta.pop() + " ";
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("invalid postfix expression");
        }


        if (out.contains("("))
            throw new IllegalArgumentException("invalid postfix expression");
        return out;
    }

    public static String PrefixtoInfix(String input)
    {
        String out = "";
        Stack sta = new Stack();
        String[] split = input.split(" ");

        for (String let : split) {
            if (!let.equals(" ")) {
                if (let.equals("+") || let.equals("-") || let.equals("*") || let.equals("/") || let.equals("^"))
                    sta.push(let);
                else {
                    if (sta.size() > 2 && GetOpNum(sta.peek()) == 0) {
                        String Eq1 = sta.pop();
                        String Op = sta.pop();

                        if (GetOpNum(Eq1) != 0)
                            sta.push("( " + Op + " " + Eq1 + " " + let + " )");
                        else
                            sta.push("( " + Eq1 + " " + Op + " " + let + " )");

                    } else
                        sta.push(let);
                }
            }
        }

        while (sta.size() > 1)
        {
            String Eq2 = sta.pop();
            String Eq1 = sta.pop();
            String Op = sta.pop();

            if (GetOpNum(Eq1) != 0)
                sta.push("( "+Op+ " "+ Eq1 + " "+ Eq2+ " )");
            else if (GetOpNum(Eq2) != 0)
                sta.push("( "+Eq1+ " "+ Eq2 + " "+ Op+ " )");
            else
                sta.push("( "+Eq1+ " "+ Op + " "+ Eq2+ " )");
        }

        String last = sta.pop();
        return last.substring(1,last.length()-2);
    }
}
