package com.mobiletrain.teamapp.model;

import java.io.Serializable;

/**
 * Created by jay on 2016/10/31.
 */
public class Content implements Serializable {

    String uCover;//头像
    String name;//用户名
    boolean is_v;//是否v
    String passtime;//日期
    String text;//内容详情
    String share_url;//分享详情
    String up;//点赞次数
    int down;//踩次数
    int forward;//分享次数
    String comment;//评论次数
    String loadUrl;//播放地址或图片地址
    int duration;//播放时长、要转格式
    int playcount;//播放次数
    String type;//类型
    String id;//条目ID
    int width;
    int height;
    String vedioCoverUrl; //视频封面

    public Content(String uCover, String name, boolean is_v, String passtime, String text, String share_url, String up, int down, int forward, String comment, String loadUrl, int duration, int playcount, String type, String id, int width, int height,String vedioCoverUrl) {
        this.uCover = uCover;
        this.name = name;
        this.is_v = is_v;
        this.passtime = passtime;
        this.text = text;
        this.share_url = share_url;
        this.up = up;
        this.down = down;
        this.forward = forward;
        this.comment = comment;
        this.loadUrl = loadUrl;
        this.duration = duration;
        this.playcount = playcount;
        this.type = type;
        this.id = id;
        this.width = width;
        this.height = height;
        this.vedioCoverUrl = vedioCoverUrl;
    }



    public String getVedioCoverUrl() {
        return vedioCoverUrl;
    }

    public String getuCover() {
        return uCover;
    }

    public String getName() {
        return name;
    }

    public boolean is_v() {
        return is_v;
    }

    public String getPasstime() {
        return passtime;
    }

    public String getText() {
        return text;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public int getForward() {
        return forward;
    }

    public String getComment() {
        return comment;
    }

    public String getLoadUrl() {
        return loadUrl;
    }

    public int getDuration() {
        return duration;
    }

    public int getPlaycount() {
        return playcount;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Content{" +
                "uCover='" + uCover + '\'' +
                ", name='" + name + '\'' +
                ", is_v=" + is_v +
                ", passtime='" + passtime + '\'' +
                ", text='" + text + '\'' +
                ", share_url='" + share_url + '\'' +
                ", up='" + up + '\'' +
                ", down=" + down +
                ", forward=" + forward +
                ", comment='" + comment + '\'' +
                ", playUrl='" + loadUrl + '\'' +
                ", duration=" + duration +
                ", playcount=" + playcount +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
