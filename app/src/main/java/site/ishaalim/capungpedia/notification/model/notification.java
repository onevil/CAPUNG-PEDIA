package site.ishaalim.capungpedia.notification.model;

import java.util.Date;

public class notification {
    String messageBody;
    String notifUrl;
    Date notifDate;

    public notification() {
    }

    public notification(String messageBody, String notifUrl, Date notifDate) {
        this.messageBody = messageBody;
        this.notifUrl = notifUrl;
        this.notifDate = notifDate;
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
