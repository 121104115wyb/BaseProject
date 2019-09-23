package com.ytlz.baseproject.moudle.resopnse;

import com.google.gson.annotations.SerializedName;
import com.ytlz.baseproject.moudle.BaseMoudle;

/**
 * Created by wyb on 2019-09-20.
 */

public class UserMoudle extends BaseMoudle {


    /**
     * code : 200
     * message : 测试
     * status : 1
     * data : {"address":"江苏省苏州市高新区竹园路208号","street":"木渎街道","communite":"欣欣社区","streetcode":"370212","code":"37021234567"}
     * name : 张三
     * sex : 男
     * phone : 12323455331
     * head : http://b-ssl.duitang.com/uploads/item/201602/01/20160201175516_vUCt2.jpeg
     * token:手机的MAC地址和时间日期生成的token
     * resfresh_token：刷新token的自定义编码格式
     */

    @SerializedName("data")
    private DataBean data;
    @SerializedName("name")
    private String name;
    @SerializedName("sex")
    private String sex;
    @SerializedName("phone")
    private String phone;
    @SerializedName("head")
    private String head;
    @SerializedName("token")
    private String token;
    @SerializedName("resfresh_token")
    private String resfresh_token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResfresh_token() {
        return resfresh_token;
    }

    public void setResfresh_token(String resfresh_token) {
        this.resfresh_token = resfresh_token;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public static class DataBean {
        /**
         * address : 江苏省苏州市高新区竹园路208号
         * street : 木渎街道
         * communite : 欣欣社区
         * streetcode : 370212
         * code : 37021234567
         */

        @SerializedName("address")
        private String address;
        @SerializedName("street")
        private String street;
        @SerializedName("communite")
        private String communite;
        @SerializedName("streetcode")
        private String streetcode;
        @SerializedName("code")
        private String code;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCommunite() {
            return communite;
        }

        public void setCommunite(String communite) {
            this.communite = communite;
        }

        public String getStreetcode() {
            return streetcode;
        }

        public void setStreetcode(String streetcode) {
            this.streetcode = streetcode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
