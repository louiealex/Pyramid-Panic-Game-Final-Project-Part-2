package PyramidPanic.GameObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Explorer extends MovableObj {
    private final int R = 2;
    private final int rotationSpeed = 2;
    private boolean upKeyPressed, downKeyPressed, leftKeyPressed, rightKeyPressed, scarabKeyPressed, wandKeyPressed;
    private int timeSinceScarab, health, livesRemaining;
    private int origX, origY, scarabCount;
    private boolean ScarabActive, SwordActive, SwordPickedUp, playerWon;
    private BufferedImage UpImg, DownImg, RightImg, LeftImg;


    public Explorer(int x, int y, int angle) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.angle = angle;
        try {
            this.UpImg = ImageIO.read(getClass().getResource("/Resources/Explorer_up.gif"));
            this.DownImg = ImageIO.read(getClass().getResource("/Resources/Explorer_down.gif"));
            this.RightImg = ImageIO.read(getClass().getResource("/Resources/Explorer_right.gif"));
            this.LeftImg = ImageIO.read(getClass().getResource("/Resources/Explorer_left.gif"));
            super.img = RightImg;
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.canMoveDown = true;
        this.canMoveLeft = true;
        this.canMoveRight = true;
        this.canMoveUp = true;

        this.active = true;
        this.ScarabActive = false;
        this.SwordActive = false;
        this.timeSinceScarab = 1400;
        this.health = 0;
        this.livesRemaining = 3;
        this.scarabCount = 0;
        this.SwordPickedUp = false;
        this.playerWon = false;

        this.downKeyPressed = false;
        this.leftKeyPressed = false;
        this.rightKeyPressed = false;
        this.upKeyPressed = false;
        this.scarabKeyPressed = false;
        this.wandKeyPressed = false;

        this.origX = x;
        this.origY = y;
    }

    public void update(int explorerX, int explorerY) {


        timeSinceScarab++;

        if (this.upKeyPressed) {
            this.moveForwards();
        }
        if (this.downKeyPressed) {
            this.moveBackwards();
        }
        if (this.leftKeyPressed) {
            this.rotateLeft();
        }
        if (this.rightKeyPressed) {
            this.rotateRight();
        }


        if (this.scarabKeyPressed && this.scarabCount > 0 && this.timeSinceScarab > 1400) {
            this.timeSinceScarab = 0;
            this.scarabCount--;
            this.ScarabActive = true;
        }

        if (this.wandKeyPressed && this.SwordPickedUp && this.health > 0) {
            this.SwordActive = true;
            this.health--;
        } else {
            this.SwordActive = false;
        }

        if (health == 0) {
            this.SwordActive = false;
        }


        if (this.timeSinceScarab > 1400) {
            ScarabActive = false;
        }

        timeSinceScarab++;


        if (SwordPickedUp && (this.origX - 50 <= this.x) && (this.x <= this.origX + 50) && (this.origY - 50 <= this.y) && (this.y <= this.origY + 50)) {
            this.livesRemaining = 0; // player Won.
            this.playerWon = true;
        }

        super.update(explorerX, explorerY);

    }

    public boolean isSwordActive() {
        return SwordActive;
    }

    public boolean isScarabActive() {
        return ScarabActive;
    }

    @Override
    public void collision(Class c) {
        //super.collision(c);

        if (c.equals(Scorpion.class) || c.equals(Beetle.class) || (c.equals(Mummy.class) && !ScarabActive)) {
            this.livesRemaining--;
        } else if (c.equals(Mummy.class) && ScarabActive) {
            this.health += 15;
        } else if (c.equals(Scarab.class)) {
            if (scarabCount < 3) {
                scarabCount++;
            }
        } else if (c.equals(Anubis.class)) {
            this.health += 5;
        } else if (c.equals(Amulet.class)) {
            this.health += 10;
        } else if (c.equals(Sword.class)) {
            this.SwordPickedUp = true;
        }

    }

    private void rotateLeft() {
        this.angle -= this.rotationSpeed;
        if (this.angle < 0) {
            this.angle += 360;
        } else if (this.angle > 360) {
            this.angle -= 360;
        }
    }

    private void rotateRight() {
        this.angle += this.rotationSpeed;
        if (this.angle < 0) {
            this.angle += 360;
        } else if (this.angle > 360) {
            this.angle -= 360;
        }
    }

    private void moveBackwards() {
        if (angle >= 315 || angle < 45) { // facing right
            if (canMoveLeft) {
                vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            } else {
                vx = 0;
                vy = 0;
            }
        }
        if (angle >= 45 && angle < 135) { // facing down
            if (canMoveUp) {
                vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            } else {
                vx = 0;
                vy = 0;
            }
        }
        if (angle >= 135 && angle < 225) { // facing left
            if (canMoveRight) {
                vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            } else {
                vx = 0;
                vy = 0;
            }
        }
        if (angle >= 225 && angle < 315) { // facing up
            if (canMoveDown) {
                vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            } else {
                vx = 0;
                vy = 0;
            }
        }
        x -= vx;
        y -= vy;
    }

    private void moveForwards() {

        if (angle >= 315 || angle < 45) { // facing right
            if (canMoveRight) {
                vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            } else {
                vx = 0;
                vy = 0;
            }
        }
        if (angle >= 45 && angle < 135) { // facing down
            if (canMoveDown) {
                vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            } else {
                vx = 0;
                vy = 0;
            }
        }
        if (angle >= 135 && angle < 225) { // facing left
            if (canMoveLeft) {
                vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            } else {
                vx = 0;
                vy = 0;
            }
        }
        if (angle >= 225 && angle < 315) { // facing up
            if (canMoveUp) {
                vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            } else {
                vx = 0;
                vy = 0;
            }
        }

        x += vx;
        y += vy;
    }


    public void setLeftKeyPressed(boolean leftKeyPressed) {
        this.leftKeyPressed = leftKeyPressed;
    }

    public void setUpKeyPressed(boolean upKeyPressed) {
        this.upKeyPressed = upKeyPressed;
    }

    public void setDownKeyPressed(boolean downKeyPressed) {
        this.downKeyPressed = downKeyPressed;
    }

    public void setRightKeyPressed(boolean rightKeyPressed) {
        this.rightKeyPressed = rightKeyPressed;
    }

    public void setScarabKeyPressed(boolean scarabKeyPressed) {
        this.scarabKeyPressed = scarabKeyPressed;
    }

    public void setWandKeyPressed(boolean wandKeyPressed) {
        this.wandKeyPressed = wandKeyPressed;
    }

    public int getHealth() {
        return health;
    }

    public int getLivesRemaining() {
        return livesRemaining;
    }

    public int getScarabCount() {
        return scarabCount;
    }

    public boolean isSwordPickedUp() {
        return SwordPickedUp;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }
}



