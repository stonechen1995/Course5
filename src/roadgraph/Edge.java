package roadgraph;

import java.util.Objects;
import geography.GeographicPoint;

/**
 * 
 * @author Stone
 *
 */
public class Edge {
	
	private GeographicPoint from;
	private GeographicPoint to;
	private String roadName;
	private String roadType;
	private double length;
	
	public Edge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		
		addEdge(from, to, roadName, roadType, length);
	}
	
	private void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		//TODO: Implement this method in WEEK 3
		if (from == null || to == null || roadName == null || roadType == null || length < 0)
			throw new IllegalArgumentException("Invalid arguments in Edge.addEdge() method");
		
		this.from = from;
		this.to = to;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Edge)) throw new IllegalArgumentException("Invalid argument in Edge.equals() method");
		
		if (this.from.getX() == ((Edge) other).from.getX() &&
			this.from.getY() == ((Edge) other).from.getY() &&
			this.to.getX() == ((Edge) other).to.getX() &&
			this.to.getY() == ((Edge) other).to.getY()&&
			this.roadName == ((Edge) other).roadName && 
			this.roadType == ((Edge) other).roadType &&
			this.length == ((Edge) other).length) {
			return true;
		}
		return false;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(from, to, roadName, roadType, length);
    }
	
	public GeographicPoint getFrom() {
		return from;
	}
	
	public GeographicPoint getTo() {
		return to;
	}
	
	public String getRoadName() {
		return roadName;
	}
	
	public String getRoadType() {
		return roadType;
	}
	
	public double getLength() {
		return length;
	}
	
	public void setFrom(GeographicPoint from) {
		this.from = from;
	}
	
	public void setTo(GeographicPoint to) {
		this.to = to;
	}
	
	public void setRoadName(String name) {
		this.roadName = name;
	}
	
	public void setRoadType(String type) {
		this.roadType = type;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public String toString() {		
		return "From: " + getFrom().toString() + ", To: " + getTo().toString()
				+ "; Name: " + getRoadName() + "; + Type:" + getRoadType()
				+ "; + length: " + getLength();
	}

}
