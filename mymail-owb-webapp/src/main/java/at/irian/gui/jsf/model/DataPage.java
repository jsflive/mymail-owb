package at.irian.gui.jsf.model;

import java.util.List;

public class DataPage<T> {

    private PagedData<T> pagedData;
    private int startRow;

	public DataPage(PagedData<T> pagedData, int startRow) {
        this.pagedData = pagedData;
        this.startRow = startRow;
	}

    public List<T> getData() {
        return pagedData.getData();
    }

	public int getDatasetSize() {
		return pagedData.getSize();
	}

	public int getStartRow() {
		return startRow;
	}
}