package com.demo.mvp.bean;

/**
 * Created by xiaoshuai on 2016/12/27.
 *
 * 笑话实体类
 */

public class JokeBean {

    /**
     * content : 女生分手的原因有两个，
     一个是：闺蜜看不上。另一个是：闺蜜看上了。
     * hashId : 607ce18b4bed0d7b0012b66ed201fb08
     * unixtime : 1418815439
     * updatetime : 2014-12-17 19:23:59
     */

    private String content;
    private String hashId;
    private int unixtime;
    private String updatetime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public int getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(int unixtime) {
        this.unixtime = unixtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
