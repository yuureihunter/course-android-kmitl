package com.example.student.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {

    String message;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        TextView helloTextView = (TextView) rootView.findViewById(R.id.textView);

        if (!message.isEmpty()) {
            helloTextView.setText(message);
        }
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString("message");
    }

    public static MainFragment newInstance(String message) {
        
        Bundle args = new Bundle();
        
        MainFragment fragment = new MainFragment();
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;
    }

}
