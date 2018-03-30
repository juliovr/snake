package cl.julio.snake;

import java.util.Random;

public class Board {

    private final Random random;
    private final int width;
    private final int height;
    private Snake snake;
    private Food food;

    public Board(Random random, int width, int height) {
        this.random = random;
        this.width = width;
        this.height = height;
        this.snake = createSnakeInTheMiddleOfTheBoard();
        this.food = createFoodInRandomPosition();
    }

    public Snake getSnake() {
        return snake;
    }

    /**
     * Create the snake in the middle of the board at creating the game with RIGHT Direction.
     *
     * @return the snake in the middle of the board with RIGHT Direction.
     */
    private Snake createSnakeInTheMiddleOfTheBoard() {
        Position middlePosition = new Position(width / 2, height / 2);
        Snake snake = new Snake(middlePosition);
        snake.changeDirection(Direction.RIGHT);

        return snake;
    }

    private Food createFoodInRandomPosition() {
        Position position;
        do {
            int randomX = random.nextInt(width);
            int randomY = random.nextInt(height);
            position = new Position(randomX, randomY);
        } while (position.equals(snake.getHeadPosition()));

        return new Food(position);
    }

    public void update() {
        int oldLength = snake.getLength();
        snake.move();
        snake.eat(food);
        int newLength = snake.getLength();

        if (oldLength < newLength) {
            food = createFoodInRandomPosition();
        }
    }

//    public void changeSnakeDirection(Direction direction) {
//        snake.changeDirection(direction);
//    }

}
