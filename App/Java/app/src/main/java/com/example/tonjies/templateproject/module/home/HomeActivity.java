package com.example.tonjies.templateproject.module.home;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.common.refrence.fragment.SimpleFragment;
import com.example.common.util.BottomNavigationViewHelper;
import com.example.tonjies.templateproject.R;

/**
 * Created by 舍长
 * describe:主页示例
 */
public class HomeActivity extends AppCompatActivity {

    //底部导航控件
    private BottomNavigationView bottomNavigationView;

    private SimpleFragment one;

    private SimpleFragment two;

    private SimpleFragment three;

    private SimpleFragment four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //初始化数据
        initData();
        //设置底部导航栏逻辑
        initBottom();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        one = SimpleFragment.getInstance("one");
        two = SimpleFragment.getInstance("two");
        three = SimpleFragment.getInstance("three");
        four = SimpleFragment.getInstance("four");

        //获取fragment管理类
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, one).show(one)
                .add(R.id.container, two).hide(two)
                .add(R.id.container, three).hide(three)
                .add(R.id.container, four).hide(four).commit();
    }

    /**
     * 设置底部导航栏逻辑
     */
    private void initBottom() {
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.train:
//                        获取管理类
                        getSupportFragmentManager().beginTransaction()
                                .show(one)
                                .hide(two)
                                .hide(three)
                                .hide(four).commit();
                        break;
                    case R.id.shop:
//                        fragment = collegeFragment;
                        //                        获取管理类
                        getSupportFragmentManager().beginTransaction()
                                .hide(one)
                                .show(two)
                                .hide(three)
                                .hide(four).commit();
                        break;
                    case R.id.fond:
                        getSupportFragmentManager().beginTransaction()
                                .hide(one)
                                .hide(two)
                                .show(three)
                                .hide(four).commit();
                        break;
                    case R.id.my:
                        getSupportFragmentManager().beginTransaction()
                                .hide(one)
                                .hide(two)
                                .hide(three)
                                .show(four).commit();

                        break;
                }
                return true;
            }
        });
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }
}
