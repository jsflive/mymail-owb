package at.irian.dao;

import at.irian.domain.Mail;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class MailRepository {

    @Inject
    private EntityManager em;

    public MailQuery getMailQuery() {
        return new MailQuery(em);
    }

    public void save(Mail mail) {
        if (mail.isTransient()) {
            em.persist(mail);
        } else {
            em.merge(mail);
        }
    }

    public void delete(Mail mail) {
        mail = em.merge(mail);
        em.remove(mail);
    }

    public Mail findById(long id) {
        return em.find(Mail.class, id);
    }

}
