package kmitl.lab03.pimpavee58070101.model;

import java.util.ArrayList;
import java.util.List;

public class Dots {

    public interface OnDotsChangedListener {
        void onDotsChanged(Dots dots);
    }

    private OnDotsChangedListener listener;

    public void setListener(OnDotsChangedListener listener){
        this.listener = listener;
    }

    private List<Dot> allDot = new ArrayList<>();
    public List<Dot> getAllDot () {
        return allDot;
    }

    public void addDot(Dot dot){
        this.allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void clearAll(){
        allDot.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y){
        for (int i = 0; i < allDot.size(); i++){
            int centerX = allDot.get(i).getCenterX();
            int centerY = allDot.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) + Math.sqrt(Math.pow(centerY - y, 2));
            if (distance <= 30){
                return i;
            }
        }
        return -1;
    }

    public void removeBy(int dotPosition){
        allDot.remove(dotPosition);
        this.listener.onDotsChanged(this);
    }
}
