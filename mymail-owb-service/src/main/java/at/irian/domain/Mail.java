package at.irian.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MAIL")
@SequenceGenerator(name = "MAIL_SEQUENCE_GENERATOR", sequenceName = "MAIL_SEQUENCE")
public class Mail implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAIL_SEQUENCE_GENERATOR")
    @Column(name = "ID")
    private Long id;

    @Column(name = "MAIL_FROM")
    private String from;

    @Column(name = "MAIL_TO")
    private String to;

    @Column(name = "SUBJECT")
    private String subject;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SENT_DATE")
    private Date date;

    @Column(name = "BODY")
    @Lob
    private String body;

    @Column(name = "PRIORITY")
    @Enumerated(EnumType.STRING)
    private MailPriority priority;

    @Column(name = "MAIL_READ")
    private boolean read;

    @OneToMany(mappedBy = "mail", cascade = CascadeType.ALL)
    private List<Attachment> attachments = new ArrayList<Attachment>();

    @Transient
    public boolean isTransient() {
        return getId() == null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BaseEntity))
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (this.getId() == null || other.getId() == null) {
            return false;
        }
        return this.getId() != null && this.getId().equals(other.getId());
    }

    public int hashCode() {
        return this.getId() != null ? this.getId().hashCode() : super.hashCode();
    }

    // Generated code

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MailPriority getPriority() {
        return priority;
    }

    public void setPriority(MailPriority priority) {
        this.priority = priority;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }


}
