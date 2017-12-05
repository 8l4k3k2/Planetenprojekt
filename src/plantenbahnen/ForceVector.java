public class SpaceObject{
    double[] directionVector =  new double[2];
    double force;

    SpaceObject(double[] directionVector,double force){
        this.directionVector = directionVector;
        this.force = force;
    }
}