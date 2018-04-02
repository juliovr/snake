package cl.julio.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JComponent implements KeyListener {

    private static final String KEY_PRESSED_LEFT = "Left";
    private static final String KEY_PRESSED_UP = "Up";
    private static final String KEY_PRESSED_RIGHT = "Right";
    private static final String KEY_PRESSED_DOWN = "Down";


    private Board board;
    private int scale;

    public Game(Board board, int scale) {
        this.board = board;
        this.scale = scale;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Snake snake = board.getSnake();
        Food food = board.getFood();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(snake.getHeadPosition().getX() * scale, snake.getHeadPosition().getY() * scale, scale, scale);

        graphics.setColor(Color.RED);
        graphics.fillRect(food.getPosition().getX() * scale, food.getPosition().getY() * scale, scale, scale);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        String keyPressed = KeyEvent.getKeyText(keyEvent.getKeyCode());
        switch (keyPressed) {
            case KEY_PRESSED_LEFT:
                board.getSnake().changeDirection(Direction.LEFT);
                break;
            case KEY_PRESSED_UP:
                board.getSnake().changeDirection(Direction.UP);
                break;
            case KEY_PRESSED_RIGHT:
                board.getSnake().changeDirection(Direction.RIGHT);
                break;
            case KEY_PRESSED_DOWN:
                board.getSnake().changeDirection(Direction.DOWN);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

}
