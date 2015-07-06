package com.tectone.doubleguard;

import java.io.Serializable;

/**
 * Created by JamesLee on 2014-10-18.
 */
public class BadgeListData implements Serializable {
    String scenario_id;
    String badgeTitle;
    String badgeContent;
    String[] badge_list_id;
    String flag;
    String badge_noti_flag;
    String badge_act_flag;


    public BadgeListData(String scenario_id, String badgeTitle, String badgeContent, String[] badge_list_id, String flag, String badge_act_flag, String badge_noti_flag) {
        this.setScenario_id(scenario_id);
        this.setBadgeTitle(badgeTitle);
        this.setBadgeContent(badgeContent);
        this.setBadge_list_id(badge_list_id);
        this.setFlag(flag);
        this.setBadge_noti_flag(badge_noti_flag);
        this.setBadge_act_flag(badge_act_flag);
    }

    public String getScenario_id() {
        return scenario_id;
    }

    public void setScenario_id(String scenario_id) {
        this.scenario_id = scenario_id;
    }

    public String getBadge_noti_flag() {
        return badge_noti_flag;
    }

    public void setBadge_noti_flag(String badge_noti_flag) {
        this.badge_noti_flag = badge_noti_flag;
    }

    public String getBadge_act_flag() {
        return badge_act_flag;
    }

    public void setBadge_act_flag(String badge_act_flag) {
        this.badge_act_flag = badge_act_flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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


    public String[] getBadge_list_id() {
        return badge_list_id;
    }

    public void setBadge_list_id(String[] badge_list_id) {
        this.badge_list_id = badge_list_id;
    }
}
