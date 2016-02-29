package com.company.brickbreaker.model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by etay on 2016-02-20.
 * Represents a brick breaker game.
 */
public class BBGame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int INIT_NUM_BRICKS = 42;
    public static final int POS_FIRST_BRICK = Brick.SIZE_X/2 + 18;

    private List<Brick> bricks;
    private Ball ball;
    private Paddle paddle;
    private boolean isGameOver;
    private int numBricksDestroyed;

    // EFFECTS: creates list of bricks, centres paddle on screen
    public BBGame() {
        bricks = new ArrayList<Brick>();
        setUp();
    }

    // MODIFIES: this
    // EFFECTS: updates tank, missiles and invaders
    public void update() {
        ball.move();
        paddle.move();

        checkCollisions();
        bounce();
        checkGameOver();
    }

    // MODIFIES: this
    // EFFECTS: turns paddle, resets game in response to given key pressed code
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_R && isGameOver) {
            setUp();
        }
        else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        }
        else {
            paddleControl(keyCode);
        }
    }

    public boolean isOver() {
        return isGameOver;
    }

    public int getNumBricksDestroyed() {
        return numBricksDestroyed;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Ball getBall() {
        return ball;
    }


    // MODIFIES: this
    // EFFECTS: initializes ball, paddle and bricks
    private void setUp() {
        ball = new Ball(WIDTH/2, Paddle.Y_POS - Paddle.SIZE_Y/2);
        paddle = new Paddle(WIDTH/2);

        int x = POS_FIRST_BRICK;
        int y = Brick.SIZE_Y/2;

        for (int i = 0; i < INIT_NUM_BRICKS; i++) {

            if (x >= WIDTH - Brick.SIZE_X/2) {
                y = y + Brick.SIZE_Y + 5;
                x = POS_FIRST_BRICK;
            }

            bricks.add(new Brick(x, y));
            x = x + Brick.SIZE_X + 5;
        }

        isGameOver = false;
        numBricksDestroyed = 0;
    }

    // MODIFIES: this
    // EFFECTS: turns paddle in response to key code
    private void paddleControl(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {
            paddle.faceLeft();
        }
        else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {
            paddle.faceRight();
        }
    }


    // MODIFIES: this
    // EFFECTS: removes any bricks that have been hit by ball
    private void checkCollisions() {
        List<Brick> bricksToRemove = new ArrayList<Brick>();

        for (Brick target : bricks) {
            if (checkBallHitBrick(target)) {

                bricksToRemove.add(target);
                numBricksDestroyed++;
                //ball.flipY();
                //ball.flipX();
            }
        }

        if (!bricksToRemove.isEmpty()) {
            ball.flipX();
            ball.flipY();
        }
        bricks.removeAll(bricksToRemove);
    }


    // MODIFIES: this
    // EFFECTS: bounces ball according to where it strikes paddle
    private void bounce() {
        if (paddle.leftCollidedWith(getBall())) {
            ball.faceLeft();
            ball.faceUp();
        }
        else if (paddle.rightCollidedWith(getBall())) {
            ball.faceRight();
            ball.faceUp();
        }
    }

    // MODIFIES: none
    // EFFECTS: if ball has hit brick, return true and add 1 to numBricksDestroyed;
    //          otherwise return false
    private boolean checkBallHitBrick(Brick target) {
        if (target.collidedWith(getBall())) {

            //ball.flipY();
            //ball.flipX();
            return true;
        }

        return false;
    }


    // MODIFIES: this
    // EFFECTS: if the ball touches bottom of screen, game is over
    private void checkGameOver() {
        if (ball.getY() > HEIGHT) {
            isGameOver = true;
        }
    }


}
