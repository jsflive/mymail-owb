package at.irian.service;

import at.irian.domain.Attachment;
import at.irian.domain.Mail;
import at.irian.domain.MailPriority;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class MailBuilder {

    private static AtomicInteger number = new AtomicInteger(1000);

    static Mail buildMail() {
        int currentNumber = number.getAndAdd(1);
        Mail mail = new Mail();
        mail.setDate(new Date());
        mail.setFrom("sender-" + currentNumber);
        mail.setTo("recipient-" + currentNumber);
        mail.setSubject("subject-" + currentNumber);
        mail.setBody("This is just a little body text.");
        mail.setRead(false);
        mail.setPriority(MailPriority.MEDIUM);
        addAttachments(mail, (new Random()).nextInt(5));
        return mail;
    }

    private static void addAttachments(Mail mail, int numberOfAttachments) {
        for (int i = 0; i < numberOfAttachments; i++) {
            Attachment attachment = new Attachment();
            attachment.setName("Attachment-" + i);
            attachment.setMimeType(getMimeType());
            attachment.setMail(mail);
            attachment.setSize((new Random()).nextInt(5000));
            mail.getAttachments().add(attachment);
        }
    }

    private static String getMimeType() {
        switch ((new Random()).nextInt(3)) {
            case 0: return "application/pdf";
            case 1: return "image/jpeg";
            case 2: return "image/png";
            default: return "text/html";
        }
    }

}
