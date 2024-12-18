package org.example;

import java.awt.*;
import java.util.Random;

public class Food {
    private Point position;

    public Food() {
        Random rand = new Random();
        position = new Point(rand.nextInt(30), rand.nextInt(30));  // Random position on the grid
    }

    public Point getPosition() {
        return position;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(position.x * 20, position.y * 20, 20, 20);  // Draw food as a red square
    }
}
