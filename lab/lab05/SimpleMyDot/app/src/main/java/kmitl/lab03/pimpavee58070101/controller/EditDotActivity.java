package kmitl.lab03.pimpavee58070101.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import kmitl.lab03.pimpavee58070101.R;
import kmitl.lab03.pimpavee58070101.model.Dot;

public class EditDotActivity extends AppCompatActivity {

    Dot dot;
    int dotPosition;
    EditText sizeEditText;
    Button buttonCancel, buttonConfirm, buttonColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dot);

        dot = getIntent().getParcelableExtra("dot");

        dotPosition = getIntent().getIntExtra("dotPosition", 30);

        sizeEditText = (EditText) findViewById(R.id.positionYEditText);
        sizeEditText.setText(String.valueOf(dot.getRadius()));

        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);

        buttonColor = (Button) findViewById(R.id.buttonColor);
        buttonColor.setBackgroundColor(dot.getColor());

    }

    public void onClickCancel(View view){
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void onClickConfirm(View view){
        Toast.makeText(this, "confirm", Toast.LENGTH_SHORT).show();

        dot.setRadius(Integer.parseInt(sizeEditText.getText().toString()));
        Intent intent = new Intent(EditDotActivity.this, MainActivity.class);
        intent.putExtra("dot", dot);
        intent.putExtra("dotPosition", dotPosition);
        setResult(RESULT_OK, intent);

        finish();
    }

    public void onClickColor (View view){
        showColorPicker();
    }

    private void showColorPicker (){
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(dot.getColor())
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {

                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        dot.setColor(selectedColor);
                        buttonColor.setBackgroundColor(selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }
}
