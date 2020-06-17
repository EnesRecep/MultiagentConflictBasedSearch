package Generator;

import Model.Agent;
import Model.BuildingEdge;
import Model.BuildingNode;
import Utils.GraphUtils;
import Utils.ShortestPath;
import org.jgrapht.Graph;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AgentGenerator {

    public static ArrayList<Agent> generateAgent(int size, Graph<BuildingNode, BuildingEdge> graph){
        ArrayList<Agent> agents = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            agents.add(new Agent());
        }
        ShortestPath sp = new ShortestPath();

        for(int j = 3 ; j > 0 ; j--){
            int finalJ = j;
                AtomicInteger finalI = new AtomicInteger(0);
                graph.vertexSet().stream().filter(str -> str.priority == finalJ).forEach(node -> {
                    ArrayList<String> tempPath = sp.Dijkstra(graph, "start", node.name);
                    agents.get(finalI.get() % agents.size()).addPath(tempPath);
                    agents.get(finalI.get() % agents.size()).addPath(GraphUtils.reverseArray(tempPath));
                    finalI.getAndIncrement();
                });


        }

        return agents;

    }
}
