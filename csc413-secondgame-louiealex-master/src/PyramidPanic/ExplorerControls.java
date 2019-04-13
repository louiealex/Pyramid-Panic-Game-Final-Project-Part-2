package PyramidPanic;

import PyramidPanic.GameObjects.Explorer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ExplorerControls implements KeyListener {

    private final int upKey;
    private final int downKey;
    private final int rightKey;
    private final int leftKey;
    private final int scarabKey;
    private final int wandKey;
    private Explorer e;

    ExplorerControls(Explorer tank, int up, int down, int left, int right, int scarab, int wand) {
        this.e = tank;
        this.upKey = up;
        this.downKey = down;
        this.rightKey = right;
        this.leftKey = left;
        this.scarabKey = scarab;
        this.wandKey = wand;
    }


    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == upKey) {
            this.e.setUpKeyPressed(true);
        }
        if (key == downKey) {
            this.e.setDownKeyPressed(true);
        }
        if (key == leftKey) {
            this.e.setLeftKeyPressed(true);
        }
        if (key == rightKey) {
            this.e.setRightKeyPressed(true);
        }
        if (key == scarabKey) {
            this.e.setScarabKeyPressed(true);
        }
        if (key == wandKey) {
            this.e.setWandKeyPressed(true);
        }
    }


    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == upKey) {
            this.e.setUpKeyPressed(false);
        }
        if (key == downKey) {
            this.e.setDownKeyPressed(false);
        }
        if (key == leftKey) {
            this.e.setLeftKeyPressed(false);
        }
        if (key == rightKey) {
            this.e.setRightKeyPressed(false);
        }
        if (key == scarabKey) {
            this.e.setScarabKeyPressed(false);
        }
        if (key == wandKey) {
            this.e.setWandKeyPressed(false);
        }
    }
}
