package main.java;

import main.java.api.GeoLocation;
import main.java.api.NodeData;


public class Node implements NodeData {

    private Geo_Location position;
    private final int key;
    private double weight;
    private transient String info;
    private transient int tag;

    // Constructor
    public Node(int key, Geo_Location loc) {
        this.key = key;
        this.position = new Geo_Location(loc);
        this.info = "";
        this.weight = 0;
        this.tag = 0;
        // Info is generated through toString() & setInfo()
    }

    // Deep copy constructor
    public Node(Node n) {
        this.key = n.getKey();
        this.position = new Geo_Location((Geo_Location) n.getPosition());
        this.info = n.getInfo();
        this.weight = n.weight;
        this.tag = n.getTag();
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(GeoLocation p) {
        this.position = new Geo_Location((Geo_Location) p);
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return "\"pos:\" \"" + this.getPosition().x() + "," + this.getPosition().y() + "," + this.getPosition().z() + "\",\n\"id:\" " + this.getKey();
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }


//    private String toString(DWGraph graph) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Node: ").append(this.getKey()).append(".\n");
//        sb.append("Position: ").append(this.getPosition().toString()).append(".\n");
//        sb.append("Edges from: ");
//        if (!graph.getEdgesFrom(this.key).isEmpty()) {
//            for (int i = 0; i < graph.getEdgesFrom(this.key).size(); i++) {
//                sb.append(graph.getEdgesFrom(this.key).get(i).toString()).append("\n");
//            }
//            sb.append(".\n");
//        }
//        sb.append("Tag: ").append(this.getTag()).append(".\n");
//        return sb.substring(0);
//    }

}
