package at.irian.gui.beans;

import at.irian.domain.MailPriority;
import at.irian.gui.util.GuiUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class SelectItemsProvider {

    private List<SelectItem> priorityItems;

    @PostConstruct
    void init() {
        // Create mail priority items on bean creation
        priorityItems = new ArrayList<SelectItem>();
        FacesContext ctx = FacesContext.getCurrentInstance();
        priorityItems.add(new SelectItem(MailPriority.LOW, GuiUtil.getResourceText(ctx, "msgs", "MailPriority.LOW")));
        priorityItems.add(new SelectItem(MailPriority.MEDIUM, GuiUtil.getResourceText(ctx, "msgs", "MailPriority.MEDIUM")));
        priorityItems.add(new SelectItem(MailPriority.HIGH, GuiUtil.getResourceText(ctx, "msgs", "MailPriority.HIGH")));
    }

    // Generated code

    public List<SelectItem> getPriorityItems() {
        return priorityItems;
    }

}
