package org.example;
import java.awt.*;
import java.util.*;

public class Snake {
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private LinkedList<Point> body;
    private int direction;

    public Snake() {
        body = new LinkedList<>();
        // Initial length of 3, so we add 3 body segments
        body.add(new Point(10, 10));  // Head position
        body.add(new Point(9, 10));   // Body segment 1
        body.add(new Point(8, 10));   // Body segment 2
        direction = RIGHT;  // Initial direction
    }

    public void move() {
        Point head = body.getFirst();
        Point newHead = null;

        switch (direction) {
            case UP: newHead = new Point(head.x, head.y - 1); break;
            case DOWN: newHead = new Point(head.x, head.y + 1); break;
            case LEFT: newHead = new Point(head.x - 1, head.y); break;
            case RIGHT: newHead = new Point(head.x + 1, head.y); break;
        }

        body.addFirst(newHead);
        body.removeLast();  // Remove tail if not growing
    }

    public void grow() {
        Point head = body.getFirst();
        body.addFirst(new Point(head.x, head.y));  // Add new body segment at head position
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean hitWall() {
        Point head = body.getFirst();
        return head.x < 0 || head.x >= 30 || head.y < 0 || head.y >= 30;  // 30x30 grid
    }

    public boolean hitSelf() {
        Point head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(head)) {
                return true;
            }
        }
        return false;
    }

    public boolean eatFood(Food food) {
        if (body.getFirst().equals(food.getPosition())) {
            grow();
            return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < body.size(); i++) {
            Point p = body.get(i);
            if (i == 0) {
                // Snake head with a gradient effect
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(p.x * 20, p.y * 20, Color.GREEN, (p.x + 1) * 20, (p.y + 1) * 20, Color.YELLOW);
                g2d.setPaint(gradient);
                g2d.fillRoundRect(p.x * 20, p.y * 20, 20, 20, 10, 10);  // Rounded corners for the head
            } else {
                // Snake body with a solid color
                g.setColor(Color.YELLOW);
                g.fillRoundRect(p.x * 20, p.y * 20, 20, 20, 10, 10);  // Rounded corners for the body
            }
        }
    }
}
