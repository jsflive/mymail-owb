package at.irian.gui.jsf.model;

import javax.faces.model.DataModel;

public class PageableDataModel<T> extends DataModel<T> {

    private int pageSize;
    private int rowIndex;
    private DataPage<T> page;
    private DataProvider<T> dataProvider;

    public PageableDataModel(int pageSize, DataProvider<T> dataProvider) {
        super();
        this.dataProvider = dataProvider;
        this.pageSize = pageSize;
        this.rowIndex = -1;
        this.page = null;
    }

    @Override
    public void setWrappedData(Object o) {
        throw new UnsupportedOperationException("setWrappedData");
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public int getRowCount() {
        return getPage().getDatasetSize();
    }

    @Override
    public T getRowData() {
        if (rowIndex < 0) {
            throw new IllegalArgumentException("invalid rowIndex");
        }

        if (page == null) {
            page = fetchPage(rowIndex, pageSize);
        }

        if (rowIndex >= page.getDatasetSize()) {
            throw new IllegalArgumentException("invalid rowIndex");
        }

        int startRow = page.getStartRow();
        int endRow = startRow + page.getData().size();

        if (rowIndex < startRow || rowIndex >= endRow) {
            page = fetchPage(rowIndex, pageSize);
            startRow = page.getStartRow();
        }

        return page.getData().get(rowIndex - startRow);
    }

    @Override
    public Object getWrappedData() {
        return page == null ? null : page.getData();
    }

    @Override
    public boolean isRowAvailable() {
        return rowIndex >= 0 && rowIndex < getPage().getDatasetSize();
    }

    public void reset() {
        page = null;
    }

    private DataPage<T> getPage() {
        // Return cached page if available
        if (page != null) {
            return page;
        }
        // Fetch new page if none was cached
        int startRow = rowIndex > -1 ? rowIndex : 0;
        page = fetchPage(startRow, pageSize);
        return page;
    }

    private DataPage<T> fetchPage(int startRow, int pageSize) {
        // If a page with same start row as specified is cached return it
        if (page != null && page.getStartRow() == startRow) {
            return this.page;
        }
        // Fetch a new page
        PagedData<T> pagedData = dataProvider.getPagedData(new PageContext(startRow, startRow + pageSize));
        return new DataPage<T>(pagedData, startRow);
    }

}
