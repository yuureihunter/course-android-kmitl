package kmitl.lab03.pimpavee58070101.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import kmitl.lab03.pimpavee58070101.R;
import kmitl.lab03.pimpavee58070101.model.Colors;
import kmitl.lab03.pimpavee58070101.model.Dot;
import kmitl.lab03.pimpavee58070101.model.Dots;
import kmitl.lab03.pimpavee58070101.view.DotView;

public class DotViewFragment extends Fragment implements Dots.OnDotsChangedListener, DotView.OnDotViewPressListener, View.OnClickListener{

    public interface OnDotLongPressedListener {
        void onDotLongPressed(Dot dot);
    }
    private Dots dots;
    private DotView dotView;
    private Colors color = new Colors();
    private OnDotLongPressedListener onDotLongPressedListener;

    public void setOnDotLongPressedListener(OnDotLongPressedListener onDotLongPressedListener) {
        this.onDotLongPressedListener = onDotLongPressedListener;
    }

    public DotViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dots = new Dots();
        dots.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dot_view, container, false);

        dotView = (DotView) rootView.findViewById(R.id.dotView);
        Button buttonClear = (Button) rootView.findViewById(R.id.buttonClear);
        Button buttonRandom = (Button) rootView.findViewById(R.id.buttonRandom);
        Button buttonShare = (Button) rootView.findViewById(R.id.buttonShare);

        dotView.setOnDotViewPressListener(this);
        buttonClear.setOnClickListener(this);
        buttonRandom.setOnClickListener(this);
        buttonShare.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dotView.setDots(dots);
    }

    public static DotViewFragment newInstance(OnDotLongPressedListener listener){
        Bundle args = new Bundle();

        DotViewFragment fragment = new DotViewFragment();
        fragment.setOnDotLongPressedListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {

        if (R.id.buttonClear == v.getId()){
            onRemoveAll();
        } else if (R.id.buttonRandom == v.getId()){
            onRandomDot();
        } else {
            onClickShare();
        }

    }

    public void onRandomDot() {
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, color.randomColor());
        dots.addDot(newDot);

    }

    public void onRemoveAll() {
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
        } else {
            dots.removeBy(dotPosition);
        }
    }

    @Override
    public void onDotViewLongPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);

        if (dotPosition == -1){
            Dot newDot = new Dot(x, y, 30, color.randomColor());
            dots.addDot(newDot);
        } else {
            Toast.makeText(getActivity(), "Edit Dot", Toast.LENGTH_SHORT).show();
            onDotLongPressedListener.onDotLongPressed(dots.getDot(dotPosition));
        }
    }

    private Bitmap captureScreen (View view){
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }

    public void onClickShare (){
        Bitmap bitmapScreenshot = captureScreen(dotView);
        File file = saveCaptureScreen(bitmapScreenshot, getSavePath(getActivity()), "image.jpg");
        shareSimpleMyDot(file);
    }

    private File getSavePath (Context context){
        File directory = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Pictures");
        if (!directory.exists()){
            if (directory.mkdir()){
                Toast.makeText(context, "CREATED PICTURE DIRECTORY", Toast.LENGTH_SHORT).show();
            }
        }

        return directory;
    }

    private File saveCaptureScreen (Bitmap bitmap, File path, String imageName){
        File image = new File(path.getAbsolutePath(), imageName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    private void shareSimpleMyDot (File file){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        startActivity(Intent.createChooser(intent, "Choose App"));
    }

}
