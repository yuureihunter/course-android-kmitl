package com.example.pimpavee.mylazyinstragram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pimpavee.mylazyinstragram.MainActivity;
import com.example.pimpavee.mylazyinstragram.R;
import com.example.pimpavee.mylazyinstragram.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;


class Holder extends RecyclerView.ViewHolder{

    ImageView image;
    TextView textViewLike;
    TextView textViewComment;

    public Holder(View itemView){
        super(itemView);
        image = itemView.findViewById(R.id.imageView);
        textViewLike = itemView.findViewById(R.id.textViewLike);
        textViewComment = itemView.findViewById(R.id.textViewComment);

    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder>{

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(posts.get(position).getUrl()).into(image);

        TextView textViewLike = holder.textViewLike;
        textViewLike.setText(String.valueOf(posts.get(position).getLike()));

        TextView textViewComment = holder.textViewComment;
        textViewComment.setText(String.valueOf(posts.get(position).getComment()));

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
