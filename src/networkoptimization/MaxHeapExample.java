package networkoptimization;

/**
 * @author Stone
 * Heap Structure Write subroutines for the max-heap structure. 
 * In particular, your implementation should include subroutines for maximum, insert, and delete.
 * Since the heap structure you implement will be used for a Dijkstra-style algorithm in the routing protocol, 
 * we suggest the following data structures in your implementation:
 * • The vertices of a graph are named by integers 0, 1, . . ., 4999;
 * • The heap is given by an array H[5000], where each element H[i] gives the name of a vertex in the graph;
 * • The vertex “values” are given in another array D[5000]. 
 *     Thus, to find the value of a vertex H[i] in the heap, we can use D[H[i]].
 * • In the operation delete(v) that deletes the vertex v from the heap H[5000], 
 *     you need to find the position of the vertex in the heap. For this, 
 *     you can use another array P[5000] so that P[v] is the position (i.e., index) of vertex v in the heap H[5000]. 
 *     Note that this array P[5000] should be modified according when you move vertices in the heap H[5000].
 */

class MaxHeapExample {
	private int[] heapArr;
    private int size;
    private int maxsize;
 
    // Constructor to initialize an
    // empty max heap with given maximum
    // capacity
    public MaxHeapExample(int maxsize)
    {
        // This keyword refers to current instance itself
        this.maxsize = maxsize;
        this.size = 0;
        heapArr = new int[this.maxsize];
    }
 
    // Returning position of parent
    private int parent(int pos) { 
    	return (pos - 1) / 2; 
    }

    // Returning left children
    private int leftChild(int pos) {
    	return (2 * pos); 
    }

    // Method 3
    // Returning left children
    private int rightChild(int pos)
    {
    	return (2 * pos) + 1;
    }

    // Method 4
    // Returning true of given node is leaf
    private boolean isLeaf(int pos)
    {
        if (pos > (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }
 
    // Method 5
    // Swapping nodes
    private void swap(int fpos, int spos)
    {
        int tmp;
        tmp = heapArr[fpos];
        heapArr[fpos] = heapArr[spos];
        heapArr[spos] = tmp;
    }
 
    // Method 6
    // Recursive function to max heapify given subtree
    private void maxHeapify(int pos)
    {
        if (isLeaf(pos))
            return;
 
        if (heapArr[pos] < heapArr[leftChild(pos)]
            || heapArr[pos] < heapArr[rightChild(pos)]) {
 
            if (heapArr[leftChild(pos)]
                > heapArr[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }
 
    // Method 7
    // Inserts a new element to max heap
    public void insert(int element)
    {
        heapArr[size] = element;
 
        // Traverse up and fix violated property
        int current = size;
        while (heapArr[current] > heapArr[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }
 
    // Method 8
    // To display heap
    public void print()
    {
        for (int i = 0; i <= size / 2; i++) {
            System.out.print(
                " PARENT : " + heapArr[i]
                + " LEFT CHILD : " + heapArr[2 * i + 1]
                + " RIGHT CHILD :" + heapArr[2 * i + 2]);
            System.out.println();
        }
    }
 
    // Method 9
    // Remove an element from max heap
    public int extractMax()
    {
        int popped = heapArr[1];
        heapArr[1] = heapArr[size--];
        maxHeapify(1);
        return popped;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Display message for better readability
        System.out.println("The Max Heap is ");
 
        MaxHeapExample maxHeap = new MaxHeapExample(15);
 
        // Inserting nodes
        // Custom inputs
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.insert(22);
        maxHeap.insert(9);
 
        // Calling maxHeap() as defined above
        maxHeap.print();
 
        // Print and display the maximum value in heap
        System.out.println("The max val is "
                           + maxHeap.extractMax());
	}
}