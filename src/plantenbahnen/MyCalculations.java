package plantenbahnen;

import java.util.ArrayList;

public class MyCalculations implements Runnable {

    ArrayList<SpaceObject> universe;
    
    public MyCalculations (ArrayList<SpaceObject> universe) {
        this.universe = universe;
    }
    
    @Override
    public void run(){
        double toAdd = 100.0;
        while ( true ) {
            for (SpaceObject so: universe) {
                so.setX(so.getX() + toAdd);
                so.setY(so.getY() + toAdd);
                so.addToTail(so.getX(), so.getY());
            }
            if ( toAdd > 0.0 ) {
                toAdd = -300.0;
            } else {
                toAdd = 300.0;
            }
        }
    }
}
