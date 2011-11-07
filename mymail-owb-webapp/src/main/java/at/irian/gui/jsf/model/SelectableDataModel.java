package at.irian.gui.jsf.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SelectableDataModel<T> extends PageableDataModel<T> {

    private Set<T> selectedObjects;

    public SelectableDataModel(int pageSize, DataProvider<T> dataProvider) {
        super(pageSize, dataProvider);
        this.selectedObjects = new HashSet<T>();
    }

    public Map<T, Boolean> getRowSelected() {
        return new HashMap<T, Boolean>() {
            @Override
            public Boolean get(Object key) {
                return selectedObjects.contains(key);
            }

            @Override
            public Boolean put(T key, Boolean value) {
                if (value == null || !value) {
                    boolean existed = selectedObjects.contains(key);
                    selectedObjects.remove(key);
                    return existed;
                } else {
                    return selectedObjects.add(key);
                }
            }
        };
    }

    public Set<T> getSelectedObjects() {
        return selectedObjects;
    }

    public void clearSelectedObjects() {
        selectedObjects.clear();
    }

    @Override
    public void reset() {
        super.reset();
        clearSelectedObjects();
    }

}
