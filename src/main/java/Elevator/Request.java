package Elevator;

public class Request implements Comparable<Request> {
    private InternalRequest internalRequest;
    private ExternalRequest externalRequest;

    public Request(InternalRequest internalRequest, ExternalRequest externalRequest) {
        this.internalRequest = internalRequest;
        this.externalRequest = externalRequest;
    }

    public int compareTo(Request other) {
        return this.internalRequest.getDestinationfloor() - other.internalRequest.getDestinationfloor();
    }

    public InternalRequest getInternalRequest() {
        return internalRequest;
    }

    public ExternalRequest getExternalRequest() {
        return externalRequest;
    }
}
