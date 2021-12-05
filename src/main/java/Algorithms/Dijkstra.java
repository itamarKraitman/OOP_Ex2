package main.java.Algorithms;

import main.java.api.DirectedWeightedGraph;
import main.java.api.DirectedWeightedGraphAlgorithms;
import main.java.api.EdgeData;
import main.java.api.NodeData;

import java.util.*;

public class Dijkstra {
    NodeData src;
    DirectedWeightedGraph currentGraph;
    HashMap<Integer, Integer> parents;
    HashMap<Integer, Double> distance;
    private final double EPS = 1e-100;


    public Dijkstra(DirectedWeightedGraph g, NodeData src) {
        this.currentGraph = g;
        this.src = src;
    }

    void DijkstraAlgo(int src) {
        NodeData current;
        Iterator<NodeData> iteratorNodes = this.currentGraph.nodeIter();
        this.parents = new HashMap<>();
        this.distance = new HashMap<>();
        while (iteratorNodes.hasNext()) {
            current = iteratorNodes.next();
            this.parents.put(current.getKey(), -1);
            this.distance.put(current.getKey(), Double.MAX_VALUE);
        }
        this.distance.replace(src, 0.0);
        Comparator<NodeData> comparator = (o1, o2) -> {
            if (Math.abs((this.distance.get(o1.getKey()) - this.distance.get(o2.getKey()))) < EPS) {
                return 0;
            } else {
                return (this.distance.get(o1.getKey()) - this.distance.get(o2.getKey()) > 0 ? +1 : -1);
            }
        };
        // initial capacity via veterans in stack overflow
        PriorityQueue<NodeData> pq = new PriorityQueue<>(this.distance.size() * 2, comparator);
        pq.offer(this.currentGraph.getNode(src));
        while (!pq.isEmpty()) {
            current = pq.poll();
            Iterator<EdgeData> destOfOutgoingEdge = this.currentGraph.edgeIter(current.getKey());
            // iterating over all adjacent of current
            while (destOfOutgoingEdge.hasNext()) { // for all edges which
                EdgeData edgeToAdj = destOfOutgoingEdge.next();
                if (this.distance.get(edgeToAdj.getDest()) > this.distance.get(current.getKey()) + edgeToAdj.getWeight()) {
                    this.distance.put(edgeToAdj.getDest(), this.distance.get(current.getKey()) + edgeToAdj.getWeight());
                    pq.offer(this.currentGraph.getNode(edgeToAdj.getDest()));
                    this.parents.put(edgeToAdj.getDest(), current.getKey());
                }
            }
        }
    }

    double getDistBetSrcToDest(int dest){
        return this.distance.get(dest);
    }

    double maxLongestDist(){
        double longest = Double.MIN_VALUE;
        for (double dist : this.distance.values()){
            if (dist > longest){
                longest = dist;
            }
        }
        return longest;
    }

    List<NodeData> shortestPathNodes(int dest){
        LinkedList<NodeData> list = new LinkedList<>();
        int pointer = dest;
        while (this.parents.get(pointer) != -1){
            list.addFirst(this.currentGraph.getNode(pointer));
            pointer = this.parents.get(pointer);
        }
        if (pointer == this.src.getKey()){
            list.addFirst(this.src);
        }
        if (list.getFirst().getKey() != this.src.getKey()){
            return null;
        }
        else {
            return list;
        }
    }

//    @Override
//    public List<NodeData> shortestPath(int src, int dest) {
//        if (src == dest) {
//            List<NodeData> list = new ArrayList<>();
//            list.add(this.graph.Nodes.get(src));
//            return list;
//        }
//        double[] distance = new double[this.graph.nodeSize()];
//        int[] parents = new int[this.graph.nodeSize()];
//        Arrays.fill(parents, -1);
//        DijkstraAlgo(parents, distance, src);
//        return path(parents, dest, src);
//    }
//
//    private List<NodeData> path(int[] parents, int dest, int src) {
//        List<NodeData> nodesPath = new ArrayList<>();
//        nodesPath.add(this.graph.Nodes.get(dest));
//        int i = parents[dest];
//        while (i != src) {
//            NodeData current = this.graph.Nodes.get(i);
//            nodesPath.add(current);
//            i = parents[current.getKey()];
//        }
//        // i equals to src
//        nodesPath.add(this.graph.Nodes.get(src));
//        Collections.reverse(nodesPath);
//        return nodesPath;
//    }

}
