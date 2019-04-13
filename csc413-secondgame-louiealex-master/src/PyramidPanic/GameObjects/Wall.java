package PyramidPanic.GameObjects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;


public class Wall extends GameObjects {
    private int strength, timeSincePushed;

    public Wall(int x, int y, int strength) {
        this.x = x;
        this.y = y;

        this.timeSincePushed = 50;
        this.angle = 0;
        this.strength = strength;


        try {

            switch (strength) {
                case 1: //not movable/moving
                case 2:
                    this.vx = 0;
                    this.vy = 0;
                    this.img = ImageIO.read(getClass().getResource("/Resources/Block.gif"));
                    break;
                case 3: // Moving Wall Random
                case 4:
                    Random rand = new Random();
                    this.vx = rand.nextInt(3) - 1;
                    this.vy = rand.nextInt(3) - 1;
                    this.img = ImageIO.read(getClass().getResource("/Resources/Wall2.gif"));
                    break;
                case 5: // Movable Wall Horizontal
                case 6:
                    this.vx = 0;
                    this.vy = 0;
                    this.img = ImageIO.read(getClass().getResource("/Resources/Block_hor.gif"));
                    break;
                case 7: // Movable Wall Vertical
                case 8:
                    this.vx = 0;
                    this.vy = 0;
                    this.img = ImageIO.read(getClass().getResource("/Resources/Block_vert.gif"));
                    break;
                case 9: // Border Wall, not movable/moving
                    this.vx = 0;
                    this.vy = 0;
                    this.img = ImageIO.read(getClass().getResource("/Resources/Wall1.gif"));
                    break;
            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        this.active = true;

    }


    @Override
    public void update(int explorerX, int explorerY) {

        switch (strength) {

            case 5: // Movable Wall Horizontal
            case 6:
            case 7: // Movable Wall Vertical
            case 8:
                if (timeSincePushed > 20) {
                    this.vx = 0;
                    this.vy = 0;
                } else {
                    if (this.strength == 5 || this.strength == 6) {
                        if (this.x < explorerX) {
                            this.vx = -2;
                        } else {
                            this.vx = 2;
                        }
                    } else {
                        if (this.y < explorerY) {
                            this.vy = -2;
                        } else {
                            this.vy = 2;
                        }
                    }
                }
                timeSincePushed++;
                break;

            default:
                break;
        }

        this.x += this.vx;
        this.y += this.vy;
    }

    @Override
    public void collision(Class c) {

        if (this.strength < 9) {

            if (c.equals(Explorer.class) && this.strength >= 5) {
                this.timeSincePushed = 0;
            } else if (c.equals(Wall.class) || c.equals(Explorer.class) || c.equals(Mummy.class) || c.equals(Scorpion.class) || c.equals(Beetle.class)) {
                this.vx = -this.vx;
                this.vy = -this.vy;
                this.x += this.vx;
                this.y += this.vy;
            }
        }

    }

    public int getStrength() {
        return strength;
    }

    public int getTimeSincePushed() {
        return timeSincePushed;
    }

}
