package entity;

/**
 * @author Alan Chen
 * @description 收据
 * @date 2020-02-12
 */
public class ReceiptPdfVO {

    //收件人
    private String addressee;

    // 发送者
    private String sender;

    // 联系人
    private String contacts;

    // 联系电话
    private String contactNumber;

    // 联系邮箱
    private String contactEmail;

    // 账单日期
    private String billDate;

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
