package cl.julio.snake;

public class Food {

    private Position position;

    public Food(Position position) {
        this.position = new Position(position.getX(), position.getY());
    }

    public Position getPosition() {
        return position;
    }

}
