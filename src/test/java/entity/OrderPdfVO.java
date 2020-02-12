package entity;

/**
 * @author Alan Chen
 * @description
 * @date 2020-02-12
 */
public class OrderPdfVO {

    // 跟进人
    private String followUpPerson;

    // 订单日期
    private String orderDate;

    // 订单编号
    private String orderNumber;

    // 行程内容
    private String tripContent;

    // 车辆类型
    private String carType;

    // 日期
    private String date;

    // 时间
    private String time;

    // 顾客姓名
    private String guestName;

    // 应收金额
    private String amountReceivable;

    public String getFollowUpPerson() {
        return followUpPerson;
    }

    public void setFollowUpPerson(String followUpPerson) {
        this.followUpPerson = followUpPerson;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTripContent() {
        return tripContent;
    }

    public void setTripContent(String tripContent) {
        this.tripContent = tripContent;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getAmountReceivable() {
        return amountReceivable;
    }

    public void setAmountReceivable(String amountReceivable) {
        this.amountReceivable = amountReceivable;
    }
}
