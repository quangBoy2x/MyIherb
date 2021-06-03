package com.example.project_thuc_tap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.project_thuc_tap.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            Map();
            viewPagerSetAdapter();
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                //xử lí chọn các tab khi chọn các icon menu tương ứng
                @Override
                public void onPageSelected(int position) {
                    switch (position){
                        case 0:
                            bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                            break;
                        case 1:
                            bottomNavigationView.getMenu().findItem(R.id.nav_categorizes).setChecked(true);
                            break;
                        case 2:
                            bottomNavigationView.getMenu().findItem(R.id.nav_shopping).setChecked(true);
                            break;
                        case 3:
                            bottomNavigationView.getMenu().findItem(R.id.nav_account).setChecked(true);
                            break;

                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.nav_categorizes:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.nav_shopping:
                            viewPager.setCurrentItem(2);
                            break;
                        case R.id.nav_account:
                            viewPager.setCurrentItem(3);
                            break;
                    }
                    return true;
                }
            });
    }



    private void Map(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_menu);
    }

    private void viewPagerSetAdapter(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
    }
}