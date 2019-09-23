package com.ytlz.baseproject.moudle.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wyb on 2019-09-20.
 */

public class LoginMoudle {
    @SerializedName("userName")
    String userName;
    @SerializedName("passWord")
    String passWord;
    @SerializedName("mac")
    String mac;
    @SerializedName("token")
    String token;

    public static LoginMoudle getInstance() {
        return new LoginMoudle();
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
