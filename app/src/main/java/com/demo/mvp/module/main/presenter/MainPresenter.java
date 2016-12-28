package com.demo.mvp.module.main.presenter;

import android.util.Log;

import com.common.utils.HttpRequestUtils;
import com.common.utils.JsonUtils;
import com.demo.mvp.bean.JokeBean;
import com.demo.mvp.module.main.model.IMainModel;
import com.demo.mvp.module.main.model.MainModelImpl;
import com.demo.mvp.module.main.view.IMainView;

import org.json.JSONException;

import java.util.List;

/**
 * Created by xiaoshuai on 2016/12/27.
 */

public class MainPresenter {
    private IMainModel iMainModel;
    private IMainView iMainView;

    public MainPresenter(IMainView iMainView){
        this.iMainView=iMainView;
        iMainModel=new MainModelImpl();
    }

    public void showJokeList(String url, final int page, int pagesize, String time){
        iMainModel.getJokeList(url, page, pagesize, time, new HttpRequestUtils.ResultCallback() {
            @Override
            public void onSuccess(String result) {
                List<JokeBean> jokeBeanList=null;
                Log.d("tag","------result-----"+result);
                try {
                    jokeBeanList=JsonUtils.parseRootJson2List(result, JokeBean.class,"data");
                    if(jokeBeanList!=null){
                        iMainView.showJokeList(jokeBeanList,page);
                    }else if(jokeBeanList.size()==0){
                        iMainView.onShowJokeListFail("获取列表为空");
                    }else{
                        iMainView.onShowJokeListFail("获取列表失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errMsg) {
                Log.e("tag","-------报错信息-------"+errMsg);
                iMainView.onShowJokeListFail(errMsg);
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled(String hint) {

            }
        });
    }
}
