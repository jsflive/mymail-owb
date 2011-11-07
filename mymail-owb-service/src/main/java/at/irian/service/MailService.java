package at.irian.service;

import at.irian.dao.MailQuery;
import at.irian.dao.MailRepository;
import at.irian.dao.MailSearchParameters;
import at.irian.dao.PagedSearchResult;
import at.irian.domain.Mail;
import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailService {
    @Inject
    private MailRepository mailRepository;

    @Transactional
    public void fetchNewMail() {
        for (int i = 0; i < 3; i++) {
            Mail mail = MailBuilder.buildMail();
            mailRepository.save(mail);
        }
    }

    @Transactional
    public void save(Mail mail) {
        mailRepository.save(mail);
    }

    @Transactional
    public void delete(Mail mail) {
        mailRepository.delete(mail);
    }

    @Transactional
    public PagedSearchResult<Mail> findAll(MailSearchParameters parameters) {
        MailQuery mailQuery = mailRepository.getMailQuery()
                .withFromLike(parameters.getFrom())
                .withSubjectLike(parameters.getSubject())
                .withPriorityIn(parameters.getPriorities())
                .setFirstResult(parameters.getFromIndex())
                .setMaxResults(parameters.getToIndex() - parameters.getFromIndex());
        return new PagedSearchResult<Mail>(mailQuery.getResultList(), mailQuery.getResultSize());
    }

    @Transactional
    public Mail findById(long id) {
        return mailRepository.findById(id);
    }

}
