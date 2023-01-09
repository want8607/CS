package datastructure.stack;

public class ArrayStack<E> implements StackInterface<E> {
    private E[] stack;
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 64;
    private final E ERROR = null;

    public ArrayStack() {
        stack = (E[]) new Object[DEFAULT_CAPACITY];
        topIndex = -1;
    }

    public ArrayStack(int n) {
        stack = (E[]) new Object[n];
        topIndex = -1;
    }

    @Override
    public void push(E newItem) {
        if (isFull()) {/*에러처리*/} else {
            stack[++topIndex] = newItem;
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) return ERROR;
        else return stack[topIndex--];
    }

    @Override
    public E top() {
        if (isEmpty()) return ERROR;
        else return stack[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return (topIndex == -1);
    }

    @Override
    public void popAll() {
        stack = (E[]) new Object[stack.length];
        topIndex = -1;
    }

    public boolean isFull() {
        return (topIndex == stack.length - 1);
    }

    public static void main(String[] args) {
        ArrayStack<Integer> s = new ArrayStack<>(10);
        s.push(300);
        s.push(200);
        s.push(100);
        s.pop();
    }
}
