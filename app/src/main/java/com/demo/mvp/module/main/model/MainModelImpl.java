package com.demo.mvp.module.main.model;

import com.common.utils.Constant;
import com.common.utils.HttpRequestUtils;

/**
 * Created by xiaoshuai on 2016/12/27.
 */

public class MainModelImpl implements IMainModel {
    /**
     * 请求数据的具体实现
     * @param url 请求路径
     * @param page 页数
     * @param pagesize 一页显示条数
     * @param time 时间戳
     * @param resultCallback 回调
     */
    @Override
    public void getJokeList(String url, int page, int pagesize, String time, HttpRequestUtils.ResultCallback resultCallback) {
        HttpRequestUtils.requestServer(HttpRequestUtils.RequestType.GET,url,new String[]{"sort","page","pagesize","time","key"},new String[]{"desc",String.valueOf(page),String.valueOf(pagesize),time, Constant.KEY_URL},resultCallback);
    }
}
