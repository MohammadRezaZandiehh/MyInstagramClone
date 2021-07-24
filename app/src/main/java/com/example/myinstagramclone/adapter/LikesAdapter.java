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

public class LikesAdapter  extends RecyclerView.Adapter<LikesAdapter.LikeViewHolder> {
    List<Posts> postList;
    Context context;

    public LikesAdapter(List<Posts> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public LikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_likes,parent,false);

        return new LikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeViewHolder holder, int position) {
        Glide.with(context).load(postList.get(position).getUserImageURL()).into(holder.imageViewProfile);
        holder.textViewUsername.setText(postList.get(position).getUsername());
        holder.textViewActivity.setText(" liked your post x");
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class LikeViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewProfile;
        TextView textViewUsername;
        TextView textViewActivity;
        public LikeViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewProfile = itemView.findViewById(R.id.imageViewProfile);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewActivity = itemView.findViewById(R.id.textViewActivity);
        }
    }
}
