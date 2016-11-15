package com.mobiletrain.teamapp.javabean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qcf on 2016/11/1.
 */

public class GifsBean {
    /**
     * status : 4
     * comment : 68
     * top_comments : [{"voicetime":0,"status":0,"cmt_type":"text","precid":0,"content":"马蓉是你吗？看不清脸","like_count":28,"u":{"header":["http://qzapp.qlogo.cn/qzapp/100336987/D9BFD74190F627033D26DE09FBC21C95/100","http://qzapp.qlogo.cn/qzapp/100336987/D9BFD74190F627033D26DE09FBC21C95/100"],"sex":"m","uid":"12273699","is_vip":false,"name":"你的影子 ﾟ"},"preuid":0,"passtime":"2016-11-01 12:11:07","voiceuri":"","id":67638198}]
     * tags : [{"id":28,"name":"动态图"},{"id":61,"name":"恶搞"},{"id":117,"name":"美女"},{"id":18910,"name":"hx"}]
     * bookmark : 64
     * text : 听说现在改这种广场舞了，看来我可以天天来了！！
     * gif : {"images":["http://wimg.spriteapp.cn/ugc/2016/10/31/581762a540468.gif","http://dimg.spriteapp.cn/ugc/2016/10/31/581762a540468.gif"],"width":220,"gif_thumbnail":["http://wimg.spriteapp.cn/ugc/2016/10/31/581762a540468_a_1.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581762a540468_a_1.jpg"],"download_url":["http://wimg.spriteapp.cn/ugc/2016/10/31/581762a540468_d.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581762a540468_d.jpg","http://wimg.spriteapp.cn/ugc/2016/10/31/581762a540468_a_1.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581762a540468_a_1.jpg"],"height":220}
     * up : 316
     * share_url : http://a.f.budejie.com/share/21773999.html?wx.qq.com
     * down : 69
     * forward : 15
     * u : {"header":["http://wimg.spriteapp.cn/profile/large/2016/08/22/57ba617141843_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/08/22/57ba617141843_mini.jpg"],"is_v":false,"uid":"17431116","is_vip":false,"name":"桃老板"}
     * passtime : 2016-11-01 11:58:01
     * type : gif
     * id : 21773999
     */

    private List<ListBean> list;

    public static GifsBean objectFromData(String str) {

        return new Gson().fromJson(str, GifsBean.class);
    }

    public static GifsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), GifsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<GifsBean> arrayGifsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<GifsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<GifsBean> arrayGifsBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<GifsBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * images : ["http://wimg.spriteapp.cn/ugc/2016/10/31/581762a540468.gif","http://dimg.spriteapp.cn/ugc/2016/10/31/581762a540468.gif"]
         * width : 220
         * gif_thumbnail : ["http://wimg.spriteapp.cn/ugc/2016/10/31/581762a540468_a_1.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581762a540468_a_1.jpg"]
         * download_url : ["http://wimg.spriteapp.cn/ugc/2016/10/31/581762a540468_d.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581762a540468_d.jpg","http://wimg.spriteapp.cn/ugc/2016/10/31/581762a540468_a_1.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581762a540468_a_1.jpg"]
         * height : 220
         */

        private GifBean gif;

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public static ListBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ListBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ListBean> arrayListBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ListBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<ListBean> arrayListBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ListBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public GifBean getGif() {
            return gif;
        }

        public void setGif(GifBean gif) {
            this.gif = gif;
        }

        public static class GifBean {
            private int width;
            private int height;
            private List<String> images;
            private List<String> gif_thumbnail;
            private List<String> download_url;

            public static GifBean objectFromData(String str) {

                return new Gson().fromJson(str, GifBean.class);
            }

            public static GifBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), GifBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<GifBean> arrayGifBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<GifBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<GifBean> arrayGifBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<GifBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public List<String> getGif_thumbnail() {
                return gif_thumbnail;
            }

            public void setGif_thumbnail(List<String> gif_thumbnail) {
                this.gif_thumbnail = gif_thumbnail;
            }

            public List<String> getDownload_url() {
                return download_url;
            }

            public void setDownload_url(List<String> download_url) {
                this.download_url = download_url;
            }
        }
    }
}
