package com.development.dto;

public class UserDto {

    private String code;
    private String appid;
    private String secret;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "code='" + code + '\'' +
                ", appid='" + appid + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
