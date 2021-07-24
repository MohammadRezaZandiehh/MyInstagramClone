package com.example.myinstagramclone.views;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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

import static androidx.core.content.ContextCompat.getSystemService;

public class SearchFragment extends Fragment {
    RecyclerView recyclerViewSearch;
    ProgressBar progressBarSearch;
    TextView textViewEmptyState;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editTextSearchPost = view.findViewById(R.id.editTextSearchPost);
        textViewEmptyState = view.findViewById(R.id.textViewEmptyState);
        progressBarSearch = view.findViewById(R.id.progressBarSearch);
        recyclerViewSearch = view.findViewById(R.id.recycelerViewSearch);
        progressBarSearch.setVisibility(View.INVISIBLE);
        editTextSearchPost.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchKeyWord = editTextSearchPost.getText().toString();
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    progressBarSearch.setVisibility(View.VISIBLE);
                    recyclerViewSearch.setVisibility(View.INVISIBLE);
                    textViewEmptyState.setVisibility(View.INVISIBLE);
                    searchInPixabay(searchKeyWord);
                    return true;
                }
                return false;
            }
        });
    }




    public void searchInPixabay(String keyWord) {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory)
                .build();

        Service service = retrofit.create(Service.class);
        service.getPostByKeyword(keyWord).enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                progressBarSearch.setVisibility(View.INVISIBLE);
                recyclerViewSearch.setVisibility(View.VISIBLE);
                List<Posts> postsList = response.body().getHits();
                if (postsList.isEmpty()){
                    textViewEmptyState.setVisibility(View.VISIBLE);
                    progressBarSearch.setVisibility(View.INVISIBLE);
                }
                HomeAdapter homeAdapter = new HomeAdapter(postsList, getActivity());
                recyclerViewSearch.setAdapter(homeAdapter);
                recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {

            }
        });
    }
}
