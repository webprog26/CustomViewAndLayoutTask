package com.example.webprog26.customview.makers;

import android.graphics.Path;
import android.graphics.Point;

import com.example.webprog26.customview.interfaces.GraphicPathMaker;

/**
 * Created by webprog26 on 12.12.2016.
 */

public class PathCreator implements GraphicPathMaker {

    public static final String TRIANGLE = "com.example.webprog26.customview.triangle";

    @Override
    public Path getPath(String shapeType, int shapeWidth, int shapeHeight) {
        switch (shapeType){
            case TRIANGLE:
                return getTrianglePath(shapeWidth, shapeHeight);
            default:
                return null;
        }
    }

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
}
