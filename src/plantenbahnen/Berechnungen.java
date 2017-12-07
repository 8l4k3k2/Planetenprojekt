package plantenbahnen;

import java.util.ArrayList;

public class Berechnungen implements Runnable{
    private Thread t;
    private ArrayList<SpaceObject> universe;

    Berechnungen(ArrayList<SpaceObject> universe) {
        // To do by Jonathan
        this.universe = universe;
    }

    @Override
    public void run(){
        SpaceObject a = universe.get(0);
        int n=0;
        while (true){
            if (n>1000){
                break;
            }
            n+=1;
            System.out.println("halo i bims 1 rechner");
        }
    }

    void start(){
        //System.out.println("Startthread");
        if (t==null){
            t = new Thread (this);
            t.start();
        }
    }

    /*
    void join() throws InterruptedException{
        this.t.join();
    }
     */
    
}
