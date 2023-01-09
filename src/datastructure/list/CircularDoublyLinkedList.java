package datastructure.list;

public class CircularDoublyLinkedList<E> implements ListInterface<E> {
    private BidirectionalNode<E> head;
    private int numItems;
    public final int NOT_FOUND = -12345;

    public CircularDoublyLinkedList() {
        numItems = 0;
        head = new BidirectionalNode<>(null);
        head.next = head.prev = head;
    }

    @Override
    public void add(int index, E x) {
        if (index >= 0 && index <= numItems) {
            BidirectionalNode<E> preNode = getNode(index - 1);
            BidirectionalNode<E> newNode = new BidirectionalNode<>(preNode, x, preNode.next);
            newNode.next.prev = newNode;
            preNode.next = newNode;
            numItems++;
        } else {/*예외처리*/}
    }

    @Override
    public void append(E x) {
        BidirectionalNode<E> prevNode = head.prev;
        BidirectionalNode<E> newNode = new BidirectionalNode<>(prevNode, x, null);
        prevNode.next = newNode;
        head.prev = newNode;
        numItems++;
    }

    @Override
    public E remove(int index) {
        if (index >= 0 && index <= numItems - 1) {
            BidirectionalNode<E> currNode = getNode(index);
            currNode.prev.next = currNode.next;
            currNode.next.prev = currNode.prev;
            numItems--;
            return currNode.item;
        } else return null;
    }

    @Override
    public boolean removeItem(E x) {
        BidirectionalNode<E> currNode = head;
        for (int i = 0; i < numItems; i++) {
            currNode = currNode.next;
            if (((Comparable) (currNode.item)).compareTo(x) == 0) {
                currNode.next.prev = currNode.prev;
                currNode.prev.next = currNode.next;
                numItems--;
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index < numItems) {
            return getNode(index).item;
        } else return null;
    }

    public BidirectionalNode<E> getNode(int index) {
        if (index >= -1 && index < numItems) {
            BidirectionalNode<E> curNode = head;
            for (int i = 0; i <= index; i++) {
                curNode = curNode.next;
            }
            return curNode;
        } else return null;
    }

    @Override
    public void set(int index, E x) {
        if (index >= 0 && index < numItems) {
            getNode(index).item = x;
        } else {/*에러처리*/}
    }

    @Override
    public int indexOf(E x) {
        BidirectionalNode<E> currNode = head;
        for (int i = 0; i < numItems; i++) {
            currNode = currNode.next;
            if (((Comparable) (currNode.item)).compareTo(x) == 0) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public int len() {
        return numItems;
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public void clear() {
        numItems = 0;
        head = new BidirectionalNode<>(null);
        head.next = head.prev = head;
    }

    public static void main(String[] args) {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(0, 300);
        list.add(0, 200);
        list.add(0, 100);
        list.append(500);
        list.append(600);
        list.remove(3);
        list.add(3, 250);
        list.add(1, 50);
        list.add(0, 10);
        list.append(700);
        list.remove(1);
        list.removeItem(600);
        for (int i = 0; i < list.len(); i++) {
            System.out.println(list.get(i));
        }
    }
}
