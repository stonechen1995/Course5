package roadgraph;

import geography.GeographicPoint;

import java.util.ArrayList;
import java.util.List;

public class IntersectionNode {
    private GeographicPoint location;
    private List<MapEdge> edges;

    public IntersectionNode() {
        this.edges = new ArrayList<>();
    }

    public GeographicPoint getLocation() {
        return location;
    }

    public void setLocation(GeographicPoint location) {
        this.location = location;
    }

    public List<MapEdge> getEdges() {
        return new ArrayList<>(edges);
    }

    public void addEdge(MapEdge edge) {
        this.edges.add(edge);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private GeographicPoint location;
        private List<MapEdge> edges;

        private Builder() {
            this.edges = new ArrayList<>();
        }

        public Builder location(GeographicPoint location) {
            this.location = location;
            return this;
        }

        public Builder edges(List<MapEdge> edges) {
            this.edges = edges;
            return this;
        }

        public IntersectionNode build() {
            IntersectionNode intersectionNode = new IntersectionNode();
            intersectionNode.location = location;
            intersectionNode.edges = edges;

            return intersectionNode;
        }
    }
}
