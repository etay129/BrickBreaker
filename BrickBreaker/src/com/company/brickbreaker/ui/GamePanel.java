package com.company.brickbreaker.ui;

import com.company.brickbreaker.model.BBGame;
import com.company.brickbreaker.model.Ball;
import com.company.brickbreaker.model.Brick;
import com.company.brickbreaker.model.Paddle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * The panel in which game is rendered
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private static final String EXIT = "X to exit";
    private BBGame game;

    // EFFECTS:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(BBGame g) {
        setPreferredSize(new Dimension(BBGame.WIDTH, BBGame.HEIGHT));
        setBackground(Color.GRAY);
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (game.isOver()) {
            gameOver(g);
        }
    }

    // MODIFIES: g
    // EFFECTS:  draws the game onto g
    private void drawGame(Graphics g) {
        drawPaddle(g);
        drawBall(g, game.getBall());
        drawBricks(g);
    }

    // MODIFIES: g
    // EFFECTS:  draws the paddle p onto g
    private void drawPaddle(Graphics g) {
        Paddle p = game.getPaddle();
        Color savedCol = g.getColor();
        g.setColor(Paddle.COLOR);
        g.fillRect(p.getX() - Paddle.SIZE_X / 2, Paddle.Y_POS - Paddle.SIZE_Y / 2, Paddle.SIZE_X, Paddle.SIZE_Y);
        g.setColor(savedCol);
    }

    // MODIFIES: g
    // EFFECTS:  draws the ball b onto g
    private void drawBall(Graphics g, Ball b) {
        Color savedCol = g.getColor();
        g.setColor(Ball.COLOR);
        g.fillOval(b.getX() - Ball.SIZE_X / 2, b.getY() - Ball.SIZE_Y / 2, Ball.SIZE_X, Ball.SIZE_Y);
        g.setColor(savedCol);
    }

    // MODIFIES: g
    // EFFECTS:  draws the bricks onto g
    private void drawBricks(Graphics g) {
        for(Brick next : game.getBricks()) {
            drawBrick(g, next);
        }
    }


    // MODIFIES: g
    // EFFECTS:  draws the brick b onto g
    private void drawBrick(Graphics g, Brick b) {
        Color savedCol = g.getColor();
        g.setColor(Brick.COLOR);
        g.fillRect(b.getX() - Brick.SIZE_X / 2, b.getY() - Brick.SIZE_Y / 2, Brick.SIZE_X, Brick.SIZE_Y);
        g.setColor(savedCol);
    }

    // MODIFIES: g
    // EFFECTS:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, BBGame.HEIGHT / 2 - 50);
        centreString(REPLAY, g, fm, BBGame.HEIGHT / 2);
        centreString(EXIT, g, fm, BBGame.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    // MODIFIES: g
    // EFFECTS:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (BBGame.WIDTH - width) / 2, yPos);
    }
}
