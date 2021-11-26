package networkoptimization;

/**
 * @author Stone
 * Heap Structure Write subroutines for the max-heap structure. 
 * In particular, your implementation should include subroutines for maximum, insert, and delete.
 * Since the heap structure you implement will be used for a Dijkstra-style algorithm in the routing protocol, 
 * we suggest the following data structures in your implementation:
 * (done)• The vertices of a graph are named by integers 0, 1, . . ., 4999;
 * • The heap is given by an array H[5000], where each element H[i] gives the name of a vertex in the graph;
 * • The vertex “values” are given in another array D[5000]. 
 *     Thus, to find the value of a vertex H[i] in the heap, we can use D[H[i]].
 * • In the operation delete(v) that deletes the vertex v from the heap H[5000], 
 *     you need to find the position of the vertex in the heap. For this, 
 *     you can use another array P[5000] so that P[v] is the position (i.e., index) of vertex v in the heap H[5000]. 
 *     Note that this array P[5000] should be modified according when you move vertices in the heap H[5000].
 */

class MaxHeap {
	
	private int[] vertices; //each vertex is represented as a number
    private int[] values; //
    private int size; //size of the heap
    
	MaxHeap(int edgeSize)
    {
		this.vertices = new int[edgeSize];
		this.values = new int[edgeSize];
		this.size = edgeSize;
    }
	
	private int leftChild(int nodeIdx) {
		return 2 * nodeIdx - 1;
	}
	
	private int rightChild(int nodeIdx) {
		return 2 * nodeIdx - 2;
	}
	
	private int parent(int nodeIdx) {
		return (nodeIdx - 1) / 2;
	}
	
	void insert(int i) {
		
	}
	
	void remove(int i) {
		
	}
	
	void max(int i) {
		
	}
	
	private void maxHeapify() {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Display message for better readability
//        System.out.println("The Max Heap is ");
// 
//        MaxHeap maxHeap = new MaxHeap(15);
// 
//        // Inserting nodes
//        // Custom inputs
//        maxHeap.insert(5);
//        maxHeap.insert(3);
//        maxHeap.insert(17);
//        maxHeap.insert(10);
//        maxHeap.insert(84);
//        maxHeap.insert(19);
//        maxHeap.insert(6);
//        maxHeap.insert(22);
//        maxHeap.insert(9);
// 
//        // Calling maxHeap() as defined above
//        maxHeap.print();
// 
//        // Print and display the maximum value in heap
//        System.out.println("The max val is "
//                           + maxHeap.extractMax());
	}
}