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

public class ImagesBean {

    /**
     * status : 4
     * comment : 33
     * top_comments : [{"voicetime":0,"status":0,"cmt_type":"text","precid":0,"content":"又一个被棒子猪拱了的女人\u2026不知道生个娃做完整容还像不像他们了\u2026","like_count":11,"u":{"header":["http://wimg.spriteapp.cn/profile","http://dimg.spriteapp.cn/profile"],"sex":"m","uid":"14204315","is_vip":false,"name":"夜深风竹敲秋韵"},"preuid":0,"passtime":"2016-11-01 00:20:24","voiceuri":"","id":67613731}]
     * tags : [{"id":1,"name":"搞笑"},{"id":60,"name":"吐槽"}]
     * bookmark : 1
     * text : 又一中国姑娘被棒子撬走啦！拟要公布恋情？这对太让人意想不到了！
     * image : {"medium":[],"big":["http://wimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b_1.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b_1.jpg"],"download_url":["http://wimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b_d.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b_d.jpg","http://wimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b.jpg"],"height":5998,"width":634,"small":[],"thumbnail_small":["http://wimg.spriteapp.cn/crop/150x150/ugc/2016/10/31/581766fa9623b.jpg","http://dimg.spriteapp.cn/crop/150x150/ugc/2016/10/31/581766fa9623b.jpg"]}
     * up : 75
     * share_url : http://a.f.budejie.com/share/21774437.html?wx.qq.com
     * down : 48
     * forward : 0
     * u : {"header":["http://wimg.spriteapp.cn/profile/large/2016/10/23/580ccc266d834_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/10/23/580ccc266d834_mini.jpg"],"is_v":false,"uid":"6627949","is_vip":false,"name":"污师_大大王"}
     * passtime : 2016-11-01 12:50:02
     * type : image
     * id : 21774437
     */

    private List<ListBean> list;

    public static ImagesBean objectFromData(String str) {

        return new Gson().fromJson(str, ImagesBean.class);
    }

    public static ImagesBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ImagesBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ImagesBean> arrayImageBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ImagesBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ImagesBean> arrayImageBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ImagesBean>>() {
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
         * medium : []
         * big : ["http://wimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b_1.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b_1.jpg"]
         * download_url : ["http://wimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b_d.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b_d.jpg","http://wimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b.jpg","http://dimg.spriteapp.cn/ugc/2016/10/31/581766fa9623b.jpg"]
         * height : 5998
         * width : 634
         * small : []
         * thumbnail_small : ["http://wimg.spriteapp.cn/crop/150x150/ugc/2016/10/31/581766fa9623b.jpg","http://dimg.spriteapp.cn/crop/150x150/ugc/2016/10/31/581766fa9623b.jpg"]
         */

        private ImageBean image;
        private int down;
        private int forward;
        /**
         * header : ["http://wimg.spriteapp.cn/profile/large/2016/10/23/580ccc266d834_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/10/23/580ccc266d834_mini.jpg"]
         * is_v : false
         * uid : 6627949
         * is_vip : false
         * name : 污师_大大王
         */

        private UBean u;
        private String passtime;
        private String type;
        private String id;
        /**
         * voicetime : 0
         * status : 0
         * cmt_type : text
         * precid : 0
         * content : 又一个被棒子猪拱了的女人…不知道生个娃做完整容还像不像他们了…
         * like_count : 11
         * u : {"header":["http://wimg.spriteapp.cn/profile","http://dimg.spriteapp.cn/profile"],"sex":"m","uid":"14204315","is_vip":false,"name":"夜深风竹敲秋韵"}
         * preuid : 0
         * passtime : 2016-11-01 00:20:24
         * voiceuri :
         * id : 67613731
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

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
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

        public static class TopCommentsBean {
            private int voicetime;
            private int status;
            private String cmt_type;
            private int precid;
            private String content;
            private int like_count;
            /**
             * header : ["http://wimg.spriteapp.cn/profile","http://dimg.spriteapp.cn/profile"]
             * sex : m
             * uid : 14204315
             * is_vip : false
             * name : 夜深风竹敲秋韵
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

            public int getVoicetime() {
                return voicetime;
            }

            public void setVoicetime(int voicetime) {
                this.voicetime = voicetime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCmt_type() {
                return cmt_type;
            }

            public void setCmt_type(String cmt_type) {
                this.cmt_type = cmt_type;
            }

            public int getPrecid() {
                return precid;
            }

            public void setPrecid(int precid) {
                this.precid = precid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getLike_count() {
                return like_count;
            }

            public void setLike_count(int like_count) {
                this.like_count = like_count;
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
