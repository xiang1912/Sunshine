package com.example.tonjies.templateproject.module.refrence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.common.util.AHelper;
import com.example.tonjies.templateproject.R;
import android.util.Log;
/**
 * Java模板汇总Activity
 * 用来进行某些需要单独出来的测试代码
 */
public class RefrenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrence);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            //测试简单网络示例Activity
            case R.id.one:
                startActivity(new Intent(this, RefrenceActivity.class));
                Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
                break;
            //测试吐司工具类
            case R.id.two:


                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.three:
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
                break;
            case R.id.four:
                Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();
                break;
            case R.id.five:
                Toast.makeText(this, "five", Toast.LENGTH_SHORT).show();
                break;
            case R.id.six:
                Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
                break;
            case R.id.seven:
                Toast.makeText(this, "seven", Toast.LENGTH_SHORT).show();
                break;
            case R.id.eight:
                Toast.makeText(this, "eight", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
