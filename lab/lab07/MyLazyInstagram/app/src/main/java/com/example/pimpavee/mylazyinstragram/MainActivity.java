package com.example.pimpavee.mylazyinstragram;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pimpavee.mylazyinstragram.adapter.PostAdapter;
import com.example.pimpavee.mylazyinstragram.api.LazyInstragramApi;
import com.example.pimpavee.mylazyinstragram.api.UserProfile;
import com.example.pimpavee.mylazyinstragram.model.FollowRequest;
import com.example.pimpavee.mylazyinstragram.model.FollowResponse;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    Spinner spinnerUsrName;
    String userName;
    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = getIntent().getStringExtra("name");

        if (userName == null){
            userName = "cartoon";
        }

        getUserProfile(userName);

        spinnerUsrName = findViewById(R.id.spinnerUsrName);

        ImageButton imageButtonSingle = findViewById(R.id.imageButtonSingle);
        ImageButton imageButtonTriple = findViewById(R.id.imageButtonTriple);
        recyclerView = findViewById(R.id.recyclerView);
        Toast.makeText(this, userName, Toast.LENGTH_SHORT).show();
        spinnerUsrName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != getUserNameNumber(userName)){
                    String item = adapterView.getItemAtPosition(i).toString();
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.putExtra("name", item);
                    startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] names = {"android", "cartoon", "nature"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsrName.setAdapter(adapter);
        spinnerUsrName.setSelection(getUserNameNumber(userName));

        imageButtonSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            }
        });

        imageButtonTriple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            }
        });

        Button buttonFollow = findViewById(R.id.buttonFollow);
        buttonFollow.setOnClickListener(this);

    }

    private void updateFollowButton(UserProfile userProfile) {
        Button buttonFollow = findViewById(R.id.buttonFollow);
        buttonFollow.setText(userProfile.isFollow() ? "Followed" : "Follow");
        buttonFollow.setBackgroundColor(userProfile.isFollow() ? Color.parseColor("#F2594B") : Color.parseColor("#375FBF"));
    }

    private void displayError(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    private AlertDialog createLoadingDialog() {
        ProgressBar bar = new ProgressBar(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(bar)
                .create();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return dialog;
    }

    private void onClickFollow(){
        final AlertDialog loadingDialog = createLoadingDialog();
        loadingDialog.show();

        FollowRequest request = new FollowRequest();
        request.setUser(userName);
        request.setFollow(!userProfile.isFollow());

        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstragramApi.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyInstragramApi lazyInstragramApi = retrofit.create(LazyInstragramApi.class);
        Call<FollowResponse> call = lazyInstragramApi.follow(request);
        call.enqueue(new Callback<FollowResponse>() {
            @Override
            public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                loadingDialog.dismiss();
                if (response.isSuccessful()) {
                    userProfile.setFollow(!userProfile.isFollow());
                    updateFollowButton(userProfile);
                } else {
                    displayError("Error!", "There is some thing wrong!");
                }
            }

            @Override
            public void onFailure(Call<FollowResponse> call, Throwable t) {
                loadingDialog.dismiss();
                displayError("Error!", "There is some thing wrong!");
            }
        });

    }

    private void getUserProfile(String usrName){
        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstragramApi.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyInstragramApi lazyInstragramApi = retrofit.create(LazyInstragramApi.class);

        Call<UserProfile> call = lazyInstragramApi.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {

                if (response.isSuccessful()) {
                    userProfile = response.body();
//                    TextView textName = (TextView) findViewById(R.id.textName);
//                    textName.setText("@" + userProfile.getUser());

                    ImageView imageProfile = findViewById(R.id.imageProfile);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile())
                            .into(imageProfile);

                    TextView textPost = findViewById(R.id.textPostValue);
                    textPost.setText(String.valueOf(userProfile.getPost()));

                    TextView textFollowing = findViewById(R.id.textFollowingValue);
                    textFollowing.setText(String.valueOf(userProfile.getFollowing()));

                    TextView textFollower = findViewById(R.id.textFollowerValue);
                    textFollower.setText(String.valueOf(userProfile.getFollower()));

                    TextView textBio = findViewById(R.id.textBio);
                    textBio.setText(String.valueOf(userProfile.getBio()));

                    PostAdapter postAdapter = new PostAdapter(MainActivity.this, userProfile.getPosts());
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                    recyclerView.setAdapter(postAdapter);

                    updateFollowButton(userProfile);

                }
                else {
                    displayError("Error!", "There is some thing wrong!");
                }

            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }

    private int getUserNameNumber(String userName){
        switch(userName){
            case "android":
                return 0;
            case "cartoon":
                return 1;
            default:
                return 2;
        }

    }

    @Override
    public void onClick(View view) {
        onClickFollow();
    }
}
