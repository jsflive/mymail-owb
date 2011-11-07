package at.irian.gui.jsf.component;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIData;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ComponentSystemEventListener;
import javax.faces.event.PreRenderComponentEvent;
import java.util.ArrayList;
import java.util.List;

@FacesComponent("at.irian.PaginatorComponent")
public class Paginator extends UINamingContainer {
    public enum PropertyKeys {numberOfPages, pageSize, currentPage, forTableClientId, listenerAdded}

    private UIData dataTable;

    public void postAddToView() {
        boolean listenerAdded = (Boolean)getStateHelper().eval(PropertyKeys.listenerAdded, Boolean.FALSE);
        if (!listenerAdded) {
            getDataTable().subscribeToEvent(PreRenderComponentEvent.class, new FirstIndexListener());
            getStateHelper().put(PropertyKeys.listenerAdded, Boolean.TRUE);
        }
    }

    public void preRenderComponent(ComponentSystemEvent event) {
        int rowCount = getDataTable().getRowCount();
        int pageSize = getDataTable().getRows();
        int first = getDataTable().getFirst();
        setPageSize(pageSize);
        setNumberOfPages(pageSize == 0 ? 1 : (int) Math.ceil((double) rowCount / (double) pageSize));
        setCurrentPage(pageSize == 0 ? 1 : first / pageSize);
        getDataTable().setFirst(getCurrentPage() * getPageSize());
    }

    public String updateCurrentPage(int currentPage) {
        setCurrentPage(currentPage);
        getDataTable().setFirst(currentPage * getPageSize());
        return null;
    }

    private UIData getDataTable() {
        if (dataTable == null) {
            String forTableId = (String)getAttributes().get("forTable");
            dataTable = (UIData)getParent().findComponent(forTableId);
        }
        return dataTable;
    }

    public String getForTableClientId() {
        String forTableClientId = (String)getStateHelper().get(PropertyKeys.forTableClientId);
        if (forTableClientId == null) {
            forTableClientId = ":" + getDataTable().getClientId();
            setForTableClientId(forTableClientId);
        }
        return forTableClientId;
    }

    private void setForTableClientId(String forTableClientId) {
        getStateHelper().put(PropertyKeys.forTableClientId, forTableClientId);
    }

    public List<Integer> getPageIndexes() {
        int numberOfPages = getNumberOfPages();
        List<Integer> pageIndexes = new ArrayList<Integer>(numberOfPages);
        for (int i = 0; i < numberOfPages; i++) {
            pageIndexes.add(i);
        }
        return pageIndexes;
    }

    private int getPageSize() {
        return (Integer)getStateHelper().get(PropertyKeys.pageSize);
    }

    private void setPageSize(int pageSize) {
        getStateHelper().put(PropertyKeys.pageSize, pageSize);
    }

    public int getNumberOfPages() {
        return (Integer)getStateHelper().get(PropertyKeys.numberOfPages);
    }

    private void setNumberOfPages(int numberOfPages) {
        getStateHelper().put(PropertyKeys.numberOfPages, numberOfPages);
    }

    public int getCurrentPage() {
        return (Integer)getStateHelper().eval(PropertyKeys.currentPage, 0);
    }

    private void setCurrentPage(int currentPage) {
        getStateHelper().put(PropertyKeys.currentPage, currentPage);
        setValueExpression("page", currentPage);
    }

    private void setValueExpression(String name, Object value) {
        ELContext ctx = FacesContext.getCurrentInstance().getELContext();
        ValueExpression ve = getValueExpression(name);
        if (ve != null) {
            ve.setValue(ctx, value);
        }
    }

    /**
     * This ComponentSystemEventListener is installed on the linked UIData instance
     * to guarantee, that the first index is not out of bounds. This can happen if
     * a new search result returns less results or if rows are deleted.
     */
    public static class FirstIndexListener implements ComponentSystemEventListener {
        public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
            UIData dataTable = (UIData)event.getComponent();
            int rowCount = dataTable.getRowCount();
            int first = dataTable.getFirst();
            int rows = dataTable.getRows();
            // Check if first index is greater or equal rowCount and therefore out of bounds
            // But not for the case where first and rowCount are 0
            if (first > 0 && first >= rowCount) {
                if (first < rowCount + rows) {
                    // If first index is within first "out of bounds" page reduce it by rows.
                    // This applies for instance if all elements of the last page are deleted.
                    dataTable.setFirst(first - rows);
                } else {
                    // If first index is not within first "out of bounds" page set it to 0.
                    // This applies for instance, if a new search returns less results.
                    dataTable.setFirst(0);
                }
            }
        }
    }

}
