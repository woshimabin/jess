package com.mobiletrain.teamapp.javabean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐和全部的bean
 * Created by qcf on 2016/10/31.
 */

public class AllContentBean{


    /**
     * status : 4
     * comment : 50
     * top_comments : [{"voicetime":0,"status":0,"cmt_type":"text","precid":0,"content":"尼玛，谁能告诉我这相机多少钱","like_count":25,"u":{"header":["http://wimg.spriteapp.cn/profile/large/2016/06/23/576bafd197975_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/06/23/576bafd197975_mini.jpg"],"sex":"m","uid":"18648133","is_vip":false,"name":"名子很好"},"preuid":0,"passtime":"2016-10-30 22:16:09","voiceuri":"","id":67529190}]
     * tags : [{"id":1,"name":"搞笑"},{"id":156,"name":"牛人"},{"id":7205,"name":"长知识"},{"id":18910,"name":"hx"}]
     * bookmark : 62
     * text : 相机83倍光学变焦加电子变焦拍月亮，效果出奇的酷炫！
     * up : 430
     * share_url : http://a.f.budejie.com/share/21749518.html?wx.qq.com
     * down : 18
     * forward : 72
     * u : {"header":["http://wimg.spriteapp.cn/profile/large/2016/10/03/57f1c71405fa9_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/10/03/57f1c71405fa9_mini.jpg"],"is_v":true,"uid":"6172490","is_vip":true,"name":"十年繁華灬不如南柯一夢丶"}
     * passtime : 2016-10-31 11:06:01
     * video : {"playfcount":1181,"height":480,"width":640,"video":["http://wvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd.mp4","http://bvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd.mp4","http://dvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd.mp4"],"download":["http://wvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpc.mp4","http://bvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpc.mp4","http://dvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpc.mp4"],"duration":100,"playcount":14677,"thumbnail":["http://wimg.spriteapp.cn/picture/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd_70.jpg","http://dimg.spriteapp.cn/picture/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd_70.jpg"],"thumbnail_small":["http://wimg.spriteapp.cn/crop/150x150/picture/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd_70.jpg","http://dimg.spriteapp.cn/crop/150x150/picture/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd_70.jpg"]}
     * type : video
     * id : 21749518
     */

    private List<ListBean> list;

    public static AllContentBean objectFromData(String str) {

        return new Gson().fromJson(str, AllContentBean.class);
    }

