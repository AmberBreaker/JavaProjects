package com.shrm.bean;

import java.util.List;

public class LocationBean {

    private String code;

    private String value;

    private List<LocationBean> childBeans;

    public LocationBean addLocationBean(String code, String value) {
        LocationBean locationBean = new LocationBean();
        locationBean.setCode(code);
        locationBean.setValue(code);
        childBeans.add(locationBean);
        return locationBean;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
