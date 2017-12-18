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
        for (SpaceObject sO:universe){
            //moveall(sO);
        }
        /*
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
        */
    }
    /*
    private void moveall(SpaceObject sO){
        double G = 6.67408*Math.pow(10,-11); //Gravitationskonstante
        Vector totalAccelVector = new Vector();
        for (SpaceObject planet:this.universe){
            if (planet!=sO){
                //newtons Gravitationsgesetz
                Vector zaehler = planet.getPositionVector().subtract(sO.getPositionVector());
                double nenner = Math.pow(zaehler.norm(),3);
                Vector temp = zaehler.divide(nenner);
                temp = temp.multiply(planet.getMass());
                totalAccelVector.addToSelf(temp);
            }
        }
        totalAccelVector.multiplyToSelf(G);



    }
    */
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
