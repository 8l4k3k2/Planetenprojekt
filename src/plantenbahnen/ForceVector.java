package plantenbahnen;

public class ForceVector{
    private double[] directionVector =  new double[2];
    private double force;

    ForceVector(double[] directionVector,double force){
        this.force = force;
        double n = this.force / Math.sqrt(Math.pow(directionVector[0],2)+Math.pow(directionVector[1],2));
        this.directionVector[0]=directionVector[0]*n;
        this.directionVector[1]=directionVector[1]*n;

    }

    ForceVector(double[] directionVector){
        this.directionVector = directionVector;
        this.force = Math.sqrt(Math.pow(directionVector[0],2)+Math.pow(directionVector[1],2));

    }

    ForceVector(){
        this.directionVector = new double[]{0,0};
        this.force = 0.0;
    }

    public double[] getDirectionVector() {
        return this.directionVector;
    }

    public double getForce() {
        return this.force;
    }
}