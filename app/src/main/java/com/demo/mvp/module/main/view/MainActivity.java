package com.demo.mvp.module.main.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.common.utils.Constant;
import com.demo.mvp.adapter.JokeAdapter;
import com.demo.mvp.bean.JokeBean;
import com.demo.mvp.module.main.presenter.MainPresenter;
import com.example.xiaoshuai.mvpeasydemo.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView{
    private MainPresenter mainPresenter;

    private XRecyclerView xRecyclerView;
    private JokeAdapter mAdapter;
    private List<JokeBean> mAllDatas;
    private int currentpage=1;
    private static final int DEFAULT_PAGE_SIZE=20;
    private Date currentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        initViews();
        initOtherVariables();
        initEvents();
    }

    /**
     * 初始化presenter
     */
    private void initPresenter(){
        mainPresenter=new MainPresenter(this);

        //首先默认加载第一页
        currentTime=new Date();
        mainPresenter.showJokeList(Constant.URL_JOKE,currentpage,DEFAULT_PAGE_SIZE,String.valueOf(currentTime.getTime()).substring(0,10));
    }

    private void initViews(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        xRecyclerView= (XRecyclerView) findViewById(R.id.recyclerview);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
    }

    /**
     * 初始化一些其他变量
     */
    private void initOtherVariables(){
        mAllDatas=new ArrayList<>();
        mAdapter=new JokeAdapter(mAllDatas);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.refresh();
    }

    /**
     * 初始化一些事件
     */
    private void initEvents(){
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentpage=1;
                currentTime=new Date();
                Log.d("tag","====currentTime===="+String.valueOf(currentTime.getTime()).substring(0,10));
                mainPresenter.showJokeList(Constant.URL_JOKE,currentpage,DEFAULT_PAGE_SIZE,String.valueOf(currentTime.getTime()).substring(0,10));
            }

            @Override
            public void onLoadMore() {
                currentpage++;
                mainPresenter.showJokeList(Constant.URL_JOKE,currentpage,DEFAULT_PAGE_SIZE,String.valueOf(currentTime.getTime()).substring(0,10));
            }
        });
    }

    @Override
    public void showJokeList(List<JokeBean> jokeBeanList, int page) {
        if(page==1){
            //page==1是下拉刷新
            xRecyclerView.refreshComplete();
            if(mAllDatas==null){
                mAllDatas=new ArrayList<>();
                mAllDatas.addAll(jokeBeanList);
            }else {
                mAllDatas.clear();
                mAllDatas.addAll(jokeBeanList);
            }
            mAdapter.notifyDataSetChanged();
        }else {
            //上拉加载
            xRecyclerView.loadMoreComplete();
            mAllDatas.addAll(jokeBeanList);
            mAdapter.notifyDataSetChanged();
        }

        if(jokeBeanList.size()==0){
            Toast.makeText(this,"没有更多数据了",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onShowJokeListFail(String error) {
        xRecyclerView.refreshComplete();
        xRecyclerView.loadMoreComplete();
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
}
