package at.irian.gui.jsf.model;

import java.util.List;

public class PagedData<T> {

    private List<T> data;
    private int size;

    // Generated code

    public PagedData(List<T> data, int size) {
        this.data = data;
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public int getSize() {
        return size;
    }

}
