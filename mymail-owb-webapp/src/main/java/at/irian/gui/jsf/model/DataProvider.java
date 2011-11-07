package at.irian.gui.jsf.model;

public interface DataProvider<T> {

    PagedData<T> getPagedData(PageContext pageContext);

}
