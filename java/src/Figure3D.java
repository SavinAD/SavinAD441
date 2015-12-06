/**
 * Created with IntelliJ IDEA.
 * User: Де
 * Date: 28.10.15
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
abstract public class Figure3D extends Figure2D {
    protected double length;
    protected double setLength(double length)
    {            return
               this.length=length;
    }
    abstract double Area();
    abstract double Volume();
}
