package at.irian.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated("EclipseLink-2.0.1.v20100213-r6600 @ Tue Mar 15 14:17:33 CET 2011")
@StaticMetamodel(Mail.class)
public class Mail_ {

	public static volatile SingularAttribute<Mail, Long> id;
	public static volatile SingularAttribute<Mail, String> to;
	public static volatile SingularAttribute<Mail, String> body;
	public static volatile SingularAttribute<Mail, String> subject;
    public static volatile SingularAttribute<Mail, MailPriority> priority;
	public static volatile SingularAttribute<Mail, Boolean> read;
	public static volatile SingularAttribute<Mail, String> from;
	public static volatile ListAttribute<Mail, Attachment> attachments;
	public static volatile SingularAttribute<Mail, Date> date;

}