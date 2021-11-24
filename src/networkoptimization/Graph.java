package networkoptimization;

import java.util.ArrayList;

import roadgraph.MapNode;

/**
 * @author Stone
 * 
 * 
 */
class Graph {
	
	//Class members
	private int numVertices;
	private int numEdges;
	private ArrayList<Edge>[] adjListMap; //list of edges; adjListMap[i] represents a list a edges of vertex i
	
	//Constructor
	Graph(int numVertices) {
		this.numVertices = numVertices;
		numEdges = 0;
		adjListMap = new ArrayList[numVertices];

		for (int i = 0; i < numVertices; i++) {
			adjListMap[i] = new ArrayList<Edge>();
        }
	}
	
	void addEdge(int nodeA, int nodeB, int weight) {
		if (nodeA > numVertices || nodeA < 0 || nodeB > numVertices || nodeB < 0 || weight < 0 )
			throw new IllegalArgumentException("Invalid arguments");

		Edge newEdge = new Edge(nodeA, nodeB, weight);
		adjListMap[nodeA].add(newEdge);
		adjListMap[nodeB].add(newEdge);
		numEdges++;
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

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
