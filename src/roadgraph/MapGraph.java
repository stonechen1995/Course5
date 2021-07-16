/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private HashMap<GeographicPoint, MapNode> nodeMap;
//	private HashMap<GeographicPoint, HashSet<MapEdge>> adjListMap; //HashSet<MapEdge> includes outgoing edges
	
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		nodeMap = new HashMap<GeographicPoint, MapNode>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return nodeMap.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3 
		return nodeMap.keySet();

	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		int num = 0;
		for (MapNode mn : nodeMap.values()) {
			num += mn.getEdges().size();
		}
		return num;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		if (!nodeMap.containsKey(location)) {
			nodeMap.put(location, new MapNode(location));
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		//TODO: Implement this method in WEEK 3
		
		MapNode fromNode = nodeMap.get(from);
		MapNode toNode = nodeMap.get(to);
		
		if (!this.containsVertex(from) || !this.containsVertex(to) ||
				from == null || to == null || roadName == null || roadType == null
				|| length < 0)
			throw new IllegalArgumentException("Invalid arguments");

		MapEdge edge = new MapEdge(fromNode, toNode, roadName, roadType, length);
		nodeMap.get(from).addEdge(edge);
	}
	
	/**
	 * Check if map contains the vertex.
	 * @param point The point to be checked.
	 * @return true if contained; false if not contained.
	 */
	public boolean containsVertex(GeographicPoint point) {
		if (nodeMap.containsKey(point)) 
			return true;
		return false;
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		// Hook for visualization.  See writeup.
		if (start == null || goal == null) return null;
	
		Queue<GeographicPoint> queue = new LinkedList<GeographicPoint>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		queue.add(start);
		while (!queue.isEmpty()) {
			GeographicPoint curr = queue.remove();
			visited.add(curr);
			nodeSearched.accept(curr);
			
			if(curr.equals(goal))
				return constructPath(start, curr, parentMap);
			
			enQueueNeighborsOfNode(curr, parentMap, visited, queue);
		}
		return null;
	}

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** 
	 * Enqueue all the neighbor nodes of the current node to the queue. Meanwhile, all neighbor nodes will be
	 * added to the visited HashSet and 
	 * @param node The node of which all neighbors ready to be enqueued.
	 * @param parent The parent map to track parent from chile
	 * @param visited The Visited HashSet is to log all nodes that have been enqueued to queue
	 * @param queue 
	 *
	private void enQueueNeighborsOfNode(GeographicPoint node, HashMap<GeographicPoint, GeographicPoint> parent,
			HashSet<GeographicPoint> visited, Queue<GeographicPoint> queue
			) {
		MapNode mn = nodeMap.get(node);
		for (MapEdge eg : mn.getEdges()) {
			if (!visited.contains(eg.getTo().getLocation())) {
				GeographicPoint destination = eg.getTo().getLocation();
				queue.add(destination);
				visited.add(destination);
				parent.put(destination, node);
			}
		}
	}*/
	@SuppressWarnings("unchecked")
	private <E> void enQueueNeighborsOfNode(E node, HashMap<E, E> parent,
			HashSet<E> visited, Queue<E> queue
			) {
		MapNode mn = nodeMap.get(node);
		for (MapEdge eg : mn.getEdges()) {
			if (!visited.contains(eg.getTo().getLocation())) {
				E destination = null;
				if (node instanceof GeographicPoint) 
					destination = (E) eg.getTo().getLocation();
				else if (node instanceof MapNode)
					destination = (E) eg.getTo();

				queue.add(destination);
				visited.add(destination);
				parent.put(destination, node);
			}
		}
	}
	
	private LinkedList<GeographicPoint> constructPath(GeographicPoint start, GeographicPoint goal, 
												HashMap<GeographicPoint, GeographicPoint> parentMap) 
												throws IllegalArgumentException {
		if (start == null || !parentMap.containsKey(goal)) 
			throw new IllegalArgumentException();
		LinkedList<GeographicPoint> result = new LinkedList<GeographicPoint>();
		GeographicPoint curr = goal;
		while(!curr.equals(start)) {
			result.addFirst(curr);
			curr = parentMap.get(curr);
		}
		result.addFirst(curr);
		return result;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
//	public List<GeographicPoint> bfs(GeographicPoint start, 
//			GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
//	{
//		// TODO: Implement this method in WEEK 3
//		// Hook for visualization.  See writeup.
//		if (start == null || goal == null) return null;
//
//		Queue<GeographicPoint> queue = new LinkedList<GeographicPoint>();
//		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
//		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
//		queue.add(start);
//		while (!queue.isEmpty()) {
//			GeographicPoint curr = queue.remove();
//			visited.add(curr);
//			nodeSearched.accept(curr);
//
//			if(curr.equals(goal))
//				return constructPath(start, curr, parentMap);
//
//			enQueueNeighborsOfNode(curr, parentMap, visited, queue);
//		}
//		return null;
//	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		if (start == null || goal == null) return null;

		PriorityQueue<MapNode> pq = new PriorityQueue<MapNode>();
		HashSet<MapNode> visited = new HashSet<MapNode>();
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint> ();
		MapNode startNode = nodeMap.get(start);
		startNode.setDistance(0);
		
		pq.add(startNode);
		int count = 0;
		while (!pq.isEmpty()) {
//			System.out.println("");
//			printQueue(new PriorityQueue(pq));
			MapNode curr = pq.remove();
//			System.out.println(curr.getLocation() + " --- head node chekced out");
			if (curr.getLocation().equals(goal)) {
//				System.out.println("find the goal");
				break;
			}
			if (!visited.contains(curr)) {
				visited.add(curr);
//				System.out.println(curr.getLocation() + " --- head node added to visited");
				for (MapEdge eg : curr.getEdges()) {
					MapNode next = eg.getTo();
					if (!visited.contains(next)) {
						next.setDistance(curr.getDistance() + eg.getLength());
						pq.add(next);
//						System.out.println(next.getLocation() + " --- neighbor added to pq");
						parentMap.put(next.getLocation(), curr.getLocation());
					}
				}
			}
		}
		return constructPath(start, goal, parentMap);
	}
	
	/**
	 * CAUTION: The input argument needs to be a NEW instance as this method will
	 * remove each element at head position while its execution.
	 * @param <T>
	 * @param queue
	 */
	private <T> void printQueue(Queue<T> queue) {
		System.out.println("print out the queue:");
		int count = 0;
		while (!queue.isEmpty()) {
			T node = queue.remove();
			System.out.println("  " + node);
//			count++;
			if (count >= 1) break;
		}
		System.out.println("print out the queue completed");
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}
	
	/** Print the graph
	 */
	public void printGraph() {
		
	}
	
	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
//		MapGraph simpleTestMap = new MapGraph();
//		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
//		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
//		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
//		
//		System.out.println("Test A using simpletest: Dijkstra should be 9 and AStar should be 5");
//		List<GeographicPoint> list = simpleTestMap.bfs(testStart, testEnd);
//		for (GeographicPoint node : list) {
//			System.out.println(node.getX() + ", " + node.getY());
//		}
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 0 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> list = simpleTestMap.bfs(testStart, testEnd);
		for (GeographicPoint node : list) {
			System.out.println(node.getX() + ", " + node.getY());
		}
		
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
//		MapGraph testMap = new MapGraph();
//		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
//		
//		// A very simple test using real data
//		testStart = new GeographicPoint(32.869423, -117.220917);
//		testEnd = new GeographicPoint(32.869255, -117.216927);
//		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
//		testroute = testMap.dijkstra(testStart,testEnd);
//		testroute2 = testMap.aStarSearch(testStart,testEnd);
//		
//		
//		// A slightly more complex test using real data
//		testStart = new GeographicPoint(32.8674388, -117.2190213);
//		testEnd = new GeographicPoint(32.8697828, -117.2244506);
//		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
//		testroute = testMap.dijkstra(testStart,testEnd);
//		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
