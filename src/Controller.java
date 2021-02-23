import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private LinkedList<EntityA> shots = new LinkedList<EntityA>();
    private LinkedList<EntityB> aliens = new LinkedList<EntityB>();

    //private LinkedList<Shot> shots = new LinkedList<Shot>();
    //private LinkedList<Alien> aliens = new LinkedList<Alien>();

    Random r = new Random();
    EntityA tempShot;
    EntityB tempAlien;

    Game game;
    Textures textures;

    public Controller(Game game, Textures textures) {
        this.game = game;
        this.textures = textures;

        for(int i = 0; i < game.NUMBER_OF_ALIENS_TO_KILL; ++i) {
            addEntityB(new Alien(r.nextInt(game.WIDTH * game.SCALE - 32),
                    -r.nextInt(1000/game.DIFFICULTY), this.textures, this.game, this));
        }
    }

    public void tick() {
        //EntityA
        for(int i = 0; i < shots.size(); ++i) {
            tempShot = shots.get(i);
            if(tempShot.getY() < 0) {
                removeEntityA(tempShot);
            }
            tempShot.tick();
        }

        //EntityB
        for(int i = 0; i < aliens.size(); ++i) {
            tempAlien = aliens.get(i);
            if(tempAlien.getY() > 448) {
                removeEntityB(tempAlien);
                game.invasion = true;
            }
            tempAlien.tick();
        }
    }

    public void render(Graphics g) {
        //EntityA
        for(int i = 0; i < shots.size(); ++i) {
            tempShot = shots.get(i);
            tempShot.render(g);
        }

        //EntityB
        for(int i = 0; i < aliens.size(); ++i) {
            tempAlien = aliens.get(i);
            tempAlien.render(g);
        }
    }

    public void addEntityA(EntityA block) {
        shots.add(block);
    }

    public void removeEntityA(EntityA block) {
        shots.remove(block);
    }

    public void addEntityB(EntityB block) {
        aliens.add(block);
    }

    public void removeEntityB(EntityB block) {
        aliens.remove(block);
    }

    public LinkedList<EntityA> getShots() {
        return shots;
    }

    public LinkedList<EntityB> getAliens() {
        return aliens;
    }
}
