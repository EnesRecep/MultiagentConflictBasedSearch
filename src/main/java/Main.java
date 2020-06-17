import Generator.AgentGenerator;
import Generator.BuildingGenerator;
import Model.AgentPath;
import Model.BuildingNode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static AgentPath finalPaths;

    public static void main(String[] args)
            throws URISyntaxException,
            IOException, InterruptedException {

        //Test cases
        ArrayList<Integer> agentNumbers = new ArrayList<>(Arrays.asList(5, 10, 20));
        ArrayList<Integer> floorNumbers = new ArrayList<>(Arrays.asList(5, 10, 20));
        ArrayList<Integer> floorSizes = new ArrayList<>(Arrays.asList(10, 20, 50));

        for (Integer floorSize : floorSizes) {
            for (Integer floorNumber : floorNumbers) {
                for (Integer agentNumber : agentNumbers) {

                    System.out.println("Starting with " + agentNumber + " agents, " + floorNumber + " floors and " + floorSize + " floor size");

                    BuildingGenerator bg = new BuildingGenerator(floorNumber, floorSize);
                    var gr = bg.generateGraph();
                    gr.addVertex(new BuildingNode("start", 0));
                    gr.addEdge(bg.getNode(gr, "start"), bg.getNode(gr, "0-N0"));
                    long start = System.currentTimeMillis();
                    var agents = AgentGenerator.generateAgent(agentNumber, gr);

                    AgentPath agentPath = new AgentPath();
                    agentPath.addAgentPaths(agents);
                    agentPath.generateChildNodesBySolvingConflicts();

                    int totalCost = 0;
                    for (ArrayList<String> path : agentPath.getAgentPaths()) {
                        totalCost += path.size();
                    }
                    System.out.println((System.currentTimeMillis() - start) + " milliseconds. Total Nodes: " + totalCost);
                    for (Model.Agent a : agents) {
                        System.out.println(a);
                    }
                    System.out.println("----------------------------------------");

                }
            }
        }

    }


    public static void createConflictFreePaths(AgentPath root) {
        var children = root.generateChildNodesBySolvingConflicts();
        finalPaths = children;
    }


}
