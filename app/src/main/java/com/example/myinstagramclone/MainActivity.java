package com.example.myinstagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.myinstagramclone.adapter.MainFragmentPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//...............
//...............

        Toolbar toolbarMain = findViewById(R.id.toolbar);  // import dar daghighe 1:01
        setSupportActionBar(toolbarMain);

        BottomNavigationView bottomNavigationViewMain = findViewById(R.id.bottomNavigationView);
        ViewPager2 viewPagerMain = findViewById(R.id.viewPagerMain);
        viewPagerMain.setUserInputEnabled(false);
        // khate bala mige dg karbar nmitone ba dast fragment ha ro varagh bzne (daghighe 31) .
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(
                getSupportFragmentManager(), this.getLifecycle());
        viewPagerMain.setAdapter(mainFragmentPagerAdapter);

        bottomNavigationViewMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){           // daghighe 46 hatmn ye bar dg gosh bdm k position ha az koja omdn .
                    case R.id.actionHopme:
                        viewPagerMain.setCurrentItem(0);
                        break;
                    case R.id.actionSerach:
                        viewPagerMain.setCurrentItem(1);
                        break;
                    case R.id.actionLikes:
                        viewPagerMain.setCurrentItem(2);
                        break;
                    case R.id.actionProfile:
                        viewPagerMain.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }
}