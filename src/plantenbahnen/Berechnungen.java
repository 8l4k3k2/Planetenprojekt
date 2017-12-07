package plantenbahnen;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
            if (!true){
                break;
            }
            n+=1;
            System.out.println("Thread laeuft");
            try{
            TimeUnit.SECONDS.sleep(1);
            }
            catch (Exception ex){
                Thread t = Thread.currentThread();
                t.getUncaughtExceptionHandler().uncaughtException(t, ex);
            }
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
