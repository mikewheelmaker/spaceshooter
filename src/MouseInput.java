import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    Game game;

    public MouseInput(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //pd1
        if(mx >= 0 && mx <= 300 && my >= 100 && my <= 130 && Game.state == Game.STATE.MENU) {
            Game.state = Game.STATE.GAME;
            game.setDIFFICULTY(1);
        }

        //pd2
        if(mx >= 0 && mx <= 300 && my >= 140 && my <= 170 && Game.state == Game.STATE.MENU) {
            Game.state = Game.STATE.GAME;
            game.setDIFFICULTY(2);
        }

        //pd3
        if(mx >= 0 && mx <= 300 && my >= 180 && my <= 210 && Game.state == Game.STATE.MENU) {
            Game.state = Game.STATE.GAME;
            game.setDIFFICULTY(3);
        }

        //pd4
        if(mx >= 0 && mx <= 300 && my >= 220 && my <= 250 && Game.state == Game.STATE.MENU) {
            Game.state = Game.STATE.GAME;
            game.setDIFFICULTY(4);
        }

        //pd5
        if(mx >= 0 && mx <= 300 && my >= 260 && my <= 290 && Game.state == Game.STATE.MENU) {
            Game.state = Game.STATE.GAME;
            game.setDIFFICULTY(5);
        }

        //help
        if(mx >= 0 && mx <= 300 && my >= 300 && my <= 330 && Game.state == Game.STATE.MENU) {
            Game.state = Game.STATE.HELP;
        }

        //exit
        if(mx >= 0 && mx <= 300 && my >= 340 && my <= 370 && Game.state == Game.STATE.MENU) {
            System.exit(2);
        }

        //backtomenuhelp
        if(mx >= 0 && mx <= 300 && my >= 250 && my <= 280 && Game.state == Game.STATE.HELP) {
            Game.state = Game.STATE.MENU;
        }

        //backtomenuendscreen
        if(mx >= 0 && mx <= 300 && my >= 180 && my <= 210 && Game.state == Game.STATE.ENDSCREEN) {
            Game.state = Game.STATE.MENU;
            game.invasion = false;
            game.victory = false;
        }

        //exitendscreen
        if(mx >= 0 && mx <= 300 && my >= 220 && my <= 250 && Game.state == Game.STATE.ENDSCREEN) {
            System.exit(2);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
