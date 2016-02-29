package com.company.brickbreaker.test;

import static org.junit.Assert.*;

import com.company.brickbreaker.model.BBGame;
import com.company.brickbreaker.model.Ball;
import com.company.brickbreaker.model.Paddle;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by etay on 2016-02-20.
 */
public class BallTest {
    private Ball ball;
    private static final int XLOC = BBGame.WIDTH/2;
    private static final int YLOC = Paddle.Y_POS - Ball.SIZE_Y/2;

    @Before
    public void runBefore(){
        ball = new Ball(XLOC, YLOC);
    }

    @Test
    public void testConstructor() {
        assertEquals(XLOC, ball.getX());
        assertEquals(YLOC, ball.getY());
    }

    @Test
    public void testMoveNEOnce() {
        ball.move();
        assertEquals(XLOC + Ball.DX, ball.getX());
        assertEquals(YLOC - Ball.DX, ball.getY());
    }

    @Test
    public void testMoveSEOnce() {
        ball.faceDown();
        ball.move();
        assertEquals(XLOC + Ball.DX, ball.getX());
        assertEquals(YLOC + Ball.DX, ball.getY());
    }

    @Test
    public void testMoveSWOnce() {
        ball.faceDown();
        ball.faceLeft();
        ball.move();
        assertEquals(XLOC - Ball.DX, ball.getX());
        assertEquals(YLOC + Ball.DX, ball.getY());
    }

    @Test
    public void testMoveNWOnce() {
        ball.faceDown();
        ball.faceUp();
        ball.faceLeft();
        ball.move();
        assertEquals(XLOC - Ball.DX, ball.getX());
        assertEquals(YLOC - Ball.DX, ball.getY());
    }

    @Test
    public void testLeftBoundary() {
        ball.faceLeft();

        while (ball.getX() < BBGame.WIDTH - Ball.SIZE_X/2) {
            ball.move();
        }

        ball.move();
        ball.move();

        assertEquals(BBGame.WIDTH - Ball.SIZE_X/2 - Ball.DX, ball.getX());
    }

    @Test
    public void testRightBoundary() {
        ball.faceRight();

        while (ball.getX() > Ball.SIZE_X/2) {
            ball.move();
        }

        ball.move();
        ball.move();

        assertEquals(Ball.SIZE_X/2 + Ball.DX, ball.getX());
    }
}
