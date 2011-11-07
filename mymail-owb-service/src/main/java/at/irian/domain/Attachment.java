package at.irian.domain;

import javax.persistence.*;

@Entity
@Table(name = "ATTACHMENT")
@SequenceGenerator(name = "ATTACHMENT_SEQUENCE_GENERATOR", sequenceName = "ATTACHMENT_SEQUENCE")
public class Attachment implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTACHMENT_SEQUENCE_GENERATOR")
    @Column(name = "ID")
    private Long id;

    @Column(name = "ATTACHMENT_NAME")
    private String name;

    @Column(name = "MIME_TYPE")
    private String mimeType;

    @Column(name = "SIZE")
    private long size;

    @ManyToOne
    @JoinColumn(name = "MAIL_ID", referencedColumnName = "ID")
    private Mail mail;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
