package kmitl.lab03.pimpavee58070101.controller;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kmitl.lab03.pimpavee58070101.Fragment.DotViewFragment;
import kmitl.lab03.pimpavee58070101.Fragment.EditDotFragment;
import kmitl.lab03.pimpavee58070101.R;
import kmitl.lab03.pimpavee58070101.model.Dot;

public class MainActivity extends AppCompatActivity implements DotViewFragment.OnDotLongPressedListener, EditDotFragment.OnClickButtonListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            initialFragment();
        }
    }

    private void initialFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, DotViewFragment.newInstance(MainActivity.this)).commit();

    }

    @Override
    public void onDotLongPressed(Dot dot) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, EditDotFragment.newInstance(dot, MainActivity.this)).addToBackStack(null)
                .commit();

    }

    @Override
    public void onClickButtonCancel() {
        getSupportFragmentManager().popBackStack();

    }

    @Override
    public void onClickButtonConfirm(Dot dot) {
        getSupportFragmentManager().popBackStack();

    }

}
