package PyramidPanic;

import PyramidPanic.GameObjects.Explorer;
import PyramidPanic.GameObjects.GameObjects;
import PyramidPanic.GameObjects.MovableObj;
import PyramidPanic.GameObjects.Wall;

import java.awt.*;

class Collision {
    private GameObjects obj1, obj2;

    Collision(GameObjects obj1, GameObjects obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    void checkForCollision() {

        //All these cases, there are no possible collisions between moving objects
        if (obj1.equals(obj2) || !obj1.isActive() || !obj2.isActive()) {
            return;
        }


        // Checks for collisions by using the rectangle intersect method
        Rectangle obj1Rect = obj1.getR();
        Rectangle obj2Rect = obj2.getR();


        //smallest overlap region possible
        if ((!obj1Rect.intersection(obj2Rect).isEmpty()) && ((obj1Rect.intersection(obj2Rect).getWidth() > 0) || (obj1Rect.intersection(obj2Rect).getHeight() > 0))) {
            obj1.collision(obj2.getClass());
            obj2.collision(obj1.getClass());
        }


        // Look Ahead: For Movable detecting Walls.
        if (obj1 instanceof MovableObj && obj2 instanceof Wall) {
            checkAhead((MovableObj) obj1, (Wall) obj2);
        }

        if (obj1 instanceof Wall && obj2 instanceof MovableObj) {
            checkAhead((MovableObj) obj2, (Wall) obj1);
        }


        // Look Ahead: For Movable detecting other Movable.
        if (obj1 instanceof MovableObj && obj2 instanceof MovableObj) {
            checkAhead((MovableObj) obj1, (MovableObj) obj2);
        }


    }


    private void checkAhead(MovableObj movableObj1, Wall wall) {

        if (movableObj1 instanceof Explorer && wall.getStrength() >= 5 && wall.getStrength() <= 8 && wall.getTimeSincePushed() > 20) {
            return;
        }

        // Look Ahead: For Tanks detecting Walls.
        // Assumes Tanks can move in any direction until a possible collision with a wall is detected.

        int checkAmount = 14;
        Rectangle movableRect = movableObj1.getR();

        // look ahead towards left of screen
        movableRect.setBounds(movableRect.x - checkAmount, movableRect.y, checkAmount, movableRect.height);
        if (movableRect.intersects(wall.getR())) {
            movableObj1.setCanMoveLeft(false);
        }

        movableRect = movableObj1.getR();
        // look ahead towards right of screen
        movableRect.setBounds(movableRect.x + movableRect.width, movableRect.y, checkAmount, movableRect.height);
        if (movableRect.intersects(wall.getR())) {
            movableObj1.setCanMoveRight(false);
        }


        movableRect = movableObj1.getR();
        // look ahead towards top of screen
        movableRect.setBounds(movableRect.x, movableRect.y - checkAmount, movableRect.width, checkAmount);
        if (movableRect.intersects(wall.getR())) {
            movableObj1.setCanMoveUp(false);
        }

        movableRect = movableObj1.getR();
        // look ahead towards bottom of screen
        movableRect.setBounds(movableRect.x, movableRect.y + movableRect.height, movableRect.width, checkAmount);
        if (movableRect.intersects(wall.getR())) {
            movableObj1.setCanMoveDown(false);

        }
    }

    private void checkAhead(MovableObj movableObj1, MovableObj movableObj2) {

        if (movableObj1 instanceof Explorer || movableObj2 instanceof Explorer) {
            return;
        }

        // Look Ahead: For Movable detecting Movable.
        // Assumes Tanks can move in any direction until a possible collision with a wall is detected.

        int checkAmount = 8;
        Rectangle movableRect = movableObj1.getR();

        // look ahead towards left of screen
        movableRect.setBounds(movableRect.x - checkAmount, movableRect.y, checkAmount, movableRect.height);
        if (movableRect.intersects(movableObj2.getR())) {
            movableObj1.setCanMoveLeft(false);
        }

        movableRect = movableObj1.getR();
        // look ahead towards right of screen
        movableRect.setBounds(movableRect.x + movableRect.width, movableRect.y, checkAmount, movableRect.height);
        if (movableRect.intersects(movableObj2.getR())) {
            movableObj1.setCanMoveRight(false);
        }


        movableRect = movableObj1.getR();
        // look ahead towards top of screen
        movableRect.setBounds(movableRect.x, movableRect.y - checkAmount, movableRect.width, checkAmount);
        if (movableRect.intersects(movableObj2.getR())) {
            movableObj1.setCanMoveUp(false);
        }

        movableRect = movableObj1.getR();
        // look ahead towards bottom of screen
        movableRect.setBounds(movableRect.x, movableRect.y + movableRect.height, movableRect.width, checkAmount);
        if (movableRect.intersects(movableObj2.getR())) {
            movableObj1.setCanMoveDown(false);

        }
    }
}

