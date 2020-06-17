package Model;

import java.util.ArrayList;

public class Agent {

    public ArrayList<String> path = new ArrayList<>();

    public Agent(ArrayList<String> path) {
        this.path = path;
    }

    public Agent() {
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public void setPath(ArrayList<String> path) {
        this.path = path;
    }

    public void addPath(ArrayList<String> path){
        this.path.addAll(path);
    }

    @Override
    public String toString() {
        return "Model.Agent{" +
                "path=" + path +
                '}';
    }
}
