package at.irian.gui.jsf.model;

public class PageContext {
    private int from;
    private int to;

    public PageContext(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
