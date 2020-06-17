package Model;

public class BuildingNode {

    public String name;
    public int priority;
    public int floor;

    public BuildingNode(String name, int priority) {
        this.name = name;
        this.priority = priority;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return name +
                " - " + priority
                ;
    }
}
