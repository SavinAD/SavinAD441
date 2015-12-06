/**
 * Created with IntelliJ IDEA.
 * User: Де
 * Date: 28.10.15
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
 public class Square extends Figure2D {
    public Square(double height,double width)
    {
        this.height=height;
        this.width=width;
    }
    @Override
    public double Area()
    {

           System.out.println("Square area");

        return

              this.height*this.width;
    }

}
