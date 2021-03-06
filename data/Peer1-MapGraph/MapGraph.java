/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
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
	
	
	private int numNodes;
	private int numEdges;
	private static Map<GeographicPoint, MapNode> nodes;
		
	//TODO: Add your member variables here in WEEK 3
	
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		numNodes = 0;
		numEdges = 0;
		nodes = new HashMap<GeographicPoint, MapNode>();
		// TODO: Implement in this constructor in WEEK 3
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
//	public int getNodes()
//	{
//		return numNodes;
//	}
	
	public int getNumVertices()
	{
		return numNodes;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		HashSet<GeographicPoint> gpSet = new HashSet<GeographicPoint>();
		for(GeographicPoint gp: nodes.keySet()) {
			gpSet.add(gp);
		}
		return gpSet;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		return numEdges;
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
		
		if(nodes.isEmpty()) {
			MapNode nieuw = new MapNode(location);
			nodes.put(location, nieuw);
			numNodes++;
			return true;
		}
		
		if(!nodes.containsKey(location)) {
			MapNode nieuw = new MapNode(location);
			nodes.put(location, nieuw);
			numNodes++;
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
		
		if(length < 0) {
			throw new IllegalArgumentException();
		}
		if(roadName == null) {
			throw new IllegalArgumentException();
		}
		if(roadType == null) {
			throw new IllegalArgumentException();
		}
		
		
		try {
		
			if(nodes.containsKey(from)&&nodes.containsKey(to)) {
				MapEdge nieuw = new MapEdge(from, to, roadName, roadType, length);
				
				MapNode curr = nodes.get(from);
				curr.addNewEdge(nieuw);
				curr = nodes.get(to);
				curr.addNewEdge(nieuw);
				numEdges++;
				
			}
			
		}
		catch(IllegalArgumentException e) {		
			e.printStackTrace();
		}	
		
		
	}
	
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	
	public boolean getOut(MapNode n) {
	//Tests whether there's an edge leaving the node
		
		for (MapEdge e: n.getEdges()) {
			if(e.getStart() == n.getLoc()) {
				return true;
			}			
		}
		
		return false;
	}
	
	
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		
		// Initialize
		MapNode startNode = nodes.get(start);
		MapNode goalNode = nodes.get(goal);
		
		//return null when there's no edge going out of the startnode
		if(getOut(startNode)==false) {
			System.out.println("No way out!  No path exists.");			
			return null;
			
		}

		
		if (startNode == null || goalNode == null) {			
			System.out.println("Start or goal node is null!  No path exists.");			
			return null;
		}

		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		
		//the method bfsSearch is called. I follow the example of maze.java (week3example)
		boolean found = bfsSearch(startNode, goalNode, parentMap, nodeSearched);

		if (!found) {
			System.out.println("No path exists");			
			return null;
		}
		
		//the method constructPath is called. I follow the example of maze.java (week3example) 
		List<GeographicPoint> route = constructPath(startNode, goalNode, parentMap);
		return route;
		
		
	}
	
	private static boolean bfsSearch(MapNode start, MapNode goal, 
			HashMap<GeographicPoint, GeographicPoint> parentMap, Consumer<GeographicPoint> nodeSearched) {
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		Queue<GeographicPoint> toExplore = new LinkedList<GeographicPoint>();
		
		toExplore.add(start.getLoc());
		boolean found = false;

		while (!toExplore.isEmpty()) {
			GeographicPoint curr = toExplore.remove();
			
			if (curr == goal.getLoc()) {
				found = true;
				break;
			}
			List<GeographicPoint> neighbors = nodes.get(curr).getNeighbors();		
			ListIterator<GeographicPoint> it = neighbors.listIterator(neighbors.size());
			while (it.hasPrevious()) {
				GeographicPoint next = it.previous();
				if (!visited.contains(next)) {
					visited.add(next);
					//next statement for the visualization in the front-end application
					nodeSearched.accept(next);
					parentMap.put(next, curr);
					toExplore.add(next);
				}
			}
		}
		return found;
	}
	
	private static List<GeographicPoint> constructPath(MapNode start, MapNode goal, HashMap<GeographicPoint, GeographicPoint> parentMap) {
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		GeographicPoint curr = goal.getLoc();
		while (curr != start.getLoc()) {
			path.addFirst(curr);
			curr = parentMap.get(curr);
		}
		path.addFirst(start.getLoc());
		return path;
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
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
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

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		//GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		GraphLoader.loadRoadMap("data/graders/mod2/ucsd.map", firstMap);
		System.out.println("DONE.");
		
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		
		/* Use this code in Week 3 End of Week Quiz */
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		//GraphLoader.loadRoadMap("data/graders/mod1/ucsd.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
//		start = new GeographicPoint(32.8756538, -117.2435715);
//		end = new GeographicPoint(32.8742087, -117.2381344);
//		end = new GeographicPoint(32.8757378, -117.2433273);
		List<GeographicPoint> route = theMap.bfs(start, end, null);
		System.out.println(route.size());
				
		
		//List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		
		
	}
	
}
