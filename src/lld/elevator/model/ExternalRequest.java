package lld.elevator.model;

public class ExternalRequest {

    private final int requestFloor;
    private final Direction direction;

    public ExternalRequest(int requestFloor, Direction direction) {
        if (direction == null || direction == Direction.NA) {
            throw new IllegalArgumentException(
                    "External request direction must be UP or DOWN"
            );
        }

        this.requestFloor = requestFloor;
        this.direction = direction;
    }

    public int getRequestFloor() {
        return requestFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}