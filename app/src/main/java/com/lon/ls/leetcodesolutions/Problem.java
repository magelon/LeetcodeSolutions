package com.lon.ls.leetcodesolutions;

public class Problem {

    String title;
    String des;
    String solutioin;
    String img;
    String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSolutioin() {
        return solutioin;
    }

    public void setSolutioin(String solutioin) {
        this.solutioin = solutioin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Problem(String title, String des, String solutioin,String img,String tags) {
        this.title = title;
        this.des = des;
        this.solutioin = solutioin;
        this.img=img;
        this.tags=tags;
    }


}
