package com.example.myinstagramclone.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myinstagramclone.R;
import com.example.myinstagramclone.WebService.Service;
import com.example.myinstagramclone.adapter.HomeAdapter;
import com.example.myinstagramclone.adapter.ProfileAdapter;
import com.example.myinstagramclone.model.PixabayPosts;
import com.example.myinstagramclone.model.Posts;
import com.qintong.library.InsLoadingView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    RecyclerView recyclerViewProfile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewProfile = view.findViewById(R.id.recyclerVIewProfile);
        InsLoadingView imageViewUser = view.findViewById(R.id.imageViewUser);

        Glide.with(getActivity())
                .load("https://images.unsplash.com/photo-1520223297779-95bbd1ea79b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1566&q=80")
                .circleCrop()
                .into(imageViewUser);
        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageViewUser.getStatus() == InsLoadingView.Status.LOADING) {
                    imageViewUser.setStatus(InsLoadingView.Status.UNCLICKED);
                } else {
                    imageViewUser.setStatus(InsLoadingView.Status.LOADING);
                }

            }
        });
        getPhotos();
    }


    void getPhotos() {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory).build();
        Service service = retrofit.create(Service.class);
        service.getAllPosts().enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                List<Posts> postList = response.body().getHits();
                recyclerViewProfile.setAdapter(new ProfileAdapter(postList, getActivity()));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                recyclerViewProfile.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {

            }
        });
    }
}