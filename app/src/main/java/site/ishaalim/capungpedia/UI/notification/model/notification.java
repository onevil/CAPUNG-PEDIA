package site.ishaalim.capungpedia.UI.notification.model;

import java.util.Date;

public class notification {
    String id;
    String messageBody;
    String notifUrl;
    Date notifDate;

    public notification() {
    }

    public notification(String id, String messageBody, String notifUrl, Date notifDate) {
        this.id = id;
        this.messageBody = messageBody;
        this.notifUrl = notifUrl;
        this.notifDate = notifDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getNotifUrl() {
        return notifUrl;
    }

    public void setNotifUrl(String notifUrl) {
        this.notifUrl = notifUrl;
    }

    public Date getNotifDate() {
        return notifDate;
    }

    public void setNotifDate(Date notifDate) {
        this.notifDate = notifDate;
    }
}
