import java.awt.*;

public class Menu {

    public Rectangle playButtonD1 = new Rectangle(0, 100, 300, 30);
    public Rectangle playButtonD2 = new Rectangle(0, 140, 300, 30);
    public Rectangle playButtonD3 = new Rectangle(0, 180, 300, 30);
    public Rectangle playButtonD4 = new Rectangle(0, 220, 300, 30);
    public Rectangle playButtonD5 = new Rectangle(0, 260, 300, 30);
    public Rectangle instructions = new Rectangle(0, 300, 300, 30);
    public Rectangle exit = new Rectangle(0, 340, 100, 30);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("SPACE TRAVELLER", 0, 50);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("PLAY (BEGINNER)", 0, 125);
        g.drawString("PLAY (EASY)", 0, 165);
        g.drawString("PLAY (MEDIUM)", 0, 205);
        g.drawString("PLAY (HARD)", 0, 245);
        g.drawString("PLAY (PRO)", 0, 285);
        g.drawString("INSTRUCTIONS", 0, 325);
        g.drawString("EXIT", 0, 365);
        g2d.draw(playButtonD1);
        g2d.draw(playButtonD2);
        g2d.draw(playButtonD3);
        g2d.draw(playButtonD4);
        g2d.draw(playButtonD5);
        g2d.draw(instructions);
        g2d.draw(exit);
    }
}
