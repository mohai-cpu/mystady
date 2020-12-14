package common.txbpt;

import java.io.Serializable;

/**
 * @author libb
 * @date 2020/11/18
 */
public class TXBPTEBaoSavePrizeInfoRequestDTO implements Serializable {
    // 活动id 固定值
    private String activityId;
    //活动名称
    private String activityName;
    //用户openid
    private String openId;
    //用户拼团团单号
    private String groupId;
    //用户保险单号
    private String policyNo;
    //奖品信息
    private String prizeName;
    //奖品对应卡号
    private String cardNumber;
    //奖品对应卡密
    private String cardPassword;
    //有效期开始时间
    private String effectiveStartTime;
    //有效期结束时间
    private String effectiveEndTime;
    //使用说明
    private String instructions;
    //中奖时间
    private String winnerTime;
    //手机号码
    private String phoneNumber;
    //奖品属性 0：虚拟奖 1：实物奖
    private String prizeAttribute;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(String effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public String getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(String effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getWinnerTime() {
        return winnerTime;
    }

    public void setWinnerTime(String winnerTime) {
        this.winnerTime = winnerTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrizeAttribute() {
        return prizeAttribute;
    }

    public void setPrizeAttribute(String prizeAttribute) {
        this.prizeAttribute = prizeAttribute;
    }
}
