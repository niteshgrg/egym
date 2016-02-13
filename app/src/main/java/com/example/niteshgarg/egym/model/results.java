package com.example.niteshgarg.egym.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niteshgarg on 13/02/16.
 */

public class results {

    private User user;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
