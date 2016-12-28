package com.common.base;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Admin on 2016/6/23.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xUtils
        x.Ext.init(this);
    }
}
