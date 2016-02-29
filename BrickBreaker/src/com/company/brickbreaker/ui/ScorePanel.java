package com.company.brickbreaker.ui;

import com.company.brickbreaker.model.BBGame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by etay on 2016-02-20.
 * Represents the panel in which the score is displayed
 */
@SuppressWarnings("serial")
public class ScorePanel extends JPanel {
    private static final String BRICKS_TXT = "Bricks destroyed: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private BBGame game;
    private JLabel bricksLbl;

    // EFFECTS: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(BBGame g) {
        game = g;
        setBackground(new Color(180, 180, 180));
        bricksLbl = new JLabel(BRICKS_TXT + game.getNumBricksDestroyed());
        bricksLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(bricksLbl);
        add(Box.createHorizontalStrut(10));
    }

    // MODIFIES: this
    // EFFECTS:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        bricksLbl.setText(BRICKS_TXT + game.getNumBricksDestroyed());
        repaint();
    }
}

