package kmitl.lab03.pimpavee58070101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        dot = new Dot(0, 0, 20, this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);


    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();

    }
}
