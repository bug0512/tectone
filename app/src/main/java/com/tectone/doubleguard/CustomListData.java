package com.tectone.doubleguard;

/**
 * Created by JamesLee on 2014-10-17.
 */
public class CustomListData {
    private int imageId;
    private String mainTitle;

    public CustomListData(int imageId, String mainTitle) {
        this.setImageId(imageId);
        this.setMainTitle(mainTitle);
    }

    public CustomListData(String mainTitle) {

        this.setMainTitle(mainTitle);
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }
}
