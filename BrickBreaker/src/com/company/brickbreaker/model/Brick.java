package com.company.brickbreaker.model;


import java.awt.*;

/**
 * Represents a brick
 */
public class Brick {

    public static final int SIZE_X = 50;
    public static final int SIZE_Y = SIZE_X/2;
    public static Color COLOR = new Color(155, 44, 15);

    private int x;
    private int y;

    // MODIFIES: this
    // EFFECTS: brick is positioned at coordinates (x, y)
    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // MODIFIES: none
    // EFFECTS: returns true if this brick has collided with ball; false otherwise
    public boolean collidedWith(Ball b) {
        Rectangle brickBoundingRect = new Rectangle(getX() - (SIZE_X / 2), getY() - (SIZE_Y / 2), SIZE_X, SIZE_Y);
        Rectangle ballBoundingRect = new Rectangle(b.getX() - (SIZE_X / 2), b.getY() - (SIZE_Y / 2), Ball.SIZE_X, Ball.SIZE_Y);
        return brickBoundingRect.intersects(ballBoundingRect);
    }



}
