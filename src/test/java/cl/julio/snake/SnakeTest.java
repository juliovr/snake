package cl.julio.snake;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SnakeTest {

    public static final Position ORIGIN = new Position(0, 0);
    public static final Position POSITION_3_3 = new Position(3, 3);

    @Test
    public void testMoveUp() {
        Snake snake = new Snake(POSITION_3_3);
        snake.changeDirection(Direction.UP);
        snake.move();

        assertThat(snake.getHeadPosition(), is(new Position(3, 2)));
    }

    @Test
    public void testMoveRight() {
        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.RIGHT);
        snake.move();

        assertThat(snake.getHeadPosition(), is(new Position(1, 0)));
    }

    @Test
    public void testMoveDown() {
        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.DOWN);
        snake.move();

        assertThat(snake.getHeadPosition(), is(new Position(0, 1)));
    }

    @Test
    public void testMoveLeft() {
        Snake snake = new Snake(POSITION_3_3);
        snake.changeDirection(Direction.LEFT);
        snake.move();

        assertThat(snake.getHeadPosition(), is(new Position(2, 3)));
    }

    @Test(expected = InvalidDirectionException.class)
    public void shouldThrowExceptionWhenTryingToMoveWithInexistingDirection() {
        Snake snake = new Snake(ORIGIN);
        snake.move();
    }

    @Test
    public void shouldEatFoodIfSnakeIsInTheSamePositionAsFood() {
        Food food = new Food(ORIGIN);

        Snake snake = new Snake(ORIGIN);
        snake.eat(food);

        assertThat(snake.getLength(), is(2));
    }

    @Test
    public void shouldEatFoodIfSnakeIsInTheSamePositionAsFoodAfterMove() {
        Food food = new Food(POSITION_3_3);

        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.DOWN);
        snake.move();
        snake.move();
        snake.move();

        snake.changeDirection(Direction.RIGHT);
        snake.move();
        snake.move();
        snake.move();

        snake.eat(food);

        assertThat(snake.getLength(), is(2));
    }

    @Test
    public void shouldNotEatFoodIfSnakeIsInTheSamePositionAsFood() {
        Food food = new Food(POSITION_3_3);

        Snake snake = new Snake(ORIGIN);
        snake.eat(food);

        assertThat(snake.getLength(), is(1));
    }

//    @Test
//    public void shouldKeepTheDirectionOfTailWhileSnakeTurn() {
//        Food food = new Food(ORIGIN);
//
//        Snake snake = new Snake(ORIGIN);
//        snake.eat(food);
//        snake.eat(food);
//
//        snake.changeDirection(Direction.RIGHT);
//        snake.move();
//    }

}
