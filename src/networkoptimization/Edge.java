/**
 * 
 */
package networkoptimization;

/**
 * @author henghong
 *
 */
class Edge {

	private int nodeA;
	private int nodeB;
	private int weight;
	
	
	Edge(int nodeA, int nodeB, int weight) {
		this.nodeA = nodeA; 
		this.nodeB = nodeB;
		this.weight = weight;
	}
	
	int getNodeA() {
		return nodeA;
	}
	
	int getNodeB() {
		return nodeB;
	}
	
	int getWeight() {
		return this.weight;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		;
	}

}
