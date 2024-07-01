package hexlet.code;

public final class DiffEntry {
    public static final String ADDED = "ADDED";
    public static final String REMOVED = "REMOVED";
    public static final String CHANGED = "CHANGED";
    public static final String UNCHANGED = "UNCHANGED";

    private final String type;
    private final Object oldValue;
    private final Object newValue;

    public DiffEntry(String type, Object oldValue, Object newValue) {
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getType() {
        return type;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
