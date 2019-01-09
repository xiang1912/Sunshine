package com.example.tonjies.templateproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tonjies.templateproject.R;

import java.util.List;

/**
 * Created by 舍长 on 2019/1/5
 * describe:
 */
public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.MyViewHolder> {

    private Context context;

    private List<String> mDates;

    public SelectAdapter(Context context, List<String> mDates) {
        this.context = context;
        this.mDates = mDates;
    }

    //填充View对象
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_city, null);
        return new MyViewHolder(view);
    }

    //加载每个item要是显示的数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(mDates.get(position));
    }

    //返回item数量
    @Override
    public int getItemCount() {
        return mDates.size();
    }

    //初始化控件
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvCityName);
        }
    }
}
