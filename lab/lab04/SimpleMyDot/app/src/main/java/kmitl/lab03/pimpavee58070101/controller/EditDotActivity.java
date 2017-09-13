package kmitl.lab03.pimpavee58070101.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import kmitl.lab03.pimpavee58070101.R;
import kmitl.lab03.pimpavee58070101.model.Dot;

public class EditDotActivity extends AppCompatActivity {

    Dot dot;
    int dotPosition;
    EditText sizeEditText;
    Button buttonCancel, buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dot);

        TextView textViewDot = (TextView) findViewById(R.id.textViewDot);

        dot = getIntent().getParcelableExtra("dot");
        textViewDot.setText("centerX : " + dot.getCenterX() +
                "\ncenterY : " + dot.getCenterY() +
                "\nradius : " + dot.getRadius() +
                "\ncolor : " + dot.getColor());

        dotPosition = getIntent().getIntExtra("dotPosition", 30);

        sizeEditText = (EditText) findViewById(R.id.sizeEditText);
        sizeEditText.setText(String.valueOf(dot.getRadius()));

        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);

    }

    public void onClickCancel(View view){
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void onClickConfirm(View view){
        dot.setRadius(Integer.parseInt(sizeEditText.getText().toString()));
        Intent intent = new Intent(EditDotActivity.this, MainActivity.class);
        intent.putExtra("dot", dot);
        intent.putExtra("dotPosition", dotPosition);
        setResult(RESULT_OK, intent);

        finish();
    }
}
