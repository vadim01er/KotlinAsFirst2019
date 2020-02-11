
import Cube.*;

import java.util.Arrays;

public class hh {
    static public void main(String arg[]) {
        Cube now = new Cube(2, Cube.CubeName.DEFAULT);
        System.out.println(now.getSizeCube());

        System.out.println(now);
        //now.toStringOneBrink(Cube.CubeName.BACK);
//        now.rotateBrink(Cube.CubeName.FRONT, 1, Cube.ClockWise.TRUE);
        now.rotateBrink(Cube.CubeName.RIGHT, 1, Cube.ClockWise.FALSE);

//        now.rotateBrink("f");
//        System.out.println(Arrays.deepToString(now.getCubeNow()));



        //now.rotateCubeTo(Cube.CubeName.LEFT);
//        System.out.println(Arrays.deepToString(now.getCubeNow()));
        System.out.println(now);
    }
}
