package com.reqica.drilon.imagepickerlibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class BitmapReScaler {

        // scaling the bitmap
        public static Bitmap reScale(Bitmap bitmap, int newWidth, int newHeight) {
            Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

            float scaleX = newWidth / (float) bitmap.getWidth();
            float scaleY = newHeight / (float) bitmap.getHeight();
            float pivotX = 0;
            float pivotY = 0;

            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

            return scaledBitmap;
        }

}
