package ru.ftc.android.shifttemple.features.Image;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;


public class CircleImageTransform implements Transformation {
    private int borderColorId;

    public CircleImageTransform(int borderColorId){
        this.borderColorId = borderColorId;
    }

    @Override
    public Bitmap transform(final Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size/2f;
        canvas.drawCircle(r, r, r - 4, paint);

        squaredBitmap.recycle();

        //Circle border
        Paint paint1 = new Paint();
        paint1.setColor(borderColorId);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setAntiAlias(true);
        paint1.setStrokeWidth(8);
        canvas.drawCircle(r, r, r - 4, paint1);

        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}