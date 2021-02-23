import java.awt.image.BufferedImage;

public class Textures {

    public BufferedImage player, shot, alien;
    private SpriteSheet spriteSheet;

    public Textures(Game game) {
        spriteSheet = new SpriteSheet(game.getSpriteSheet());

        getTextures();
    }

    private void getTextures() {
        player = spriteSheet.grabImage(1, 1, 32, 32);
        alien = spriteSheet.grabImage(2, 1, 32, 32);
        shot = spriteSheet.grabImage(3, 1, 32, 32);
    }
}
