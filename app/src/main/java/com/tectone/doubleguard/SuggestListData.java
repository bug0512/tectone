package com.tectone.doubleguard;

/**
 * Created by JamesLee on 2014-10-20.
 */
public class SuggestListData {
    String suggestTitle;

    public SuggestListData(String suggestTitle) {
        this.setSuggestTitle(suggestTitle);
    }

    public String getSuggestTitle() {
        return suggestTitle;
    }

    public void setSuggestTitle(String suggestTitle) {
        this.suggestTitle = suggestTitle;
    }
}
