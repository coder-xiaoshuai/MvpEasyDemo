package com.demo.mvp.module.main.model;

import com.common.utils.HttpRequestUtils;

/**
 * Created by xiaoshuai on 2016/12/27.
 *
 * 可以理解为一个功能清单
 */

public interface IMainModel {
    /**
     * 获取笑话列表
     * @param url 请求路径
     * @param page 页数
     * @param pagesize 一页显示条数
     * @param time 时间戳
     * @param resultCallback 回调
     */
    public void getJokeList(String url, int page,int pagesize,String time, HttpRequestUtils.ResultCallback resultCallback);
}
