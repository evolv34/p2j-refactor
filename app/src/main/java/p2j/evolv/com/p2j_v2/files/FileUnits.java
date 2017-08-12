package p2j.evolv.com.p2j_v2.files;

public enum FileUnits {
    B("B"),
    KB("KB"),
    MB("MB"),
    GB("GB"),
    TB("TB");

    private String unit;

    public String getUnit() {
        return unit;
    }

    FileUnits(String unit) {
        this.unit = unit;
    }
}
