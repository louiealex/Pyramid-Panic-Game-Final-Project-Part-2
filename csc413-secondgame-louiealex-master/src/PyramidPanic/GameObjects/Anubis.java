package PyramidPanic.GameObjects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Anubis extends GameObjects {

    public Anubis(int x, int y) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.angle = 0;
        this.active = true;
        try {
            this.img = ImageIO.read(getClass().getResource("/Resources/Treasure2.gif"));
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
