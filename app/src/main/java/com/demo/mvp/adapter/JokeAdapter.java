package com.demo.mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.mvp.bean.JokeBean;
import com.example.xiaoshuai.mvpeasydemo.R;

import java.util.List;

/**
 * Created by xiaoshuai on 2016/12/27.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder>  {
    private List<JokeBean> mDatas;
    public JokeAdapter(List<JokeBean> jokeBeanList){
        this.mDatas=jokeBeanList;
    }
    @Override
    public JokeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(JokeAdapter.ViewHolder holder, int position) {
        Log.d("tag","-----------"+mDatas.get(position).getContent());
        holder.content.setText(mDatas.get(position).getContent());
        holder.updatetime.setText(mDatas.get(position).getUpdatetime());
    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private TextView updatetime;
        public ViewHolder(View view){
            super(view);
            content = (TextView) view.findViewById(R.id.tv_content);
            updatetime= (TextView) view.findViewById(R.id.tv_updatetime);
        }
    }
}
