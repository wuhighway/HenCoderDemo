package hencoderdemo.highway.com.hencoderdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import hencoderdemo.highway.com.hencoderdemo.R;

/**
 * @author JH
 * @date 2017/11/9
 */


public class FoldingView extends View {
    private static final String TAG = "FoldingView";
    private Bitmap bitmap;
    private Paint paint;
    private int foldCount = 4;
    private int eveWidth;
    private int foldWidth;
    private float flodPre = 1f;
    private int foldHeight;
    private Matrix[] matrix = new Matrix[foldCount];
    private Paint mShadowPaint;
    private LinearGradient mShadowGradientShader;
    private Paint mSolidPaint;

    public FoldingView(Context context) {
        super(context);
        initBitmap();
    }

    public FoldingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBitmap();
    }

    public FoldingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmap();
    }

    private void initBitmap() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        eveWidth = bitmap.getWidth() / foldCount;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < foldCount; i++) {
            matrix[i] = new Matrix();
        }
        int alpha = (int) (255 * 0.8f * 0.8f);
        mSolidPaint = new Paint();
        mSolidPaint.setColor(Color.argb((int) (alpha * 0.8F), 0, 0, 0));
        mShadowPaint = new Paint();
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowGradientShader = new LinearGradient(0, 0, 0.5f * eveWidth, 0,
                Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        mShadowPaint.setShader(mShadowGradientShader);
        mShadowPaint.setAlpha(alpha);

        Poly();
    }

    private void Poly() {
        foldWidth = (int) (eveWidth * flodPre);
        foldHeight = (int) (Math.sqrt(eveWidth * eveWidth - foldWidth * foldWidth) / 2);
        float[] src = new float[8];
        float[] dst = new float[8];
        for (int i = 0; i < foldCount; i++) {
            src[0] = i * eveWidth;
            src[1] = 0;
            src[2] = src[0] + eveWidth;
            src[3] = 0;
            src[4] = src[0];
            src[5] = bitmap.getHeight();
            src[6] = src[2];
            src[7] = src[5];

            boolean isEve = i % 2 == 0;
            dst[0] = i * foldWidth;
            dst[1] = isEve ? 0 : foldHeight;
            dst[2] = dst[0] + foldWidth;
            dst[3] = isEve ? foldHeight : 0;
            dst[4] = dst[0];
            dst[5] = isEve ? bitmap.getHeight() : bitmap.getHeight() - foldHeight;
            dst[6] = dst[2];
            dst[7] = isEve ? bitmap.getHeight() - foldHeight : bitmap.getHeight();
            matrix[i].setPolyToPoly(src, 0, dst, 0, src.length >> 1);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < foldCount; i++) {

            canvas.save();
            canvas.translate(getWidth() / 2 - bitmap.getWidth() / 2, getHeight() / 2 - bitmap.getHeight() / 2);
            canvas.concat(matrix[i]);
            canvas.clipRect(eveWidth * i, 0, eveWidth * i + eveWidth, bitmap.getHeight());
            canvas.drawBitmap(bitmap, 0, 0, paint);
            canvas.translate(eveWidth * i, 0);
//            if (flodPre <= 0.99) {
            if (i % 2 != 0) {
                //绘制阴影
                canvas.drawRect(0, 0, eveWidth, bitmap.getHeight(),
                        mShadowPaint);
            } else {
                //绘制黑色遮盖
                canvas.drawRect(0, 0, eveWidth, bitmap.getHeight(),
                        mSolidPaint);
            }
//            }
            canvas.restore();
        }
    }

    public void setProgress(int progress) {
        if (progress == 0)
            return;
        flodPre = progress * 1f / 100;
        foldWidth = (int) (eveWidth * flodPre);
        Poly();
        invalidate();
    }
}
