package datastructure.stack;

public class ReverseString {
    public static void main(String[] args) {
        String input = "Test abcde";
        String t = reverse(input);
        System.out.println("Input string: " + input);
        System.out.println("Reversed string: " + t);
    }

    private static String reverse(String s) {
        ArrayStack<Character> st = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            Character w = s.charAt(i);
            st.push(w);
        }
        String output = "";
        while (!st.isEmpty()) {
            output = output + st.pop();
        }
        return output;
    }
}
