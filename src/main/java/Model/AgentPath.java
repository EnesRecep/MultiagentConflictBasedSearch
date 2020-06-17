package Model;

import java.util.ArrayList;
import java.util.HashSet;

public class AgentPath {

    private ArrayList<ArrayList<String>> agentPaths = new ArrayList<>();
    public boolean stopCondition = false;

    public AgentPath() {
    }

    public AgentPath(ArrayList<ArrayList<String>> agentPaths) {
        this.agentPaths = agentPaths;
    }

    public ArrayList<ArrayList<String>> getAgentPaths() {
        return agentPaths;
    }

    public void setAgentPaths(ArrayList<ArrayList<String>> agentPaths) {
        this.agentPaths = agentPaths;
    }

    public void addAgentPath(ArrayList<String> agentPaths){
        this.agentPaths.add(agentPaths);
    }
    public void addAgentPath(Agent agent){
        this.agentPaths.add(agent.path);
    }
    public void addAgentPaths(ArrayList<Agent> agents){
        for (Agent agent : agents) {
            this.agentPaths.add(agent.path);
        }
    }

    public AgentPath generateChildNodesBySolvingConflicts() {

        ArrayList<ArrayList<String>> toBeReturned = new ArrayList<>();

        int longest = Integer.MIN_VALUE;
        for (ArrayList<String> agentPath : agentPaths)
            if (agentPath.size() >= longest)
                longest = agentPath.size();

        HashSet<Integer> eliminated = new HashSet<>();
        boolean changed = false;
        boolean startException = true;
        for (int i = 1; i < longest; i++) {
            HashSet<String> existingValues = new HashSet<>();
            toBeReturned.clear();
            existingValues.clear();

            for (int j = 0; j < agentPaths.size(); j++) {
                if(i >= agentPaths.get(j).size()){
                    eliminated.add(j);
                }
                if (eliminated.contains(j)) {
                    //toBeReturned.add((ArrayList<String>) agentPaths.get(j).clone());
                    continue;
                }
                if (existingValues.contains(agentPaths.get(j).get(i)) && !agentPaths.get(j).get(i).equals("start")) {
                    //toBeReturned.add((ArrayList<String>) agentPaths.get(j).clone());
                    agentPaths.get(j).add(i, agentPaths.get(j).get(i - 1));
                    changed = true;
                } else {
                    //toBeReturned.add((ArrayList<String>) agentPaths.get(j).clone());
                    existingValues.add(agentPaths.get(j).get(i));
                }
            }

//            if (changed)
//                i--;
                //return new Model.AgentPath(toBeReturned);
        }
        return new AgentPath(agentPaths);

    }

    @Override
    public String toString() {
        return "Model.AgentPath{" +
                "agentPaths=" + agentPaths +
                '}';
    }
}
