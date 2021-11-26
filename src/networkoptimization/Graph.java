package networkoptimization;

import java.util.ArrayList;


/**
 * @author Stone
 * 
 * 
 */
class Graph {

	//Class members
	private int numVertices; //number of vertices in the graph needs to be predefined
	private int numEdges;
	private ArrayList<Edge>[] adjListMap; //list of edges; adjListMap[i] represents a list a edges of vertex i
	//To return an edge set of a vertex, do "adjListMap[i]".
	
	//Constructor
	Graph(int numVertices) {
		this.numVertices = numVertices;
		numEdges = 0;
		adjListMap = new ArrayList[numVertices];

		for (int i = 0; i < numVertices; i++) {
			adjListMap[i] = new ArrayList<Edge>();
		}
	}

	//false means 
	void addEdge(int nodeA, int nodeB, int weight) {
		if (nodeA > numVertices || nodeA < 0 || nodeB > numVertices || 
				nodeB < 0 || weight < 0 || nodeA == nodeB )
			throw new IllegalArgumentException("Invalid arguments");

		if (this.getEdgeBtw(nodeA, nodeB) != null) {
//			System.out.println("Edge Exisiting, printing the graph: ");
//			this.printGraph();
			throw new NullPointerException("Edge existing");
		}
		
//		if (this.adjListMap[nodeA].contains(nodeB)) 
//			throw new NullPointerException("Edge existing");
		
		Edge newEdge = new Edge(nodeA, nodeB, weight);
		adjListMap[nodeA].add(newEdge);
		adjListMap[nodeB].add(newEdge);
		numEdges++;
	}
	
	ArrayList<Edge> getEdgesofNode(int node) {
		return adjListMap[node];
	}
	
	Edge getEdgeBtw(int nodeA, int nodeB) {
		ArrayList<Edge> edgeSet;
		if (this.getEdgesofNode(nodeA).size() < this.getEdgesofNode(nodeB).size()) {
			edgeSet = this.getEdgesofNode(nodeA);
			for (Edge edge : edgeSet) if (edge.getNode(nodeA) == nodeB) return edge;
		} else {
			edgeSet = this.getEdgesofNode(nodeB);
			for (Edge edge : edgeSet) if (edge.getNode(nodeB) == nodeA) return edge;
		}
		return null;
	}

	int getNumVertices()
	{
		return numVertices;
	}

	int getNumEdges()
	{
		return numEdges;
	}

	ArrayList<Edge>[] getMap() { 
		//TODO: a new copy or return the same one?
		return adjListMap;
	}

	void printGraphInfo() {
		System.out.println("numVertices: " + getNumVertices() + "; numEdges: " + getNumEdges() + "; avgDegree: " + getNumEdges()*2/getNumVertices() );
	}
	
	void printGraph() {
		System.out.println("numVertices: " + getNumVertices() + "; numEdges: " + getNumEdges() + "; avgDegree: " + getNumEdges()*2/getNumVertices() );
		for (int vertexIdx = 0; vertexIdx < this.getNumVertices(); vertexIdx++) {
			System.out.println(vertexIdx + ": (" + this.getEdgesofNode(vertexIdx).size() + " neighbors)");
			if (adjListMap[vertexIdx].isEmpty()) System.out.print("No incident edges!");
			else {
				for (Edge edge : adjListMap[vertexIdx]) {
					System.out.print("   ");
					edge.printEdge();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph testG1 = new Graph(8);
		testG1.addEdge(1, 2, 20);
		testG1.addEdge(3, 2, 20);
		testG1.addEdge(3, 4, 20);
		testG1.addEdge(5, 4, 20);
		testG1.addEdge(5, 6, 20);
		testG1.addEdge(6, 7, 20);
		testG1.addEdge(1, 0, 20);
		testG1.addEdge(0, 7, 20);
		
		try {
			testG1.addEdge(0, 7, 30);
		} catch (NullPointerException e) {
			System.out.println("Edge exisiting");
		}

		testG1.getEdgesofNode(1).get(0).setWeight(100);
		
		testG1.printGraph();
		
		System.out.println("find edge");
		testG1.getEdgeBtw(0,1).printEdge();

	}
	
}



