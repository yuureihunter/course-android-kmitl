package kmitl.lab03.pimpavee58070101.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kmitl.lab03.pimpavee58070101.model.Dot;
import kmitl.lab03.pimpavee58070101.model.Dots;

public class DotView extends View{

    private Paint paint;
    private Dots allDot;

    public void setDots(Dots dots) {
        this.allDot = dots;

    }

    public interface OnDotViewPressListener{
        void onDotViewPressed(int x, int y);
        void onDotViewLongPressed(int x, int y);
    }

    private OnDotViewPressListener onDotViewPressListener;
    public void setOnDotViewPressListener(OnDotViewPressListener onDotViewPressListener){
        this.onDotViewPressListener = onDotViewPressListener;
    }

    final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onDotViewPressListener.onDotViewPressed((int) e.getX(), (int) e.getY());
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            onDotViewPressListener.onDotViewLongPressed((int) e.getX(), (int) e.getY());
        }
    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return gestureDetector.onTouchEvent(event);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.allDot != null ) {
            for (Dot dot : allDot.getAllDot()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
            }
        }

    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }



}
