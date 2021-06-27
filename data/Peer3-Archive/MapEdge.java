package roadgraph;

import geography.GeographicPoint;

import java.util.Objects;

public class MapEdge {
    private GeographicPoint start;
    private GeographicPoint end;
    private String streetName;
    private String streetType;
    private double distance;

    public GeographicPoint getStart() {
        return start;
    }

    public void setStart(GeographicPoint start) {
        this.start = start;
    }

    public GeographicPoint getEnd() {
        return end;
    }

    public void setEnd(GeographicPoint end) {
        this.end = end;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapEdge mapEdge = (MapEdge) o;
        return Double.compare(mapEdge.distance, distance) == 0 && Objects.equals(start, mapEdge.start) && Objects.equals(end, mapEdge.end) && Objects.equals(streetName, mapEdge.streetName) && Objects.equals(streetType, mapEdge.streetType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, streetName, streetType, distance);
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private GeographicPoint start;
        private GeographicPoint end;
        private String streetName;
        private String streetType;
        private double distance;

        private Builder() {
        }

        public Builder start(GeographicPoint start) {
            this.start = start;
            return this;
        }

        public Builder end(GeographicPoint end) {
            this.end = end;
            return this;
        }

        public Builder streetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder streetType(String streetType) {
            this.streetType = streetType;
            return this;
        }

        public Builder distance(double distance) {
            this.distance = distance;
            return this;
        }

        public MapEdge build() {
            MapEdge mapEdge = new MapEdge();
            mapEdge.setStart(start);
            mapEdge.setEnd(end);
            mapEdge.setStreetName(streetName);
            mapEdge.setStreetType(streetType);
            mapEdge.setDistance(distance);
            return mapEdge;
        }
    }
}
