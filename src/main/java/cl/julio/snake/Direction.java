package cl.julio.snake;

public enum Direction {

    STOP, UP, RIGHT, DOWN, LEFT;

    private Direction opposite;

    static {
        STOP.opposite = STOP;
        UP.opposite = DOWN;
        DOWN.opposite = UP;
        RIGHT.opposite = LEFT;
        LEFT.opposite = RIGHT;
    }

    public Direction getOpposite() {
        return opposite;
    }

}
