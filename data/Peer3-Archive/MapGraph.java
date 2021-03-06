/**
 * @author UCSD MOOC development team and YOU
 * <p>
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between
 */
package roadgraph;


import geography.GeographicPoint;
import util.GraphLoader;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author UCSD MOOC development team and YOU
 * <p>
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between
 */
public class MapGraph {
    //TODO: Add your member variables here in WEEK 3
    private final Map<GeographicPoint, IntersectionNode> intersections;

    /**
     * Create a new empty MapGraph
     */
    public MapGraph() {
        intersections = new HashMap<>();
    }

    /**
     * Get the number of vertices (road intersections) in the graph
     *
     * @return The number of vertices in the graph.
     */
    public int getNumVertices() {
        return intersections.size();
    }

    /**
     * Return the intersections, which are the vertices in this graph.
     *
     * @return The vertices in this graph as GeographicPoints
     */
    public Set<GeographicPoint> getVertices() {
        return intersections.values()
                			.stream()
                			.map(IntersectionNode::getLocation)
                			.collect(Collectors.toSet());
    }

    /**
     * Get the number of road segments in the graph
     *
     * @return The number of edges in the graph.
     */
    public int getNumEdges() {
        return intersections.values().stream()
                .flatMap(s -> s.getEdges().stream())
                .collect(Collectors.toSet())
                .size();
    }


    /**
     * Add a node corresponding to an intersection at a Geographic Point
     * If the location is already in the graph or null, this method does
     * not change the graph.
     *
     * @param location The location of the intersection
     * @return true if a node was added, false if it was not (the node
     * was already in the graph, or the parameter is null).
     */
    public boolean addVertex(GeographicPoint location) {
        if (Objects.isNull(location) || this.intersections.containsKey(location)) {
            return false;
        }

        this.intersections.put(location, IntersectionNode.builder()
                .location(location)
                .build());

        return true;
    }

    /**
     * Adds a directed edge to the graph from pt1 to pt2.
     * Precondition: Both GeographicPoints have already been added to the graph
     *
     * @param from     The starting point of the edge
     * @param to       The ending point of the edge
     * @param roadName The name of the road
     * @param roadType The type of the road
     * @param length   The length of the road, in km
     * @throws IllegalArgumentException If the points have not already been
     *                                  added as nodes to the graph, if any of the arguments is null,
     *                                  or if the length is less than 0.
     */
    public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
                        String roadType, double length) throws IllegalArgumentException {
        if (Objects.isNull(from) || Objects.isNull(to) || Objects.isNull(roadName) || Objects.isNull(roadType)) {
            throw new IllegalArgumentException("Parameters have to be not null!");
        } else if (length < 0) {
            throw new IllegalArgumentException("Length can't be less than 0");
        } else if (!this.intersections.containsKey(from) || !this.intersections.containsKey(to)) {
            throw new IllegalArgumentException("Points not exist");
        }

        MapEdge mapEdge = MapEdge.builder()
                .distance(length)
                .start(from)
                .end(to)
                .streetName(roadName)
                .streetType(roadType)
                .build();

        intersections.get(from).addEdge(mapEdge);
    }


    /**
     * Find the path from start to goal using breadth first search
     *
     * @param start The starting location
     * @param goal  The goal location
     * @return The list of intersections that form the shortest (unweighted)
     * path from start to goal (including both start and goal).
     */
    public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
        // Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {
        };
        return bfs(start, goal, temp);
    }

    /**
     * Find the path from start to goal using breadth first search
     *
     * @param start        The starting location
     * @param goal         The goal location
     * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
     * @return The list of intersections that form the shortest (unweighted)
     * path from start to goal (including both start and goal).
     */
    public List<GeographicPoint> bfs(GeographicPoint start,
                                     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
        // TODO: Implement this method in WEEK 3
        Queue<GeographicPoint> queue = new ArrayDeque<>();
        Set<GeographicPoint> visited = new LinkedHashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            GeographicPoint current = queue.poll();

            nodeSearched.accept(current);

            if (current.equals(goal)) {
                return new ArrayList<>(visited);
            }

            List<GeographicPoint> connectedVertices = intersections.get(current).getEdges().stream()
                    .map(MapEdge::getEnd)
                    .collect(Collectors.toList());

            for (GeographicPoint connectedVertex : connectedVertices) {
                if (!visited.contains(connectedVertex)) {
                    visited.add(connectedVertex);
                    queue.add(connectedVertex);
                }
            }
        }

        return new ArrayList<>();
    }


    /**
     * Find the path from start to goal using Dijkstra's algorithm
     *
     * @param start The starting location
     * @param goal  The goal location
     * @return The list of intersections that form the shortest path from
     * start to goal (including both start and goal).
     */
    public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
        // Dummy variable for calling the search algorithms
        // You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {
        };
        return dijkstra(start, goal, temp);
    }

    /**
     * Find the path from start to goal using Dijkstra's algorithm
     *
     * @param start        The starting location
     * @param goal         The goal location
     * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
     * @return The list of intersections that form the shortest path from
     * start to goal (including both start and goal).
     */
    public List<GeographicPoint> dijkstra(GeographicPoint start,
                                          GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
        // TODO: Implement this method in WEEK 4

        // Hook for visualization.  See writeup.
        //nodeSearched.accept(next.getLocation());

        return null;
    }

    /**
     * Find the path from start to goal using A-Star search
     *
     * @param start The starting location
     * @param goal  The goal location
     * @return The list of intersections that form the shortest path from
     * start to goal (including both start and goal).
     */
    public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
        // Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {
        };
        return aStarSearch(start, goal, temp);
    }

    /**
     * Find the path from start to goal using A-Star search
     *
     * @param start        The starting location
     * @param goal         The goal location
     * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
     * @return The list of intersections that form the shortest path from
     * start to goal (including both start and goal).
     */
    public List<GeographicPoint> aStarSearch(GeographicPoint start,
                                             GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
        // TODO: Implement this method in WEEK 4

        // Hook for visualization.  See writeup.
        //nodeSearched.accept(next.getLocation());

        return null;
    }


    public static void main(String[] args) {
//        System.out.print("Making a new map...");
//        MapGraph firstMap = new MapGraph();
//        System.out.print("DONE. \nLoading the map...");
//        GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
//        System.out.println("DONE.");

        // You can use this method for testing.


        /* Here are some test cases you should try before you attempt
         * the Week 3 End of Week Quiz, EVEN IF you score 100% on the
         * programming assignment.
         */

        MapGraph simpleTestMap = new MapGraph();
        GraphLoader.loadRoadMap("data/maps/ucsd.map", simpleTestMap);

        //Lat: 32.8762498, Lon: -117.2438229
        GeographicPoint testStart = new GeographicPoint(32.8762498, -117.2438229);
        //Lat: 32.8765435, Lon: -117.2435225
        GeographicPoint testEnd = new GeographicPoint(32.8765435, -117.2435225);

        simpleTestMap.bfs(testStart, testEnd, System.out::println);


		/*
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
		*/


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
