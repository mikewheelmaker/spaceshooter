import javax.xml.soap.Text;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Shot extends GameObject implements EntityA {

    private Textures textures;
    private Game game;

    public Shot(double x, double y, Textures textures, Game game) {
        super(x, y);
        this.textures = textures;
        this.game = game;
    }

    public void tick() {
        y -= 10;
    }

    public void render(Graphics g) {
        g.drawImage(textures.shot, (int)x, (int)y, null);
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
