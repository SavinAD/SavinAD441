import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Figure2D> figure = new ArrayList<Figure2D>();
        Figure2D square=new Square(6,4) ;
        figure.add(square);
        Figure3D cube=new Cube(5,4,3);
        figure.add(cube);
        int count=0;
        for(Figure2D f:figure){
            System.out.println(((Figure2D)f).Area());
            if (f instanceof Figure3D){
                System.out.println(((Figure3D)f).Volume());


            }

        }

    }
}
