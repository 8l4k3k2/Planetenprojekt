package plantenbahnen;

import java.util.ArrayList;

public class Berechnungen implements Runnable{
    private Thread t;
    private ArrayList<SpaceObject> universe;
    private double timedif;
    private boolean runtime;

    Berechnungen(ArrayList<SpaceObject> universe) {
        this.universe = universe;
    }

    @Override
    public void run(){
        //Instant start,end;
        long start, end;
        start = System.nanoTime();
        this.runtime=true;
        while (this.runtime) {
            for (SpaceObject sO : universe) {
                end = System.nanoTime();
                this.timedif = (end-start) / Math.pow(10, 9);
                this.timedif *= 100.0;
                moveall(sO);
                start = end;
            }
            for (SpaceObject sO : universe) {

                //sets x=x1,y=y1 and adds Line to tail
                sO.setNewCoordinates();
            }
        }
    }

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
        //accellVector
        totalAccelVector.multiplyToSelf(G);

        //velocityVector
        totalAccelVector.multiplyToSelf(this.timedif);

        //velocityVector(newton) and velocityVector from s0 added together
        Vector temp = sO.getVelocityVector().add(totalAccelVector);

        //set new velocityVector
        sO.setVelocityVectorNew(temp);
        //set new Coordinates
        sO.addPositionVectorToCoordinates(sO.getVelocityVectorNew().multiply(this.timedif));


    }

    void start(){
        //System.out.println("Startthread");
        if (t==null){
            t = new Thread (this);
            t.start();
        }
    }
    
    void stop(){
        this.runtime=false;
    }

    /*
    void join() throws InterruptedException{
        this.t.join();
    }
     */
    
}
