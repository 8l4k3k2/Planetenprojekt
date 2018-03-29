package plantenbahnen;

import java.util.ArrayList;

import static java.lang.Thread.State.TERMINATED;

public class Berechnungen implements Runnable{
    private Thread t;
    private ArrayList<SpaceObject> universe;
    private boolean runtime;
    private double deltaTime;
    private double dt;
    private double requestedDT;
    private boolean requestSetDT = false;
    private GuiElements gui;

    Berechnungen(ArrayList<SpaceObject> universe, GuiElements gui) {
        this.universe = universe;
        this.gui = gui;
    }

    @Override
    public void run(){
        this.runtime = true;
        while (this.runtime) {

            if (this.requestSetDT){ //manual lock
                this.dt = this.requestedDT;
                this.requestSetDT = false;
            }

            for (SpaceObject sO: this.universe) {
                this.deltaTime = this.dt;
                moveall(sO);
            }
            
            for (SpaceObject sO: this.universe) {
                //sets x=x1,y=y1 and adds Line to tail
                sO.setNewCoordinates();
            }
        }
    }

    private void moveall(SpaceObject sO){
        double G = 6.67408*Math.pow(10,-11); //Gravitationskonstante
        Vector totalAccelVector = new Vector();
        for (SpaceObject planet: this.universe){
            if ( planet != sO ){
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
        totalAccelVector.multiplyToSelf(this.deltaTime);

        //velocityVector(newton) and velocityVector from s0 added together
        Vector temp = sO.getVelocityVector().add(totalAccelVector);

        //set new velocityVector
        sO.setVelocityVectorNew(temp);
        //set new Coordinates
        sO.addPositionVectorToCoordinates(sO.getVelocityVectorNew().multiply(this.deltaTime));


    }

    void start(){
        if (t == null || t.getState() == TERMINATED){
            t = new Thread(this);
            t.start();
            gui.setSimulationStatus(1);
        }
        /*if (t.getState() == TERMINATED) {
            t = new Thread(this);
            t.start();
        }*/
    }
    
    void stop(){
        this.runtime = false;
        gui.setSimulationStatus(0);
    }

    public void setDeltaTime(double dt) {
        this.requestedDT = dt;
        this.requestSetDT = true;
    }

    public double getDeltaTime() {
        return this.deltaTime;
    }
}
