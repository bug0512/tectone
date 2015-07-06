package com.tectone.doubleguard.model;

/**
 * Created by JamesLee on 2014-10-26.
 */
public class Config {
    String activator_value;
    String sensitivity_value;

    public Config(String activator_value, String sensitivity_value) {
        this.setActivator_value(activator_value);
        this.setSensitivity_value(sensitivity_value);
    }

    public String getActivator_value() {
        return activator_value;
    }

    public void setActivator_value(String activator_value) {
        this.activator_value = activator_value;
    }

    public String getSensitivity_value() {
        return sensitivity_value;
    }

    public void setSensitivity_value(String sensitivity_value) {
        this.sensitivity_value = sensitivity_value;
    }
}
