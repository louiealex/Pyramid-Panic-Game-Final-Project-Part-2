package PyramidPanic.GameObjects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Scarab extends GameObjects {

    public Scarab(int x, int y) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.angle = 0;
        this.active = true;
        try {
            super.img = ImageIO.read(getClass().getResource("/Resources/Scarab.gif"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(int explorerX, int explorerY) {

    }

    @Override
    public void collision(Class c) {
        if (c.equals(Explorer.class)) {
            this.active = false;
        }
    }
}
