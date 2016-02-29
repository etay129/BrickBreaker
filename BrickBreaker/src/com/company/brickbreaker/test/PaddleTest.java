package com.company.brickbreaker.test;

import static org.junit.Assert.*;

import com.company.brickbreaker.model.BBGame;
import com.company.brickbreaker.model.Paddle;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by etay on 2016-02-20.
 * Unit tests for the Paddle class
 */
public class PaddleTest {
    private static final int XLOC = BBGame.WIDTH / 2;
    private Paddle paddle;

    @Before
    public void runBefore() {
        paddle = new Paddle(XLOC);
    }

    @Test
    public void testConstructor() {
        assertEquals(XLOC, paddle.getX());
    }

    @Test
    public void testMoveRightOnce() {
        paddle.move();
        assertEquals(XLOC + Paddle.DX, paddle.getX());
    }

    @Test
    public void testMoveRightMany() {
        paddle.faceRight();

        for (int i = 0; i < 50; i++) {
            paddle.move();
        }

        assertEquals(XLOC + Paddle.DX * 50, paddle.getX());
    }

    @Test
    public void testMoveLeftOnce() {
        paddle.faceLeft();
        paddle.move();
        assertEquals(XLOC - Paddle.DX, paddle.getX());
    }

    @Test
    public void testMoveLeftMany() {
        paddle.faceLeft();

        for (int i = 0; i < 50; i++) {
            paddle.move();
        }

        assertEquals(XLOC - Paddle.DX * 50, paddle.getX());
    }

    @Test
    public void testLeftBoundary() {
        paddle.faceLeft();

        while (paddle.getX() > Paddle.SIZE_X/2) {
            paddle.move();
        }

        paddle.move();
        paddle.move();

        assertEquals(Paddle.SIZE_X/2 + Paddle.DX, paddle.getX());
    }

    @Test
    public void testRightBoundary() {

        while (paddle.getX() < BBGame.WIDTH - Paddle.SIZE_X/2) {
            paddle.move();
        }

        paddle.move();
        paddle.move();

        assertEquals(BBGame.WIDTH - Paddle.SIZE_X/2 - Paddle.DX, paddle.getX());
    }


}
