package com.example.niteshgarg.egym.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niteshgarg on 13/02/16.
 */
public class Picture {

    private String large;
    private String medium;
    private String thumbnail;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
