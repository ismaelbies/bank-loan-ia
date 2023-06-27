public enum Contact {
    UNKNOWN("unknown", 1),
    TELEPHONE("telephone", 1);


    private String label;
    private Double value;

    private Contact(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
