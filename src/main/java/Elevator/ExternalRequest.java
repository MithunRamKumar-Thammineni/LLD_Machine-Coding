package Elevator;

public class ExternalRequest {
    private Direction directionToGo;
    private int sourcefloor;

    public ExternalRequest(Direction directionToGo, int sourcefloor) {
        this.directionToGo = directionToGo;
        this.sourcefloor = sourcefloor;
    }

    public Direction getDirectionToGo() {
        return directionToGo;
    }

    public void setDirectionToGo(Direction directionToGo) {
        this.directionToGo = directionToGo;
    }

    public int getSourcefloor() {
        return sourcefloor;
    }

    public void setSourcefloor(int sourcefloor) {
        this.sourcefloor = sourcefloor;
    }
}
