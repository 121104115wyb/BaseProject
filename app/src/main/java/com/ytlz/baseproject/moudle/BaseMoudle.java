package com.ytlz.baseproject.moudle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wyb on 2019-09-20.
 */

public class BaseMoudle implements Serializable {


    /**
     * code : 200
     * message : 测试
     * status : 1
     */

    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
