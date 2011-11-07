package at.irian.gui.beans;

import at.irian.gui.jsf.MailPriorityConverter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named
public class ConverterProvider {

    public MailPriorityConverter getMailPriorityConverter() {
        return new MailPriorityConverter();
    }

}
