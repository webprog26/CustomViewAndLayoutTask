package com.example.webprog26.customview.makers;

import android.graphics.Path;
import android.graphics.Point;

import com.example.webprog26.customview.interfaces.GraphicPathMaker;

/**
 * Created by webprog26 on 12.12.2016.
 */

public class PathCreator implements GraphicPathMaker {

    public static final String TRIANGLE = "com.example.webprog26.customview.triangle";
    public static final String RECTANGLE = "com.example.webprog26.customview.rectangle";

    @Override
    public Path getPath(String shapeType, int shapeWidth, int shapeHeight) throws Exception{
        switch (shapeType){
            case TRIANGLE:
                return getTrianglePath(shapeWidth, shapeHeight);
            case RECTANGLE:
                return getRectanglePath(shapeWidth, shapeHeight);
            default:
                throw new Exception("Wrong parameters");
        }
    }

    /**
     * Returns {@link Path} instance with triangle parameters
     * @param triangleWidth int
     * @param triangleHeight int
     * @return Path
     */
    private Path getTrianglePath(int triangleWidth, int triangleHeight){
        Point p1 = new Point(0, triangleHeight), p2 = null, p3 = null;
        p2 = new Point(p1.x + triangleWidth, p1.y);
        p3 = new Point(p1.x + (triangleWidth / 2), p1.y - triangleHeight);
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);

        return path;
    }

    /**
     * Returns {@link Path} instance with rectangle parameters
     * @param rectangleWidth int
     * @param rectangleHeight int
     * @return Path
     */
    private Path getRectanglePath(int rectangleWidth, int rectangleHeight){
        Point p1 = new Point(0, rectangleHeight), p2 = null, p3 = null, p4 = null;
        p2 = new Point(p1.x + rectangleWidth, p1.y);
        p3 = new Point(p2.x, p2.y - rectangleHeight);
        p4 = new Point(p1.x, p3.y);
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.lineTo(p4.x, p4.y);

        return path;
    }
}
