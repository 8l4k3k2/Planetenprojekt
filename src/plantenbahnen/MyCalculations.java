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
                so.setx(so.getx() + toAdd);
                so.sety(so.gety() + toAdd);
            }
            if ( toAdd > 0.0 ) {
                toAdd = -300.0;
            } else {
                toAdd = 300.0;
            }
        }
    }
}
