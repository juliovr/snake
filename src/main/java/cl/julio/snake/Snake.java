package cl.julio.snake;

public class Snake {

    private Node head;
    private int length;
    private Direction currentDirection;

    /**
     * Create a snake with the position passed as parameter, default length 1 and STOP Direction.
     *
     * @param position of the snake to be created
     */
    public Snake(Position position) {
        head = new Node(new Position(position.getX(), position.getY()));
        length = 1;
        this.currentDirection = Direction.STOP;
    }

    public int getLength() {
        return length;
    }

    public Position getHeadPosition() {
        return head.position;
    }

    private Node getLastNode() {
        Node lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        return lastNode;
    }

    public Position getLastPosition() {
        return getLastNode().position;
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
        Position oldHeadPosition = head.position;
        head.position = getNewHeadPositionBasedOnCurrentDirection();
        updateTail(head.next, oldHeadPosition);
    }

    private Position getNewHeadPositionBasedOnCurrentDirection() {
        switch (currentDirection) {
            case UP:
                return new Position(head.position.getX(), head.position.getY() - 1);
            case RIGHT:
                return new Position(head.position.getX() + 1, head.position.getY());
            case DOWN:
                return new Position(head.position.getX(), head.position.getY() + 1);
            case LEFT:
                return new Position(head.position.getX() - 1, head.position.getY());
            default:
                throw new InvalidDirectionException();
        }
    }

    private void updateTail(Node node, Position newPosition) {
        if (node == null)
            return;

        updateTail(node.next, node.position);
        node.position = newPosition;
    }

    /**
     * Eat the food only if both, snake and food, are in the same position and
     * add the food eaten to the last position of the snake size.
     *
     * @param food object to be eaten.
     */
    public void eat(Food food) {
        if (cannotEat(food))
            return;

        Position newLastPosition = getNewLastPositionBasedOnCurrentDirection();

        addTail(newLastPosition);
    }

    private Position getNewLastPositionBasedOnCurrentDirection() {
        switch (currentDirection) {
            case UP:
                return new Position(getLastPosition().getX(), getLastPosition().getY() + 1);
            case DOWN:
                return new Position(getLastPosition().getX(), getLastPosition().getY() - 1);
            case RIGHT:
                return new Position(getLastPosition().getX() - 1, getLastPosition().getY());
            case LEFT:
                return new Position(getLastPosition().getX() + 1, getLastPosition().getY());
            default:
                throw new InvalidDirectionException();
        }
    }

    /**
     * Determine if the snake can eat the food if and only if both, snake and food, are in the same position.
     *
     * @param food to be eaten
     * @return true if the snake can eat the food, otherwise false.
     */
    private boolean cannotEat(Food food) {
        return !getHeadPosition().equals(food.getPosition());
    }

    private void addTail(Position position) {
        Node lastNode = getLastNode();
        lastNode.next = new Node(position);
        length++;
    }


    private static class Node {
        Position position;
        Node next;

        Node(Position position) {
            this.position = position;
            this.next = null;
        }

    }

}
