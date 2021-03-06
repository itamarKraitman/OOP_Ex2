package main.java.GraphClass;

import main.java.api.GeoLocation;

public class Geo_Location implements GeoLocation {

    private final double x;
    private final double y;
    private final double z;

    // Constructor
    public Geo_Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Geo_Location(Geo_Location g) {
        this.x = g.x();
        this.y = g.y();
        this.z = g.z();
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        double d_x = Math.pow((g.x() - this.x), 2);
        double d_y = Math.pow((g.y() - this.y), 2);
        double d_z = Math.pow((g.z() - this.z), 2);
        return Math.sqrt(d_x + d_y + d_z);
    }

    public String toString() {
        return "(" + this.x + "," + this.y + "," + this.z + ")";
    }
}
