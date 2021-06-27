package roadgraph;

import java.util.Map;
import geography.GeographicPoint;

public class MapEdge {
	
	private GeographicPoint startLoc;
	private GeographicPoint endLoc;
	private String roadName;
	private String roadType;
	private double length;
	

	public MapEdge(GeographicPoint start, GeographicPoint end, String name, String type, double len) {
	
		this.startLoc = start;
		this.endLoc = end;
		this.roadName = name;
		this.roadType = type;
		this.length = len;
		
	}
	
	public GeographicPoint getStart() {
		
		return startLoc;
	}
	
	public GeographicPoint getEnd() {
		
		return endLoc;
	}
	
	public double getLength() {
		
		return length;
	}
	
	public String getRoadname() {
		
		return roadName;
	}
	
	public String getRoadType() {
		
		return roadType;
	}

	public String toString() {		
		return startLoc.toString() + " " + endLoc.toString();
	}



}
