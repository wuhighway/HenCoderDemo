package hencoderdemo.highway.com.hencoderdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import hencoderdemo.highway.com.hencoderdemo.R;

/**
 * @author JH
 * @date 2017/11/7
 */


public class DzView extends View {

    private Bitmap mThumbUp;
    private Bitmap mThumbNormal;
    private Bitmap mShining;
    private Paint mBitmapPaint;
    private int mThumbWidth;
    private int mThumbHeight;
    private int mShiningWidth;
    private int mShiningHeight;
    private Path mClipPath;
    private PointF mShiningPoint;
    private PointF mThumbPoint;
    private boolean isUp = true;
    private PointF mCirclePoint;
    private float mRadiusMax;
    private int mRadiusMin;

    public DzView(Context context) {
        super(context);
        initBitmapInfo();
    }

    public DzView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBitmapInfo();
    }

    public DzView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmapInfo();
    }

    private void initBitmapInfo() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        resetBitmap();
        mThumbWidth = mThumbUp.getWidth();
        mThumbHeight = mThumbUp.getHeight();

        mShiningWidth = mShining.getWidth();
        mShiningHeight = mShining.getHeight();
        mShiningPoint = new PointF(getPaddingLeft() + dip2px(getContext(), 2), getPaddingTop());
        mThumbPoint = new PointF(getPaddingLeft(), getPaddingTop() + dip2px(getContext(), 8));


        mCirclePoint = new PointF();
        mCirclePoint.x = mThumbPoint.x + mThumbWidth / 2;
        mCirclePoint.y = mThumbPoint.y + mThumbHeight / 2;

        mRadiusMax = Math.max(mCirclePoint.x - getPaddingLeft(), mCirclePoint.y - getPaddingTop());
        mRadiusMin = dip2px(getContext(), 8);//这个值是根据点击效果调整得到的
        mClipPath = new Path();
        mClipPath.addCircle(mCirclePoint.x, mCirclePoint.y, mRadiusMax, Path.Direction.CW);
    }

    private void resetBitmap() {
        mThumbUp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected);
        mThumbNormal = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_unselected);
        mShining = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected_shining);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isUp) {
            canvas.save();
            canvas.clipPath(mClipPath);
            canvas.drawBitmap(mShining, mShiningPoint.x, mShiningPoint.y, mBitmapPaint);
            canvas.restore();
            canvas.drawBitmap(mThumbUp, mThumbPoint.x, mThumbPoint.y, mBitmapPaint);
        } else {
            canvas.drawBitmap(mThumbNormal, mThumbPoint.x, mThumbPoint.y, mBitmapPaint);
        }
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    private void setThumbUpScale(float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        mThumbUp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected);
        mThumbUp = Bitmap.createBitmap(mThumbUp, 0, 0, mThumbUp.getWidth(), mThumbUp.getHeight(),
                matrix, true);
        postInvalidate();
    }

}
