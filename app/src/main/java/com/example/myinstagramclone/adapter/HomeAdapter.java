package com.example.myinstagramclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myinstagramclone.R;
import com.example.myinstagramclone.model.Posts;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    List<Posts> postList;
    Context context;

    public HomeAdapter(List<Posts> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Posts post = postList.get(position);
        holder.textViewComment.setText(post.getComments() + "");
        holder.textViewLikes.setText(post.getLikes() + "");
        holder.textViewViews.setText(post.getViews() + "");
        holder.textViewUsername.setText(post.getUsername() + "");

        Glide.with(context).load(post.getWebformatURL()).into(holder.imageViewPostContent);
        Glide.with(context)
                .load(post.getUserImageURL())
                .circleCrop()
                .into(holder.imageViewUserProfile);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPostContent;
        ImageView imageViewUserProfile;
        TextView textViewComment;
        TextView textViewLikes;
        TextView textViewViews;
        TextView textViewUsername;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPostContent = itemView.findViewById(R.id.imageViewPostContent);
            imageViewUserProfile = itemView.findViewById(R.id.imageViewUserProfile);
            textViewComment = itemView.findViewById(R.id.textViewComment);
            textViewLikes = itemView.findViewById(R.id.textViewLikes);
            textViewViews = itemView.findViewById(R.id.textViewViews);
            textViewUsername = itemView.findViewById(R.id.textViewUserName);
        }
    }
}
