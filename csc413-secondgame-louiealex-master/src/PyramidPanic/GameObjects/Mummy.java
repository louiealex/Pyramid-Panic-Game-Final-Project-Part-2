package PyramidPanic.GameObjects;

import PyramidPanic.GameWorld;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Mummy extends MovableObj {

    private GameWorld gw;

    public Mummy(int x, int y, GameWorld gw) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.angle = 0;
        this.active = true;
        this.gw = gw;

        try {
            this.RightImg = ImageIO.read(getClass().getResource("/Resources/Mummy_right.gif"));
            this.LeftImg = ImageIO.read(getClass().getResource("/Resources/Mummy_left.gif"));
            this.UpImg = ImageIO.read(getClass().getResource("/Resources/Mummy_up.gif"));
            this.DownImg = ImageIO.read(getClass().getResource("/Resources/Mummy_down.gif"));
            super.img = RightImg;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(int explorerX, int explorerY) {

        if (Integer.max(explorerX, this.x) - Integer.min(explorerX, this.x) > Integer.max(explorerY, this.y) - Integer.min(explorerY, this.y)) {

            if (((explorerX > this.x && !gw.getMummiesFlee()) || (explorerX < this.x && gw.getMummiesFlee())) && canMoveRight) {
                this.vx = 1;
                super.img = RightImg;
            } else if (((explorerX < this.x && !gw.getMummiesFlee()) || (explorerX > this.x && gw.getMummiesFlee())) && canMoveLeft) {
                this.vx = -1;
                super.img = LeftImg;
            } else {
                this.vx = 0;
            }

            x += vx;

        } else {

            if (((explorerY > this.y && !gw.getMummiesFlee()) || (explorerY < this.y && gw.getMummiesFlee())) && canMoveDown) {
                this.vy = 1;
                super.img = DownImg;
            } else if (((explorerY < this.y && !gw.getMummiesFlee()) || (explorerY > this.y && gw.getMummiesFlee())) && canMoveUp) {
                this.vy = -1;
                super.img = UpImg;
            } else {
                this.vy = 0;
            }

            y += vy;

        }
        super.update(explorerX, explorerY);
    }

    @Override
    public void collision(Class c) {
        super.collision(c);
    }
}
