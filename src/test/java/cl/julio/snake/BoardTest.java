package cl.julio.snake;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final Position MIDDLE_POINT_ON_BOARD = new Position(WIDTH / 2, HEIGHT / 2);

    @Mock
    private Random random;

    @Test
    public void snakeShouldAppearInTheMiddleOfTheBoard() {
        given(random.nextInt(anyInt())).willReturn(0);

        Board board = new Board(random, WIDTH, HEIGHT);
        Snake snake = board.getSnake();

        assertThat(snake.getHeadPosition(), is(MIDDLE_POINT_ON_BOARD));
    }

    @Test
    public void snakeShouldGoOnRightDirectionOnCreateBoard() {
        given(random.nextInt(anyInt())).willReturn(0);

        Board board = new Board(random, WIDTH, HEIGHT);
        Snake snake = board.getSnake();

        assertThat(snake.getCurrentDirection(), is(Direction.RIGHT));
    }

    @Test
    public void testNumberOfRandomInvocationToCreateInitialFood() {
        int xFood = 1;
        int yFood = 1;

        given(random.nextInt(anyInt()))
                .willReturn(xFood)
                .willReturn(yFood);

        // Create board that invoke the initial creation of objects
        Board board = new Board(random, WIDTH, HEIGHT);

        int randomInvocationToCreateFood = 2;

        verify(random, times(randomInvocationToCreateFood)).nextInt(anyInt());
    }

    @Test
    public void foodShouldNotAppearInAPositionOfSnake() {
        int xFoodFirstCall = WIDTH / 2;
        int yFoodFirstCall = HEIGHT / 2;
        int xFoodSecondCall = 1;
        int yFoodSecondCall = 1;

        given(random.nextInt(anyInt()))
                .willReturn(xFoodFirstCall)
                .willReturn(yFoodFirstCall)
                .willReturn(xFoodSecondCall)
                .willReturn(yFoodSecondCall);

        // Create board that invoke the initial creation of objects
        Board board = new Board(random, WIDTH, HEIGHT);

        int randomInvocationToCreateFood = 2;
        int randomInvocationToCreateFoodAfterFirstIsWrong = 2;

        int totalRandomInvocations = randomInvocationToCreateFood + randomInvocationToCreateFoodAfterFirstIsWrong;

        verify(random, times(totalRandomInvocations)).nextInt(anyInt());
    }

    @Test
    public void shouldMoveSnakeAndTryToEatTheFoodOnUpdateTheBoard() {
        int xFood = MIDDLE_POINT_ON_BOARD.getX() + 1;
        int yFood = MIDDLE_POINT_ON_BOARD.getY();

        given(random.nextInt(anyInt()))
                .willReturn(xFood)
                .willReturn(yFood);

        Board board = new Board(random, WIDTH, HEIGHT);
        board.update();

        Snake snake = board.getSnake();

        assertThat(snake.getHeadPosition(), is(new Position((WIDTH / 2) + 1, HEIGHT / 2)));
        assertThat(snake.getLength(), is(2));
    }

}