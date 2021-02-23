import java.awt.*;

public class Player extends GameObject implements EntityA {

    private double velX = 0.0;
    private double velY = 0.0;

    private Textures textures;

    public Player(double x, double y, Textures textures) {
        super(x, y);
        this.textures = textures;

    }

    public void tick() {
        x += velX;
        y += velY;

        if(x <= 0) {
            x = 0;
        }
        if(x >= 620) {
            x = 620;
        }
        if(y <= 0) {
            y = 0;
        }
        if(y >= 448) {
            y = 448;
        }
    }

    public void render(Graphics g) {
        g.drawImage(textures.player, (int)x, (int)y, null);
    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
}
