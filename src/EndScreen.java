import java.awt.*;

public class EndScreen {
    Game game;

    public Rectangle backToMenu = new Rectangle(0, 180, 300, 30);
    public Rectangle exit = new Rectangle(0, 220, 300, 30);

    public EndScreen(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("SPACE TRAVELLER", 0, 50);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        if(game.victory) {
            g.drawString("VICTORY!", 0, 100);
            g.drawString("Score: " + game.getSCORE(), 0, 140);
        }
        if(game.invasion) {
            g.drawString("INVASION!", 0, 100);
            g.drawString("Score: " + game.getSCORE(), 0, 140);
        }

        g.drawString("MAIN MENU", 0, 205);
        g.drawString("EXIT", 0, 245);
        g2d.draw(backToMenu);
        g2d.draw(exit);
    }
}
