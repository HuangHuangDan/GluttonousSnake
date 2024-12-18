package org.example;
import org.example.GamePanel;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        GamePanel gamePanel = new GamePanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(gamePanel);
        frame.setVisible(true);
        gamePanel.startGame();
    }
}

