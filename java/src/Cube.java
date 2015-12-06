/**
 * Created with IntelliJ IDEA.
 * User: Де
 * Date: 28.10.15
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public class Cube extends Figure3D {
    public Cube(double height,double width,double length)
    {   this.length=length;
        this.height=height;
        this.width=width;
    }
    @Override
    public double Area()
    {
        System.out.println("Cube area");
        return
        this.height*this.width;
    }
    @Override
    public double Volume()
    {        System.out.println("Cube Volume");
        return
        this.height*this.width*this.length;
    }
}
