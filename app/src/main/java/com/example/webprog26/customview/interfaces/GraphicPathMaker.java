package com.example.webprog26.customview.interfaces;

import android.graphics.Path;

/**
 * Created by webprog26 on 12.12.2016.
 */

public interface GraphicPathMaker {

    /**
     * Returns new {@link Path} instance with received parameters
     * @param shapeType {@link String}
     * @param shapeWidth int
     * @param shapeHeight int
     * @return Path
     * @throws Exception
     */
    public Path getPath(String shapeType, int shapeWidth, int shapeHeight) throws Exception;
}
