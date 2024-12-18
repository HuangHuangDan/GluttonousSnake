package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Snake snake;
    private Food food;
    private int score;
    private Image backgroundImage;
    public GamePanel() {
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setBackground(Color.BLACK);
        this.score = 0;
        try {
//            backgroundImage = ImageIO.read(new File("/Users/torryy/Desktop/GluttonousSnake/background.jpg"));
             backgroundImage = ImageIO.read(getClass().getClassLoader().getResource("background.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        snake = new Snake();
        food = new Food();
        // Initialize the timer with a delay of 200 ms
        timer = new Timer(200, this);
        timer.start();  // Start the timer
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        checkCollisions();
        if (snake.eatFood(food)) {
            food = new Food();  // Generate new food
            score++;
        }
        repaint();
    }

    private void checkCollisions() {
        if (snake.hitWall() || snake.hitSelf()) {
            timer.stop();  // Stop the timer when the game is over
            JOptionPane.showMessageDialog(this, "Game Over! Your score: " + score);
            int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                startGame();  // Restart the game
            } else {
                System.exit(0);  // Exit the game
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image (stretch it to fit the entire panel)
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw the snake and food
        snake.draw(g);
        food.draw(g);

        // Draw the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 30);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            snake.setDirection(Snake.UP);
        } else if (key == KeyEvent.VK_DOWN) {
            snake.setDirection(Snake.DOWN);
        } else if (key == KeyEvent.VK_LEFT) {
            snake.setDirection(Snake.LEFT);
        } else if (key == KeyEvent.VK_RIGHT) {
            snake.setDirection(Snake.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
