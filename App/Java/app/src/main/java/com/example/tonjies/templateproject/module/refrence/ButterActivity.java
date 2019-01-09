package com.example.tonjies.templateproject.module.refrence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.common.util.AHelper;
import com.example.tonjies.templateproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 舍长
 * describe:黄油刀测试Activity
 */
public class ButterActivity extends AppCompatActivity {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.btn03)
    Button btn03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn01, R.id.btn02, R.id.btn03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                AHelper.show("one");
//                Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn02:
                AHelper.show("two");
//                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn03:
                AHelper.show("three");
//                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
