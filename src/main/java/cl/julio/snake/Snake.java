package cl.julio.snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private Direction currentDirection;
    private int length;
//    private Position position;

    //TODO: rename to "position"
    private List<Position> tailPositions;

    /**
     * Create a snake with the position passed as parameter, default length 1 and STOP Direction.
     *
     * @param position
     */
    public Snake(Position position) {
        tailPositions = new ArrayList<>();
        tailPositions.add(new Position(position.getX(), position.getY()));
//        this.position = new Position(position.getX(), position.getY());
//        this.length = 1;
        this.currentDirection = Direction.STOP;
    }

    public int getLength() {
        return tailPositions.size();
//        return length;
    }

    public Position getHeadPosition() {
        return tailPositions.get(0);
    }

    public void changeDirection(Direction direction) {
        this.currentDirection = direction;
    }

    /**
     * Move the snake based on the current direction.
     *
     * @throws InvalidDirectionException if the current direction is STOP.
     */
    public void move() {
        switch (currentDirection) {
            case UP:
                getHeadPosition().setY(getHeadPosition().getY() - 1);
                break;
            case RIGHT:
                getHeadPosition().setX(getHeadPosition().getX() + 1);
                break;
            case DOWN:
                getHeadPosition().setY(getHeadPosition().getY() + 1);
                break;
            case LEFT:
                getHeadPosition().setX(getHeadPosition().getX() - 1);
                break;
            default:
                throw new InvalidDirectionException();
        }
    }

    //TODO: rename method for "move" after validate the functionality
    public void moveSnake() {

    }

    /**
     * Eat the food only if both are in the same position.
     *
     * @param food object to be eaten.
     */
    public void eat(Food food) {
        if (getHeadPosition().equals(food.getPosition()))
            tailPositions.add(food.getPosition());
    }

}
