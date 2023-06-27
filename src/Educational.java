public enum Educational {
    UNKNOWN("inknown", 0),
    SECONDARY("secondary", 1),
    PRIMARY("primary", 1),
    TERTIARY("tertiary", 1);

    private String label;
    private Double value;

    private Educational(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
