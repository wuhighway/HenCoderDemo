package hencoderdemo.highway.com.hencoderdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import hencoderdemo.highway.com.hencoderdemo.R;

/**
 * @author JH
 * @date 2017/11/2
 */


public class DemoView extends View {

    private Paint mPaint;
    private Shader mShader;
    private Xfermode xfermode;
    private Paint xPaint;

    public DemoView(Context context) {
        this(context, null);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    //使用ComposeShader要关闭硬件加速
    private void initView(Context context) {
        mPaint = new Paint();
//        mShader = new LinearGradient(100, 100, 200, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
//        mShader = new RadialGradient(300f, 300f, 300f, new int[]{Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3")}, new float[]{0.1f
//                , 0.8f}, Shader.TileMode.REPEAT);
//        mShader = new SweepGradient(300f, 300f, new int[] {Color.parseColor("#E91E63")
//                , Color.parseColor("#2196F3")}, null);//new float[]{0.3f, 0.8f} );
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.xz);
        mShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.zhu);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

// ComposeShader：结合两个 Shader
        Shader shader = new ComposeShader(mShader, shader2, PorterDuff.Mode.SCREEN);
        mPaint.setShader(shader);

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
        xPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(0, 0,getWidth(), getHeight() , mPaint);
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        drawMode1(canvas);
        drawMode2(canvas);
        drawMode3(canvas);
        drawMode4(canvas);
        drawMode5(canvas);
        drawMode6(canvas);
        drawMode7(canvas);
        drawMode8(canvas);
        drawMode9(canvas);
        drawMode10(canvas);
        drawMode11(canvas);
        drawMode12(canvas);
        drawMode13(canvas);
        drawMode14(canvas);
        drawMode15(canvas);
        drawMode16(canvas);
        drawMode17(canvas);
        drawMode18(canvas);

        canvas.restoreToCount(saved);

        xPaint.setColor(Color.BLACK);
        xPaint.setTextSize(32);
        canvas.drawText("CLEAR", 100, 40, xPaint);
        canvas.drawText("SRC", 100, 115, xPaint);
        canvas.drawText("DST", 100, 190, xPaint);
        canvas.drawText("SRC_OVER", 100, 265, xPaint);
        canvas.drawText("DST_OVER", 100, 340, xPaint);
        canvas.drawText("SRC_IN", 100, 415, xPaint);
        canvas.drawText("DST_IN", 100, 490, xPaint);
        canvas.drawText("SRC_OUT", 100, 565, xPaint);

        canvas.drawText("DST_OUT", 100, 640, xPaint);
        canvas.drawText("SRC_ATOP", 100, 715, xPaint);

        canvas.drawText("DST_ATOP", 100, 790, xPaint);

        canvas.drawText("XOR", 100, 865, xPaint);
        canvas.drawText("DARKEN", 100, 940, xPaint);
        canvas.drawText("LIGHTEN", 100, 1015, xPaint);
        canvas.drawText("MULTIPLY", 100, 1090, xPaint);
        canvas.drawText("SCREEN", 100, 1165, xPaint);
        canvas.drawText("ADD", 100, 1240, xPaint);
        canvas.drawText("OVERLAY", 100, 1315, xPaint);
        xPaint.setAntiAlias(true);
        xPaint.setStyle(Paint.Style.STROKE);

        xPaint.setStrokeWidth(5);
        canvas.drawCircle(500, 450, 100, xPaint);
        xPaint.setStrokeWidth(40);
        canvas.drawCircle(500, 600, 100, xPaint);


        xPaint.setStrokeWidth(1);
        canvas.drawCircle(500, 300, 100, xPaint);
    }

    private void drawMode1(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 0, 50, 50, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 50, 25, xPaint);

        xPaint.setXfermode(null);
    }

    private void drawMode2(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 75, 50, 125, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 125, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode3(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 150, 50, 200, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 200, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode4(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 225, 50, 275, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 275, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode5(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 300, 50, 350, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 350, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode6(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 375, 50, 425, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 425, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode7(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 450, 50, 500, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 500, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode8(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 525, 50, 575, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 575, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode9(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 600, 50, 650, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 650, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode10(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 675, 50, 725, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 725, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode11(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 750, 50, 800, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 800, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode12(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 825, 50, 875, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 875, 25, xPaint);
        xPaint.setXfermode(null);
    }


    private void drawMode13(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 900, 50, 950, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 950, 25, xPaint);
        xPaint.setXfermode(null);
    }

    private void drawMode14(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 975, 50, 1025, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 1025, 25, xPaint);
        xPaint.setXfermode(null);
    }


    private void drawMode15(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 1050, 50, 1100, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 1100, 25, xPaint);
        xPaint.setXfermode(null);
    }


    private void drawMode16(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 1125, 50, 1175, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 1175, 25, xPaint);
        xPaint.setXfermode(null);
    }


    private void drawMode17(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.ADD);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 1200, 50, 1250, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 1250, 25, xPaint);
        xPaint.setXfermode(null);
    }


    private void drawMode18(Canvas canvas) {
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.OVERLAY);
        xPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawRect(0, 1275, 50, 1325, xPaint);
        xPaint.setXfermode(xfermode); // 设置 Xfermode
        xPaint.setColor(Color.parseColor("#2196F3"));
        canvas.drawCircle(50, 1325, 25, xPaint);
        xPaint.setXfermode(null);
    }









}
