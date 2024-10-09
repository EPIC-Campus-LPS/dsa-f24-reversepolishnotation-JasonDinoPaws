
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
     *                      Pushes to Stack N1 Let N2
     *                  else
     *                      Pushes Let to Stack
     *              </pre>
     * @return Solved Number
     */
    public static int evalulatePostfix(String input)
    {
        Stack sta = new Stack();
        String[] split = input.split(" ");

        for (String let : split)
        {
            if (let.equals("+") || let.equals("-") || let.equals("*") || let.equals("/") || let.equals("^")) {
                String N2 = sta.pop();
                String N1 = sta.pop();
                sta.push("" + combine(N1, N2, let));
            }
            else
                sta.push(let);
        }

        return Integer.parseInt(sta.pop());
    }

    private static int GetOpNum(String Op)
    {
        if (Op.equals("^") || Op.equals("("))
            return 1;
        else if (Op.equals("*") || Op.equals("/"))
            return 2;
        else
            return 3;
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
     *
     *              Loops threw each character in the input
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
     *
     *             Adds whats left in the stack to the Out
     *             </pre>
     * @return Postfix
     */
    public static String infixToPostfix(String input)
    {
        String out = "";
        Stack sta = new Stack();

        for (int i = 0; i < input.length(); i++)
        {
            String let = input.substring(i,i+1);
            if (let.equals(" ")) continue;


            if (let.equals(")")) {
                while (!sta.peek().equals("("))
                    out += sta.pop() + " ";
                sta.pop();
            }
            else if (let.equals("+") || let.equals("-") || let.equals("*") || let.equals("/") || let.equals("^") || let.equals("("))
            {
                while (sta.size() > 0 && OpIsHigher(let,sta.peek()))
                    out += sta.pop() + " ";

                sta.push(let);
            }
            else
                out += let + " ";
        }

        while (sta.size() > 0)
            out += sta.pop() + " ";

        return out;
    }
}
