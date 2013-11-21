package com.qq.ecc.openapi.eventpush.model;

public class BindCardReq {

    private String openId; // 用户的唯一标识
    private String mobile; // 手机号
    private String cardNo; // 卡号

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

}
