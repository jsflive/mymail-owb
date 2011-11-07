package at.irian.service;

import at.irian.domain.Mail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class InitializationService {

    private static final Log log = LogFactory.getLog(InitializationService.class);

    // Get the entity manager used for initializing database values. Here, we cannot use
    // the produced em as there is no request context available.
    @PersistenceContext(unitName = "mymail")
    private EntityManager em;

    public void init() {
        initProviders();
    }

    private void initProviders() {
        try {
            em.getTransaction().begin();
            for (int i = 0; i < 100; i++) {
                Mail mail = MailBuilder.buildMail();
                em.persist(mail);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            log.error("error initializing database", e);
        }
    }

}
