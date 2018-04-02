package cl.julio.snake;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class BoundaryTest {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int MIDDLE_WIDTH = WIDTH / 2;
    private static final int MIDDLE_HEIGHT = HEIGHT / 2;

    @Mock
    private Random random;
    private Board board;

    @Before
    public void setUp() {
        board = new Board(random, WIDTH, HEIGHT);
    }

    @Test(expected = SnakeOutOfBoardException.class)
    public void shouldThrowAnExceptionWhenSnakeGoOutOfTheBoardMovingLeft() {
        board.getSnake().changeDirection(Direction.LEFT);

        for (int i = 0; i <= MIDDLE_WIDTH; i++) {
            board.update();
        }
    }

    @Test(expected = SnakeOutOfBoardException.class)
    public void shouldThrowAnExceptionWhenSnakeGoOutOfTheBoardMovingRight() {
        board.getSnake().changeDirection(Direction.RIGHT);

        for (int i = 0; i <= MIDDLE_WIDTH; i++) {
            board.update();
        }
    }

    @Test(expected = SnakeOutOfBoardException.class)
    public void shouldThrowAnExceptionWhenSnakeGoOutOfTheBoardMovingUp() {
        board.getSnake().changeDirection(Direction.UP);

        for (int i = 0; i <= MIDDLE_HEIGHT; i++) {
            board.update();
        }
    }

    @Test(expected = SnakeOutOfBoardException.class)
    public void shouldThrowAnExceptionWhenSnakeGoOutOfTheBoardMovingDown() {
        board.getSnake().changeDirection(Direction.DOWN);

        for (int i = 0; i <= MIDDLE_HEIGHT; i++) {
            board.update();
        }
    }

}
