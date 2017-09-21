package kmitl.lab03.pimpavee58070101.Fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import kmitl.lab03.pimpavee58070101.R;
import kmitl.lab03.pimpavee58070101.model.Dot;

public class EditDotFragment extends Fragment implements View.OnClickListener{

    public interface OnClickButtonListener {
        void onClickButtonCancel();
        void onClickButtonConfirm(Dot dot);
    }

    private OnClickButtonListener onClickButtonListener;

    public void setOnClickButtonListener(OnClickButtonListener onClickButtonListener) {
        this.onClickButtonListener = onClickButtonListener;
    }

    public EditDotFragment() {
        // Required empty public constructor
    }

    Dot dot;
    EditText sizeEditText, positionX, positionY;
    Button buttonColor, buttonCancel, buttonConfirm;
    int dotColor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dot = getArguments().getParcelable("dot");
        if (dot != null){
            dotColor = dot.getColor();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);

        sizeEditText = (EditText) rootView.findViewById(R.id.editText);
        positionX = (EditText) rootView.findViewById(R.id.positionXEditText);
        positionY = (EditText) rootView.findViewById(R.id.positionYEditText);
        buttonColor = (Button) rootView.findViewById(R.id.buttonColor);
        buttonCancel = (Button) rootView.findViewById(R.id.buttonCancel);
        buttonConfirm = (Button) rootView.findViewById(R.id.buttonConfirm);

        buttonColor.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttonConfirm.setOnClickListener(this);

        sizeEditText.setText(String.valueOf(dot.getRadius()));
        positionX.setText(String.valueOf(dot.getCenterX()));
        positionY.setText(String.valueOf(dot.getCenterY()));
        buttonColor.setBackgroundColor(dot.getColor());

        return rootView;
    }

    public static EditDotFragment newInstance(Dot dot, OnClickButtonListener listener) {

        Bundle args = new Bundle();

        EditDotFragment fragment = new EditDotFragment();
        fragment.setOnClickButtonListener(listener);
        args.putParcelable("dot", dot);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onClick(View v) {
        if (R.id.buttonColor == v.getId()){
            onClickColor();
        } else if (R.id.buttonCancel == v.getId()){
            onClickCancel();
        } else {
            onClickConfirm();
        }

    }

    public void onClickCancel(){
        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
        onClickButtonListener.onClickButtonCancel();

    }

    public void onClickConfirm(){
        Toast.makeText(getActivity(), "Confirm", Toast.LENGTH_SHORT).show();

        dot.setColor(dotColor);
        dot.setRadius(Integer.parseInt(sizeEditText.getText().toString()));
        dot.setCenterX(Integer.parseInt(positionX.getText().toString()));
        dot.setCenterY(Integer.parseInt(positionY.getText().toString()));
        onClickButtonListener.onClickButtonConfirm(dot);
    }

    public void onClickColor (){
        showColorPicker();
    }

    private void showColorPicker (){
        ColorPickerDialogBuilder
                .with(getActivity())
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
                        dotColor = selectedColor;
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
