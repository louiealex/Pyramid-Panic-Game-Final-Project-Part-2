package PyramidPanic.GameObjects;

import PyramidPanic.GameWorld;

import java.awt.image.BufferedImage;

public abstract class MovableObj extends GameObjects {

    BufferedImage UpImg, DownImg, RightImg, LeftImg;
    boolean canMoveUp, canMoveDown, canMoveLeft, canMoveRight;


    @Override
    public void update(int explorerX, int explorerY) {

        checkBorder();
        canMoveDown = true;
        canMoveLeft = true;
        canMoveRight = true;
        canMoveUp = true;
    }

    @Override
    public void collision(Class c) {
        if (c.equals(Explorer.class)) {
            this.active = false;
        }
    }

    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameWorld.WORLD_WIDTH - 88) {
            x = GameWorld.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameWorld.WORLD_HEIGHT - 80) {
            y = GameWorld.WORLD_HEIGHT - 80;
        }
    }

    public void setCanMoveUp(boolean canMoveUp) {
        this.canMoveUp = canMoveUp;
    }

    public void setCanMoveDown(boolean canMoveDown) {
        this.canMoveDown = canMoveDown;
    }

    public void setCanMoveLeft(boolean canMoveLeft) {
        this.canMoveLeft = canMoveLeft;
    }

    public void setCanMoveRight(boolean canMoveRight) {
        this.canMoveRight = canMoveRight;
    }

}
