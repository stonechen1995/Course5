package roadgraph;

import java.util.Objects;
import geography.GeographicPoint;

/**
 * 
 * @author Stone
 *
 */
public class MapEdge {
	
	private MapNode from;
	private MapNode to;
	private String roadName;
	private String roadType;
	private double length;
	
	public MapEdge(MapNode from, MapNode to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		
		addEdge(from, to, roadName, roadType, length);
	}
	
	private void addEdge(MapNode from, MapNode to, String roadName,
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
		
		if (this.from.getLocation().getX() == ((MapEdge) other).from.getLocation().getX() &&
			this.from.getLocation().getY() == ((MapEdge) other).from.getLocation().getY() &&
			this.to.getLocation().getX() == ((MapEdge) other).to.getLocation().getX() &&
			this.to.getLocation().getY() == ((MapEdge) other).to.getLocation().getY()&&
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
	
	public MapNode getFrom() {
		return from;
	}
	
	public MapNode getTo() {
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
	
	public void setFrom(MapNode from) {
		this.from = from;
	}
	
	public void setTo(MapNode to) {
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
