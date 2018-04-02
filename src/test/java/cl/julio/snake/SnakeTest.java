package cl.julio.snake;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SnakeTest {

    private static final Position ORIGIN = new Position(0, 0);
    private static final Position POSITION_1_0 = new Position(1, 0);
    private static final Position POSITION_2_0 = new Position(2, 0);
    private static final Position POSITION_3_3 = new Position(3, 3);
    private static final Position POSITION_0_1 = new Position(0, 1);
    private static final Position POSITION_0_2 = new Position(0, 2);
    private static final Position POSITION_3_2 = new Position(3, 2);
    private static final Position POSITION_2_3 = new Position(2, 3);
    private static final Position POSITION_2_1 = new Position(2, 1);
    private static final Position POSITION_2_2 = new Position(2, 2);
    private static final Position POSITION_1_2 = new Position(1, 2);
    private static final Position POSITION_3_0 = new Position(3, 0);
    private static final Position POSITION_4_0 = new Position(4, 0);
    private static final Position POSITION_4_2 = new Position(4, 2);
    private static final Position POSITION_4_1 = new Position(4, 1);
    private static final Position POSITION_6_1 = new Position(6, 1);

    @Test
    public void testMoveUp() {
        Snake snake = new Snake(POSITION_3_3);
        snake.changeDirection(Direction.UP);
        snake.move();

        assertThat(snake.getHeadPosition(), is(POSITION_3_2));
    }

    @Test
    public void testMoveRight() {
        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.RIGHT);
        snake.move();

        assertThat(snake.getHeadPosition(), is(POSITION_1_0));
    }

    @Test
    public void testMoveDown() {
        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.DOWN);
        snake.move();

        assertThat(snake.getHeadPosition(), is(POSITION_0_1));
    }

    @Test
    public void testMoveLeft() {
        Snake snake = new Snake(POSITION_3_3);
        snake.changeDirection(Direction.LEFT);
        snake.move();

        assertThat(snake.getHeadPosition(), is(POSITION_2_3));
    }

    @Test(expected = InvalidDirectionException.class)
    public void shouldThrowExceptionWhenTryingToMoveWithAnInvalidDirection() {
        Snake snake = new Snake(ORIGIN);
        snake.move();
    }

    @Test
    public void shouldKeepTheDirectionOfTailWhileSnakeTurnDown() {
        Food food = new Food(POSITION_2_0);

        Snake snake = new Snake(ORIGIN);

        /**
         * TTH-
         * ----
         */
        snake.changeDirection(Direction.RIGHT);
        snake.move();
        snake.move();
        snake.eat(food);
        snake.eat(food);

        /**
         * -TT-
         * --H-
         */
        snake.changeDirection(Direction.DOWN);
        snake.move();

        assertThat(snake.getHeadPosition(), is(POSITION_2_1));
        assertThat(snake.getLastPosition(), is(POSITION_1_0));
    }

    @Test
    public void shouldKeepTheDirectionOfTailWhileSnakeTurnUp() {
        Food food = new Food(POSITION_2_2);

        Snake snake = new Snake(POSITION_0_2);

        /**
         * -----
         * -----
         * TTH--
         * -----
         */
        snake.changeDirection(Direction.RIGHT);
        snake.move();
        snake.move();
        snake.eat(food);
        snake.eat(food);

        /**
         * ----
         * --H-
         * -TT-
         * ----
         */
        snake.changeDirection(Direction.UP);
        snake.move();

        assertThat(snake.getHeadPosition(), is(POSITION_2_1));
        assertThat(snake.getLastPosition(), is(POSITION_1_2));
    }

    @Test
    public void shouldKeepTheDirectionOfTailWhileSnakeTurnLeft() {
        Food food = new Food(POSITION_2_0);

        Snake snake = new Snake(POSITION_2_2);

        /**
         * --H--
         * --T--
         * --T--
         * -----
         */
        snake.changeDirection(Direction.UP);
        snake.move();
        snake.move();
        snake.eat(food);
        snake.eat(food);

        /**
         * -HT--
         * --T--
         * -----
         * -----
         */
        snake.changeDirection(Direction.LEFT);
        snake.move();

        assertThat(snake.getHeadPosition(), is(POSITION_1_0));
        assertThat(snake.getLastPosition(), is(POSITION_2_1));
    }

    @Test
    public void shouldKeepTheDirectionOfTailWhileSnakeTurnRight() {
        Food food = new Food(POSITION_2_0);

        Snake snake = new Snake(POSITION_2_2);

        /**
         * --H--
         * --T--
         * --T--
         * -----
         */
        snake.changeDirection(Direction.UP);
        snake.move();
        snake.move();
        snake.eat(food);
        snake.eat(food);

        /**
         * --TH-
         * --T--
         * -----
         * -----
         */
        snake.changeDirection(Direction.RIGHT);
        snake.move();

        assertThat(snake.getHeadPosition(), is(POSITION_3_0));
        assertThat(snake.getLastPosition(), is(POSITION_2_1));
    }

    @Test
    public void shouldKeepTheDirectionOfTailWhileSnakeTurnSeveralTimes() {
        Food food = new Food(POSITION_4_0);

        Snake snake = new Snake(POSITION_4_2);

        /**
         * ----H--
         * ----T--
         * ----T--
         * ----T--
         * ----T--
         */
        snake.changeDirection(Direction.UP);
        snake.move();
        snake.move();
        snake.eat(food);
        snake.eat(food);
        snake.eat(food);
        snake.eat(food);

        /**
         * ----TTH-
         * ----T---
         * ----T---
         * --------
         * --------
         */
        snake.changeDirection(Direction.RIGHT);
        snake.move();
        snake.move();

        /**
         * ----TTT-
         * ----T-H-
         * --------
         * --------
         * --------
         */
        snake.changeDirection(Direction.DOWN);
        snake.move();

        assertThat(snake.getHeadPosition(), is(POSITION_6_1));
        assertThat(snake.getLastPosition(), is(POSITION_4_1));
    }

    @Test
    public void shouldEatFoodIfSnakeIsInTheSamePositionAsFood() {
        Food food = new Food(ORIGIN);

        Snake snake = new Snake(ORIGIN);

        // any direction
        snake.changeDirection(Direction.UP);
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

    @Test
    public void testAddingLastPositionInSnakeMovingRightDirection() {
        Food food = new Food(POSITION_2_0);

        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.RIGHT);
        snake.move();
        snake.move();
        snake.eat(food);

        assertThat(snake.getLastPosition(), is(POSITION_1_0));
    }

    @Test
    public void testAddingLastPositionInSnakeMovingLeftDirection() {
        Food food = new Food(ORIGIN);

        Snake snake = new Snake(POSITION_2_0);
        snake.changeDirection(Direction.LEFT);
        snake.move();
        snake.move();
        snake.eat(food);

        assertThat(snake.getLastPosition(), is(POSITION_1_0));
    }

    @Test
    public void testAddingLastPositionInSnakeMovingUpDirection() {
        Food food = new Food(ORIGIN);

        Snake snake = new Snake(POSITION_0_2);
        snake.changeDirection(Direction.UP);
        snake.move();
        snake.move();
        snake.eat(food);

        assertThat(snake.getLastPosition(), is(POSITION_0_1));
    }

    @Test
    public void testAddingLastPositionInSnakeMovingDownDirection() {
        Food food = new Food(POSITION_0_2);

        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.DOWN);
        snake.move();
        snake.move();
        snake.eat(food);

        assertThat(snake.getLastPosition(), is(POSITION_0_1));
    }

    @Test
    public void shouldNotChangeDirectionIfChangeToLeftWhileGoingRight() {
        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.RIGHT);
        snake.changeDirection(Direction.LEFT);

        assertThat(snake.getCurrentDirection(), is(Direction.RIGHT));
    }

    @Test
    public void shouldNotChangeDirectionIfChangeToRightWhileGoingLeft() {
        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.LEFT);
        snake.changeDirection(Direction.RIGHT);

        assertThat(snake.getCurrentDirection(), is(Direction.LEFT));
    }

    @Test
    public void shouldNotChangeDirectionIfChangeToUpWhileGoingDown() {
        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.DOWN);
        snake.changeDirection(Direction.UP);

        assertThat(snake.getCurrentDirection(), is(Direction.DOWN));
    }

    @Test
    public void shouldNotChangeDirectionIfChangeToDownWhileGoingUp() {
        Snake snake = new Snake(ORIGIN);
        snake.changeDirection(Direction.UP);
        snake.changeDirection(Direction.DOWN);

        assertThat(snake.getCurrentDirection(), is(Direction.UP));
    }

}
