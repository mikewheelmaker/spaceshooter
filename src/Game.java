import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Space Traveller";

    private boolean running = false;
    private Thread thread;

    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private boolean isShooting = false;

    private Player p;
    private Controller c;
    private Textures t;
    private Menu m;
    private Help h;
    private EndScreen es;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;

    public int DIFFICULTY = 1;
    public int NUMBER_OF_ALIENS_TO_KILL = DIFFICULTY * 10;
    public int NUMBER_OF_ALIENS_KILLED = 0;
    public int SCORE = 0;
    public boolean invasion = false;
    public boolean victory = false;

    public static enum STATE {
        MENU,
        GAME,
        HELP,
        ENDSCREEN
    }

    public static STATE state = STATE.MENU;

    public void init() {
        requestFocus();

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            spriteSheet = loader.loadImage("/images/sprite_sheet.png");
            background = loader.loadImage("/images/background.png");
        } catch(IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput(this));

        t = new Textures(this);
        p = new Player(WIDTH * SCALE / 2, HEIGHT * SCALE - 32, t);
        c = new Controller(this, t);
        m = new Menu();
        h = new Help();
        es = new EndScreen(this);

        ea = c.getShots();
        eb = c.getAliens();
    }

    private synchronized void start() {
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if(!running)
            return;

        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        System.exit(1);
    }

    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0.0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                tick();
                updates++;
                delta --;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " Ticks, FPS: " + frames);
                updates = 0;
                frames = 0;
            }

            if(NUMBER_OF_ALIENS_TO_KILL == NUMBER_OF_ALIENS_KILLED) {
                victory = true;
            }

            if(invasion || victory) {
                state = STATE.ENDSCREEN;
            }
        }

        stop();
    }

    private void tick() {
        if(state == STATE.GAME) {
            p.tick();
            c.tick();
            updateSCORE();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////////////
        if(state == STATE.GAME) {
            g.drawImage(background,0,0, null);
            p.render(g);
            c.render(g);
        } else if(state == STATE.MENU) {
            g.drawImage(background,0,0, null);
            m.render(g);
        } else if(state == STATE.HELP) {
            g.drawImage(background,0,0, null);
            h.render(g);
        } else if(state == STATE.ENDSCREEN) {
            g.drawImage(background,0,0, null);
            es.render(g);
        }
        ///////////////////////////////////////
        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(state == STATE.GAME) {
            if(key == KeyEvent.VK_RIGHT) {
                p.setVelX(5);
            } else if(key == KeyEvent.VK_LEFT) {
                p.setVelX(-5);
            } else if(key == KeyEvent.VK_DOWN) {
                p.setVelY(5);
            } else if(key == KeyEvent.VK_UP) {
                p.setVelY(-5);
            } else if(key == KeyEvent.VK_SPACE && !isShooting) {
                c.addEntityA(new Shot(p.getX(), p.getY(), t, this));
                isShooting = true;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(state == STATE.GAME) {
            if(key == KeyEvent.VK_RIGHT) {
                p.setVelX(0);
            } else if(key == KeyEvent.VK_LEFT) {
                p.setVelX(0);
            } else if(key == KeyEvent.VK_DOWN) {
                p.setVelY(0);
            } else if(key == KeyEvent.VK_UP) {
                p.setVelY(0);
            } else if(key == KeyEvent.VK_SPACE) {
                isShooting = false;
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public int getDIFFICULTY() {
        return DIFFICULTY;
    }

    public void setDIFFICULTY(int DIFFICULTY) {
        this.DIFFICULTY = DIFFICULTY;
    }

    public int getNUMBER_OF_ALIENS_KILLED() {
        return NUMBER_OF_ALIENS_KILLED;
    }

    public void setNUMBER_OF_ALIENS_KILLED(int NUMBER_OF_ALIENS_KILLED) {
        this.NUMBER_OF_ALIENS_KILLED = NUMBER_OF_ALIENS_KILLED;
    }

    public int getSCORE() {
        return SCORE;
    }

    public void setSCORE(int SCORE) {
        this.SCORE = SCORE;
    }

    public void updateSCORE() {
        this.SCORE = NUMBER_OF_ALIENS_KILLED * 100 * DIFFICULTY;
    }
}
