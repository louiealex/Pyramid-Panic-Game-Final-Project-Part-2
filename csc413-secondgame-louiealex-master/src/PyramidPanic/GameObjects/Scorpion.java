package PyramidPanic.GameObjects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Scorpion extends MovableObj {

    public Scorpion(int x, int y) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.angle = 0;
        this.active = true;
        try {
            this.RightImg = ImageIO.read(getClass().getResource("/Resources/Scorpion_right.gif"));
            this.LeftImg = ImageIO.read(getClass().getResource("/Resources/Scorpion_left.gif"));
            super.img = RightImg;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void update(int explorerX, int explorerY) {


        if (explorerX > this.x && canMoveRight) {
            this.vx = 1;
            super.img = RightImg;
        } else if (explorerX < this.x && canMoveLeft) {
            this.vx = -1;
            super.img = LeftImg;
        } else {
            this.vx = 0;
        }
        x += vx;

        super.update(explorerX, explorerY);

    }

    @Override
    public void collision(Class c) {
        super.collision(c);
    }
}
