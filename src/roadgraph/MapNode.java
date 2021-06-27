/**
 * 
 */
package roadgraph;

import geography.GeographicPoint;

/**
 * @author Stone
 *
 */
public class MapNode implements Comparable<MapNode> {

//	private HashSet<MapEdge> edges;
	private GeographicPoint location;
	private double distance;
	
	public MapNode(GeographicPoint point) {
		if (point == null) throw new IllegalArgumentException();
		
		this.location = point;
		distance = Double.MAX_VALUE;
	}
	
	public MapNode(GeographicPoint point, double dist) {
		this(point);
		this.distance = dist;
	}
	
	public MapNode(double latitude, double longitude) {
		this(new GeographicPoint(latitude, longitude));
	}
	
	public MapNode(double latitude, double longitude, double dist) {
		this(new GeographicPoint(latitude, longitude), dist);
	}
	
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double num) {
		distance = num;
	}
	
	public GeographicPoint getLocation() {
		return location;
	}
	
	/**
	 * The instance of this class is compared by the field "distance"
	 * If the distance of this object is larger than the 
	 * node passed in as an argument, then return a positive value
	 * If the distance of this object is less than the 
	 * node passed in as an argument, then return a negative value
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
		
		return this.location.equals(node.location);
	}
	
	/** Because we compare nodes using their location, we also 
	 * may use their location for HashCode.
	 * @return The HashCode for this node, which is the HashCode for the 
	 * underlying point
	 */
	@Override
	public int hashCode() {
		return location.hashCode();
	}
	
}
