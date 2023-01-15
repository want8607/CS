package datastructure.sort;

import datastructure.list.LinkedList;

public class Sorting {
	int A[];
	public Sorting(int B[]) {
		A = B;
	}
	//선택 정렬 - 배열에서 가장 큰수를 찾아서
	//맨 뒤로 보냄
	public void selectionSort() { 
		int k;
		int tmp;
		for(int last = A.length-1; last >= 1; last--) {
			k = theLargest(last); //A[]에서 가장 큰 수 찾기
			tmp = A[k];
			A[k] = A[last];
			A[last] = tmp;
		}
	}
	
	private int theLargest(int last) {
		int largest = 0;
		for (int i = 0; i < A.length; i++) {
			if(A[i] > A[largest]) largest = i;
		}
		return largest;
	}
	
	//버블 정렬
	public void bubbleSort() {
		for (int last = A.length-1; last >=2; last--) {
			boolean swapped = false;
			for (int i = 0; i < A.length; i++) {
				if(A[i]>A[i+1]) {
					int tmp = A[i];
					A[i] = A[i+1];
					A[i+1] = tmp;
					swapped = true;
				}
			}
			if(swapped == false) {	//스왑된적 없다면 정렬완료
					break;
			}	
		}
	}
	
	//삽입정렬
	public void insertionSort() {
		for (int i = 1; i < A.length; i++) {
			int loc = i-1;
			int newItem = A[i];
			while(loc >= 0 && newItem < A[loc]) {
				A[loc+1] = A[loc];
				loc--;
			}
			A[loc+1] = newItem;
		}
	}
	
	//병합정렬
	public void mergeSort() {
		int[] B = new int[A.length];
		mSort(0, A.length-1, B);
	}
	
	public void mSort(int p, int r, int[] B) { //
		if(p < r) {
			int q = (p+r)/2;
			mSort(p, q, B);
			mSort(q+1, r, B);
			merge(p, q, r, B);
		}
	}
	
	private void merge(int p, int q, int r, int[] B) {
		int i = p;
		int j = q+1;
		int t = 0;
		while(i <= q && j<=r) {
			if(A[i] <= A[j]) B[t++] = A[i++];
			else B[t++] = A[j++];
		}
		while(i<=q) {	// 배열왼쪽이 남은 경우
			B[t++] = A[i++]; 
		}
		while(j<=r) {	// 배열 오른쪽이 남은 경우
			B[t++] = A[j++];
		}
		i = p;
		t = 0;
		while(i<=r) {	//결과를 A[p..r]에 저장
			A[i++] = B[t++];
		}
	}
	
	// 퀵 정렬
	public void quickSort() {
		qSort(0, A.length-1);
	}
	
	//partition함수를 재귀적으로 부르는 과정에서 정렬이 이루어짐
	private void qSort(int p, int r) {
		if(p < r) {
			int q = partition(p,r);
			qSort(p, q-1);
			qSort(q+1, r);
		}
	}
	
	// 기준원소를 잡아서 기준원소보다작은 원소는 앞으로 큰원소는 뒤로 이동시킴
	private int partition(int p, int r) { 
		int x = A[r]; //맨뒤원소 기준
		int i = p - 1; // x보다 작은 원소들이 있는 구역의 끝지점
		int tmp ;
		for (int j = p; j < r; j++) {// j는 아직 정해지지 않은 구간의 시작지점
			if(A[j] <= x) {
				i++;
				tmp = A[i];
				A[i] = A[j];
				A[j] = tmp;
			}
		}
		tmp = A[i+1]; // 기준원소와 2구역 첫원소를 교환
		A[i+1] = A[r];
		A[r] = tmp;
		return i+1;
	}
	
	//셸 정렬 - 원소가 제자리에서 멀리 존재할 확률을 낮추고 삽입 정렬 실행
	public void shellSort() {
		for (int h = A.length/7; h > 5; h = h/5-1) {
			for(int k =0; k<=h-1; k++) {
				stepInsertionSort(k, h);
			}
			stepInsertionSort(0, 1);
		}
	}
	
	private void stepInsertionSort(int k, int h) {
		int j, insItem;
		for(int i = k+h; i < A.length; i+=h) {
			insItem = A[i];
			for(j= i-h; j >= 0 && A[j] >insItem; j-=h) {
				A[j+h] = A[j];
			}
			A[j+h] = insItem;
		}
	}
	
	//계수 정렬
	public int[] countingSort(int k) {
		int[] cnt = new int[k];
		for (int i = 0; i < k; i++) {
			cnt[i] = 0;
		}
		for(int i = 0; i < A.length; i++) {
			cnt[A[i]]++;
		}
		cnt[0]--;
		for (int i = 1; i < k; i++) {
			cnt[i] += cnt[i-1];
		}
		int[] B = new int[A.length];
		for (int j = A.length-1; j >=0; j--) {
			B[cnt[A[j]]] = A[j];
			cnt[A[j]]--;
		}
		return B;
	}
	
	//기수 정렬
	public void radixSort() {
		int[] cnt = new int[10], start = new int[10];
		int[] B = new int[A.length];
		int max = -1;
		for (int i = 0; i < A.length; i++) {
			if(A[i] > max) max = A[i];
		}
		int numDigits = (int)Math.log10(max) + 1;
		for(int digit =1; digit <= numDigits; digit++) {
			for(int d = 0; d <= 9; d++) {
				cnt[d] = 0;
			}
			for (int i = 0; i < A.length; i++) {
				cnt[(int)(A[i]/Math.pow(10,digit-1))%10]++;
			}
			start[0] = 0;
			for (int d = 1; d < 10; d++) {
				start[d] = start[d-1] + cnt[d-1];
			}
			for (int i = 0; i < A.length; i++) {
				B[start[(int)(A[i]/Math.pow(10, digit-1))%10]++] = A[i];
			}
			for (int i = 0; i < A.length; i++) {
				A[i] = B[i];
			}
		}
	}
	
	//버킷 정렬
	public void bucketSort() {
		LinkedList<Integer> B[];
		int numLists = A.length;
		B = new LinkedList[numLists];
		for (int i = 0; i < numLists; i++) {
			B[i] = new LinkedList();
		}
		int max;
		if(A[0] < A[1]) max =1;
		else max =0;
		for (int i = 2; i < A.length; i++) {
			if(A[max] < A[i]) max = i;
		}
		int band = A[max] +1;
		int bucketId;
		for (int i = 0; i < B.length; i++) {
			bucketId = (int)((float)(A[i]/band)*numLists);
			B[bucketId].add(0,A[i]);
		}
		int finger = 0, p, r = -1;
		for (int i = 0; i < numLists; i++) {
			for (int j = 0; j < B[i].len(); j++) {
				A[finger++] = B[i].getNode(j).item;
			}
			p = r+1; r= finger -1;
			rangeInsertionSort(p,r);
		}
	}
	
	private void rangeInsertionSort(int p, int r) {
		for (int i = p+1; i <= r; i++) {
			int loc = i-1;
			int x = A[i];
			while(loc >= p && x < A[loc]) {
				A[loc+1] = A[loc];
				loc--;
			}
			A[loc+1] = x;
		}
	}
}











