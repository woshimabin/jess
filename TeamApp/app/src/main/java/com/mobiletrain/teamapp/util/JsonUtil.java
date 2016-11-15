package com.mobiletrain.teamapp.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mobiletrain.teamapp.javabean.AllContentBean;
import com.mobiletrain.teamapp.javabean.CommentBean;
import com.mobiletrain.teamapp.javabean.GifsBean;
import com.mobiletrain.teamapp.javabean.ImagesBean;
import com.mobiletrain.teamapp.javabean.RegisterBean;
import com.mobiletrain.teamapp.javabean.TabBean;

/**
 * Created by idea on 2016/10/8.
 */
public class JsonUtil {

    public static TabBean parseTabBean(String json) {
        TabBean tabBean = null;
        try {
            tabBean = new Gson().fromJson(json, TabBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return tabBean;
    }

    public static AllContentBean parseToAllContentBean(String json) {
        AllContentBean allContentBean = null;
        try {
            allContentBean = new Gson().fromJson(json, AllContentBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return allContentBean;
    }
    public static ImagesBean parseToImagesBean(String json) {
        ImagesBean imagesBean = null;
        try {
            imagesBean = new Gson().fromJson(json, ImagesBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return imagesBean;
    }
    public static GifsBean parseToGifsBean(String json) {
        GifsBean gifsBean = null;
        try {
            gifsBean = new Gson().fromJson(json, GifsBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return gifsBean;
    }

       public static CommentBean parseToCommentBean(String json) {
        CommentBean commentBean = null;
        try {
            commentBean = new Gson().fromJson(json,CommentBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return commentBean;
    }

    public static RegisterBean parseToRegisterBean(String json) {
        RegisterBean registerBean = null;
        try {
            registerBean = new Gson().fromJson(json, RegisterBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return registerBean;
    }


}