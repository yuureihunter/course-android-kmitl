package kmitl.lab03.pimpavee58070101.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    private final int SENT_DOT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lab04
//        Button btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
//        final DotSerializable dotSerializable = new DotSerializable();
//        dotSerializable.setCenterX(150);
//        dotSerializable.setCenterY(150);
//        dotSerializa ble.setRadius(30);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SENT_DOT_REQUEST){
            if (resultCode == RESULT_OK){
                Dot dot = data.getParcelableExtra("dot");
                int dotPosition = data.getIntExtra("dotPosition", 30);
                dots.getAllDot().set(dotPosition, dot);
            }
        }
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);

        if (dotPosition == -1){
            Dot newDot = new Dot(x, y, 30, color.randomColor());
            dots.addDot(newDot);
        }
        else {
//            dots.removeBy(dotPosition);
            Intent intent = new Intent(MainActivity.this, EditDotActivity.class);
            intent.putExtra("dot", (Parcelable) dots.getAllDot().get(dotPosition));
            intent.putExtra("dotPosition", dotPosition);
            startActivityForResult(intent, SENT_DOT_REQUEST);
        }
    }

    private Bitmap captureScreen (View view){
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }

    public void onClickShare (View view){
        Bitmap bitmapScreenshot = captureScreen(dotView);
        File file = saveCaptureScreen(bitmapScreenshot, getSavePath(this), "image.jpg");
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
