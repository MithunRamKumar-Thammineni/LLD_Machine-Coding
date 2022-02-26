package Elevator;

public class TestElevator {
    Elevator elevator = new Elevator();

    ExternalRequest externalRequest = new ExternalRequest(Direction.UP, 0);

    InternalRequest internalRequest = new InternalRequest(5);

    Request request = new Request(internalRequest, externalRequest);
}
