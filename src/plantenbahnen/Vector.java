package plantenbahnen;

public class Vector {
    private double x;
    private double y;


    Vector(double x, double y){
        this.x=x;
        this.y=y;
    }

    Vector(double x, double y, double norm){
        double n = norm / Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        this.x=x*n;
        this.y=y*n;
    }

    Vector(){
        this.x=0;
        this.y=0;
    }

    public double norm(){
        return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2));
    }

    public Vector add(Vector newV){
        return new Vector(this.x+newV.x(),this.y+newV.y());
    }

    public void addToSelf(Vector newV){
        this.x+=newV.x();
        this.y+=newV.y();
    }

    public Vector subtract(Vector newV){
        return new Vector(this.x-newV.x(),this.y-newV.y());
    }

    public Vector multiply(double value){
        return new Vector(this.x*value,this.y*value);
    }

    public void multiplyToSelf(double value){
        this.x=this.x*value;
        this.y=this.y*value;
    }
    public Vector divide(double value){
        return new Vector(this.x/value,this.y/value);
    }


    public double x(){
        return this.x;
    }

    public double y(){
        return this.y;
    }

    public double[] get(){
        return new double[]{this.x,this.y};
    }



}
