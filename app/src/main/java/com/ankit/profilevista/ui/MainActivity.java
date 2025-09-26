package com.ankit.profilevista.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ankit.profilevista.R;
import com.ankit.profilevista.viewmodel.UserViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private UserViewModel viewModel;
    private UsersAdapter adapter;
    private ProgressBar progressBar;
    private TextView tvError;
    private SwipeRefreshLayout swipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        tvError = findViewById(R.id.tvError);
        swipe = findViewById(R.id.swipeRefresh);

        adapter = new UsersAdapter((user, avatar) -> {
            Intent i = new Intent(MainActivity.this, DetailActivity.class);
            i.putExtra("user_id", user.id);
            startActivity(i);
        });


        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(UserViewModel.class);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(UserViewModel.class);

        // Observe list
        viewModel.getAllUsers().observe(this, users -> {
            adapter.setData(users);
            tvError.setVisibility(users == null || users.isEmpty() ? View.VISIBLE : View.GONE);
            if (users == null || users.isEmpty()) {
                tvError.setText("No users found. Pull to refresh or check network.");
            }
        });

        // Loading + error
        viewModel.getLoading().observe(this, loading -> {
            swipe.setRefreshing(Boolean.TRUE.equals(loading));
            progressBar.setVisibility(Boolean.TRUE.equals(loading) ? View.VISIBLE : View.GONE);
        });

        viewModel.getError().observe(this, err -> {
            if (err != null) {
                tvError.setText(err);
                tvError.setVisibility(View.VISIBLE);
            } else {
                tvError.setVisibility(View.GONE);
            }
        });

        swipe.setOnRefreshListener(() -> viewModel.refreshFromNetwork());


        viewModel.refreshFromNetwork();
    }
}