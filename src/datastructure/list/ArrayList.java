package datastructure.list;

public class ArrayList<E> implements ListInterface<E> {
    private E [] item;
    private int numItems;
    private static final int DEFAULT_CAPACITY = 64;
    private final int NOT_FOUND = -12345;

    public ArrayList() {
        item = (E[]) new Object[DEFAULT_CAPACITY];
        numItems = 0;
    }

    public ArrayList(int n) {
        item = (E[]) new Object[n];
        numItems = 0;
    }

    @Override
    public void add(int index, E x) {
        if (numItems >= item.length || index < 0 || index > numItems) {
            /* 에러 */
        } else {
            for (int i = numItems - 1; i >= index; i--)
                item[i + 1] = item[i];
            item[index] = x;
            numItems++;
        }
    }

    @Override
    public void append(E x) {
        if (numItems >= item.length) {
            /* 에러 */
        } else {
            item[numItems++] = x;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > numItems - 1 || isEmpty()) {
            return null;
        } else {
            E tmp = item[index];
            for (int i = index; i < numItems - 1; i++)
                item[i] = item[i + 1];
            numItems--;
            return tmp;
        }
    }

    @Override
    public boolean removeItem(E x) {
        int k = 0;
        while (k < numItems && ((Comparable)item[k]).compareTo(x) != 0) {
            k++;
        }
        if (k == numItems) {
            return false;
        } else {
            for (int i = k; i < numItems - 1; i++)
                item[i] = item[i + 1];
            numItems--;
            return true;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || isEmpty() || numItems - 1 < index) {
            return null;
        } else {
            return item[index];
        }
    }

    @Override
    public void set(int index, E x) {
        if (index < 0 || numItems - 1 < index) {
            /*에러*/
        } else {
            item[index] = x;
        }
    }

    @Override
    public int indexOf(E x) {
        for (int i = 0; i < numItems; i++) {
            if (((Comparable) item[i]).compareTo(x) == 0) {
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
        item = (E[]) new Object[item.length];
        numItems = 0;
    }

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0,300);
        list.add(0,200);
        list.add(0,100);
        list.append(500);
        list.append(600);
        list.remove(3);
        list.add(3,250);
        list.add(1,50);
        list.add(0,10);
        list.append(700);
        list.remove(1);
        list.removeItem(600);
        for (int i = 0; i < list.len() ; i++) {
            System.out.println(list.get(i));
        }
    }
}
