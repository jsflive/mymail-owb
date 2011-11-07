package at.irian.gui.beans;

import at.irian.dao.MailSearchParameters;
import at.irian.domain.Mail;
import at.irian.gui.jsf.model.SelectableDataModel;
import at.irian.service.MailService;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewAccessScoped
public class MailBean implements Serializable {

    @Inject
    private MailService mailService;
    private MailSearchParameters searchParameters;
    private Mail selectedMail;
    private SelectableDataModel<Mail> dataTableModel;
    private int currentPage;

    @PostConstruct
    void init() {
        // Initialize search parameters
        searchParameters = new MailSearchParameters();

        // Initialize data table model
        dataTableModel = new SelectableDataModel<Mail>(10, new MailDataProvider(mailService, searchParameters));
    }

    public String search() {
        dataTableModel.reset();
        return "mailList?faces-redirect=true";
    }

    public String fetchMails() {
        mailService.fetchNewMail();
        dataTableModel.reset();
        return null;
    }

    public String delete(Mail mail) {
        mailService.delete(mail);
        dataTableModel.reset();
        return null;
    }

    public String show(Mail mail) {
        selectedMail = mailService.findById(mail.getId());
        selectedMail.setRead(true);
        mailService.save(selectedMail);
        return "showMail";
    }

    public String switchMailRead(Mail mail) {
        mail.setRead(!mail.isRead());
        mailService.save(mail);
        dataTableModel.reset();
        return null;
    }

    public String save() {
        mailService.save(selectedMail);
        selectedMail = null;
        dataTableModel.reset();
        return "mailList";
    }

    public String cancel() {
        mailService.save(selectedMail);
        selectedMail = null;
        dataTableModel.reset();
        return "mailList";
    }

    public String deleteSelectedMails() {
        for (Mail mail : dataTableModel.getSelectedObjects()) {
            mailService.delete(mail);
        }
        dataTableModel.reset();
        return null;
    }

    public SelectableDataModel getMails() {
        return dataTableModel;
    }

    // Generated code

    public Mail getSelectedMail() {
        return selectedMail;
    }

    public MailSearchParameters getSearchParameters() {
        return searchParameters;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
