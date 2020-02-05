package parser;

import java.util.Objects;

public class Smell implements Comparable<Smell>{
    private String smellName;
    private String smellType;

    public Smell(String smellName, String smellType) {
        this.smellName = smellName;
        this.smellType = smellType;
    }

    public String getSmellName() {
        return smellName;
    }

    public String getSmellType() {
        return smellType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Smell smell = (Smell) o;
        return Objects.equals(getSmellName(), smell.getSmellName()) &&
                Objects.equals(getSmellType(), smell.getSmellType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSmellName(), getSmellType());
    }

    @Override
    public int compareTo(Smell o) {
        return this.smellName.compareTo(o.smellName);
    }
}