    public static AllContentBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), AllContentBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<AllContentBean> arrayAllContentBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<AllContentBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<AllContentBean> arrayAllContentBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<AllContentBean>>() {
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
        private int status;
        private String comment;
        private String bookmark;
        private String text;
        private String up;
        private String share_url;
        private int down;
        private int forward;
        private ImageBean image;
        /**
         * header : ["http://wimg.spriteapp.cn/profile/large/2016/10/03/57f1c71405fa9_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/10/03/57f1c71405fa9_mini.jpg"]
         * is_v : true
         * uid : 6172490
         * is_vip : true
         * name : 十年繁華灬不如南柯一夢丶
         */

        private UBean u;
        private String passtime;
        /**
         * playfcount : 1181
         * height : 480
         * width : 640
         * video : ["http://wvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd.mp4","http://bvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd.mp4","http://dvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd.mp4"]
         * download : ["http://wvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpc.mp4","http://bvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpc.mp4","http://dvideo.spriteapp.cn/video/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpc.mp4"]
         * duration : 100
         * playcount : 14677
         * thumbnail : ["http://wimg.spriteapp.cn/picture/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd_70.jpg","http://dimg.spriteapp.cn/picture/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd_70.jpg"]
         * thumbnail_small : ["http://wimg.spriteapp.cn/crop/150x150/picture/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd_70.jpg","http://dimg.spriteapp.cn/crop/150x150/picture/2016/1030/27776fd0-9ea4-11e6-be6c-90b11c479401_wpd_70.jpg"]
         */

        private VideoBean video;
        private String type;
        private String id;
        /**
         * voicetime : 0
         * status : 0
         * cmt_type : text
         * precid : 0
         * content : 尼玛，谁能告诉我这相机多少钱
         * like_count : 25
         * u : {"header":["http://wimg.spriteapp.cn/profile/large/2016/06/23/576bafd197975_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/06/23/576bafd197975_mini.jpg"],"sex":"m","uid":"18648133","is_vip":false,"name":"名子很好"}
         * preuid : 0
         * passtime : 2016-10-30 22:16:09
         * voiceuri :
         * id : 67529190
         */

        private List<TopCommentsBean> top_comments;
        /**
         * id : 1
         * name : 搞笑
         */

        private List<TagsBean> tags;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getBookmark() {
            return bookmark;
        }

        public void setBookmark(String bookmark) {
            this.bookmark = bookmark;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getDown() {
            return down;
        }

        public void setDown(int down) {
            this.down = down;
        }

        public int getForward() {
            return forward;
        }

        public void setForward(int forward) {
            this.forward = forward;
        }

        public UBean getU() {
            return u;
        }

        public void setU(UBean u) {
            this.u = u;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<TopCommentsBean> getTop_comments() {
            return top_comments;
        }

        public void setTop_comments(List<TopCommentsBean> top_comments) {
            this.top_comments = top_comments;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class UBean {
            private boolean is_v;
            private String uid;
            private boolean is_vip;
            private String name;
            private List<String> header;

            public static UBean objectFromData(String str) {

                return new Gson().fromJson(str, UBean.class);
            }

            public static UBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), UBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<UBean> arrayUBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<UBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<UBean> arrayUBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<UBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public boolean isIs_v() {
                return is_v;
            }

            public void setIs_v(boolean is_v) {
                this.is_v = is_v;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public boolean isIs_vip() {
                return is_vip;
            }

            public void setIs_vip(boolean is_vip) {
                this.is_vip = is_vip;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getHeader() {
                return header;
            }

            public void setHeader(List<String> header) {
                this.header = header;
            }
        }

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public static class ImageBean {
            private int height;
            private int width;
            private List<?> medium;
            private List<String> big;
            private List<String> download_url;
            private List<?> small;
            private List<String> thumbnail_small;

            public static ImageBean objectFromData(String str) {

                return new Gson().fromJson(str, ImageBean.class);
            }

            public static ImageBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ImageBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ImageBean> arrayImageBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ImageBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<ImageBean> arrayImageBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ImageBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<?> getMedium() {
                return medium;
            }

            public void setMedium(List<?> medium) {
                this.medium = medium;
            }

            public List<String> getBig() {
                return big;
            }

            public void setBig(List<String> big) {
                this.big = big;
            }

            public List<String> getDownload_url() {
                return download_url;
            }

            public void setDownload_url(List<String> download_url) {
                this.download_url = download_url;
            }

            public List<?> getSmall() {
                return small;
            }

            public void setSmall(List<?> small) {
                this.small = small;
            }

            public List<String> getThumbnail_small() {
                return thumbnail_small;
            }

            public void setThumbnail_small(List<String> thumbnail_small) {
                this.thumbnail_small = thumbnail_small;
            }
        }

        public static class VideoBean {
            private int playfcount;
            private int height;
            private int width;
            private int duration;
            private int playcount;
            private List<String> video;
            private List<String> download;
            private List<String> thumbnail;
            private List<String> thumbnail_small;

            public static VideoBean objectFromData(String str) {

                return new Gson().fromJson(str, VideoBean.class);
            }

            public static VideoBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), VideoBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<VideoBean> arrayVideoBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<VideoBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<VideoBean> arrayVideoBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<VideoBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public int getPlayfcount() {
                return playfcount;
            }

            public void setPlayfcount(int playfcount) {
                this.playfcount = playfcount;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getPlaycount() {
                return playcount;
            }

            public void setPlaycount(int playcount) {
                this.playcount = playcount;
            }

            public List<String> getVideo() {
                return video;
            }

            public void setVideo(List<String> video) {
                this.video = video;
            }

            public List<String> getDownload() {
                return download;
            }

            public void setDownload(List<String> download) {
                this.download = download;
            }

            public List<String> getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(List<String> thumbnail) {
                this.thumbnail = thumbnail;
            }

            public List<String> getThumbnail_small() {
                return thumbnail_small;
            }

            public void setThumbnail_small(List<String> thumbnail_small) {
                this.thumbnail_small = thumbnail_small;
            }
        }

        public static class TopCommentsBean {
            private String content;
            /**
             * header : ["http://wimg.spriteapp.cn/profile/large/2016/06/23/576bafd197975_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/06/23/576bafd197975_mini.jpg"]
             * sex : m
             * uid : 18648133
             * is_vip : false
             * name : 名子很好
             */

            private UBean u;
            private int preuid;
            private String passtime;
            private String voiceuri;
            private int id;

            public static TopCommentsBean objectFromData(String str) {

                return new Gson().fromJson(str, TopCommentsBean.class);
            }

            public static TopCommentsBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), TopCommentsBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<TopCommentsBean> arrayTopCommentsBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<TopCommentsBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<TopCommentsBean> arrayTopCommentsBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<TopCommentsBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public UBean getU() {
                return u;
            }

            public void setU(UBean u) {
                this.u = u;
            }

            public int getPreuid() {
                return preuid;
            }

            public void setPreuid(int preuid) {
                this.preuid = preuid;
            }

            public String getPasstime() {
                return passtime;
            }

            public void setPasstime(String passtime) {
                this.passtime = passtime;
            }

            public String getVoiceuri() {
                return voiceuri;
            }

            public void setVoiceuri(String voiceuri) {
                this.voiceuri = voiceuri;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class UBean {
                private String sex;
                private String uid;
                private boolean is_vip;
                private String name;
                private List<String> header;

                public static UBean objectFromData(String str) {

                    return new Gson().fromJson(str, UBean.class);
                }

                public static UBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), UBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<UBean> arrayUBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<UBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<UBean> arrayUBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<UBean>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public boolean isIs_vip() {
                    return is_vip;
                }

                public void setIs_vip(boolean is_vip) {
                    this.is_vip = is_vip;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<String> getHeader() {
                    return header;
                }

                public void setHeader(List<String> header) {
                    this.header = header;
                }
            }
        }

        public static class TagsBean {
            private int id;
            private String name;

            public static TagsBean objectFromData(String str) {

                return new Gson().fromJson(str, TagsBean.class);
            }

            public static TagsBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), TagsBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<TagsBean> arrayTagsBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<TagsBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<TagsBean> arrayTagsBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<TagsBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
