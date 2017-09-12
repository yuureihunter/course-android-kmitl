package kmitl.lab03.pimpavee58070101.controller;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import kmitl.lab03.pimpavee58070101.R;
import kmitl.lab03.pimpavee58070101.model.Colors;
import kmitl.lab03.pimpavee58070101.model.Dot;
import kmitl.lab03.pimpavee58070101.model.DotParcelable;
import kmitl.lab03.pimpavee58070101.model.DotSerializable;
import kmitl.lab03.pimpavee58070101.model.Dots;
import kmitl.lab03.pimpavee58070101.view.DotView;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangedListener, DotView.OnDotViewPressListener{

    private Dots dots;
    private DotView dotView;
    private Colors color = new Colors();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lab04
//        Button btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
//        final DotSerializable dotSerializable = new DotSerializable();
//        dotSerializable.setCenterX(150);
//        dotSerializable.setCenterY(150);
//        dotSerializable.setRadius(30);
//        dotSerializable.setColor(Color.RED);
//
//        final DotParcelable dotParcelable = new DotParcelable(130, 130, 30, Color.BLUE);
//
//        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                intent.putExtra("x", 8);
//
//                intent.putExtra("dotSerializable", dotSerializable);
//
//                intent.putExtra("dotParcelable", dotParcelable);
//                startActivity(intent);
//            }
//        });

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        dots = new Dots();
        dots.setListener(this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, color.randomColor());
        dots.addDot(newDot);

    }

    public void onRemoveAll(View view) {
        dots.clearAll();
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();

    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);

        if (dotPosition == -1){
            Dot newDot = new Dot(x, y, 30, color.randomColor());
            dots.addDot(newDot);
        }
        else {
            dots.removeBy(dotPosition);
        }
    }

}
