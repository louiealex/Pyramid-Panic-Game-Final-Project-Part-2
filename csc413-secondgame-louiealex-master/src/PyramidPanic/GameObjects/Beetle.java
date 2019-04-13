package PyramidPanic.GameObjects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Beetle extends MovableObj {

    public Beetle(int x, int y) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.angle = 0;
        this.active = true;
        try {
            this.DownImg = ImageIO.read(getClass().getResource("/Resources/Beetle_down.gif"));
            this.UpImg = ImageIO.read(getClass().getResource("/Resources/Beetle_up.gif"));
            super.img = DownImg;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void update(int explorerX, int explorerY) {


        if (explorerY > this.y && canMoveDown) {
            this.vy = 1;
            super.img = DownImg;
        } else if (explorerY < this.y && canMoveUp) {
            this.vy = -1;
            super.img = UpImg;
        } else {
            this.vy = 0;
        }

        this.y += vy;

        super.update(explorerX, explorerY);

    }

    @Override
    public void collision(Class c) {
        super.collision(c);
    }
}
