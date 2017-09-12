package kmitl.lab03.pimpavee58070101.model;


import android.graphics.Color;

import java.util.Random;

public class Colors {

    public int randomColor(){
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.rgb(r, g, b);
    }
}
