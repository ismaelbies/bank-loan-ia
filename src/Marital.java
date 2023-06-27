public enum Marital {
    MARRIED("married", 1),
    DIVORCED("divorced", 1),
    SINGLED("single", 1);

    private String label;
    private Double value;

    private Marital(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
