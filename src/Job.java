public enum Job {
    ADMIN("admin.", 1),
    UNKNOWN("unknown", 0),
    UNEMPLOYED("unemployed", 0),
    MANAGEMENT("management", 1),
    HOUSEMAID("housemaid", 1),
    ENTREPRENEUR("entrepreneur", 1),
    STUDENT("student", 0),
    BLUE_COLOR("blue-color", 1),
    SELF_EMPLOYED("self-employed", 1),
    RETIRED("retired", 1),
    TECHNICIAN("technician", 1),
    SERVICES("services", 1);

    private String label;
    private Double value;

    private Job(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
