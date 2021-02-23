import java.awt.*;

public class Alien extends GameObject implements EntityB {

    private Textures textures;
    private Game game;
    private Controller c;

    public Alien(double x, double y, Textures textures, Game game, Controller c) {
        super(x, y);
        this.textures = textures;
        this.game = game;
        this.c = c;
    }

    public void tick() {
        if(game.state == Game.STATE.GAME) {
            y += game.getDIFFICULTY();

            if(Physics.Collision(this,game.ea)) {
                c.removeEntityB(this);
                game.setNUMBER_OF_ALIENS_KILLED(game.getNUMBER_OF_ALIENS_KILLED() + 1);
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(textures.alien, (int)x, (int)y, null);
    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
