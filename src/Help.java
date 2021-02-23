import java.awt.*;

public class Help {
    public Rectangle backToMenu = new Rectangle(0, 250, 300, 30);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("SPACE TRAVELLER", 0, 50);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Use Arrow Keys to move around", 0, 100);
        g.drawString("Press SPACE to shoot", 0, 140);
        g.drawString("Don't let the aliens reach the bottom", 0, 180);
        g.drawString("Kill all the aliens to win", 0, 220);
        g.drawString("MAIN MENU", 0, 275);
        g2d.draw(backToMenu);
    }
}
