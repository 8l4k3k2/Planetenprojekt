public class SpaceObject {
    private double[] coordinates = new double[2];
    private double mass;
    private ForceVector forceVector;

    SpaceObject(double[] coordinates,double mass, double forceVector){
        this.coordinates = coordinates;
        this.mass = mass;
        this.forceVector = forceVector;
    }
}