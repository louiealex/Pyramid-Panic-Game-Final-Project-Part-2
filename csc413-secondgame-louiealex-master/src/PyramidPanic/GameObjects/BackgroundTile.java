package PyramidPanic.GameObjects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BackgroundTile extends GameObjects {

    public BackgroundTile(int x, int y) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.angle = 0;
        try {
            this.img = ImageIO.read(getClass().getResource("/Resources/Background2.bmp"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        this.active = true;
    }


    @Override
    public void update(int explorerX, int explorerY) {

    }

    @Override
    public void collision(Class c) {

    }


}
