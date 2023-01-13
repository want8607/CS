package datastructure.queue;

import datastructure.stack.LinkedStack;

public class Palindrome {
    public static void main(String[] args) {
        System.out.println("Palindrom check");
        String str = "aabbaa";
        boolean t = isPalindrom(str);
        System.out.println(str + " is Palindrom?: " + t);
    }

    public static boolean isPalindrom(String str) {
        LinkedQueue<Character> queue = new LinkedQueue<>();
        LinkedStack<Character> stack = new LinkedStack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            queue.enqueue(ch);
            stack.push(ch);
        }

        while (!stack.isEmpty() && stack.pop() == queue.dequeue()){
        }
        if (stack.isEmpty()) return true;
        else return false;
    }
}
