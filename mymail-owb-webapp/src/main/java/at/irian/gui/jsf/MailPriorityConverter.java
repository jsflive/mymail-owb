package at.irian.gui.jsf;

import at.irian.domain.MailPriority;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("at.irian.MailPriorityConverter")
public class MailPriorityConverter implements Converter {

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) throws ConverterException {
        return ((MailPriority)o).name();
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) throws ConverterException {
        return MailPriority.valueOf(s);
    }
}
