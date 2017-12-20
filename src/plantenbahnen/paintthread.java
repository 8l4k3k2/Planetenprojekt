package plantenbahnen;

import java.util.ArrayList;

import static java.lang.Thread.State.TERMINATED;

public class paintthread implements Runnable {
    private ArrayList<SpaceObject> universe;
    private Thread t;
    private boolean run;


    paintthread (ArrayList<SpaceObject> universe) {
        this.universe = universe;
    }

    @Override
    public void run(){
        run = true;

        while (run){
            for (SpaceObject so: universe){
                so.setCircleProperties();
            }
            try
            {
                Thread.sleep(30);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }

    }

    void start(){
        if (t==null){
            t = new Thread (this);
            t.start();
        }
        if (t.getState()==TERMINATED) {
            t=new Thread(this);
            t.start();
        }
    }
    void stop(){
        this.run=false;
    }
}
