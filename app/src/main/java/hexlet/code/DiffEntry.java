package hexlet.code;

class DiffEntry {
    private DiffType type;
    private Object oldValue;
    private Object newValue;

    public DiffEntry(DiffType type, Object oldValue, Object newValue) {
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public DiffType getType() {
        return type;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}

enum DiffType {
    ADDED,
    REMOVED,
    CHANGED,
    UNCHANGED
}