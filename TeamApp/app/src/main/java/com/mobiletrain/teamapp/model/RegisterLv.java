package com.mobiletrain.teamapp.model;

/**
 * Created by jay on 2016/11/5.
 */
public class RegisterLv {

    String image;
    String name;
    String sub_number;
    String post_num;

    public RegisterLv(String image, String name, String sub_number, String post_num) {
        this.image = image;
        this.name = name;
        this.sub_number = sub_number;
        this.post_num = post_num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub_number() {
        return sub_number;
    }

    public void setSub_number(String sub_number) {
        this.sub_number = sub_number;
    }

    public String getPost_num() {
        return post_num;
    }

    public void setPost_num(String post_num) {
        this.post_num = post_num;
    }

    @Override
    public String toString() {
        return "RegisterLv{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", sub_number='" + sub_number + '\'' +
                ", post_num='" + post_num + '\'' +
                '}';
    }
}
