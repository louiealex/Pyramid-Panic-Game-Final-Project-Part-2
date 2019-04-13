package PyramidPanic;

import PyramidPanic.GameObjects.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GameWorld extends JPanel {

    public static final int WORLD_WIDTH = 2240;
    public static final int WORLD_HEIGHT = 2240;

    private static final int SCREEN_WIDTH = 1280;//1440
    private static final int SCREEN_HEIGHT = 704;//900

    //private static final int SCREEN_WIDTH = 800;
    //private static final int SCREEN_HEIGHT = 600;

    private Explorer e1;

    private BufferedImage world;
    private BufferedImage panel, lives, scarab;
    private Graphics2D buffer;
    private JFrame jf;

    private ArrayList<GameObjects> ActiveGameObjects;


    public static void main(String[] args) {
        //Thread x;
        GameWorld gw = new GameWorld();
        gw.init();

        try {
            while (gw.e1.getLivesRemaining() > 0) {

                int i = 0;
                // removes bullets if collided with object or edge.
                while (i < gw.ActiveGameObjects.size()) {
                    gw.ActiveGameObjects.get(i).update(gw.e1.getX(), gw.e1.getY());
                    if (!gw.ActiveGameObjects.get(i).isActive()) {
                        gw.ActiveGameObjects.remove(i);
                    } else {
                        i++;
                    }
                }

                for (i = 0; i < gw.ActiveGameObjects.size(); i++) {
                    for (int j = 0; j < gw.ActiveGameObjects.size(); j++) {
                        GameObjects obj1 = gw.ActiveGameObjects.get(i);
                        GameObjects obj2 = gw.ActiveGameObjects.get(j);

                        Collision collision = new Collision(obj1, obj2);
                        collision.checkForCollision(); // Also looks ahead if its a Tank and  Wall or  Wall and Tank
                    }
                }

                //System.out.println("Tank1: " + GameWorld.t1.toString());

                gw.repaint();

                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }

        long temp = System.currentTimeMillis();
        while (System.currentTimeMillis() - temp < 10000) {
            gw.repaint();
        }
        //Code to exit game window and stop execution
        Window win = SwingUtilities.getWindowAncestor(gw);
        win.setVisible(false);
        win.dispose();

    }

    private void init() {


        this.jf = new JFrame("Pyramid Panic");
        this.world = new BufferedImage(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);

        ActiveGameObjects = new ArrayList<>();
        initMap();

        e1 = new Explorer(WORLD_WIDTH / 2, WORLD_HEIGHT * 29 / 32, 0);
        ExplorerControls PlayerControls = new ExplorerControls(e1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER, KeyEvent.VK_SPACE);

        this.ActiveGameObjects.add(e1);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(PlayerControls);

        this.jf.setSize(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT + 32);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

        try {
            this.panel = ImageIO.read(getClass().getResource("/Resources/Panel.gif"));
            this.lives = ImageIO.read(getClass().getResource("/Resources/Lives.gif"));
            this.scarab = ImageIO.read(getClass().getResource("/Resources/Scarab.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initMap() {

        //background walls
        for (int i = 0; i < WORLD_WIDTH; i = i + 640) {
            for (int j = 0; j < WORLD_HEIGHT; j = j + 448) {
                BackgroundTile b = new BackgroundTile(i, j);
                ActiveGameObjects.add(b);
            }
        }


        for (int i = 0; i < WORLD_WIDTH; i = i + 32) {
            for (int j = 0; j < WORLD_HEIGHT; j = j + 32) {
                //border walls
                if (i == 0 || (WORLD_WIDTH - i) <= 32 || j == 0 || (WORLD_HEIGHT - j) <= 32) {
                    Wall w = new Wall(i, j, 9);
                    ActiveGameObjects.add(w);


                    // Walls not moving/movable
                } else if (i < (WORLD_WIDTH / 4 + 17) && i > (WORLD_WIDTH / 4 - 17) && j % 7 == 0) {
                    Wall w = new Wall(i, j, 1);
                    ActiveGameObjects.add(w);
                } else if (i < (WORLD_WIDTH * 3 / 4 + 17) && i > (WORLD_WIDTH * 3 / 4 - 17) && j % 7 == 0) {
                    Wall w = new Wall(i, j, 2);
                    ActiveGameObjects.add(w);

                } else if (((i < (WORLD_WIDTH * 3 / 8 + 16) && i > (WORLD_WIDTH * 3 / 8 - 17)) || (i < (WORLD_WIDTH * 5 / 8 + 17) && i > (WORLD_WIDTH * 5 / 8 - 16))) && (j % 3 == 0)) {
                    Wall w = new Wall(i, j, 1);
                    ActiveGameObjects.add(w);

                    // Movable Horizontal
                } else if (((i < (WORLD_WIDTH * 3 / 8 + 16) && i > (WORLD_WIDTH * 3 / 8 - 17)) || (i < (WORLD_WIDTH * 5 / 8 + 17) && i > (WORLD_WIDTH * 5 / 8 - 16)))) {
                    Wall w = new Wall(i, j, 5);
                    ActiveGameObjects.add(w);


                    // Random Moving Walls
                } else if (j < (WORLD_HEIGHT / 4 + 17) && j > (WORLD_HEIGHT / 4 - 17) && i % 17 == 0) {
                    Wall w = new Wall(i, j, 3);
                    ActiveGameObjects.add(w);
                } else if (j < (WORLD_HEIGHT * 3 / 4 + 17) && j > (WORLD_HEIGHT * 3 / 4 - 17) && i % 17 == 0) {
                    Wall w = new Wall(i, j, 4);
                    ActiveGameObjects.add(w);

                    // Blocking Vertical
                } else if (((j < (WORLD_HEIGHT * 3 / 8 + 16) && j > (WORLD_HEIGHT * 3 / 8 - 17)) || (j < (WORLD_HEIGHT * 5 / 8 + 17) && j > (WORLD_HEIGHT * 5 / 8 - 16))) && (i % 3 == 0)) {
                    Wall w = new Wall(i, j, 1);
                    ActiveGameObjects.add(w);

                    // Moving Walls Vertical
                } else if ((j < (WORLD_HEIGHT * 3 / 8 + 16) && j > (WORLD_HEIGHT * 3 / 8 - 17)) || (j < (WORLD_HEIGHT * 5 / 8 + 17) && j > (WORLD_HEIGHT * 5 / 8 - 16))) {
                    Wall w = new Wall(i, j, 7);
                    ActiveGameObjects.add(w);
                } else if (i < (WORLD_WIDTH * 3 / 4) && i > (WORLD_WIDTH * 1 / 4) && (j < (WORLD_HEIGHT / 2 + 17)) && (j > (WORLD_HEIGHT / 2 - 17))) {
                    Wall w = new Wall(i, j, 1);
                    ActiveGameObjects.add(w);

                    // Enemies/Creatures
                } else if (i < (WORLD_WIDTH * 3 / 16 + 16) && i > (WORLD_WIDTH * 3 / 16 - 17) && j % 13 == 0) {
                    Beetle b = new Beetle(i, j);
                    this.ActiveGameObjects.add(b);
                } else if (i < (WORLD_WIDTH * 5 / 16 + 17) && i > (WORLD_WIDTH * 5 / 16 - 16) && j % 13 == 0) {
                    Beetle b = new Beetle(i, j);
                    this.ActiveGameObjects.add(b);
                } else if (i < (WORLD_WIDTH * 5 / 16 + 17) && i > (WORLD_WIDTH * 5 / 16 - 17) && j % 7 == 0) {
                    Mummy m = new Mummy(i, j, this);
                    this.ActiveGameObjects.add(m);
                } else if (i < (WORLD_WIDTH * 11 / 16 + 17) && i > (WORLD_WIDTH * 11 / 16 - 17) && j % 7 == 0) {
                    Mummy m = new Mummy(i, j, this);
                    this.ActiveGameObjects.add(m);
                } else if (i < (WORLD_WIDTH / 2 + 17) && i > (WORLD_WIDTH / 2 - 17) && (j < WORLD_HEIGHT * 3 / 4) && j % 3 == 0) {
                    Mummy m = new Mummy(i, j, this);
                    this.ActiveGameObjects.add(m);
                } else if (i < (WORLD_WIDTH * 5 / 8 + 17) && i > (WORLD_WIDTH * 5 / 8 - 17) && (j < WORLD_HEIGHT * 3 / 4) && j % 5 == 0) {
                    Mummy m = new Mummy(i, j, this);
                    this.ActiveGameObjects.add(m);
                } else if (i < (WORLD_WIDTH * 3 / 8 + 17) && i > (WORLD_WIDTH * 3 / 8 - 17) && (j < WORLD_HEIGHT * 3 / 4) && j % 5 == 0) {
                    Mummy m = new Mummy(i, j, this);
                    this.ActiveGameObjects.add(m);
                } else if (i < (WORLD_WIDTH * 3 / 16 + 17) && i > (WORLD_WIDTH * 3 / 16 - 17) && j % 13 == 0) {
                    Scorpion s = new Scorpion(i, j);
                    this.ActiveGameObjects.add(s);
                } else if (i < (WORLD_WIDTH * 13 / 16 + 17) && i > (WORLD_WIDTH * 13 / 16 - 17) && j % 13 == 0) {
                    Scorpion s = new Scorpion(i, j);
                    this.ActiveGameObjects.add(s);


                    // PowerUps
                } else if (i < (WORLD_WIDTH / 16 + 17) && i > (WORLD_WIDTH / 16 - 17) && j % 7 == 0) {
                    Scarab s1 = new Scarab(i, j);
                    this.ActiveGameObjects.add(s1);
                } else if (i < (WORLD_WIDTH * 15 / 16 + 17) && i > (WORLD_WIDTH * 15 / 16 - 17) && j % 7 == 0) {
                    Scarab s1 = new Scarab(i, j);
                    this.ActiveGameObjects.add(s1);
                } else if (i < (WORLD_WIDTH * 11 / 32 + 17) && i > (WORLD_WIDTH * 11 / 32 - 17) && j % 5 == 0) {
                    Scarab s1 = new Scarab(i, j);
                    this.ActiveGameObjects.add(s1);
                } else if (i < (WORLD_WIDTH * 21 / 32 + 17) && i > (WORLD_WIDTH * 21 / 32 - 17) && j % 5 == 0) {
                    Scarab s1 = new Scarab(i, j);
                    this.ActiveGameObjects.add(s1);
                } else if (i < (WORLD_WIDTH / 2 + 17) && i > (WORLD_WIDTH / 2 - 17) && j % 3 == 0) {
                    Amulet a = new Amulet(i, j);
                    this.ActiveGameObjects.add(a);
                } else if (i < (WORLD_WIDTH / 2 + 17) && i > (WORLD_WIDTH / 2 - 17) && j % 5 == 0) {
                    Anubis a1 = new Anubis(i, j);
                    this.ActiveGameObjects.add(a1);
                } else if (i < (WORLD_WIDTH * 5 / 32 + 17) && i > (WORLD_WIDTH * 5 / 32 - 17) && j % 3 == 0) {
                    Amulet a = new Amulet(i, j);
                    this.ActiveGameObjects.add(a);
                } else if (i < (WORLD_WIDTH * 27 / 32 + 17) && i > (WORLD_WIDTH * 27 / 32 - 17) && j % 5 == 0) {
                    Anubis a1 = new Anubis(i, j);
                    this.ActiveGameObjects.add(a1);
                }
            }
        }

        Sword sword = new Sword(WORLD_WIDTH / 2, WORLD_HEIGHT * 3 / 32);
        this.ActiveGameObjects.add(sword);

    }


    public boolean getMummiesFlee() {
        return e1.isScarabActive() || e1.isSwordActive();
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        this.buffer = this.world.createGraphics();
        super.paintComponent(g2);

        if (this.e1.isSwordPickedUp() && !this.e1.isSwordActive()) {
            float opacity = 0.2f;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        } else {
            float opacity = 1f;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        }


        if (this.e1.getLivesRemaining() > 0) {
            // drawing each active object
            for (int i = 0; i < ActiveGameObjects.size(); i++) {
                ActiveGameObjects.get(i).drawImage(this.buffer);
            }


            // calculating Subimage to be shown.
            int sub1x = e1.getX() - SCREEN_WIDTH * 20 / 40;
            if ((e1.getX() - SCREEN_WIDTH * 20 / 40) < 0) {
                sub1x = 0;
            } else if ((e1.getX() + SCREEN_WIDTH * 20 / 40) > (WORLD_WIDTH)) {
                sub1x = WORLD_WIDTH - SCREEN_WIDTH * 20 / 20;
            }

            int sub1y = e1.getY() - SCREEN_HEIGHT / 2;
            if ((e1.getY() - SCREEN_HEIGHT / 2) < 0) {
                sub1y = 0;
            } else if ((e1.getY() + SCREEN_HEIGHT / 2) > (WORLD_HEIGHT)) {
                sub1y = WORLD_HEIGHT - SCREEN_HEIGHT;
            }

            // displaying sub images: Player
            BufferedImage sub1 = this.world.getSubimage(sub1x, sub1y, SCREEN_WIDTH, SCREEN_HEIGHT);

            if (this.e1.isSwordPickedUp() && !this.e1.isSwordActive()) {
                int minX, maxX, minY, maxY;
                if ((e1.getX() - SCREEN_WIDTH / 2) < 0) {
                    maxX = Integer.max(SCREEN_WIDTH / 2 - (SCREEN_WIDTH / 2 - e1.getX()) + SCREEN_WIDTH / 4, SCREEN_WIDTH / 4);
                    minX = Integer.max(maxX - SCREEN_WIDTH / 2, 0);
                } else if ((e1.getX() + SCREEN_WIDTH / 2) > WORLD_WIDTH) {

                    maxX = Integer.min(SCREEN_WIDTH - (WORLD_WIDTH - e1.getX()) + SCREEN_WIDTH / 4, SCREEN_WIDTH);
                    minX = maxX - SCREEN_WIDTH / 2;

                } else {
                    minX = SCREEN_WIDTH / 4;
                    maxX = SCREEN_WIDTH * 3 / 4;
                }


                if ((e1.getY() - SCREEN_HEIGHT / 2) < 0) {
                    maxY = Integer.max(SCREEN_HEIGHT / 2 - (SCREEN_HEIGHT / 2 - e1.getY()) + SCREEN_HEIGHT / 4, SCREEN_HEIGHT / 4);
                    minY = Integer.max(maxY - SCREEN_HEIGHT / 2, 0);
                } else if ((e1.getY() + SCREEN_HEIGHT / 2) > WORLD_HEIGHT) {
                    maxY = Integer.min(SCREEN_HEIGHT - (WORLD_HEIGHT - e1.getY()) + SCREEN_HEIGHT / 4, SCREEN_HEIGHT);
                    minY = maxY - SCREEN_HEIGHT / 2;
                } else {
                    minY = SCREEN_HEIGHT / 4;
                    maxY = SCREEN_HEIGHT * 3 / 4;
                }

                for (int i = 0; i < SCREEN_WIDTH; i++) {
                    for (int j = 0; j < SCREEN_HEIGHT; j++) {
                        if (i < minX || i > maxX || j < minY || j > maxY) {
                            sub1.setRGB(i, j, 0);
                        }
                    }
                }
            }
            g2.drawImage(sub1, 0, 0, null);


            // Displaying the panel.
            g2.drawImage(panel.getScaledInstance(SCREEN_WIDTH / 2, SCREEN_WIDTH / 2 * 32 / 640, BufferedImage.TYPE_INT_RGB), 0, SCREEN_HEIGHT - SCREEN_WIDTH / 2 * 32 / 640, null);
            g2.setColor(Color.BLACK);

            //Display Lives on Panel
            for (int i = 0; i < this.e1.getLivesRemaining(); i++) {
                g2.drawImage(lives, i * 40 + SCREEN_WIDTH * 6 / 100, SCREEN_HEIGHT - SCREEN_WIDTH / 2 * 32 / 640, null);
                g2.setFont(new Font("Arial", Font.BOLD, 25));
                g2.drawString(Integer.toString(this.e1.getHealth()), SCREEN_WIDTH * 42 / 100, SCREEN_HEIGHT - SCREEN_WIDTH / 2 * 11 / 640);
            }

            // Display Health on Panel
            for (int i = 0; i < this.e1.getScarabCount(); i++) {
                g2.drawImage(scarab, i * 40 + SCREEN_WIDTH * 20 / 100, SCREEN_HEIGHT - SCREEN_WIDTH / 2 * 32 / 640, null);
                g2.setFont(new Font("Arial", Font.BOLD, 25));
                g2.drawString(Integer.toString(this.e1.getHealth()), SCREEN_WIDTH * 42 / 100, SCREEN_HEIGHT - SCREEN_WIDTH / 2 * 11 / 640);
            }


        } else {
            float opacity = 1f;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            if (this.e1.isPlayerWon()) {
                g2.drawString("You made it back to the original position and Won", SCREEN_WIDTH * 9 / 20, SCREEN_HEIGHT * 11 / 40);
            } else if (!this.e1.isPlayerWon()) {
                g2.drawString("You Lost", SCREEN_WIDTH * 9 / 20, SCREEN_HEIGHT * 11 / 40);
            }
            g2.drawString("Your Score is " + e1.getHealth(), SCREEN_WIDTH * 9 / 20, SCREEN_HEIGHT * 18 / 40);
            g2.drawString("This window will close after about 10 seconds.", SCREEN_WIDTH * 8 / 20, SCREEN_HEIGHT * 25 / 40);
        }

    }


}

