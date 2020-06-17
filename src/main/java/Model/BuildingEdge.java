package Model;

public class BuildingEdge {


    public BuildingEdge(String name) {
        this.name = name;
    }

    public String name;


    @Override
    public String toString() {
        return name;
    }
}
