package com.demo.mvp.module.main.view;

import com.demo.mvp.bean.JokeBean;

import java.util.List;

/**
 * Created by xiaoshuai on 2016/12/27.
 */

public interface IMainView {
    /**
     * 显示笑话列表
     * @param jokeBeanList
     * @param page
     */
    public void showJokeList(List<JokeBean> jokeBeanList,int page);

    /**
     * 显示失败
     * @param error
     */
    public void onShowJokeListFail(String error);
}
