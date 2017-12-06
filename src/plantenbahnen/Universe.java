package plantenbahnen;

import java.util.ArrayList;

public class Universe {
    private ArrayList<SpaceObject> universe = new ArrayList<>();

    public void start(){
        //this.universe.add()
        SpaceObject sun = new SpaceObject(new double[]{600,600},59700000000000.4,new ForceVector(),50,10,new int[]{0,0,255});
        this.universe.add(sun);

        ForceVector nF = new ForceVector(new double[]{150,150},3000000);
        SpaceObject earth = new SpaceObject(new double[]{450,450},500000000000,nF,);
    }
}
