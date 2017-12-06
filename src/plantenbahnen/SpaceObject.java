package plantenbahnen;

public class SpaceObject {
    private double[] coordinates = new double[2];
    private double mass;
    private ForceVector fVector;

    SpaceObject(double[] coordinates,double mass, ForceVector fVector){
        this.coordinates = coordinates;
        this.mass = mass;
        this.fVector = fVector;
    }

    public ForceVector getfVector() {
        return fVector;
    }

    public double getMass() {
        return mass;
    }

    public double[] getCoordinates() {
        return coordinates;
    }
}