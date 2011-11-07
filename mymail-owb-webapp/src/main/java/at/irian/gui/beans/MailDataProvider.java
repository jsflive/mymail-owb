package at.irian.gui.beans;

import at.irian.dao.MailSearchParameters;
import at.irian.dao.PagedSearchResult;
import at.irian.domain.Mail;
import at.irian.gui.jsf.model.DataProvider;
import at.irian.gui.jsf.model.PageContext;
import at.irian.gui.jsf.model.PagedData;
import at.irian.service.MailService;

public class MailDataProvider implements DataProvider<Mail> {
    private MailService mailService;
    private MailSearchParameters searchParameters;

    public MailDataProvider(MailService mailService, MailSearchParameters searchParameters) {
        this.mailService = mailService;
        this.searchParameters = searchParameters;
    }

    public PagedData<Mail> getPagedData(PageContext pageContext) {
        searchParameters.setFromIndex(pageContext.getFrom());
        searchParameters.setToIndex(pageContext.getTo());
        PagedSearchResult<Mail> searchResult = mailService.findAll(searchParameters);
        return new PagedData<Mail>(searchResult.getResultList(), searchResult.getResultSize());
    }
}
