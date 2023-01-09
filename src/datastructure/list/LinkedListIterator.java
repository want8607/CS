package datastructure.list;

import java.util.Iterator;

public class LinkedListIterator implements Iterator<Node<Integer>> {
    private Node<Integer> current;

    public LinkedListIterator(LinkedList<Integer> list) {
        current = list.getNode(-1);
    }

    @Override
    public boolean hasNext() {
        return current.next != null;
    }

    @Override
    public Node<Integer> next() {
        return current = current.next;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0, 200);
        list.add(0, 100);
        list.append(500);
        list.append(600);
        list.append(700);
        LinkedListIterator iter = new LinkedListIterator(list);
        while (iter.hasNext()) {
            System.out.println(iter.next().item);
        }
    }
}
