package kmitl.lab03.pimpavee58070101.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kmitl.lab03.pimpavee58070101.model.Dot;

public class DotView extends View {

    private Paint paint;
    ArrayList<Dot> dots = new ArrayList();

    public void addDot(Dot dot) {
        //this.dot = dot;
        dots.add(dot);
        Log.d("Dot size", String.valueOf(dots.size()));
    }

    public void clearDot(){
        dots.clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dots != null ) {
            for (Dot dot : dots) {
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
