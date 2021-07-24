package com.example.myinstagramclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myinstagramclone.R;
import com.example.myinstagramclone.model.PixabayPosts;
import com.example.myinstagramclone.model.Posts;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfilePostsViewHolder> {

    List<Posts> postsList;
    Context context;

    public ProfileAdapter(List<Posts> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfilePostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_profile, parent, false);
        return new ProfilePostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePostsViewHolder holder, int position) {
        Posts post = postsList.get(position);
        Glide.with(context)
                .load(post.getWebformatURL())
                .placeholder(R.color.gray)
                .into(holder.imageViewPost);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }


    //...........
    class ProfilePostsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPost;

        public ProfilePostsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPost = itemView.findViewById(R.id.imageViewProfilePosts);
        }
    }
}
