package datastructure.heap;

public class Heap<E extends Comparable> implements PQInterface<E> {
	private E[] A;
	private int numItems;
	
	public Heap(int arraySize) {
		A = (E[]) new Comparable[arraySize]; //Comparable 객체로 이루어진 배열를 생성
	}
	
	public Heap(E[] B, int numElements) {
		A = B;
		numItems = numElements;
	}
	
	@Override
	public void insert(E newItem) throws Exception { // 마지막 노드에 값을 추가하고 부모노드를 따라가며 비교하면 된다.
		if(numItems < A.length) {
			A[numItems] = newItem;
			percolateUp(numItems);
			numItems++;
		} else throw new PQException("HeapErr: Insert()-Overflow");
	}
	
	
	public void percolateUp(int i) { // 스며오르기 i에서 부터 힙성질을 만족하도록 수선(재귀적으로 부모노드와 비교하기) O(logn)
		int parent = (i-1)/2;
		if (parent >= 0 && A[i].compareTo(A[parent])>0) {
			E temp = A[i];
			A[i] = A[parent];
			A[parent] = temp;
			percolateUp(parent);
		}
	}

	@Override
	public E deleteMax() throws Exception { //맨위의 노드에 맨뒤의 노드값을 복사하고 맨위노드부터 다시 정렬
		if(!isEmpty()) {
			E max =A[0]; 
			A[0] = A[numItems-1];
			numItems--;
			percolateDown(0);
			return max;
		}else throw new PQException("HeapErr: DeleteMax()-Underflow");
	}
	
	public void percolateDown(int i) { // 스며내리기 A[i]를 루트로 하는 서브 트리를 수선
		int child = (2*i)+1;
		int rchild = (2*i)+2;
		if(child<=numItems-1) {
			if(rchild <= numItems-1 && A[child].compareTo(A[rchild]) < 0) {
				child = rchild;
			}
			if(A[i].compareTo(A[child])<0) {
				E temp = A[i];
				A[i] = A[child];
				A[child] = temp;
				percolateDown(child);
			}
		}
	}
	
	public void buildHeap() {  //무작위 배열을 힙으로 만들기 O(n)
		if(numItems >= 2) {
			for(int i =(numItems-2)/2 ; i>= 0 ; i--) { //마지막 노드의 부모 노드 부터 첫번째 노드까지 정리해야함 
				//2번 노드를 루트로하는 트리가 힙조건을 만족하기 위해서는 
				//5,6번 노드를 루트로 하는 트리가 힙조건을 만족해야하기에 percolateDown()함수를 사용  
				percolateDown(i); 
			}
		}
	}
	
	@Override
	public E max() throws Exception {
		if(!isEmpty()) {
			return A[0];
		}else throw new PQException("HeapErr: Max()-Empty!");
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public void clear() {
		A = (E[]) new Comparable[A.length];
		numItems = 0;
	}
	
	public static void main(String[] args) throws Exception {
		Heap<Integer> h = new Heap<>(5);
		try {
			h.insert(1);
			h.insert(10);
			h.clear();
			h.insert(30);
			h.insert(10);
			h.insert(30);
			h.insert(20);
			h.insert(40); // 에러발생
			h.deleteMax();
			h.insert(1);
			h.insert(3);
			h.deleteMax();
		}catch(PQException e) {
			String msg = e.getMessage();
			System.out.println(msg);
		}
	}
	
}
