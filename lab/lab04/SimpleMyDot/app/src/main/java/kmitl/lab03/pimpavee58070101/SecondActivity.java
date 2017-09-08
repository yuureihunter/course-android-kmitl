package kmitl.lab03.pimpavee58070101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kmitl.lab03.pimpavee58070101.model.DotParcelable;
import kmitl.lab03.pimpavee58070101.model.DotSerializable;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView textViewDot = (TextView) findViewById(R.id.textViewDot);

        int x = getIntent().getIntExtra("x", 10);
        DotSerializable dotSerializable = (DotSerializable) getIntent().getSerializableExtra("dotSerializable");

        TextView textView2 = (TextView) findViewById(R.id.text2);
        textView2.setText(String.valueOf(x));

        textViewDot.setText("centerX : " + dotSerializable.getCenterX() +
        ", centerY : " + dotSerializable.getCenterY() +
        ", radius : " + dotSerializable.getRadius() +
        ", color : " + dotSerializable.getColor());

        DotParcelable dotParcelable = getIntent().getParcelableExtra("dotParcelable");
        textViewDot.setText("centerX : " + dotParcelable.getCenterX() +
                "\ncenterY : " + dotParcelable.getCenterY() +
                "\nradius : " + dotParcelable.getRadius() +
                "\ncolor : " + dotParcelable.getColor());

    }
}
