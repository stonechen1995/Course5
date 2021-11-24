package networkoptimization;

import java.util.ArrayList;

class Node {
	
	private int index;
	private ArrayList<Edge> edges;
	private double distance = Double.MAX_VALUE;
	
	Node(int index) {
		this.index = index;
	}
	
	void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	void removeEdge(int edge) {
		edges.remove(edge);
	}
	
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}
	
//	public ArrayList<Node> getNeighbors() {
//		ArrayList<Node> result = new ArrayList<Node>();
//		for (Edge edge : edges) {
//			result.add(edge.getNodeA(index));
//		}
//		return result;
//	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double num) {
		distance = num;
	}

}
