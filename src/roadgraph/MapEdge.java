package roadgraph;

import java.util.Objects;
import geography.GeographicPoint;

/**
 * 
 * @author Stone
 *
 */
public class MapEdge {
	
	private GeographicPoint from;
	private GeographicPoint to;
	private String roadName;
	private String roadType;
	private double length;
	
	public MapEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		
		addEdge(from, to, roadName, roadType, length);
	}
	
	private void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		//TODO: Implement this method in WEEK 3
		if (from == null || to == null || roadName == null || roadType == null || length < 0)
			throw new IllegalArgumentException("Invalid arguments in MapEdge.addEdge() method");
		
		this.from = from;
		this.to = to;
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof MapEdge)) throw new IllegalArgumentException("Invalid argument in MapEdge.equals() method");
		
		if (this.from.getX() == ((MapEdge) other).from.getX() &&
			this.from.getY() == ((MapEdge) other).from.getY() &&
			this.to.getX() == ((MapEdge) other).to.getX() &&
			this.to.getY() == ((MapEdge) other).to.getY()&&
			this.roadName == ((MapEdge) other).roadName && 
			this.roadType == ((MapEdge) other).roadType &&
			this.length == ((MapEdge) other).length) {
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
