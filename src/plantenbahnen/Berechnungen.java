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
                //sets x=x1,y=y1 and adds Line to trajectory
                sO.setNewCoordinates();
            }
        }
    }

    private void moveall(SpaceObject sO){
        double G = 6.67408*Math.pow(10,-11); //Gravitationskonstante
        Vector totalAccelVector = new Vector();
        for (SpaceObject planet: this.universe){
            if ( planet != sO ){
                // Newtons Gravitationsgesetz
                Vector zaehler = planet.getPositionVector().subtract(sO.getPositionVector());
                double nenner = Math.pow(zaehler.norm(),3);
                Vector temp = zaehler.divide(nenner);
                temp = temp.multiply(planet.getMass());
                totalAccelVector.addToSelf(temp);
            }
        }
        // Beschleunigungsvektor (a_neu = m * G)
        totalAccelVector.multiplyToSelf(G);

        // Geschwindikeitsvektor (v_neu = a_neu * dt)
        totalAccelVector.multiplyToSelf(this.deltaTime);

        // Addiere den alten und den neuen Geschwindigkeitsvektor (temp = v_alt + v_neu)
        Vector temp = sO.getVelocityVector().add(totalAccelVector);

        // Überschreibe den alten durch den neuen Geschwindigkeitsvektor (v_alt = v_neu)
        sO.setVelocityVectorNew(temp);
        
        // Setze die neuen Koordinaten (s = v * dt)
        sO.addPositionVectorToCoordinates(sO.getVelocityVectorNew().multiply(this.deltaTime));
    }

    void start(){
        if ( t == null || t.getState() == TERMINATED ){
            t = new Thread(this);
            t.start();
            gui.setSimulationStatus(1);
        } 
    }
    
    void stop(){
        this.runtime = false; // run() will finish and start again when start() ist invoked
        gui.setSimulationStatus(0);
    }

    public void setDeltaTime(double dt) {
        this.requestedDT = dt;
        this.requestSetDT = true;
    }

    public double getDeltaTime() {
        return this.deltaTime;
    }
    
    public Thread getThread() {
        return this.t;
    }
}
