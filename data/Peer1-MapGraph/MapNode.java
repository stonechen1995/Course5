package roadgraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import geography.GeographicPoint;


public class MapNode {

	private GeographicPoint location;	
	private List<MapEdge> edgeList;
	private List<GeographicPoint> neighbors;
	
	public MapNode(GeographicPoint loc) {
		location = loc;
		edgeList = new ArrayList<MapEdge>();
		neighbors = new LinkedList<GeographicPoint>();
	}
	
	public boolean addNewEdge(MapEdge edge) {
		if(!edgeList.contains(edge)) {
			return edgeList.add(edge);			
		}
		return false;
	}
	
	public GeographicPoint getLoc() {
		
		return location;
		
	}

	public List<GeographicPoint> getNeighbors() {
		List<GeographicPoint> neighbors = new LinkedList<GeographicPoint>();
				
		for(MapEdge e: edgeList) {
			neighbors.add(e.getEnd());
			neighbors.add(e.getStart());
		}		
		
		return neighbors;
	}
	
	
	public List<MapEdge> getEdges(){
		return edgeList;
	}
	
}
