package plantenbahnen;

public class SpaceObject {
    private double[] coordinates = new double[2];
    private double mass;
    private ForceVector fVector;
    private int size;
    private int thickness;
    private int[] colour;

    SpaceObject(double[] coordinates, double mass, ForceVector fVector,int size,int thickness, int[] colour) {
        this.coordinates = coordinates;
        this.mass = mass;
        this.fVector = fVector;


        this.size = size; //10;
        this.thickness = thickness ;//0;
        this.colour = colour; //new int[]{0, 0, 255};
    }

    public void setfVector(ForceVector fVector) {
        this.fVector = fVector;
    }

    public ForceVector getfVector() {
        return this.fVector;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getThickness() {
        return thickness;
    }

    public void setColour(int[] colour) {
        this.colour = colour;
    }

    public int[] getColour() {
        return colour;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return this.coordinates;
    }

    public double getMass() {
        return this.mass;
    }
}