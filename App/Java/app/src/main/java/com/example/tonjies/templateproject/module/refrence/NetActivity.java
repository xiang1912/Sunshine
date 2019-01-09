package com.example.tonjies.templateproject.module.refrence;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.util.L;
import com.example.tonjies.templateproject.R;
import com.example.tonjies.templateproject.module.refrence.bean.Student;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 网络请求小案例
 */
public class NetActivity extends AppCompatActivity {


    private TextView tvNetReturn;//显示服务器返回的信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        initView(this);//初始化控件
    }

    /**
     * 初始化控件
     */
    private void initView(Context context) {
        tvNetReturn = findViewById(R.id.tvNetReturn);
    }


    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //菜单选项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            case R.id.one:
                Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.two:
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.three:
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.four:
                Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.five:
                Toast.makeText(this, "five", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.six:
                Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.seven:
                Toast.makeText(this, "seven", Toast.LENGTH_SHORT).show();
                break;
            //
            case R.id.eight:
                Toast.makeText(this, "eight", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }


}
