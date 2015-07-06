package com.tectone.doubleguard;

import java.io.Serializable;

/**
 * Created by JamesLee on 2014-10-18.
 */
public class RecommandListData implements Serializable {
    String badgeTitle;
    String badgeContent;
    int badgeImage;
    String flag;


    public RecommandListData(String badgeTitle, String badgeContent, int badgeImage, String flag) {
        this.setBadgeTitle(badgeTitle);
        this.setBadgeContent(badgeContent);
        this.setBadgeImage(badgeImage);
        this.setFlag(flag);
    }

    public String getBadgeTitle() {
        return badgeTitle;
    }

    public void setBadgeTitle(String badgeTitle) {
        this.badgeTitle = badgeTitle;
    }

    public String getBadgeContent() {
        return badgeContent;
    }

    public void setBadgeContent(String badgeContent) {
        this.badgeContent = badgeContent;
    }

    public int getBadgeImage() {
        return badgeImage;
    }

    public void setBadgeImage(int badgeImage) {
        this.badgeImage = badgeImage;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
