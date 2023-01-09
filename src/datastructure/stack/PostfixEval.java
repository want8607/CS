package datastructure.stack;

public class PostfixEval {
    public static void main(String[] args) {
        String postfix = "700 3 47 + 6 * - 4 /";
        System.out.println("Input string: " + postfix);
        int answer = evaluate(postfix);
        System.out.println("Answer: " + answer);
    }

    public static int evaluate(String input) {
        ArrayStack<Integer> st = new ArrayStack<>();
        boolean isPreviousNum = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                if (isPreviousNum) {
                    Integer top = st.pop();
                    top = top * 10 + (ch - '0');
                    st.push(top);
                } else
                    st.push(ch - '0');
                isPreviousNum = true;
            } else if (isOperator(ch)) {
                int a, b;
                b = st.pop();
                a = st.pop();
                st.push(operation(ch, a, b));
                isPreviousNum = false;
            } else {
                isPreviousNum = false;
            }
        }
        return st.pop();
    }

    public static int operation(char ch, int a, int b) {
        final int ERROR = -12345;
        if (ch == '+') {
            return a + b;
        } else if (ch == '*') {
            return a * b;
        } else if (ch == '/') {
            return a / b;
        } else if (ch == '-') {
            return a - b;
        } else {
            return ERROR;
        }
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '/' || ch == '*';
    }
}
