package networkoptimization;

import java.util.ArrayList;

/**
 * @author Stone
 * Heap Structure Write subroutines for the max-heap structure. 
 * In particular, your implementation should include subroutines for maximum, insert, and delete.
 * Since the heap structure you implement will be used for a Dijkstra-style algorithm in the routing protocol, 
 * we suggest the following data structures in your implementation:
 * (done)• The vertices of a graph are named by integers 0, 1, . . ., 4999;
 * (done)• The heap is given by an array H[5000], where each element H[i] gives the name of a vertex in the graph;
 * (done)• The vertex “values” are given in another array D[5000]. 
 *     Thus, to find the value of a vertex H[i] in the heap, we can use D[H[i]].
 * • In the operation delete(v) that deletes the vertex v from the heap H[5000], 
 *     you need to find the position of the vertex in the heap. For this, 
 *     you can use another array P[5000] so that P[v] is the position (i.e., index) of vertex v in the heap H[5000]. 
 *     Note that this array P[5000] should be modified accordingly when you move vertices in the heap H[5000].
 */

class MaxHeap1 {
	
	private ArrayList<Integer> verticesName; //each cell stores the vertex ID
    private ArrayList<Integer> data; //data[i] corresponds to vertices[i]
    
	MaxHeap1(int predefinedSize)
    {
		this.verticesName = new ArrayList<Integer>();
		this.data = new ArrayList<Integer>();
    }
	
	int size() {
		return verticesName.size();
	}
	
	private int getLeftChildNodeID(int nodeIdx) {
		return 2 * nodeIdx + 1;
	}
	
	private int getRightChildNodeID(int nodeIdx) {
		return 2 * nodeIdx + 2;
	}
	
	private int getParentID(int nodeIdx) {
		return (nodeIdx - 1) / 2;
	}
	
	int getMax() {
		return verticesName.get(0);
	}
	
	void insert(int vertexName, int value) {
		verticesName.add(vertexName);
		data.add(value);
		moveUp(verticesName.size()-1);
	}
	
	private void moveUp(int nodeIdx) {
		//System.out.println("move up: nodeIdx = " + nodeIdx + " > 0; data[nodeIdx] = " + data[nodeIdx] 
				//+ " < data[getParentID(nodeIdx)] = " + data[getParentID(nodeIdx)]);
		if (nodeIdx > 0 && data.get(nodeIdx) > data.get(getParentID(nodeIdx))) {
			//System.out.println("swapped");
			swap(nodeIdx, getParentID(nodeIdx));
			moveUp(getParentID(nodeIdx));
		}
	}
	
//	private void delete
	
	int remove(int nodeIdx) {
		swap(verticesName.size()-1, nodeIdx);
		int removed = verticesName.remove(verticesName.size()-1);
		data.remove(verticesName.size()-1);
		moveDown(nodeIdx);
		
		return removed;
//		return data[actualSize];
	}
	
	int remove() {
		return remove(0);
	}
	
	private void moveDown(int nodeIdx) {
		int temp = swapParentWithChildren(nodeIdx);
		if (temp != -1) 
			moveDown(temp);
	}
	
	//swap the parent with the largest child; return true if swapped, otherwise return false;
	private int swapParentWithChildren(int parentIdx) { 
		//System.out.println("swapParentWithChildren starts: " + "parentDataIdx = " + parentIdx);
		int maxIdx = parentIdx;
		
		if (getLeftChildNodeID(parentIdx) < verticesName.size() && 
				data.get(getLeftChildNodeID(parentIdx)) > data.get(maxIdx))
			maxIdx = getLeftChildNodeID(parentIdx);
		if (getRightChildNodeID(parentIdx) < verticesName.size() && 
				data.get(getRightChildNodeID(parentIdx)) > data.get(maxIdx))
			maxIdx = getRightChildNodeID(parentIdx);

		if (maxIdx != parentIdx) {
			swap(maxIdx, parentIdx);
			//printHeap();
			//System.out.println("swapped successed");
			return maxIdx; 
		}
		
		//System.out.println("swapped failed");
		return -1;
	}
	
	int getVertexName(int i) {
		return verticesName.get(i);
	}
	
	int getData(int i) {
		return data.get(i);
	}
	
	int getDataFromVertexName(int vertexName) {
		for (int i = 0; i < verticesName.size(); i++) {
			if (vertexName == verticesName.get(i))
				return data.get(i);
		}
		
		return -1;
	}
	
	private void swap(int nodeA, int nodeB) {
		int temp = verticesName.get(nodeA);
		verticesName.set(nodeA, verticesName.get(nodeB));
		verticesName.set(nodeB, temp);
		temp = data.get(nodeA);
		data.set(nodeA, data.get(nodeB));
		data.set(nodeB, temp);
	}
	
	public void printHeap()
    {
		System.out.println("Root: VertexName -> " + getVertexName(0)+ " : " + getData(0));
        for (int id = 0; id <= verticesName.size() / 2; id++) {
        	if (id < verticesName.size())
                System.out.print("(" + id + ")parent: " + verticesName.get(id) + " = " + data.get(id));
        	if (2 * id + 1 < verticesName.size())
            	System.out.print("; (" + (2 * id + 1) + ")left: " + verticesName.get(2 * id + 1) + " = " + data.get(2 * id + 1));
        	if (2 * id + 2 < verticesName.size())
            	System.out.print("; (" + (2 * id + 2) + ")right: " + verticesName.get(2 * id + 2) + " = " + data.get(2 * id + 2));
        	System.out.println();
        }
        System.out.println("");
    }
	
	public void printHeapArray()
    {
		System.out.println("Root: VertexName -> " + getVertexName(0)+ " : " + getData(0));
        for (int id = 0; id < verticesName.size(); id++) {
            System.out.println("(" + id + ")->" + verticesName.get(id) + " : " + data.get(id)); 
        }
        System.out.println();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        MaxHeap1 heapTest = new MaxHeap1(9);
//      heapTest.printHeap();
        heapTest.insert(0, 6);
//        heapTest.printHeap();
        heapTest.insert(1, 10);
//        heapTest.printHeap();
        heapTest.insert(2, 20);
//        heapTest.printHeap();
        heapTest.insert(3, 22);
//        heapTest.printHeap();
        heapTest.insert(4, 33);
//        heapTest.printHeap();
        heapTest.insert(5, 3);
//        heapTest.printHeap();
        heapTest.insert(6, 99);
//        heapTest.printHeap();
        heapTest.insert(7, 24);
//        heapTest.printHeap();
        heapTest.insert(8, 42);
        heapTest.printHeap();
        
        heapTest.remove(0);
        heapTest.remove(0);
        heapTest.printHeap();
 
	}
}