package Elevator;

public class InternalRequest {
    private int destinationfloor;

    public InternalRequest(int destinationfloor) {
        this.destinationfloor = destinationfloor;
    }

    public int getDestinationfloor() {
        return destinationfloor;
    }

    public void setDestinationfloor(int destinationfloor) {
        this.destinationfloor = destinationfloor;
    }
}
