package kmitl.lab03.pimpavee58070101;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Random;

import kmitl.lab03.pimpavee58070101.model.Dot;
import kmitl.lab03.pimpavee58070101.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener{

    private Dot dot;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        new Dot(centerX, centerY, 30, Color.rgb(r, g, b), this);

    }

    public void onClearDot(View view) {
        dotView.clearDot();
        dotView.invalidate();
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.addDot(dot);
        dotView.invalidate();

    }
}
