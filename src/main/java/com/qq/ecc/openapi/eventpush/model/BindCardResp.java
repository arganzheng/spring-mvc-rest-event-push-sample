package com.qq.ecc.openapi.eventpush.model;

import java.util.Map;

public class BindCardResp {

    private String    openId;       // 用户的唯一标识
    private String    mobile;       // 手机号
    private String    cardId;       // 必填。卡的唯一id，可以和卡号一样
    private String    cardNo;       // 卡号

    private String    expiryDate;   // 2013-10-31 20:18:20
    private int       cardLevelId;  // 必填。卡等级id(数值)
    private int       points;       // 必填。积分
    private String    name;         // 　持卡人姓名
    private String    birthday;     // "2000-12-12", // 持卡人生日，格式yyyy-MM-dd
    private Character sex;          // 持卡人性别，M（男）或F（女）
    private Map       cardExtension; // 扩展信息，key、value形式

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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCardLevelId() {
        return cardLevelId;
    }

    public void setCardLevelId(int cardLevelId) {
        this.cardLevelId = cardLevelId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public Map getCardExtension() {
        return cardExtension;
    }

    public void setCardExtension(Map cardExtension) {
        this.cardExtension = cardExtension;
    }

}
