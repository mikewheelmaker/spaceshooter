import java.util.LinkedList;

public class Physics {

    public static boolean Collision(EntityA enta, LinkedList<EntityB> entb) {
        for(int i = 0; i < entb.size(); ++i) {
            if(enta.getBounds(32, 32).intersects(entb.get(i).getBounds(32, 32))) {
                return true;
            }
        }
        return false;
    }

    public static boolean Collision(EntityB entb, LinkedList<EntityA> enta) {
        for(int i = 0; i < enta.size(); ++i) {
            if(entb.getBounds(32, 32).intersects(enta.get(i).getBounds(32, 32))) {
                return true;
            }
        }
        return false;
    }

}
