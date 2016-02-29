package com.company.brickbreaker.model;

import java.awt.*;

/**
 * Represents a ball.
 */
public class Ball {

    public static final int SIZE_X = 10;
    public static final int SIZE_Y = SIZE_X;
    public static final int DX = 4;
    public static final Color COLOR = new Color(250, 250, 250);

    private int directionX; // +1 for rightwards, -1 for leftwards
    private int directionY; // +1 for upwards, -1 for downwards
    private int x;
    private int y;

    // EFFECTS: places ball at position (x, y) facing up
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        directionX = 1;
        directionY = -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // MODIFIES: this
    // EFFECTS: ball faces towards left
    public void faceLeft() {
        directionX = -1;
    }

    // MODIFIES: this
    // EFFECTS: ball faces towards right
    public void faceRight() {
        directionX = 1;
    }

    // MODIFIES: this
    // EFFECTS: ball faces downwards
    public void faceDown() {
        directionY = 1;
    }

    // MODIFIES: this
    // EFFECTS: ball faces upwards
    public void faceUp() {
        directionY = -1;
    }


    // MODIFIES: this
    // EFFECTS: ball faces opposite direction
    public void flipX() {
        directionX = directionX * -1;
    }

    // MODIFIES: this
    // EFFECTS: ball faces opposite direction
    public void flipY() {
        directionY = directionY * -1;
    }

    // MODIFIES: this
    // EFFECTS:  tank is moved DX units in whatever direction it is facing and is
    //           constrained to remain within vertical boundaries of game
    public void move() {
        x = x + directionX * DX;
        y = y + directionY * DX;

        handleBoundary();
    }

    // MODIFIES: this
    // EFFECTS: ball is constrained to remain within vertical boundaries of game
    private void handleBoundary() {
        if (x < 0 + Ball.SIZE_X/2) {
            x = 0 + Ball.SIZE_X / 2;
            directionX = directionX * -1;
        }
        else if (x > BBGame.WIDTH - Ball.SIZE_X/2) {
            x = BBGame.WIDTH - Ball.SIZE_X/2;
            directionX = directionX * -1;
        }
        else if (y < Ball.SIZE_Y/2) {
            y = Ball.SIZE_Y/2;
            faceDown();
        }

    }


}
