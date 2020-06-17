package Utils;

import Model.BuildingEdge;
import Model.BuildingNode;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;

public class ShortestPath {

    public ArrayList<String> AStar(Graph<BuildingNode, BuildingEdge> graph, String source, String target){
        AStarShortestPath<BuildingNode, BuildingEdge> astar =
                new AStarShortestPath<BuildingNode, BuildingEdge>(graph, new AStarAdmissibleHeuristic<BuildingNode>() {
                    @Override
                    public double getCostEstimate(BuildingNode buildingNode, BuildingNode v1) {
                        return v1.getFloor() - buildingNode.getFloor();
                    }
                });
        AStarShortestPath.SingleSourcePaths<BuildingNode, BuildingEdge> iPaths2 = astar.getPaths(
                graph.vertexSet().stream().filter(str -> str.name.equals(source)).findAny()
                        .get());

        var pathsA = iPaths2.getPath(
                graph.vertexSet().stream().filter(
                        str -> str.name.equals(target)).findAny()
                        .get());

        List<BuildingNode> nodes = pathsA.getVertexList();
        List<BuildingEdge> edges = pathsA.getEdgeList();

        ArrayList<String> path = new ArrayList<>();
        for (int i = 0 ; i < edges.size() ; i++){
            path.add(nodes.get(i).name);
            path.add(edges.get(i).name);
        }
        path.add(nodes.get(nodes.size() - 1).name);

        return path;
    }

    public ArrayList<String> Dijkstra(Graph<BuildingNode, BuildingEdge> graph, String source, String target){
        DijkstraShortestPath<BuildingNode, BuildingEdge> dijkstraAlg =
                new DijkstraShortestPath<>(graph);
        ShortestPathAlgorithm.SingleSourcePaths<BuildingNode, BuildingEdge> iPaths = dijkstraAlg.getPaths(
                graph.vertexSet().stream().filter(str -> str.name.equals(source)).findAny()
                        .get());

        var paths = iPaths.getPath(
                graph.vertexSet().stream().filter(
                        str -> str.name.equals(target)).findAny()
                        .get());

        List<BuildingNode> nodes = paths.getVertexList();
        List<BuildingEdge> edges = paths.getEdgeList();

        ArrayList<String> all = new ArrayList<>();
        for (int i = 0 ; i < edges.size() ; i++){
            all.add(nodes.get(i).name);
            all.add(edges.get(i).name);
        }
        all.add(nodes.get(nodes.size() - 1).name);

        return all;
    }
}
