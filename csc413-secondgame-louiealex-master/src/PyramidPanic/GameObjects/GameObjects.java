package PyramidPanic.GameObjects;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class GameObjects {

    int x, y, vx, vy, angle;
    Boolean active;
    BufferedImage img;

    public abstract void update(int explorerX, int explorerY);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public abstract void collision(Class c);

    public Rectangle getR() {
        return (new Rectangle(this.x, this.y, img.getWidth(), img.getHeight()));
    }

    public boolean isActive() {
        return active;
    }


    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);

    }


}


