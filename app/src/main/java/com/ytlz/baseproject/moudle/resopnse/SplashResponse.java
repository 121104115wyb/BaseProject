package com.ytlz.baseproject.moudle.resopnse;

import com.google.gson.annotations.SerializedName;
import com.ytlz.baseproject.moudle.BaseMoudle;

import java.util.List;

/**
 * Created by wyb on 2019-09-23.
 */

public class SplashResponse extends BaseMoudle {


    /**
     * data : [{"imgurl":"dddddd","tips":"这是什么"},{"imgUrl":"dddddd","tips":"这是什么"},{"imgUrl":"dddddd","tips":"这是什么"}]
     */

    @SerializedName("data")
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tips : "这是一张图片"
         * imgUrl : "http://b.hiphotos.baidu.com/image/pic/item/908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg"
         */

        @SerializedName("tips")
        private String tips;
        @SerializedName("imgUrl")
        private String imgUrl;

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
