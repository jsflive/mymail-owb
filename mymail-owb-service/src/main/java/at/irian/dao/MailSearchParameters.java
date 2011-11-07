package at.irian.dao;

import at.irian.domain.MailPriority;

import java.util.List;

public class MailSearchParameters {
    private String from;
    private String subject;
    private List<MailPriority> priorities;
    private int fromIndex;
    private int toIndex;

    // Generated code

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<MailPriority> getPriorities() {
        return priorities;
    }

    public void setPriorities(List<MailPriority> priorities) {
        this.priorities = priorities;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }
}
