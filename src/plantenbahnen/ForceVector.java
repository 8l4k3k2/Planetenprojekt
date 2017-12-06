package plantenbahnen;

public class ForceVector{
    private double[] directionVector =  new double[2];
    private double force;

    ForceVector(double[] directionVector,double force){
        this.directionVector = directionVector;
        this.force = force;

    }

    ForceVector(double[] directionVector){
        this.directionVector = directionVector;
        this.force = Math.sqrt(Math.pow(directionVector[0],2)+Math.pow(directionVector[1],2));

    }

    public double[] getDirectionVector() {
        return directionVector;
    }

    public double getForce() {
        return force;
    }
}