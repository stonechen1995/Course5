/**
 * 
 */
package roadgraph;

import java.util.HashSet;
import java.util.Set;

import geography.GeographicPoint;

/**
 * @author Stone
 *
 */
public class MapNode implements Comparable<MapNode> {
	
	//fields
	private GeographicPoint location;
	private HashSet<MapEdge> edgeSet;
	private double distance = Double.MAX_VALUE;
	
	//Constructors
	public MapNode(GeographicPoint location) {
		this.location = location;
		edgeSet = new HashSet<MapEdge>();
	}
	
	public MapNode(GeographicPoint location, double distacne) {
		this(location);
		this.distance = distacne;
	}
	
	//Methods
	public void addEdge(MapEdge edge) {
		edgeSet.add(edge);
	}
	
	public void removeEdge(MapEdge edge) {
		edgeSet.remove(edge);
	}
	
	public Set<MapEdge> getEdges() {
		return this.edgeSet;
	}
	
	public GeographicPoint getLocation() {
		return this.location;
	}
	
	public Set<GeographicPoint> getNeighbors() {
		Set<GeographicPoint> result = new HashSet<GeographicPoint>();
		for (MapEdge edge : edgeSet) {
			result.add(edge.getTo());
		}
		return result;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double num) {
		distance = num;
	}
	
	/**
	 * The instance of this class is compared by the field "distance"
	 * @param node is another MapNode to be compared with this object.
	 * @return positive number if the distance of this object is larger 
	 * than the node passed in as an argument.
	 * @return negative number if the distance of this object is less 
	 * than the node passed in as an argument.
	 */
	public int compareTo(MapNode node) {
		if (!(node instanceof MapNode) || node == null) 
			throw new IllegalArgumentException();
		
		return (int) (this.distance - node.distance);
	}
	
	/** Returns whether two nodes are equal.
	 * Nodes are considered equal if their locations are the same, 
	 * even if their street list is different.
	 * @param o the node to compare to
	 * @return true if these nodes are at the same location, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MapNode) || obj == null) 
			return false;
		
		MapNode node = (MapNode) obj;
		
		return node.location.equals(this.location);
	}
}
