package at.irian.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipseLink-2.0.1.v20100213-r6600 @ Tue Mar 15 14:17:33 CET 2011")
@StaticMetamodel(Attachment.class)
public class Attachment_ {

	public static volatile SingularAttribute<Attachment, Long> id;
	public static volatile SingularAttribute<Attachment, Mail> mail;
	public static volatile SingularAttribute<Attachment, String> name;
	public static volatile SingularAttribute<Attachment, String> mimeType;
	public static volatile SingularAttribute<Attachment, Long> size;

}