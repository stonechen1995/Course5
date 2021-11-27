package networkoptimization;


import java.util.ArrayList;

/**
 * Created by Songyuan on 3/27/17.
 */
public class MaxHeapExample2 {

	//private static Edge[] maxHeap;
	private static int[] vertices;
	private static int[] value;
	private static int heapNumber;

	public MaxHeapExample2(int edgeNumber) {
		vertices = new int[edgeNumber + 1];
		value = new int[edgeNumber + 1];
		heapNumber = 0;
	}

	public static int getHeapNumber() {
		return heapNumber;
	}

	public static int getIndex(int index) {
		return  vertices[index];
	}

	public static void insert(int vertex, int bw) {
		heapNumber++;
		vertices[heapNumber] = vertex;
		value[heapNumber] = bw;
		heapfy(heapNumber);
	}

	public static int maximum() {
		return vertices[1];
	}

	public static void delete(int index) {
		vertices[index] = vertices[heapNumber];
		value[index] = value[heapNumber];
		heapNumber--;
		heapfy(index);
	}

	public static void heapfy(int k) {
		int l = 2 * k + 1;
		int r = 2 * k + 2;
		int largest = k;
		if (l <= heapNumber && value[l] > value[k]) {
			largest = l;
		}
		if (r <= heapNumber && value[r] > value[largest]) {
			largest = r;
		}
		if (largest != k) {
			swap(largest, k);
			heapfy(largest);
		}
	}

	public static void swap(int pos1, int pos2) {
		int tempVertices = vertices[pos1];
		vertices[pos1] = vertices[pos2];
		vertices[pos2] = tempVertices;
		int tempValue = value[pos1];
		value[pos1] = value[pos2];
		value[pos2] = tempValue;
	}

	public void print()
    {
//		if (!updated) heapify(); 
        for (int id = 0; id <= heapNumber / 2; id++) {
            System.out.print("(" + id + ")parent: " + vertices[id] + " = " + value[id]); 
            try {
            	System.out.print("; (" + (2 * id + 1) + ")left: " + vertices[2 * id + 1] + " = " + value[2 * id + 1]);
            	System.out.println("; (" + (2 * id + 2) + ")right: " + vertices[2 * id + 2] + " = " + value[2 * id + 2]);
            } catch (Exception e) {
            	;
            }
        }
        System.out.println("");
    }
	
	public static void main(String[] args) {
		MaxHeapExample2 heapTest = new MaxHeapExample2(9);
        heapTest.insert(0, 6);
        heapTest.insert(1, 10);
        heapTest.insert(2, 20);
        heapTest.insert(3, 22);
        heapTest.insert(4, 33);
        heapTest.insert(5, 3);
        heapTest.insert(6, 99);
        heapTest.insert(7, 24);
        heapTest.insert(8, 42);
        
		System.out.println("TEST " + heapTest.maximum());
        heapTest.delete(1);
		System.out.println("TEST " + heapTest.maximum());
		heapTest.delete(1);
		System.out.println("TEST " + heapTest.maximum());
		heapTest.delete(1);
		System.out.println("TEST " + heapTest.maximum());
		heapTest.delete(1);
		System.out.println("TEST " + heapTest.maximum());
		heapTest.delete(1);
		System.out.println("TEST " + heapTest.maximum());
		


		
		/*
		MaxHeapExample2 newHeap = new MaxHeapExample2(10);
		int max;
		newHeap.insert(1,23);
		max = newHeap.maximum();
		System.out.println("TEST " + max);
		newHeap.insert(3,12);
		max = newHeap.maximum();
		System.out.println("TEST " + max);
		newHeap.insert(6,121);
		max = newHeap.maximum();
		System.out.println("TEST " + max);
		newHeap.delete(1);
		newHeap.insert(4,29);
		max = newHeap.maximum();
		System.out.println("TEST " + max);
		*/
	}
}
