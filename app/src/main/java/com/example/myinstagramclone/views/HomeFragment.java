package com.example.myinstagramclone.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myinstagramclone.R;
import com.example.myinstagramclone.WebService.Service;
import com.example.myinstagramclone.adapter.HomeAdapter;
import com.example.myinstagramclone.model.PixabayPosts;
import com.example.myinstagramclone.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewHome = view.findViewById(R.id.recycelerViewMian);
        ProgressBar progressBarHome = view .findViewById(R.id.progressBarHome);
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory)
                .build();

        Service service = retrofit.create(Service.class);
//............
        service.getAllPosts().enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                progressBarHome.setVisibility(View.INVISIBLE);
                List<Posts> postList = response.body().getHits();
                HomeAdapter homeAdapter = new HomeAdapter(postList, getActivity());
                recyclerViewHome.setAdapter(homeAdapter);
                recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
//............
            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {

            }
        });
    }
}