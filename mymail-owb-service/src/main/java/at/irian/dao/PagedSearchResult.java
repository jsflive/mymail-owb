package at.irian.dao;

import java.io.Serializable;
import java.util.List;

public class PagedSearchResult<T> implements Serializable {

    private List<T> resultList;
    private int resultSize;

    // Generated code

    public PagedSearchResult(List<T> resultList, int resultSize) {
        this.resultList = resultList;
        this.resultSize = resultSize;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public int getResultSize() {
        return resultSize;
    }

}
