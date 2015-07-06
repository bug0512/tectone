package com.tectone.doubleguard.model;

/**
 * Created by JamesLee on 2014-10-22.
 */
public class NotiBean {
    String scenario_id;
    String scenario_content;
    String badge_name;

    public NotiBean(String scenario_id, String scenario_content, String badge_name) {
        this.setScenario_id(scenario_id);
        this.setScenario_content(scenario_content);
        this.setBadge_name(badge_name);
    }

    public String getScenario_id() {
        return scenario_id;
    }

    public void setScenario_id(String scenario_id) {
        this.scenario_id = scenario_id;
    }

    public String getScenario_content() {
        return scenario_content;
    }

    public void setScenario_content(String scenario_content) {
        this.scenario_content = scenario_content;
    }


    public String getBadge_name() {
        return badge_name;
    }

    public void setBadge_name(String badge_name) {
        this.badge_name = badge_name;
    }

}
