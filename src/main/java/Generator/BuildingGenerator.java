package Generator;

import Model.BuildingEdge;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.ScaleFreeGraphGenerator;
import org.jgrapht.graph.DefaultUndirectedGraph;
import Generator.*;
import Model.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public class BuildingGenerator {

    private int floor;
    private int singleFloorSize;
    private int nodeID = 0;
    private int edgeID = 0;
    public BuildingGenerator(int floor, int singleFloorSize) {
        this.floor = floor;
        this.singleFloorSize = singleFloorSize;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getSingleFloorSize() {
        return singleFloorSize;
    }

    public void setSingleFloorSize(int singleFloorSize) {
        this.singleFloorSize = singleFloorSize;
    }

    Supplier<BuildingEdge> edgeSupplier = new Supplier<BuildingEdge>() {
        private int id = 0;

        @Override
        public BuildingEdge get() {
            return new BuildingEdge("E" + edgeID++);
        }
    };
    Random rand = new Random();

    Supplier<BuildingNode> nodeSupplier = new Supplier<>() {
        private int id = 0;
        @Override
        public BuildingNode get() {
            return new BuildingNode("N" + nodeID++, getRandomPriority());
        }
    };

    public int getRandomPriority() {
        int rnd = rand.nextInt(100);
        if (rnd < 90)
            return 0;
        else if (rnd < 94)
            return 1;
        else if (rnd < 97)
            return 2;
        else
            return 3;
    }

    public Graph<BuildingNode, BuildingEdge> generateGraph() {

        ArrayList<Graph<BuildingNode, BuildingEdge>> floors = new ArrayList<>();

        for (int i = 0; i < floor; i++) {
            edgeID = 0;
            nodeID = 0;
            Graph<BuildingNode, BuildingEdge> completeGraph =
                    new DefaultUndirectedGraph<>(nodeSupplier, edgeSupplier, false);
            GraphGenerator cc = new ScaleFreeGraphGenerator(singleFloorSize);
            cc.generateGraph(completeGraph);
            for (BuildingNode buildingNode : completeGraph.vertexSet()) {
                buildingNode.setFloor(i);
                buildingNode.name = i + "-" + buildingNode.name;
            }
            floors.add(completeGraph);
        }

        for (int i = 1; i < floors.size(); i++) {
            Graphs.addGraph(floors.get(0), floors.get(i));
            floors.get(0).addEdge(getNode(floors.get(0), (i - 1) + "-N0"), getNode(floors.get(0), (i) + "-N0"));
        }

        return floors.get(0);
    }

    public BuildingNode getNode(Graph<BuildingNode, BuildingEdge> graph, String name) {
        return graph.vertexSet().stream().filter(str -> str.name.equals(name)).findAny().get();
    }

    public BuildingEdge getEdge(Graph<BuildingNode, BuildingEdge> graph, String name) {
        return graph.edgeSet().stream().filter(str -> str.name.equals(name)).findAny().get();
    }
}
