package com.company.brickbreaker.model;

import java.awt.*;

/**
 * Represents a paddle
 */
public class Paddle {

    public static final int SIZE_X = 80;
    public static final int SIZE_Y = 8;
    public static final int DX = 4;
    public static final int Y_POS = BBGame.HEIGHT - 40;
    public static final Color COLOR = new Color(3, 19, 250);

    private int direction; // +1 for right, -1 for left
    private int x;

    // EFFECTS: places paddle at position (x, Y_POS) facing right
    public Paddle(int x) {
        this.x = x;
        direction = 1;
    }

    public int getX() {
        return x;
    }

    // MODIFIES: this
    // EFFECTS paddle is facing right
    public void faceRight() {
        direction = 1;
    }

    // MODIFIES: this
    // EFFECTS paddle is facing left
    public void faceLeft() {
        direction = -1;
    }

    // MODIFIES: this
    // EFFECTS:  paddle is moved DX units in whatever direction it is facing and is
    //           constrained to remain within vertical boundaries of game
    public void move() {
        x = x + direction * DX;

        handleBoundary();
    }

    // MODIFIES: this
    // EFFECTS: paddle is constrained to remain within vertical boundaries of game and
    //          switches direction when boundary is hit
    private void handleBoundary() {
        if (x < 0 + Paddle.SIZE_X/2) {
            x = 0 + Paddle.SIZE_X / 2;
            direction = direction * -1;
        }
        else if (x > BBGame.WIDTH - Paddle.SIZE_X/2) {
            x = BBGame.WIDTH - Paddle.SIZE_X/2;
            direction = direction * -1;
        }
    }

    public boolean leftCollidedWith(Ball b) {
        Rectangle paddleBoundingRect = new Rectangle(getX() - SIZE_X / 2, Y_POS - SIZE_Y / 2, SIZE_X/2, SIZE_Y);
        Rectangle ballBoundingRect = new Rectangle(b.getX() - Ball.SIZE_X / 2, b.getY() - Ball.SIZE_Y/ 2, Ball.SIZE_X, Ball.SIZE_Y);
        return paddleBoundingRect.intersects(ballBoundingRect);
    }

    public boolean rightCollidedWith(Ball b) {
        Rectangle paddleBoundingRect = new Rectangle(getX(), Y_POS - SIZE_Y / 2, SIZE_X/2, SIZE_Y);
        Rectangle ballBoundingRect = new Rectangle(b.getX() - Ball.SIZE_X / 2, b.getY() - Ball.SIZE_Y/ 2, Ball.SIZE_X, Ball.SIZE_Y);
        return paddleBoundingRect.intersects(ballBoundingRect);

    }

}
