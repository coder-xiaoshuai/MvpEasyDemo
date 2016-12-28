package com.common.utils;

import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by Admin on 2016/6/22.
 */
public class HttpRequestUtils {

    private static final String TAG = "HttpRequestUtils";

    public  enum RequestType{
        GET,POST
    }


    public interface ResultCallback {
        void onSuccess(String result);

        void onError(String errMsg);

        void onFinish();

        void onCancelled(String hint);
    }


    /**
     * 请求网络
     * @param requestType
     * @param url
     * @param keys
     * @param values
     * @param resultCallback
     * @return
     */
    public static Callback.Cancelable requestServer(RequestType requestType,String url, String[] keys, String[] values, final ResultCallback resultCallback) {
        if (keys.length == 0 || values.length == 0 || keys.length != values.length) {
            Log.e(TAG, "********参数错误或者参数不匹配********");
            return null;
        }
        RequestParams params = new RequestParams(url);
        if (keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                params.addParameter(keys[i], values[i]);
            }
        }
        Callback.Cancelable cancelable=null;
        if(requestType==RequestType.GET){
            cancelable= x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    resultCallback.onSuccess(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    resultCallback.onError(ex.toString());
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    resultCallback.onCancelled(cex.toString());
                }

                @Override
                public void onFinished() {
                    resultCallback.onFinish();
                }
            });
        }else if(requestType==RequestType.POST){

            cancelable=x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                resultCallback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                resultCallback.onError(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                resultCallback.onCancelled(cex.toString());
            }

            @Override
            public void onFinished() {
                resultCallback.onFinish();
            }
        });
        }

       return cancelable;
    }

    /**
     * 取消网络请求
     * @param cancelable
     */
    public static void cancelRequest(Callback.Cancelable cancelable){
        cancelable.cancel();
    }

}
