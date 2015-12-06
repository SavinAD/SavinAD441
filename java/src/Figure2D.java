/**
 * Created with IntelliJ IDEA.
 * User: Де
 * Date: 28.10.15
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
abstract public class Figure2D {
    protected double height;
    protected double width;
    protected double setHeight(double height)
    {      return
        this.height=height;
    }
    protected double setWidth(double width)
    {       return
        this.width=width;
    }
    abstract double Area();

}
