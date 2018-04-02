package cl.julio.snake;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main {

    private static final String TITLE = "Snake";
    private static final int SCALE = 20;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final Component CENTER_POSITION = null;
    private static final int REFRESH_SECONDS = 1 * 500;

    public static void main(String[] args) throws InterruptedException {
        int width = 10;
        int height = 10;

        Random random = new Random();
        Board board = new Board(random, WIDTH, HEIGHT);

        Game game = new Game(board, SCALE);


        JFrame frame = new JFrame(TITLE);
        frame.setSize(width * (SCALE + 3), height * (SCALE + 7));
        frame.setLocationRelativeTo(CENTER_POSITION);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(game, BorderLayout.CENTER);
        frame.addKeyListener(game);
        frame.setResizable(false);
        frame.setVisible(true);

        boolean isRunning = true;

        while (isRunning) {
            try {
                Thread.sleep(REFRESH_SECONDS);
                board.update();
                game.repaint();
            } catch (SnakeOutOfBoardException e) {
                isRunning = false;
            }
        }


        JOptionPane.showMessageDialog(CENTER_POSITION, "Game Over");
        System.exit(0);
    }

}
