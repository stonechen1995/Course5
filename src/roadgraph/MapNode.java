/**
 * 
 */
package roadgraph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
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
	
	public Set<MapNode> getNeighbors() {
		Set<MapNode> result = new HashSet<MapNode>();
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
//		System.out.println("this.distance: " + this.distance);
//		System.out.println("node.distance: " + node.distance);
		
		System.out.println(this.location + " - " + node.location + " ");
		System.out.println("    " + this.distance + " - " + node.distance + " = " + (this.distance-node.distance));
		
		if (this.distance - node.distance > 0) return 1;
		else if (this.distance - node.distance < 0) return -1;
		else return 0;
	}

	/** Because we compare nodes using their location, we also 
	 * may use their location for HashCode.
	 * @return The HashCode for this node, which is the HashCode for the 
	 * underlying point
	 */
	@Override
	public int hashCode() {
		return Objects.hash(location, distance);
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
		
		return node.location.equals(this.location) && 
				this.distance == node.distance;
	}

	@Override
	public String toString() {
		return "MapNode [location=" + location + ", distance=" + distance + "]";
	}

	/**
	 * The instance of this class is compared by the field "distance"
	 * @param node is another MapNode to be compared with this object.
	 * @return positive number if the distance of this object is larger 
	 * than the node passed in as an argument.
	 * @return negative number if the distance of this object is less 
	 * than the node passed in as an argument.
	 */
//	@Override
//	public int compare(MapNode node1, MapNode node2) {
//		// TODO Auto-generated method stub
//		if (!(node1 instanceof MapNode) || node1 == null ||
//			!(node2 instanceof MapNode) || node2 == null) 
//			throw new IllegalArgumentException();
//
//		return node1.compareTo(node2);
//	}
	
	
}

//class ComparatorByDistance implements Comparator<String> {
//    public int compare(String str1, String str2) {
//        String first_Str;
//        String second_Str;
//        first_Str = str1;
//        second_Str = str2;
//        return second_Str.compareTo(first_Str);
//    }
//}
